Vue.createApp({
    data() {
        return {
            accountInfo: {},
            errorToats: null,
            errorMsg: null,
            health:0,
            enemyHealth:40,
            player:null,
        }
    },
    methods: {
        getData: function () {
            axios.get(`/server/player/2`)
            .then((response) => {
                    //get player info
                    this.player = response.data;
                    this.health=this.player.monster.health;
                })
                .catch((error) => {
                    // handle error
                    this.errorMsg = "Error getting data";
                    this.errorToats.show();
                })            
        },
        formatDate: function (date) {
            return new Date(date).toLocaleDateString('en-gb');
        },
        sendAttack: function(){
            //enviar ataque
            //resolver ataque
                this.enemyHealth-=10;
            //revisar condicion de victoria/derrota
                if(this.enemyHealth<=0){
                    this.win();
                }
        },
        win: function(){
            this.errorMsg = "Win!!";
                    this.errorToats.show();
        },
        lost: function(){
            this.errorMsg = "Lost!! :(";
            this.errorToats.show();
        }

        /* signOut: function () {
            axios.post('/api/logout')
                .then(response => window.location.href = "/web/index.html")
                .catch(() => {
                    this.errorMsg = "Sign out failed"
                    this.errorToats.show();
                })
        }, */
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.getData();
    }
}).mount('#app')
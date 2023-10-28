package com.mokepon.mokepon.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class AttackPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private long id;

    /*@OneToOne(mappedBy = "attack")
    private Player player;*/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "battle_id")
    private Battle battle;

    private AttackElement element;
    private int multiplier=1;

    public AttackPlayer(AttackElement element, int multiplier) {
        this.element = element;
        this.multiplier = multiplier;
    }

    public AttackPlayer() {
    }

    public long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Battle getBattle() {
        return battle;
    }

    public AttackElement getElement() {
        return element;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setElement(AttackElement element) {
        this.element = element;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

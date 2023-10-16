package com.mokepon.mokepon.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private long id;

    @OneToMany(mappedBy = "battle",fetch=FetchType.EAGER)
    private Set<Player> fighters= new HashSet<>();

    public Battle() {
    }

    public long getId() {
        return id;
    }

    public Set<Player> getFighters() {
        return fighters;
    }

    public void addFighter(Player player){
        player.setBattle(this);
        fighters.add(player);
    }
}

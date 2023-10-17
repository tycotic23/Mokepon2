package com.mokepon.mokepon.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class AttackPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private long id;

    @OneToOne
    private Player player;
    @ManyToOne
    private Battle battle;

    private AttackElement element;
    private int multiplier=1;
}

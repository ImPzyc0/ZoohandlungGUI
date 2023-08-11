package com.daniel.infolk.zoohandlung;

public abstract class Tier {

    private final String name;

    private int alter;
    protected double preis;

    public Tier(String name, int alter){
        this.name = name;
        this.alter = alter;
    }

    public Tier(String name, int alter, double preis){
        this.name = name;
        this.alter = alter;
        this.preis = preis;
    }

    public abstract void macheGeräusche();

}

package com.zoohandlung;

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

    public void seiWütend(){
        System.out.println("ka");
    }

    public String getName() {
        return name;
    }

    public int getAlter(){return alter;}


    public double getPreis() {
        return preis;
    }


}

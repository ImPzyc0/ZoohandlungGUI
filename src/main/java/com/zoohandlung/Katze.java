package com.zoohandlung;

public class Katze extends Tier {

    public String getRasse() {
        return rasse;
    }

    private final String rasse;

    public Katze(String name, int alter, String rasse){
        super(name, alter);
        preis = 0;
        this.rasse = rasse;
    }

    public Katze(String name, int alter, double preis, String rasse){
        super(name, alter, preis);
        this.rasse = rasse;
    }


    @Override
    public void macheGeräusche() {
        System.out.println("miau");
    }

}

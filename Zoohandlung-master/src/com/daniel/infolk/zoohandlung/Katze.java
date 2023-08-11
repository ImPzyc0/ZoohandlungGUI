package com.daniel.infolk.zoohandlung;

public class Katze extends Tier {

    public Katzenrasse getRasse() {
        return rasse;
    }

    private final Katzenrasse rasse;

    public Katze(String name, int alter, Katzenrasse rasse){
        super(name, alter);
        preis = rasse.getPreis();
        this.rasse = rasse;
    }

    public Katze(String name, int alter, double preis, Katzenrasse rasse){
        super(name, alter, preis);
        this.rasse = rasse;
    }

    @Override
    public void macheGeräusche() {
        System.out.println("miau");
    }
}

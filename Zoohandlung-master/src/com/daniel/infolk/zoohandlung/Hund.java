package com.daniel.infolk.zoohandlung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hund extends Tier {

    private final List<Befehle> befehle;

    public Hunderasse getRasse() {
        return rasse;
    }

    private final Hunderasse rasse;

    public Hund(String name, int alter, Hunderasse rasse, Befehle... befehle) {
        super(name, alter);
        preis = rasse.getPreis();
        this.rasse = rasse;

        this.befehle = new ArrayList<>(Arrays.asList(befehle));
    }

    public Hund(String name, int alter, double preis, Hunderasse rasse, Befehle... befehle) {
        super(name, alter, preis);
        this.rasse = rasse;

        this.befehle = new ArrayList<>(Arrays.asList(befehle));
    }

    public void befehl(String befehl) {

        if (befehle.contains(Befehle.valueOf(befehl))) {
            Befehle.valueOf(befehl).fuereBefehlAus();
        }

    }

    @Override
    public void macheGeräusche() {
        System.out.println("Wau");
    }
}

package com.daniel.infolk.zoohandlung;

public enum Katzenrasse {

    ORANGE(200),
    GEFLECKTWEIß(20);

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    private double preis;

    private Katzenrasse(double preis) {

        this.preis = preis;

    }
}

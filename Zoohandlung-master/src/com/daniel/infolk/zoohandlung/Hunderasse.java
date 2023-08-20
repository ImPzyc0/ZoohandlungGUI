package com.daniel.infolk.zoohandlung;

public enum Hunderasse {

    SCHAEFERHUND(200),
    CHIHUAHA(20);

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    private double preis;

    private Hunderasse(double preis) {

        this.preis = preis;

    }
}

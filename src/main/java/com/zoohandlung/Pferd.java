package com.zoohandlung;

public class Pferd extends Tier {

    public String getRasse() {
        return rasse;
    }

    private final String rasse;
    private static final String[] rassen = new String[]{"Schimmel", "Braun"};
    public static String[] getRassen(){return rassen;}


    public Pferd(String name, int alter, String rasse){
        super(name, alter);
        preis = 0;
        this.rasse = rasse;
    }

    public Pferd(String name, int alter, double preis, String rasse){
        super(name, alter, preis);
        this.rasse = rasse;
    }


    @Override
    public void macheGeräusche() {
        System.out.println("hühühühühühühhühühhühü");
    }

    @Override
    public String[] getAktionen() {
        return new String[]{"macheGeräusche"};
    }

    @Override
    public void aktionenAusfuehren(String aktionen) {
        macheGeräusche();
    }

}

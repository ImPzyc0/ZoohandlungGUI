package com.zoohandlung;

public abstract class Tier {

    private final String name;

    private boolean aktionenAusgeführt;

    private static final String[] tiere = new String[]{"Pferd", "Hund", "Katze"};

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

    public static String[] getTiere(){return tiere;}

    public abstract String[] getAktionen();

    public boolean aktionenAusgeführt(){
        return aktionenAusgeführt;
    }
    public void setzeAktionenAusgeführt(boolean aktionenAusgeführt){
        this.aktionenAusgeführt = aktionenAusgeführt;
    }

    public abstract void aktionenAusfuehren(String aktionen);
}

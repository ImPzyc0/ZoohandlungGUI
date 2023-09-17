package com.zoohandlung;

public class Hund extends Tier {

    private final String[] befehle;

    public String getRasse() {
        return rasse;
    }

    private final String rasse;

    private static final String[] rassen = new String[]{"Border Collie", "Schaeferhund", "Mops"};
    public static String[] getRassen(){return rassen;}


    public Hund(String name, int alter, String rasse, String[] befehle){
        super(name, alter);
        preis = 0;
        this.rasse = rasse;

        this.befehle = befehle;
    }

    public Hund(String name, int alter, String rasse, String[] befehle, double preis){
        super(name, alter);
        this.rasse = rasse;

        this.preis = preis;
        this.befehle = befehle;
    }

    public void befehl(String befehl){

        for(String str : befehle){
            if (str.equalsIgnoreCase(befehl)) {

                System.out.println("Befehl: "+befehl);
                return;
            }
        }
        System.out.println("Kein Befehl gefunden");
    }

    @Override
    public void macheGeräusche() {
        System.out.println("Wau");
    }

    @Override
    public String[] getAktionen() {
        String[] str = new String[befehle.length+2];
        for(int i = 0; i< befehle.length; i++){
            str[i]= befehle[i];
        }
        str[befehle.length] = "macheGeräusche";
        str[befehle.length+1] = "seiWütend";
        return str;
    }

    @Override
    public void aktionenAusfuehren(String aktionen) {
        if(aktionen.equals("macheGeräusche")){
            macheGeräusche();
        }else {
            befehl(aktionen);
        }
    }
}

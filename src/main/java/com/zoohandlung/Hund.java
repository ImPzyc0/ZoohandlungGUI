package com.zoohandlung;

import com.zoohandlung.main.Main;
import javafx.scene.media.AudioClip;

import java.util.Objects;

public class Hund extends Tier {

    private final String[] befehle;

    public String getRasse() {
        return rasse;
    }

    private final String rasse;

    private static final String[] rassen = new String[]{"Border Collie", "Schaeferhund", "Mops"};
    public static String[] getRassen(){return rassen;}


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
            }
        }
        AudioClip clip = new AudioClip(Main.getMainInstanz().getClass().getResource("dog_barking-6296.mp3").toExternalForm());
        clip.play();
    }

    @Override
    public void macheGeraeusche() {
        AudioClip clip = new AudioClip(Main.getMainInstanz().getClass().getResource("dog_barking-6296.mp3").toExternalForm());
        clip.play();
    }

    @Override
    public void seiWütend() {
        macheGeraeusche();
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
            macheGeraeusche();
        }else {
            befehl(aktionen);
        }
    }
}

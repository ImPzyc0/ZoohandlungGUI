package com.zoohandlung;

import com.zoohandlung.main.Main;
import javafx.scene.media.AudioClip;

public class Katze extends Tier {

    public String getRasse() {
        return rasse;
    }

    private final String rasse;
    private static final String[] rassen = new String[]{"Orange", "Braun-Gefleckt", "Schwarz-Weiß"};

    public static String[] getRassen(){return rassen;}

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
    public void macheGeraeusche() {
        AudioClip clip = new AudioClip(Main.getMainInstanz().getClass().getResource("sleepy-cat-purring-and-meowing-39248.mp3").toExternalForm());
        clip.play();
    }

    @Override
    public void seiWuetend() {
        macheGeraeusche();
    }

    @Override
    public String[] getAktionen() {
        return new String[]{"macheGeräusche", "seiWütend"};
    }

    @Override
    public void aktionenAusfuehren(String aktionen) {
        if(aktionen.equals("macheGeräusche")){
            macheGeraeusche();
        }else{
            seiWuetend();
        }
    }

}

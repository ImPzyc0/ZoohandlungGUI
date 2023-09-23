package com.zoohandlung.main;

import com.zoohandlung.Katze;
import com.zoohandlung.Zoohandlung;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class ZoohandlungsManager {

    //Die Main-Klasse sollte nur die Methoden haben, um das Programm laufen zu können und die Controller sollten auch nicht die Zoohandlung haben, weshalb es den ZoohandlungsManager gibt
    //der eigentlich nur die Zoohandlung regelt

    private Zoohandlung zoohandlung;

    public static long MINIMALE_OEFFNUNGSZEIT = 5000;

    public boolean kannSchließen() {
        return kannSchließen;
    }

    private boolean kannSchließen = false;

    public void ladZoohandlung(){
        //Vorerst erstellt nur eine Zoohandlung

        zoohandlung = new Zoohandlung("|");

        zoohandlung.neuesTier(new Katze("Nieve 1",201,200, "Orange"));
        zoohandlung.neuesTier(new Katze("Luna 2",202, 201,"Orange"));
        zoohandlung.neuesTier(new Katze("fluffy 3",203,202, "Orange"));
        zoohandlung.neuesTier(new Katze("kylo 4",204, 203,"Orange"));
        zoohandlung.neuesTier(new Katze("tricksie 5",205,204, "Orange"));
        zoohandlung.neuesTier(new Katze("louie 6",206,205, "Orange"));
        zoohandlung.neuesTier(new Katze("mort 7",207,206, "Orange"));
        zoohandlung.neuesTier(new Katze("björn höcke 8",208,207, "Orange"));

        zoohandlung.neuerPfleger("Michael", 0);
        zoohandlung.neuerPfleger("Kakauka", 0);
    }

    public Zoohandlung getZoohandlung(){return zoohandlung;}

    public void starteSchliessenTimer(){
        kannSchließen = false;
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        kannSchließen = true;
                        Main.getMainInstanz().getController().kannGeschlossenWerden();
                    }
                });

            }
        }, MINIMALE_OEFFNUNGSZEIT);

    }
}

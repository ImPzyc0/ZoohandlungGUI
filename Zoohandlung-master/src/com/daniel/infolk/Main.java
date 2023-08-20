package com.daniel.infolk;

import com.daniel.infolk.zoohandlung.Zoohandlung;
import com.daniel.infolk.zoohandlung.Katze;
import com.daniel.infolk.zoohandlung.Katzenrasse;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {

        Timer timer = new Timer();

        Zoohandlung handlung = new Zoohandlung("1");

        handlung.neuerPfleger();

        handlung.neuesTier(new Katze("nunn", 20, 10, Katzenrasse.ORANGE));

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handlung.oeffnen();
                handlung.Update();
                handlung.schließen();
            }
        }, 0, 100);

    }


}

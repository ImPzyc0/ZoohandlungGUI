package com.daniel.infolk.zoohandlung;

import com.daniel.infolk.zoohandlung.Befehl;

public enum Befehle {

    PLATZ(new Befehl() {
        @Override
        public void fuehreBefehlAus() {
            System.out.println("Platz");
        }
    });

    private Befehl befehl;

    Befehle(Befehl befehl){
        this.befehl = befehl;
    }

    public void fuereBefehlAus(){
        befehl.fuehreBefehlAus();
    }
}

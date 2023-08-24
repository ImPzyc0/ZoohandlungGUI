package com.zoohandlung;

import java.util.Arrays;

public class Zoohandlung {

    private Pfleger[] pfleger = new Pfleger[0];
    private Tier[] tiere = new Tier[0];

    private final String ladenId;

    public Zoohandlung(String ladenId){
        this.ladenId = ladenId;
    }

    public void schließen(){
        System.out.println("geschlossen "+ ladenId);
    }

    public void geöffnet(){
        System.out.println("geöffnet "+ ladenId);
    }

    public void neuesTier(Tier tier){
        Tier[] neueTiere = new Tier[tiere.length+1];
        int y = 0;
        for(int i = 0; i<tiere.length; i++){

            neueTiere[i] = tiere[i];
            y = i+1;
        }
        neueTiere[y] = tier;

        tiere = neueTiere;
    }

    public void verkaufTier(Tier tier){
        Tier[] neueTiere = new Tier[tiere.length-1];
        int y = 0;
        for(int i = 0; i<tiere.length; i++){
            if(tiere[i].equals(tier)){
                continue;
            }
            neueTiere[y] = tiere[i];
            y++;
        }

        tiere = neueTiere;
    }

    public void neuerPfleger(String name){
        Pfleger[] neuePfleger = new Pfleger[pfleger.length+1];
        int y = 0;
        for(int i = 0; i<pfleger.length; i++){

            neuePfleger[i] = pfleger[i];
            y = i+1;
        }
        neuePfleger[y] = new Pfleger("Michael");

        pfleger = neuePfleger;
    }

    public Tier getTier(String name){
        for(Tier tier : tiere){
            if(tier.getName().equalsIgnoreCase(name)) return tier;
        }
        return null;
    }

    public Pfleger getPfleger(String name){
        for(Pfleger pfl : pfleger){
            if(pfl.getName().equalsIgnoreCase(name)) return pfl;
        }
        return null;
    }

    public Tier[] getTiere(){
        return tiere.clone();
    }

    public Tier tierImAlterVon(Tier[] tiere, int alter){

        for(Tier tier : tiere){
            if(tier.getAlter() == alter) {return tier;}
        }

        return null;

    }


    //benutzt quicksort, selbst umgesetzt
    //Im Grunde haben wir eine Liste. Wir suchen uns irgendeine Zahl daraus aus. Jetzt schmeißen wir alles was kleiner ist nach links und alles was größer ist nach rechts.
    //Das ganze wiederholen wir in immer kleineren Listen, und irgendwann ist dann die Liste soriert ;)
    //https://www.youtube.com/watch?v=eNUM23f6g-s
    public Tier[] getTiereNachAlter(){

        //Klonen und direkt returnen falls es nicht sortiert werden muss
        Tier[] sortierteTiere = tiere.clone();
        if(tiere.length < 2){return sortierteTiere;}

        //Den 1. Pivot (wo es geteilt wird) bestimmen
        Tier[] pivots = new Tier[sortierteTiere.length];
        pivots[0] = sortierteTiere[sortierteTiere.length/2];
        //Einfach nach dem Pivot sortieren

        for(int i = 1; i<sortierteTiere.length/2; i++){
            //Für alle Pivots die gerade angegeben sind sortieren
            for(int y = 0; y<i; y++){
                sortiereNachPivot(sortierteTiere, pivots[y]);
            }
            //Ale neuen Pivot position herausfinden
            int[] neuePivotPos = new int[i+1];
            for(int z = 0; z<i-1; z++){
                for(int y = 0; y<sortierteTiere.length;y++){
                    if(pivots[z] == sortierteTiere[y]){
                        neuePivotPos[z] = y;
                    }
                }
            }
            neuePivotPos[i] = sortierteTiere.length;
            //Nähst niedrigsten Pivot herausfinden
            int[] neuePivotPosAbstandZumLetzten = new int[neuePivotPos.length];
            for(int y = 0; y<neuePivotPos.length;y++){
                //Letzte Position die gefunden wurde
                int letzter = 0;
                //Mit positionen vergleichen
                for(int z = 0; z<neuePivotPos.length; z++){
                    if(neuePivotPos[y] > neuePivotPos[z] && neuePivotPos[z] > letzter){
                        letzter = z;
                    }
                }
                //Abstand zum letzten Pivot /2 teilen
                neuePivotPosAbstandZumLetzten[y] = (neuePivotPos[y]-letzter)/2;
            }

            //Neue pivot pos setzen
            Tier[] neuePivotPotsTiere = new Tier[neuePivotPosAbstandZumLetzten.length];
            for(int y = 0; y<neuePivotPosAbstandZumLetzten.length; y++){
                neuePivotPotsTiere[y] = sortierteTiere[neuePivotPosAbstandZumLetzten[y]];
            }
             pivots = Arrays.copyOf(neuePivotPotsTiere, pivots.length);
            //Falls es schon sortiert ist zurückgeben
            if(istSortiert(sortierteTiere)){
                return sortierteTiere;
            }
        }

        return sortierteTiere;
    }

    private boolean istSortiert(Tier[] sortierteTiere){
        for(int i = 0; i<sortierteTiere.length-1;i++){
            if(sortierteTiere[i].getAlter() > sortierteTiere[i+1].getAlter()){
                return false;
            }
        }

        return true;
    }

    private Tier[] sortiereNachPivot(Tier[] sortierteTiere, Tier pivot){
        //Bestimmen welche Tiere Älter bzw. Jünger sind
        System.out.println("Pivot: "+pivot.getName());
        Tier[] tiereAlterNiedriger = new Tier[sortierteTiere.length-1];
        Tier[] tiereAlterHoeher = new Tier[sortierteTiere.length];
        int zaehler = 0;
        int zaehler2 = 0;

        for(Tier tier : sortierteTiere){
            if(tier.getAlter() < pivot.getAlter()){
                tiereAlterNiedriger[zaehler] = tier;
                zaehler++;
            }else{
                tiereAlterHoeher[zaehler2] = tier;
                zaehler2++;
            }
        }

        Tier[] tiereAlterNiedrigerGek = Arrays.copyOf(tiereAlterNiedriger, zaehler);
        Tier[] tiereAlterHoeherGek = Arrays.copyOf(tiereAlterHoeher, zaehler2);

        //Die 2 Listen zusammenbringen
        for(int i = 0; i<tiereAlterNiedrigerGek.length;i++){
            sortierteTiere[i] = tiereAlterNiedrigerGek[i];
        }

        for(int i = tiereAlterNiedrigerGek.length; i<sortierteTiere.length; i++){
            sortierteTiere[i] = tiereAlterHoeherGek[i-tiereAlterNiedrigerGek.length];
        }
        return sortierteTiere;
    }
}

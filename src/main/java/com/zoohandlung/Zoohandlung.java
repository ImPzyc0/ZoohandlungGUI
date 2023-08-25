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
    //Das ganze wiederholen wir in immer kleineren Listen, und irgendwann ist dann die Liste sortiert ;) - Erklärung aus dem Internetz
    //https://www.youtube.com/watch?v=eNUM23f6g-s
    public Tier[] getTiereNachAlter() {
        return sortiereNachPivot(tiere.clone(), tiere.length/2);
    }

    private Tier[] sortiereNachPivot(Tier[] list, int pivot){
        //Returnen falls unnötig
        if(list.length <2){return list;}
        //Liste Klonen, Pivot Tier herausfinden
        Tier pivotTier = list[pivot];

        //In Arrays packen welche Niedriger und welche höher als der Pivot sind
        Tier[] niedriegerAlsPivot = new Tier[list.length-1];
        int y = 0;
        Tier[] hoeherAlsPivot = new Tier[list.length];
        int i = 0;
        for(Tier tier : list){
            if(tier == pivotTier) {continue;}
            if(tier.getAlter() < pivotTier.getAlter()){
                niedriegerAlsPivot[y] = tier;
                y++;
            }else{
                hoeherAlsPivot[i] = tier;
                i++;
            }
        }
        //Listen kürzen und zusammentun
        niedriegerAlsPivot = Arrays.copyOf(niedriegerAlsPivot, y);
        hoeherAlsPivot = Arrays.copyOf(hoeherAlsPivot, i);

        list = Arrays.copyOf(niedriegerAlsPivot, list.length);
        list[niedriegerAlsPivot.length] = pivotTier;

        for(int n = 0; n<hoeherAlsPivot.length; n++){
            list[n+niedriegerAlsPivot.length+1] = hoeherAlsPivot[n];
        }
        //Falls sortiert returnen
        if(istSortiertNachAlter(list)){
            return list;
        }
        //Falls nicht, Rekursiv aufrufen und die höhere und niedrigere Liste sortieren
        niedriegerAlsPivot = Arrays.copyOf(niedriegerAlsPivot, niedriegerAlsPivot.length+1);
        niedriegerAlsPivot[niedriegerAlsPivot.length-1] = pivotTier;
        list = Arrays.copyOf(sortiereNachPivot(niedriegerAlsPivot, niedriegerAlsPivot.length/2), list.length);

        hoeherAlsPivot = sortiereNachPivot(hoeherAlsPivot,hoeherAlsPivot.length/2);
        for(int n = 0; n<hoeherAlsPivot.length; n++){
            list[n+niedriegerAlsPivot.length] = hoeherAlsPivot[n];
        }
        //Ende
        return list;
    }

    private boolean istSortiert(int[] list){
        for(int i = 0; i<list.length-1; i++){
            if(list[i] > list[i+1]){
                return false;
            }
        }
        return true;
    }

    private boolean istSortiertNachAlter(Tier[] list) {
        //In int-Array umwandeln und dann überprüfen, kann dann auch später benutzt werden
        if(list.length < 2){return true;}
        int[] list2 = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            list2[i] = list[i].getAlter();
        }
        return istSortiert(list2);
    }
}

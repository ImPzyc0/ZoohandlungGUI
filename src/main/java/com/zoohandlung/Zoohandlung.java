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

    public Tier[] getTiereNachName(String name){
        Tier[] tiereMitName = new Tier[tiere.length];
        Tier[] tiereMitNamenEnthalten = new Tier[tiere.length];

        int i = 0;
        int y = 0;

        for(Tier tier : tiere){
            if(tier.getName().equalsIgnoreCase(name)){
                tiereMitName[i] = tier;
                i++;
            }else if(tier.getName().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(tier.getName().toLowerCase())){
                tiereMitNamenEnthalten[y] = tier;
                y++;
            }
        }

        tiereMitName = Arrays.copyOf(tiereMitName, i+y);
        tiereMitNamenEnthalten = Arrays.copyOf(tiereMitNamenEnthalten, y);

        for(int n = 0; n<tiereMitNamenEnthalten.length; n++){
            tiereMitName[n+i] = tiereMitNamenEnthalten[n];
        }

        return tiereMitName;
    }

    //benutzt quicksort, selbst umgesetzt
    //Im Grunde haben wir eine Liste. Wir suchen uns irgendeine Zahl daraus aus. Jetzt schmeißen wir alles was kleiner ist nach links und alles was größer ist nach rechts.
    //Das ganze wiederholen wir in immer kleineren Listen, und irgendwann ist dann die Liste sortiert ;) - Erklärung aus dem Internetz
    //https://www.youtube.com/watch?v=eNUM23f6g-s
    public Tier[] getTiereNachAlter() {
        return sortiereNach(tiere.clone(), tiere.length/2, 1);
    }
    public Tier[] getTiereNachPreis() {return sortiereNach(tiere.clone(), tiere.length/2, 2);}

    //1 - Alter
    //2 - Preis
    private Tier[] sortiereNach(Tier[] list, int pivot, int sortiereNach){
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
            if((sortiereNach == 1 && tier.getAlter() < pivotTier.getAlter()) || (sortiereNach == 2 && tier.getPreis() < pivotTier.getPreis())){
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
        if(istSortiertNach(list,  sortiereNach)){
            return list;
        }
        //Falls nicht, Rekursiv aufrufen und die höhere und niedrigere Liste sortieren
        niedriegerAlsPivot = Arrays.copyOf(niedriegerAlsPivot, niedriegerAlsPivot.length+1);
        niedriegerAlsPivot[niedriegerAlsPivot.length-1] = pivotTier;
        list = Arrays.copyOf(sortiereNach(niedriegerAlsPivot, niedriegerAlsPivot.length/2, sortiereNach), list.length);

        hoeherAlsPivot = sortiereNach(hoeherAlsPivot,hoeherAlsPivot.length/2, sortiereNach);
        for(int n = 0; n<hoeherAlsPivot.length; n++){
            list[n+niedriegerAlsPivot.length] = hoeherAlsPivot[n];
        }
        //Ende
        return list;
    }

    private boolean istSortiert(double[] list){
        for(int i = 0; i<list.length-1; i++){
            if(list[i] > list[i+1]){
                return false;
            }
        }
        return true;
    }

    private boolean istSortiertNach(Tier[] list, int sortiereNach) {
        //In int-Array umwandeln und dann überprüfen, kann dann auch später benutzt werden
        if(list.length < 2){return true;}

        double[] list2 = new double[list.length];
        for (int i = 0; i < list.length; i++) {
            list2[i] = sortiereNach == 1 ? list[i].getAlter() : list[i].getPreis();
        }
        return istSortiert(list2);
    }

}

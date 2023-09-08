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


    public Pfleger getPfleger(String name){
        for(Pfleger pfl : pfleger){
            if(pfl.getName().equalsIgnoreCase(name)) return pfl;
        }
        return null;
    }

    public Tier[] getTiere(){
        return tiere.clone();
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

    public Tier[] getTiereMitPreis(int preis){
        System.out.println("Preis");
        return null;
    }
    public Tier[] getTiereMitAlter(int alter){
        System.out.println("Alter");
        return null;
    }
    public Tier[] getTiereMitAlterHöher(int alter){
        System.out.println("> alter");
        return null;
    }

    public Tier[] getTiereMitAlterNiedriger(int alter){
        System.out.println("< alter");
        return null;
    }
    public Tier[] getTiereMitPreisHöher(int preis){
        System.out.println("> preis");
        return null;
    }
    public Tier[] getTiereMitPreisNiedriger(int preis){
        System.out.println("< preis");
        return null;
    }




    //benutzt quicksort, selbst umgesetzt
    //Im Grunde haben wir eine Liste. Wir suchen uns irgendeine Zahl daraus aus. Jetzt schmeißen wir alles was kleiner ist nach links und alles was größer ist nach rechts.
    //Das ganze wiederholen wir in immer kleineren Listen, und irgendwann ist dann die Liste sortiert ;) - Erklärung aus dem Internetz
    //https://www.youtube.com/watch?v=eNUM23f6g-s
    public Tier[] getTiereNachAlter() {
        return sortiereNach(tiere.clone(), 1);
    }
    public Tier[] getTiereNachPreis() {return sortiereNach(tiere.clone(), 2);}

    //1 - Alter
    //2 - Preis
    private Tier[] sortiereNach(Tier[] list, int sortiereNach){
        int i = 0;
        while(i != list.length-1){
            if(i < list.length-1 && ((sortiereNach == 1 && list[i].getAlter() > list[i+1].getAlter()) || (sortiereNach == 2 && list[i].getPreis() > list[i+1].getPreis()))){
                Tier speicher = list[i];
                list[i] = list[i+1];
                list[i+1] = speicher;
                if(i != 0){
                    i--;
                }
            }else{
                i++;
            }
        }
        return list;
    }

}

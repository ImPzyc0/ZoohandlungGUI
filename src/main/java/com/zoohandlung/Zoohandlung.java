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
        Tier[] tiereNachPreis = getTiereNachPreis();
        int unteresEnde = -1;
        for(int i = 0; i < tiereNachPreis.length;i++){
            if(unteresEnde == -1 && preis == (int) tiereNachPreis[i].getPreis()){
                unteresEnde = i;
            }else if(unteresEnde != -1 && preis != (int) tiereNachPreis[i].getPreis()) {
                return Arrays.copyOfRange(tiereNachPreis, unteresEnde, i);

            }
        }

        return unteresEnde == -1 ? new Tier[0] : Arrays.copyOfRange(tiereNachPreis, unteresEnde, tiereNachPreis.length);
    }
    public Tier[] getTiereMitAlter(int alter){
        Tier[] tiereNachAlter = getTiereNachAlter();
        int unteresEnde = -1;
        for(int i = 0; i < tiereNachAlter.length;i++){
            if(unteresEnde == -1 && alter == tiereNachAlter[i].getAlter()){
                unteresEnde = i;
            }else if(unteresEnde != -1 && alter != tiereNachAlter[i].getAlter()) {
                return Arrays.copyOfRange(tiereNachAlter, unteresEnde, i);

            }
        }

        return unteresEnde == -1 ? new Tier[0] : Arrays.copyOfRange(tiereNachAlter, unteresEnde, tiereNachAlter.length);
    }
    public Tier[] getTiereMitAlterHoeher(int alter){
        Tier[] tiereNachAlter = getTiereNachAlter();
        for(int i = 0; i < tiereNachAlter.length;i++){
            if(alter >= tiereNachAlter[i].getAlter()){
                return Arrays.copyOfRange(tiereNachAlter, i, tiereNachAlter.length);
            }
        }

        return new Tier[0];
    }

    public Tier[] getTiereMitAlterNiedriger(int alter){
        Tier[] tiereNachAlter = getTiereNachAlter();
        for(int i = tiereNachAlter.length-1; i > -1;i--){
            if(alter <= tiereNachAlter[i].getAlter()){
                return Arrays.copyOfRange(tiereNachAlter, 0, i+1);
            }
        }

        return new Tier[0];
    }
    public Tier[] getTiereMitPreisHoeher(int preis){
        Tier[] tiereNachPreis = getTiereNachPreis();
        for(int i = 0; i < tiereNachPreis.length;i++){
            if(preis >= (int) tiereNachPreis[i].getPreis()){
                return Arrays.copyOfRange(tiereNachPreis, i, tiereNachPreis.length);
            }
        }

        return new Tier[0];
    }
    public Tier[] getTiereMitPreisNiedriger(int preis){
        Tier[] tiereNachPreis = getTiereNachPreis();
        for(int i = tiereNachPreis.length-1; i > -1;i--){
            if(preis <= (int) tiereNachPreis[i].getPreis()){
                return Arrays.copyOfRange(tiereNachPreis, 0, i+1);
            }
        }

        return new Tier[0];
    }

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

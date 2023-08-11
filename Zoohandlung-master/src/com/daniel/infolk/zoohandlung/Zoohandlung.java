package com.daniel.infolk.zoohandlung;


import java.util.ArrayList;
import java.util.List;

public class Zoohandlung {

    private final List<Pfleger> pfleger;
    private final ArrayList<Tier> tiere;

    private final String ladenId;

    private int geld = 0;

    public void Update(){

    for (Pfleger pfl : pfleger) { 
      int zaehler = 0;
      pfl.versorgTier(tiere.get(zaehler));
      zaehler++;  
    }                                                                                           

    }

    public Zoohandlung(String ladenId){
        this.ladenId = ladenId;

        this.pfleger = new ArrayList<>();
        this.tiere = new ArrayList<>();
    }

    public void schließen(){
        System.out.println("geschlossen "+ ladenId);
    }

    public void oeffnen(){
        System.out.println("geöffnet "+ ladenId);
    }

    public void neuesTier(Tier tier){
        tiere.add(tier);
    }

    public void verkaufTier(Tier tier){
        tiere.remove(tier);
    }
  
    public void neuerPfleger(){
        pfleger.add(new Pfleger()); 
    }

}

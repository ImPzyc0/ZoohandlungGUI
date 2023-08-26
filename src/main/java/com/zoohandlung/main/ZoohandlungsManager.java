package com.zoohandlung.main;

import com.zoohandlung.Katze;
import com.zoohandlung.Tier;
import com.zoohandlung.Zoohandlung;

public class ZoohandlungsManager {

    //Json-files laden und speichern

    private Zoohandlung zoohandlung;

    public void ladDateien(){
        //Vorerst erstellt nur eine Zoohandlung

        zoohandlung = new Zoohandlung("lll");

        zoohandlung.neuesTier(new Katze("Mimi 1",20,20.32, "Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 2",20, 20,"Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 3",20,20, "Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 4",20, 20,"Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 5",20,20, "Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 6",20,20, "Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 7",20,20, "Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 8",20,20, "Orange"));

        zoohandlung.neuerPfleger("Michael");

        //System.out.println(zoohandlung.tierImAlterVon(zoohandlung.getTiere(), 19) == null ? "Null":zoohandlung.tierImAlterVon(zoohandlung.getTiere(), 20).getName());
        //Tier[] tiereNachAlter = zoohandlung.getTiereNachAlter();
        //for(int i = 0; i < tiereNachAlter.length; i++){
        //    System.out.println("Tier "+i+": "+tiereNachAlter[i].getName());
        //}
    }

    public Zoohandlung getZoohandlung(){return zoohandlung;}

    public void speicherDateien(){

    }

}

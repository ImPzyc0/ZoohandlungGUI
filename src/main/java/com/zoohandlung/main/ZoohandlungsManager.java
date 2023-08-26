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

        zoohandlung.neuesTier(new Katze("Mimi 1",20,20, "Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 2",202, 201,"Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 3",203,202, "Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 4",204, 203,"Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 5",205,204, "Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 6",206,205, "Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 7",207,206, "Orange"));
        zoohandlung.neuesTier(new Katze("Mimi 8",208,207, "Orange"));

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

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

        zoohandlung.neuesTier(new Katze("Nieve 1",201,200, "Orange"));
        zoohandlung.neuesTier(new Katze("Luna 2",202, 201,"Orange"));
        zoohandlung.neuesTier(new Katze("fluffy 3",203,202, "Orange"));
        zoohandlung.neuesTier(new Katze("kylo 4",204, 203,"Orange"));
        zoohandlung.neuesTier(new Katze("tricksie 5",205,204, "Orange"));
        zoohandlung.neuesTier(new Katze("louie 6",206,205, "Orange"));
        zoohandlung.neuesTier(new Katze("mort 7",207,206, "Orange"));
        zoohandlung.neuesTier(new Katze("björn höcke 8",208,207, "Orange"));

        zoohandlung.neuerPfleger("Michael");
    }
    //Für jede mögliche Tierart einen Check, ob das möglich wäre, bevor man es hinzufügt, da es im Controller ziemlich hässlich wäre
    //Returned null, wenn alle parameter ok waren

    public Zoohandlung getZoohandlung(){return zoohandlung;}
}

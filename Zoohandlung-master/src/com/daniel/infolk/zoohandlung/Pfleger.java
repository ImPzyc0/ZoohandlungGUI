package com.daniel.infolk.zoohandlung;

public class Pfleger {

    private Tier amVersorgen;

    public void versorgTier(Tier tier){
        amVersorgen = tier;
        tier.macheGeräusche();
        System.out.println("Ouiii");
    }

}
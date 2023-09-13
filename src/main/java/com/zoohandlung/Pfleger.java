package com.zoohandlung;

public class Pfleger {

    private final String name;
    private Tier amVersorgen;

    public Pfleger(String name){
        this.name = name;
    }

    public void versorgTier(Tier tier){
        amVersorgen = tier;
        System.out.println(name+" versorgt: "+tier.getName());
    }

    public String getName() {
        return name;
    }

}

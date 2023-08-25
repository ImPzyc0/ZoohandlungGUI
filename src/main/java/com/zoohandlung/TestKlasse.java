package com.zoohandlung;

public class TestKlasse {



    // System.out.println(array);
    // for(int i = 0; i<matrix.length; i++){
    //     for(int j = 0; j<matrix[i].length; j++){
    //         System.out.println(matrix[i][j]);
    //     }
    // //}

    public static boolean zahlVorhanden(int zahl, int[] x){
        for(int i = 0; i<x.length; i++){
            if(x[i] == zahl) {return true;}
        }

        return false;
    }

    public static int wieOftZahlVorhanden(int zahl, int[] x){
        int y = 0;
        for(int i = 0; i<x.length; i++){
            if(x[i] == zahl) {y++;}
        }

        return y;
    }

    public static int produkt(int[] x){
        int y = 1;
        for(int i = 0; i<x.length; i++){
            y = y*x[i];
        }

        return y;
    }

    public static int minIndex(int[] x){
        int y = Integer.MAX_VALUE;
        for(int i = 0; i<x.length;i++){
            if(i<y) {y = i;}
        }

        return y;
    }

    public static int summe(int[][] x){
        int y = 0;

        for(int i = 0; i<x.length; i++){
            for(int j = 0; j<x[i].length; j++){
                y += x[i][j];
            }
        }

        return y;
    }

    public static int minZeileIndex(int[][] x){
        int y = Integer.MAX_VALUE;
        int z = -1;
        for(int i = 0; i<x.length; i++){
            for(int j = 0; j<x[i].length; j++){
                if(x[i][j] < y){y = x[i][j]; z = i;}
            }
        }
        return z;
    }
}

package com.zoohandlung;

import java.util.Arrays;

public class TestKlasse {


    public static void main(String[] args){

        int[] a = new int[]{33,40,87,56,15,7,12,34,17,99,10,52};

        mergesort(a, 0, a.length-1);

        System.out.println("result: " +Arrays.toString(a));
    }

    public static void mergesort(int[] a, int low, int high){
        if(high <= low) return;

        mergesort(a, low, (low+high)/2);
        mergesort(a, (low+high)/2+1, high);

        int[] z = combineArrays(Arrays.copyOfRange(a, low, (low+high)/2+1), Arrays.copyOfRange(a, (low+high)/2+1, high+1));

        for(int n = 0; n<z.length; n++){
            a[n+low] = z[n];
        }
    }

    public static int[] combineArrays(int[] a, int[] b){
        int[] z = new int[a.length+ b.length];
        int na = 0;
        int nb = 0;

        for(int x = 0; x < z.length; x++){
            if(na == a.length){
                z[x] = b[nb];
                nb++;
            }else if(nb == b.length){
                z[x] = a[na];
                na++;
            }else{
                if(b[nb] < a[na]){
                    z[x] = b[nb];
                    nb++;
                }else {
                    z[x] = a[na];
                    na++;
                }
            }
        }
        return z;
    }

}
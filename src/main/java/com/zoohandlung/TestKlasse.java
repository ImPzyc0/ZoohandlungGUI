package com.zoohandlung;

import java.util.Arrays;

public class TestKlasse {


    public static void main(String[] args){

        int[] a = new int[]{1,2,3,4,5,6};

         mergesort(a, 0, 5);

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

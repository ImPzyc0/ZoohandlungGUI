package com.zoohandlung.main;

import com.zoohandlung.Katze;
import com.zoohandlung.Tier;
import com.zoohandlung.Zoohandlung;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;

public class Main extends Application {

    //Variablen fürs lesen der JSON-Dateien:


   //Main instanz
   private static Main main;

    public EventController getController() {
        return controller;
    }

    //Instanz vom Controller
    private EventController controller;

    //Instanz vom Manager
    private ZoohandlungsManager manager;

   public static Main getMainInstanz(){
       return main;
   }

   public Main(){
       Main.main = this;

       this.manager = new ZoohandlungsManager();
       manager.ladDateien();
   }

   public void setControllerInstanz(EventController controller){
       this.controller = controller;
   }

    public ZoohandlungsManager getManager() {
        return manager;
    }

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(Arrays.toString(Quicksort(new int[]{1,2,4,3})));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Zoohandlung");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static int[] UnserSort(int[] list){
        int i = 0;
        while(i != list.length-1){
            if(i < list.length-1 && list[i] > list[i+1]){
                int speicher = list[i];
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

    public static int[] Quicksort(int[] list){
        int pivot = list[list.length-1];
        int pivotIndex = list.length-1;
        for(int i = 0; i< list.length/2; i++) {
            int y = list.length-2-i;
            if(y<i){break;}
            do{
                System.out.println(list[i]+ " : "+list[y]+" : "+pivot);
                if (list[i] > pivot && list[y] < pivot) {
                    Wechseln(list, i, y);
                    pivotIndex = i;
                    break;
                }
                y--;
            }while(i<=y);
        }
        Wechseln(list, pivotIndex, list.length-1);
        return list;
    }

    public static void Wechseln(int[] a, int b, int c){
        System.out.println("l");
       int temp = a[b];
       a[b] = a[c];
       a[c] = temp;
    }

}
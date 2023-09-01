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

    public static int binaereSuche(int Zahl, int[] a){
        if(a.length == 0){return -1;}
        int mitte = a[a.length/2];
        if(Zahl == mitte){
            return a.length/2;
        }
        if(mitte < Zahl){
           int ergebnis = binaereSuche(Zahl, Arrays.copyOfRange(a, a.length/2, a.length-1));
           return ergebnis == -1 ? -1 : ergebnis+ a.length /2;
       }else{
           return binaereSuche(Zahl, Arrays.copyOfRange(a, 0, a.length/2));
       }
    }

}
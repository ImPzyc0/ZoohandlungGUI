package com.zoohandlung.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

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
       manager.ladZoohandlung();
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

    //Der Code für den Algorithmus des sortieren der Tiere
        /*
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
    //Der Code für den Algorithmus des suchen nach Tieren
    public static int binaereSuche(int zahl, int[] a){
       int y = 0;
       int i = a.length-1;
        while(y <= i){
            int mitte = (i-y)/2+y;
            if(a[mitte] == zahl){
                return mitte;
            }

            if(a[mitte] < zahl){
                y = mitte+1;
            }else{
                i = mitte-1;
            }
        }

        return -1;
    }
         */

}
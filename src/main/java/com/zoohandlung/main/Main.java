package com.zoohandlung.main;

import com.zoohandlung.Katze;
import com.zoohandlung.Tier;
import com.zoohandlung.Zoohandlung;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    //Variablen fürs lesen der JSON-Dateien:


   //Main instanz
   private static Main main;

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

       Zoohandlung zoohandlung = new Zoohandlung("lll");

       zoohandlung.neuesTier(new Katze("Mimi 4",24,20, "Orange"));
       zoohandlung.neuesTier(new Katze("Mimi 2",8,20, "Orange"));
       zoohandlung.neuesTier(new Katze("Mimi 8",300,20, "Orange"));
       zoohandlung.neuesTier(new Katze("Mimi 7",299,20, "Orange"));
       zoohandlung.neuesTier(new Katze("Mimi 3",20,20, "Orange"));
       zoohandlung.neuesTier(new Katze("Mimi 6",200,20, "Orange"));
       zoohandlung.neuesTier(new Katze("Mimi 1",2,20, "Orange"));
       zoohandlung.neuesTier(new Katze("Mimi 5",25,20, "Orange"));


       System.out.println(zoohandlung.tierImAlterVon(zoohandlung.getTiere(), 19) == null ? "Null":zoohandlung.tierImAlterVon(zoohandlung.getTiere(), 20).getName());
       Tier[] tiereNachAlter = zoohandlung.getTiereNachAlter().clone();
       for(int i = 0; i < tiereNachAlter.length; i++){
           System.out.println("Tier "+i+": "+tiereNachAlter[i].getName());
       }
   }

   public void setControllerInstanz(EventController controller){
       this.controller = controller;
   }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Zoohandlung");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
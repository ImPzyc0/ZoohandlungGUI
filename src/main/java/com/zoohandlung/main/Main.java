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
}
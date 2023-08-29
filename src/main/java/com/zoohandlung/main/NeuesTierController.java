package com.zoohandlung.main;

import com.zoohandlung.Tier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NeuesTierController implements Initializable {

    @FXML
    private ChoiceBox<String> tiere = new ChoiceBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tiere.getItems().addAll(Tier.getTiere());
        tiere.setOnAction(this::getTier);
    }

    protected void getTier(ActionEvent event){

        String tier = tiere.getValue();

        switch (tier){
            case "Pferd":
                zeigePferdView();
                break;
            case "Hund":
                zeigeHundView();
                break;
            case "Katze":
                zeigeKatzeView();
                break;
            default:
                break;
        }
    }

    protected void zeigeHundView(){
        System.out.println("ö2");
    }

    protected void zeigeKatzeView(){

    }
    protected void zeigePferdView(){

    }
}

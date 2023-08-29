package com.zoohandlung.main;

import com.zoohandlung.Tier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class NeuesTierController implements Initializable {

    @FXML
    private ChoiceBox<String> tiere = new ChoiceBox<>();

    @FXML
    private StackPane hundPane, katzePane, pferdPane;

    @FXML
    private TextField katzeAlter, hundAlter, pferdAlter, katzePreis, hundPreis, pferdPreis, katzeName, hundName, pferdName;

    @FXML
    private ChoiceBox<String> katzeRasse, hundRasse, pferdRasse;

    @FXML
    private Button katzeEnter, hundEnter, pferdEnter;

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
        hundPane.setVisible(false);
        pferdPane.setVisible(true);
        katzePane.setVisible(true);
    }

    protected void zeigeKatzeView(){
        hundPane.setVisible(true);
        pferdPane.setVisible(true);
        katzePane.setVisible(false);
    }
    protected void zeigePferdView(){
        hundPane.setVisible(true);
        pferdPane.setVisible(false);
        katzePane.setVisible(true);
    }
}

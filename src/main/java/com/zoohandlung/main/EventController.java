package com.zoohandlung.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;

public class EventController {

    @FXML
    private Label tierLabel1, tierLabel2, tierLabel3, rasse, name, alter, preis;
    @FXML
    private Button neuesTierButton, sortierenNachButton, oeffnenButton, aktionenButton;
    @FXML
    private TextField suchenNach;
    @FXML
    private ScrollBar tierScrollBar;


    public EventController(){
        Main.getMainInstanz().setControllerInstanz(this);
    }


    @FXML
    protected void onOeffnenButtonClick() {
        oeffnenButton.setText(oeffnenButton.getText().equals("Öffnen") ? "Schließen":"Öffnen");
    }

    @FXML
    protected void onNeuesTierButtonclick(){
        //NeuesTier fenster öffnen, eigener Controller!
    }

    @FXML
    protected void onSortierenNachButtonClick(){
        //Labels updaten nach Algorithmus
    }

    @FXML
    protected void onSuchenNachEnter(){
        //Von Oben nach unten nach den Ergebnissen sortieren
    }

    @FXML
    protected void onTierScrollBarUpdate(){
        //Labels neu text setzen


    }

    @FXML
    protected void onAktionenButtonClick(){
        //Aktionen menü öffnen fürs tier
    }
}

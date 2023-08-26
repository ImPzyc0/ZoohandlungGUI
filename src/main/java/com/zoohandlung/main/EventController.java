package com.zoohandlung.main;

import com.zoohandlung.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;

import java.util.Timer;
import java.util.TimerTask;

public class EventController {

    @FXML
    private Label tierLabel1, tierLabel2, tierLabel3, tierLabel4, tierLabel5, tierLabel6,rasse, name, alter, preis;
    @FXML
    private Button neuesTierButton, sortierenNachButton, oeffnenButton, aktionenButton;
    @FXML
    private TextField suchenNach;
    @FXML
    private ScrollBar tierScrollBar;

    private final ZoohandlungsManager manager;
    private final Zoohandlung zoohandlung;

    private int letzterScrollbarWert = 0;


    public EventController(){
        Main.getMainInstanz().setControllerInstanz(this);
        manager = Main.getMainInstanz().getManager();
        zoohandlung = manager.getZoohandlung();
    }

    public void setzeStartWerte(){
        tierScrollBar.setValue(0);
        tierScrollBar.setMax(zoohandlung.getTiere().length-6);
        tierScrollBar.setMin(0);
        tierScrollBar.setVisibleAmount(1);
        tierScrollBar.setUnitIncrement(1);
        tierScrollBar.setBlockIncrement(1);
        onTierScrollBarUpdate();
        Timer timer = new Timer();
        letzterScrollbarWert = (int) tierScrollBar.getValue();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(((int) tierScrollBar.getValue()) != letzterScrollbarWert){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            onTierScrollBarUpdate();
                        }
                    });
                }
                letzterScrollbarWert = (int) tierScrollBar.getValue();
            }
        }, 0, 10);

        if(zoohandlung.getTiere().length > 0){
            setzeAngezeigtesTier(zoohandlung.getTiere()[0]);
        }
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

    protected void onTierScrollBarUpdate(){
        //Labels neu text setzen

        tierLabel1.setText(zoohandlung.getTiere().length > 0 ? zoohandlung.getTiere()[(int) tierScrollBar.getValue()].getName()+ " - "+ zoohandlung.getTiere()[(int) tierScrollBar.getValue()].getClass().getSimpleName(): "-");
        tierLabel2.setText(zoohandlung.getTiere().length > 1 ? zoohandlung.getTiere()[(int) tierScrollBar.getValue()+1].getName()+ " - "+ zoohandlung.getTiere()[(int) tierScrollBar.getValue()+1].getClass().getSimpleName() : "-");
        tierLabel3.setText(zoohandlung.getTiere().length > 2 ? zoohandlung.getTiere()[(int) tierScrollBar.getValue()+2].getName()+ " - "+ zoohandlung.getTiere()[(int) tierScrollBar.getValue()+2].getClass().getSimpleName() : "-");
        tierLabel4.setText(zoohandlung.getTiere().length > 3 ? zoohandlung.getTiere()[(int) tierScrollBar.getValue()+3].getName()+ " - "+ zoohandlung.getTiere()[(int) tierScrollBar.getValue()+3].getClass().getSimpleName() : "-");
        tierLabel5.setText(zoohandlung.getTiere().length > 4 ? zoohandlung.getTiere()[(int) tierScrollBar.getValue()+4].getName()+ " - "+ zoohandlung.getTiere()[(int) tierScrollBar.getValue()+4].getClass().getSimpleName() : "-");
        tierLabel6.setText(zoohandlung.getTiere().length > 5 ? zoohandlung.getTiere()[(int) tierScrollBar.getValue()+5].getName()+ " - "+ zoohandlung.getTiere()[(int) tierScrollBar.getValue()+5].getClass().getSimpleName() : "-");

    }

    @FXML
    protected void onAktionenButtonClick(){
        //Aktionen menü öffnen fürs tier
    }

    private void setzeAngezeigtesTier(Tier tier){
        switch (tier.getClass().getSimpleName()) {
            case "Katze":
                Katze katze = (Katze) tier;
                rasse.setText("Rasse: Katze - "+katze.getRasse());
                break;
            case "Hund":
                Hund hund = (Hund) tier;
                rasse.setText("Rasse: Hund - "+hund.getRasse());
                break;
            case "Pferd":
                Pferd pferd = (Pferd) tier;
                rasse.setText("Rasse: Katze - "+pferd.getRasse());
                break;
            default:
                rasse.setText("Rasse: "+tier.getClass().getSimpleName());
                break;
        }

        name.setText("Name: "+tier.getName());
        alter.setText("Alter: "+ tier.getAlter());
        preis.setText("Preis: "+ tier.getPreis()+ "$");
    }


}

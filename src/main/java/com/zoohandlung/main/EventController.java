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
    private Tier[] alternativeTiere = null;

    private int letzterScrollbarWert = 0;

    //0 - normal sortiert
    //1 - nach Alter (jung-alt)
    //2 - nach Alter (alt-jung)
    //3 - nach Preis (niedrig-hoch)
    //4 - nach Preis (hoch-niedrig)
    //Darf ja keine Enums verwenden, deshalb so hässlich
    private int sortiertNachModus = 0;
    private final String[] sortiertNachModusText = new String[]{"Jung - Alt", "Alt - Jung", "Günstig - Teuer", "Teuer - Günstig","Standard"};
    private final int sortiertNachModusMax = 4;

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
        alternativeTiere = null;

        //Labels updaten nach Algorithmus
        sortiertNachModus = sortiertNachModusMax == sortiertNachModus ? 0 : sortiertNachModus+1;
        sortierenNachButton.setText(sortiertNachModusText[sortiertNachModus]);
        onTierScrollBarUpdate();
        tierScrollBar.setValue(0);
        onClickLabel1();
    }

    @FXML
    protected void onSuchenNachEnter(){
        //Von Oben nach unten nach den Ergebnissen sortieren
        onTierScrollBarUpdate(zoohandlung.getTiereNachName(suchenNach.getText()));

    }

    protected void onTierScrollBarUpdate(){

        if(alternativeTiere != null){
            onTierScrollBarUpdate(alternativeTiere);
        }

        //Labels neu text setzen
        Tier[] tiere;
        switch(sortiertNachModus){
            case 0:
                tiere = zoohandlung.getTiere();
                break;
            case 1:
                tiere = zoohandlung.getTiereNachAlter();
                break;
            case 2:
                tiere = sortiereListeUm(zoohandlung.getTiereNachAlter());
                break;
            case 3:
                tiere = zoohandlung.getTiereNachPreis();
                break;
            case 4:
                tiere = sortiereListeUm(zoohandlung.getTiereNachPreis());
                break;
            default:
                return;
        }
        tierLabel1.setText(tiere.length > 0 ? tiere[(int) tierScrollBar.getValue()].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()].getClass().getSimpleName(): "-");
        tierLabel2.setText(tiere.length > 1 ? tiere[(int) tierScrollBar.getValue()+1].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()+1].getClass().getSimpleName() : "-");
        tierLabel3.setText(tiere.length > 2 ? tiere[(int) tierScrollBar.getValue()+2].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()+2].getClass().getSimpleName() : "-");
        tierLabel4.setText(tiere.length > 3 ? tiere[(int) tierScrollBar.getValue()+3].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()+3].getClass().getSimpleName() : "-");
        tierLabel5.setText(tiere.length > 4 ? tiere[(int) tierScrollBar.getValue()+4].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()+4].getClass().getSimpleName() : "-");
        tierLabel6.setText(tiere.length > 5 ? tiere[(int) tierScrollBar.getValue()+5].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()+5].getClass().getSimpleName() : "-");
    }

    protected void onTierScrollBarUpdate(Tier[] tiere){
        //Labels neu text setzen
        alternativeTiere = tiere;
        tierLabel1.setText(tiere.length > 0 ? tiere[(int) tierScrollBar.getValue()].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()].getClass().getSimpleName(): "-");
        tierLabel2.setText(tiere.length > 1 ? tiere[(int) tierScrollBar.getValue()+1].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()+1].getClass().getSimpleName() : "-");
        tierLabel3.setText(tiere.length > 2 ? tiere[(int) tierScrollBar.getValue()+2].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()+2].getClass().getSimpleName() : "-");
        tierLabel4.setText(tiere.length > 3 ? tiere[(int) tierScrollBar.getValue()+3].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()+3].getClass().getSimpleName() : "-");
        tierLabel5.setText(tiere.length > 4 ? tiere[(int) tierScrollBar.getValue()+4].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()+4].getClass().getSimpleName() : "-");
        tierLabel6.setText(tiere.length > 5 ? tiere[(int) tierScrollBar.getValue()+5].getName()+ " - "+ tiere[(int) tierScrollBar.getValue()+5].getClass().getSimpleName() : "-");

        tierScrollBar.setValue(0);
        tierScrollBar.setMax(tiere.length-6);
        tierScrollBar.setMin(0);

        if(tiere.length > 0){
            setzeAngezeigtesTier(tiere[0]);
        }
    }

    @FXML
    protected void onAktionenButtonClick(){
        //Aktionen menü öffnen fürs tier
    }

    @FXML
    protected void onClickLabel1(){
        setzeAngezeigtesTier(zoohandlung.getTiere()[letzterScrollbarWert]);
    }
    @FXML
    protected void onClickLabel2(){
        setzeAngezeigtesTier(zoohandlung.getTiere()[letzterScrollbarWert+1]);
    }
    @FXML
    protected void onClickLabel3(){
        setzeAngezeigtesTier(zoohandlung.getTiere()[letzterScrollbarWert+2]);
    }
    @FXML
    protected void onClickLabel4(){
        setzeAngezeigtesTier(zoohandlung.getTiere()[letzterScrollbarWert+3]);
    }
    @FXML
    protected void onClickLabel5(){
        setzeAngezeigtesTier(zoohandlung.getTiere()[letzterScrollbarWert+4]);
    }
    @FXML
    protected void onClickLabel6(){
        setzeAngezeigtesTier(zoohandlung.getTiere()[letzterScrollbarWert+5]);
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

    private Tier[] sortiereListeUm(Tier[] arr){
        Tier[] arrUmsortiert = new Tier[arr.length];
        for(int i = 0; i<arr.length; i++){
            arrUmsortiert[arr.length-i-1] = arr[i];
        }
        return arrUmsortiert;
    }
}

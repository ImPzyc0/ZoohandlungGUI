package com.zoohandlung.main;

import com.zoohandlung.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class EventController implements Initializable {

    @FXML
    private Label tierLabel1, tierLabel2, tierLabel3, tierLabel4, tierLabel5, tierLabel6, rasse, name, alter, preis, sortiertNach;

    private Label[] tierLabels;
    @FXML
    private Button sortierenNachButton, oeffnenButton;
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

        alternativeTiere = zoohandlung.getTiere();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        tierLabels = new Label[]{tierLabel1, tierLabel2, tierLabel3, tierLabel4, tierLabel5, tierLabel6};
        tierScrollBar.setValue(0);
        tierScrollBar.setMax(zoohandlung.getTiere().length-6);
        tierScrollBar.setMin(0);
        tierScrollBar.setVisibleAmount(1);
        tierScrollBar.setUnitIncrement(1);
        tierScrollBar.setBlockIncrement(1);
        tierLabelsUpdate();
        Timer timer = new Timer();
        letzterScrollbarWert = (int) tierScrollBar.getValue();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(((int) tierScrollBar.getValue()) != letzterScrollbarWert){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            tierLabelsUpdate();
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

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("neues-tier-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Neues Tier");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    protected void onSortierenNachButtonClick(){
        suchenNach.setText("");
        //Labels updaten nach Algorithmus
        sortiertNachModus = sortiertNachModusMax == sortiertNachModus ? 0 : sortiertNachModus+1;
        sortierenNachButton.setText(sortiertNachModusText[sortiertNachModus]);
        sortiertNach.setText("Sortiert nach:    "+sortiertNachModusText[sortiertNachModus-1 < 0 ? sortiertNachModusMax: sortiertNachModus-1]);
        updateAlternativeTiere();
        tierLabelsUpdate();
        tierScrollBar.setValue(0);
        onClickLabel1();
    }

    @FXML
    protected void onSuchenNachEnter(){
        String suche = suchenNach.getText();
        //Von Oben nach unten nach den Ergebnissen sortieren
        //Je nach Sortiert wird unterschiedlich gesucht
        //Falls genaues Ergebnis gesucht wird muss ein "=" davor
        //Beispiel: 100 input u. Modus alt-Jung/Jung-alt, gibt alle Tiere deren Alter 100 ist zurück

        //Sortiert nach und eine Zahl als Input gibt alle Tiere die passen
        //Beispiel: 100 input u. Modus alt-jung, gibt alle Tiere über oder gleich 100 Wochen alt zurück
        //Beispiel: 100 input u. Modus günstig-teuer, gibt alle Tiere die billiger oder gleich 100 sind
        //Beispiel: "ldsajfkldasfj" als input u. Modus günstig-teuer sucht nach dem String, da es keine Zahl ist
        if(suche.isEmpty()){
            return;
        }

        int sucheZahl = 0;
        boolean genaueSuche = false;
        if(suche.charAt(0) == '='){
            genaueSuche = true;
            if(suche.length() == 1){
                updateTierScrollBarSuche(zoohandlung.getTiereNachName(suche));
                return;
            }
            //Nach bestimmten Wert suchen
            String sucheNeu = suche.substring(1);
            try{
                sucheZahl = Integer.parseInt(sucheNeu);
            }catch(NumberFormatException x){
                updateTierScrollBarSuche(zoohandlung.getTiereNachName(suche));
                return;
            }
        }
        if(!genaueSuche){
            try{
                sucheZahl = Integer.parseInt(suche);
            }catch(NumberFormatException x){
                updateTierScrollBarSuche(zoohandlung.getTiereNachName(suche));
                return;
            }
        }
        //Falls nicht nach einem bestimmten Wert gesucht wird
        switch (sortiertNachModus){
            //Jung-alt
            case 1:
                if(genaueSuche){
                    updateTierScrollBarSuche(zoohandlung.getTiereMitAlter(sucheZahl));
                }else{
                    updateTierScrollBarSuche(zoohandlung.getTiereMitAlterNiedriger(sucheZahl));
                }
                break;
            //alt-jung
            case 2:
                if(genaueSuche){
                    updateTierScrollBarSuche(zoohandlung.getTiereMitAlter(sucheZahl));
                }else{
                    updateTierScrollBarSuche(zoohandlung.getTiereMitAlterHoeher(sucheZahl));
                }
                break;
            //Günstig-teuer
            case 3:
                if(genaueSuche){
                    updateTierScrollBarSuche(zoohandlung.getTiereMitPreis(sucheZahl));
                }else{
                    updateTierScrollBarSuche(zoohandlung.getTiereMitPreisNiedriger(sucheZahl));
                }
                break;
            //teuer-günstig
            case 4:
                if(genaueSuche){
                    updateTierScrollBarSuche(zoohandlung.getTiereMitPreis(sucheZahl));
                }else{
                    updateTierScrollBarSuche(zoohandlung.getTiereMitPreisHoeher(sucheZahl));
                }
                break;
            //Standard
            default:
                updateTierScrollBarSuche(zoohandlung.getTiereNachName(suche));
                break;
        }
    }

    public void updateAlternativeTiere(){
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
        alternativeTiere = tiere;
        tierLabelsUpdate();
        updateScrollBar();
    }

    protected void updateTierScrollBarSuche(Tier[] tiere){
        //Labels neu text setzen
        alternativeTiere = tiere;
        tierLabelsUpdate();
        updateScrollBar();
    }

    private void updateScrollBar(){
        tierScrollBar.setValue(0);
        tierScrollBar.setMax(alternativeTiere.length-6);

        if(alternativeTiere.length > 0){
            setzeAngezeigtesTier(alternativeTiere[0]);
        }else{
            setzeAngezeigtesTier(null);
        }
    }

    protected void tierLabelsUpdate(){
        //Labels neu text setzen
        for(int i = 0; i<6; i++){
            tierLabels[i].setText(alternativeTiere.length > i ? alternativeTiere[(int) tierScrollBar.getValue()+i].getName()+ " - "+ alternativeTiere[(int) tierScrollBar.getValue()+i].getClass().getSimpleName(): "-");
        }
    }

    @FXML
    protected void onAktionenButtonClick(){
        //Aktionen menü öffnen fürs tier
    }
    //Deutlich schöner als eine Method die das rechnerisch überprüft
    @FXML
    protected void onClickLabel1(){
        setzeAngezeigtesTier(alternativeTiere == null ? zoohandlung.getTiere()[letzterScrollbarWert] : alternativeTiere[letzterScrollbarWert]);
    }
    @FXML
    protected void onClickLabel2(){
        setzeAngezeigtesTier(alternativeTiere == null ? zoohandlung.getTiere()[letzterScrollbarWert+1] : alternativeTiere[letzterScrollbarWert+1]);
    }
    @FXML
    protected void onClickLabel3(){
        setzeAngezeigtesTier(alternativeTiere == null ? zoohandlung.getTiere()[letzterScrollbarWert+2] : alternativeTiere[letzterScrollbarWert+2]);
    }
    @FXML
    protected void onClickLabel4(){
        setzeAngezeigtesTier(alternativeTiere == null ? zoohandlung.getTiere()[letzterScrollbarWert+3] : alternativeTiere[letzterScrollbarWert+3]);
    }
    @FXML
    protected void onClickLabel5(){
        setzeAngezeigtesTier(alternativeTiere == null ? zoohandlung.getTiere()[letzterScrollbarWert+4] : alternativeTiere[letzterScrollbarWert+4]);
    }
    @FXML
    protected void onClickLabel6(){
        setzeAngezeigtesTier(alternativeTiere == null ? zoohandlung.getTiere()[letzterScrollbarWert+5] : alternativeTiere[letzterScrollbarWert+5]);
    }

    private void setzeAngezeigtesTier(Tier tier){

        if(tier == null){
            rasse.setText("Rasse: -");
            name.setText("Name: -");
            alter.setText("Alter: -");
            preis.setText("Preis: -");

            return;
        }

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
                rasse.setText("Rasse: Pferd - "+pferd.getRasse());
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

    public void setSortiertNachModus(int sortiertNachModus) {
        suchenNach.setText("");
        //Labels updaten nach Algorithmus
        sortiertNachModus = sortiertNachModusMax == sortiertNachModus ? 0 : sortiertNachModus+1;
        sortierenNachButton.setText(sortiertNachModusText[sortiertNachModus]);
        sortiertNach.setText("Sortiert nach:    "+sortiertNachModusText[sortiertNachModus-1 < 0 ? sortiertNachModusMax: sortiertNachModus-1]);
        this.sortiertNachModus = sortiertNachModus;
    }


}

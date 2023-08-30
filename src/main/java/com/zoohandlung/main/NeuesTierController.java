package com.zoohandlung.main;

import com.zoohandlung.Hund;
import com.zoohandlung.Katze;
import com.zoohandlung.Pferd;
import com.zoohandlung.Tier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class NeuesTierController implements Initializable {

    @FXML
    private ChoiceBox<String> tiere = new ChoiceBox<>();

    @FXML
    private Label errorLabel;

    @FXML
    private StackPane hundPane, katzePane, pferdPane;

    @FXML
    private TextField katzeAlter, hundAlter, pferdAlter, katzePreis, hundPreis, pferdPreis, katzeName, hundName, pferdName;

    @FXML
    private ChoiceBox<String> katzeRasse, hundRasse, pferdRasse;

    @FXML
    private CheckBox lieg, platz;

    @FXML
    private Button katzeEnter, hundEnter, pferdEnter;

    private Main mainInstanz;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainInstanz = Main.getMainInstanz();
        tiere.getItems().addAll(Tier.getTiere());
        tiere.setOnAction(this::getTier);

        katzeRasse.getItems().addAll(Katze.getRassen());
        hundRasse.getItems().addAll(Hund.getRassen());
        pferdRasse.getItems().addAll(Pferd.getRassen());
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
        hundPane.setVisible(true);
        pferdPane.setVisible(false);
        katzePane.setVisible(false);
    }

    protected void zeigeKatzeView(){
        hundPane.setVisible(false);
        pferdPane.setVisible(false);
        katzePane.setVisible(true);
    }
    protected void zeigePferdView(){
        hundPane.setVisible(false);
        pferdPane.setVisible(true);
        katzePane.setVisible(false);
    }

    @FXML
    protected void onEnter() {

        int alter;
        double preis;
        String name;
        String rasse;

        switch (tiere.getValue()) {
            case "Hund":
                try{
                    alter = Integer.parseInt(hundAlter.getText());
                }catch(NumberFormatException x){
                    errorLabel.setText("Ungültiges Alter!");
                    return;
                }
                try{
                    preis = Double.parseDouble(hundPreis.getText());
                }catch(NumberFormatException x){
                    errorLabel.setText("Ungültiger Preis!");
                    return;
                }
                if(hundName.getText().equalsIgnoreCase("")){
                    errorLabel.setText("Ungültiger Name!");
                    return;
                }
                name = hundName.getText();
                if(hundRasse.getValue() == null || hundRasse.getValue().equalsIgnoreCase("")){
                    errorLabel.setText("Rasse auswählen!");
                    return;
                }
                rasse = hundRasse.getValue();
                String[] befehle = new String[0];
                if(lieg.isSelected()){
                    befehle = new String[]{"lieg"};
                }
                if(platz.isSelected()){
                    befehle = Arrays.copyOf(befehle, befehle.length+1);
                    befehle[befehle.length-1] = "platz";
                }
                mainInstanz.getManager().getZoohandlung().neuesTier(new Hund(name, alter, rasse, befehle, preis));

                break;
            case "Pferd":
                try{
                    alter = Integer.parseInt(pferdAlter.getText());
                }catch(NumberFormatException x){
                    errorLabel.setText("Ungültiges Alter!");
                    return;
                }
                try{
                    preis = Double.parseDouble(pferdPreis.getText());
                }catch(NumberFormatException x){
                    errorLabel.setText("Ungültiger Preis!");
                    return;
                }
                if(pferdName.getText().equalsIgnoreCase("")){
                    errorLabel.setText("Ungültiger Name!");
                    return;
                }
                name = pferdName.getText();
                if(pferdRasse.getValue() == null || pferdRasse.getValue().equalsIgnoreCase("")){
                    errorLabel.setText("Rasse auswählen!");
                    return;
                }
                rasse = pferdRasse.getValue();
                mainInstanz.getManager().getZoohandlung().neuesTier(new Pferd(name, alter, preis, rasse));
                break;
            case "Katze":
                try{
                    alter = Integer.parseInt(katzeAlter.getText());
                }catch(NumberFormatException x){
                    errorLabel.setText("Ungültiges Alter!");
                    return;
                }
                try{
                    preis = Double.parseDouble(katzePreis.getText());
                }catch(NumberFormatException x){
                    errorLabel.setText("Ungültiger Preis!");
                    return;
                }
                if(katzeName.getText().equalsIgnoreCase("")){
                    errorLabel.setText("Ungültiger Name!");
                    return;
                }
                name = katzeName.getText();
                if(pferdRasse.getValue() == null || katzeRasse.getValue().equalsIgnoreCase("")){
                    errorLabel.setText("Rasse auswählen!");
                    return;
                }
                rasse = katzeRasse.getValue();
                mainInstanz.getManager().getZoohandlung().neuesTier(new Katze(name, alter, preis, rasse));
                break;
            default:
                break;
        }

        mainInstanz.getController().updateTierScrollBarSuche(Main.getMainInstanz().getManager().getZoohandlung().getTiere());
    }
}

package com.zoohandlung.main;

import com.zoohandlung.Hund;
import com.zoohandlung.Katze;
import com.zoohandlung.Pferd;
import com.zoohandlung.Tier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class NeuesTierController implements Initializable {
    //Der Controller wenn ein neues Tier gemacht wird

    @FXML
    private ChoiceBox<String> tiere = new ChoiceBox<>();

    @FXML
    private Label errorLabel;

    @FXML
    private StackPane hundPane, katzePane, pferdPane;

    @FXML
    private TextField katzeAlter, hundAlter, pferdAlter, katzePreis, hundPreis, pferdPreis, katzeName, hundName, pferdName;

    private TextField[][] TIER_FIELDS;

    private final int KATZE = 0;
    private final int HUND = 1;
    private final int PFERD = 2;

    @FXML
    private ChoiceBox<String> katzeRasse, hundRasse, pferdRasse;

    private ChoiceBox<String>[] RASSE_BOXEN;

    @FXML
    private CheckBox lieg, platz;

    private Main mainInstanz;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainInstanz = Main.getMainInstanz();
        tiere.getItems().addAll(Tier.getTiere());
        tiere.setOnAction(this::getTier);

        katzeRasse.getItems().addAll(Katze.getRassen());
        hundRasse.getItems().addAll(Hund.getRassen());
        pferdRasse.getItems().addAll(Pferd.getRassen());

         TIER_FIELDS = new TextField[][]{{katzeAlter, katzePreis, katzeName}, {hundAlter, hundPreis, hundName}, {pferdAlter, pferdPreis, pferdName}};
         RASSE_BOXEN = new ChoiceBox[]{katzeRasse, hundRasse, pferdRasse};
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

    //Zeige die Stackpanes für die unterschiedlichen Tiere
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
    //Wenn der Enter Button für ein neues tier gedrückt wird
    @FXML
    protected void onEnter() {

        int alter;
        double preis;
        String name;
        String rasse;
        int tier;

        switch (tiere.getValue()) {
            case "Hund":
                tier = HUND;
                break;
            case "Pferd":
                tier = PFERD;
                break;
            case "Katze":
                tier = KATZE;
                break;
            default:
                return;
        }

        try{
            alter = Integer.parseInt(TIER_FIELDS[tier][0].getText());
        }catch(NumberFormatException x){
            errorLabel.setText("Ungültiges Alter!");
            return;
        }
        try{
            preis = Double.parseDouble(TIER_FIELDS[tier][1].getText());
        }catch(NumberFormatException x){
            errorLabel.setText("Ungültiger Preis!");
            return;
        }

        if(preis <= 0){
            errorLabel.setText("Preis kann nicht negativ oder null sein!");
            return;
        }
        if(alter <= 0){
            errorLabel.setText("Alter kann nicht negativ oder null sein!");
            return;
        }

        if(mainInstanz.getManager().getZoohandlung().getGeld() - preis < 0){
            errorLabel.setText("Tier ist zu teuer!");
            return;
        }

        if(TIER_FIELDS[tier][2].getText().length() < 3){
            errorLabel.setText("Name muss mindestens 3 Zeichen lang sein!");
            return;
        }
        name = TIER_FIELDS[tier][2].getText();
        if(RASSE_BOXEN[tier].getValue() == null || RASSE_BOXEN[tier].getValue().length() < 3){
            errorLabel.setText("Rasse auswählen!");
            return;
        }
        rasse = RASSE_BOXEN[tier].getValue();
        String[] befehle = new String[0];
        if(tier == HUND){
            if(lieg.isSelected()){
                befehle = new String[]{"lieg"};
            }
            if(platz.isSelected()){
                befehle = Arrays.copyOf(befehle, befehle.length+1);
                befehle[befehle.length-1] = "platz";
            }
        }
        switch(tier){
            case KATZE:
                mainInstanz.getManager().getZoohandlung().neuesTier(new Katze(name, alter, preis, rasse));
                break;
            case HUND:
                mainInstanz.getManager().getZoohandlung().neuesTier(new Hund(name, alter, rasse, befehle, preis));
                break;
            case PFERD:
                mainInstanz.getManager().getZoohandlung().neuesTier(new Pferd(name, alter, preis, rasse));
                break;
            default:
                break;
        }

        TIER_FIELDS[tier][1].setText("");
        TIER_FIELDS[tier][0].setText("");
        TIER_FIELDS[tier][2].setText("");
        RASSE_BOXEN[tier].setValue(null);

        errorLabel.setText("Tier Hinzugefügt!");
        mainInstanz.getController().setzeSortiertNachModus(-1);
        mainInstanz.getController().updateAlternativeTiere();
        mainInstanz.getController().updateGeldLabel();
    }
}

package com.zoohandlung.main;

import com.zoohandlung.Pfleger;
import com.zoohandlung.Tier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AktionenController implements Initializable {

    private Tier tier;

    @FXML
    private ChoiceBox<String> pfleger, aktion;
    @FXML
    private Label errorLabel;

    @FXML
    protected void onAktion() {

        if(pfleger.getValue() == null || aktion.getValue() == null){
            errorLabel.setText("Bitte Pfleger und Aktion auswählen!");
            return;
        }
        tier.setAktionenAusgefuehrt(true);
        Main.getMainInstanz().getManager().getZoohandlung().getPfleger(pfleger.getValue()).versorgTier(tier);

        tier.aktionenAusfuehren(aktion.getValue());

        Stage stage = (Stage) pfleger.getScene().getWindow();
        Main.getMainInstanz().getController().setAktionenFensterOffen(false);
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tier = Main.getMainInstanz().getController().getAktuellAngezeigtesTier();

        for(Pfleger pfleger1 : Main.getMainInstanz().getManager().getZoohandlung().getPfleger()){
            pfleger.getItems().add(pfleger1.getName());
        }
        aktion.getItems().addAll(tier.getAktionen());
    }

    @FXML
    protected void onTierVerkaufen(){

        Main.getMainInstanz().getManager().getZoohandlung().verkaufTier(tier);

        Main.getMainInstanz().getController().setzeSortiertNachModus(-1);
        Main.getMainInstanz().getController().updateTierScrollBarSuche(Main.getMainInstanz().getManager().getZoohandlung().getTiere());
        Main.getMainInstanz().getController().updateAlternativeTiere();

        Main.getMainInstanz().getController().updateGeldLabel();

        Stage stage = (Stage) pfleger.getScene().getWindow();
        Main.getMainInstanz().getController().setAktionenFensterOffen(false);
        stage.close();

    }
}

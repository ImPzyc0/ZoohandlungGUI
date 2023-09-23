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
    private EventController controller;
    private ZoohandlungsManager manager;

    //Das Aktionen fenster für das füttern und das ausführen von Aktionen
    @FXML
    protected void onAktion() {
        if(pfleger.getValue() == null || aktion.getValue() == null){
            errorLabel.setText("Bitte Pfleger und Aktion auswählen!");
            return;
        }
        tier.setAktionenAusgefuehrt(true);
        manager.getZoohandlung().pflegerVersorgtTier(manager.getZoohandlung().getPfleger(pfleger.getValue()));
        tier.fuetterTier();
        tier.aktionenAusfuehren(aktion.getValue());

        Stage stage = (Stage) pfleger.getScene().getWindow();
        controller.setAktionenFensterOffen(false);
        stage.close();

        controller.updateAktivePfleger();
        controller.setzeAngezeigtesTier(controller.getAktuellAngezeigtesTier());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tier = Main.getMainInstanz().getController().getAktuellAngezeigtesTier();

        for(Pfleger pfleger1 : Main.getMainInstanz().getManager().getZoohandlung().getPfleger()){
            pfleger.getItems().add(pfleger1.getName());
        }
        aktion.getItems().addAll(tier.getAktionen());

        manager = Main.getMainInstanz().getManager();
        controller = Main.getMainInstanz().getController();
    }

    @FXML
    protected void onTierVerkaufen(){

        manager.getZoohandlung().verkaufTier(tier);

        controller.setzeSortiertNachModus(-1);
        controller.updateTierScrollBarSuche(Main.getMainInstanz().getManager().getZoohandlung().getTiere());
        controller.updateAlternativeTiere();

        controller.updateGeldLabel();

        Stage stage = (Stage) pfleger.getScene().getWindow();
        controller.setAktionenFensterOffen(false);
        stage.close();

    }
}

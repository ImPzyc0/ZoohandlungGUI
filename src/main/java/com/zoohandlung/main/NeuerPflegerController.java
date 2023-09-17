package com.zoohandlung.main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NeuerPflegerController {

    @FXML
    private TextField pflegerName;

    @FXML
    private Label errorLabel;

    @FXML
    protected void onButtonClick(){

        if(pflegerName.getText() == null || pflegerName.getText().length() < 3 || pflegerName.getText().length() > 10){
            errorLabel.setText("Pfleger Name muss zwischen 3 und 9 Buchstaben lang sein!");
            return;
        }else if(Main.getMainInstanz().getManager().getZoohandlung().getGeld() < 200){
            errorLabel.setText("Ein Pfleger kostet 200€!");
            return;
        }

        Main.getMainInstanz().getManager().getZoohandlung().neuerPfleger(pflegerName.getText(), 200);

        Main.getMainInstanz().getController().updateAktivePfleger();

        Stage stage = (Stage) pflegerName.getScene().getWindow();

        stage.close();
    }

}

package com.zoohandlung.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EventController {
    @FXML
    private Button oeffnenButton;

    @FXML
    protected void onOeffnenButtonClick() {
        oeffnenButton.setText(oeffnenButton.getText().equals("Öffnen") ? "Schließen":"Öffnen");
    }
}

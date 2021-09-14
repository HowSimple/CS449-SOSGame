package com.example.cs449sosgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HelloController {
    @FXML
    private Label welcomeText;



    @FXML
    protected void onNewGameButtonClick() {
        welcomeText.setText("Starting new game");
    }
    @FXML
    protected void onCheckBoxClick() {}
    @FXML
    protected void onGridClick() {

    }


}
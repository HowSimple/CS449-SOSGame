package com.example.cs449sosgame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SOSController {

    private BoardCell[][] gridButtons;
    public class BoardCell extends Button implements EventHandler<ActionEvent> {
        private int row;
        private int column;

        public void handle(ActionEvent event) {
           // if (isGameOver(row, column))




        }
    }

    public void blockInput()
    {

    }

}

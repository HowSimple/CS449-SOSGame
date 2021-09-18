package com.example.cs449sosgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HelloController {
    @FXML Rectangle box1, box2, box3, box4, box5, box6, box7, box8, box9 ;
    @FXML Rectangle box10, box11, box12, box13, box14, box15, box16, box17, box18, box19
                   ,box20, box21, box22, box23, box24, box25, box26, box27, box28, box29
                   ,box30, box31, box32, box33, box34, box35, box36 ,box37, box38, box39
                   ,box40, box41, box42, box43, box44, box45, box46, box47, box48, box49
                   ,box50, box51, box52, box53, box54, box55, box56, box57, box58, box59
                   ,box60, box61, box62, box63, box64;
    @FXML Color boardColor;


    @FXML
    private Label welcomeText;
    @FXML
    public void initialize(){
        boardColor = Color.WHITE;
        box1.setFill(boardColor);

    }
    @FXML
    protected void boxClicked_A1(){
        box1.setFill(Color.BLACK);
    }
    @FXML
    protected void boxClicked_A2(){
        box2.setFill(Color.BLACK);
    }
    @FXML
    protected void boxClicked_A3(){
        box3.setFill(Color.BLACK);
    }
    @FXML
    protected void boxClicked_A4(){
        box4.setFill(Color.BLACK);
    }
    @FXML
    protected void boxClicked_A5(){
        box5.setFill(Color.BLACK);
    }
    @FXML
    protected void boxClicked_A6(){
        box6.setFill(Color.BLACK);
    }
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
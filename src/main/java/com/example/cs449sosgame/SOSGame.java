package com.example.cs449sosgame;

import javafx.scene.control.Label;

public class SOSGame {
    private char[][] board;
    private char activePlayer;
    private Label showActivePlayer;
    public SOSGame(int size)
    {
        activePlayer = 'S';
        board = new char[size][size];

    }

    public void switchActivePlayer()
    {
        if (activePlayer == 'S')
            activePlayer = 'O';
        else activePlayer = 'S';

    }
    public void blockInput()
    {

    }
    public void restartGame(){

    }


    // TODO:
    public void checkIfGameEnds ()
    {


    }
}

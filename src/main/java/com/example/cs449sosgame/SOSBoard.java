package com.example.cs449sosgame;

import javafx.scene.control.Label;

public class SOSBoard {
    private char[][] board;
    private int boardSize;
    private char activePlayer;
    private Label showActivePlayer;
    public SOSBoard(int size)
    {
        activePlayer = 'S';
        board = new char[size][size];

    }
    public void makeMove(int row, int column)
    {
        if ((row >=0 && row < boardSize && column >= 0 && column < boardSize) && board[row][column] == 0)
        {
               board[row][column] = activePlayer;
               switchActivePlayer();
        }



    }
    public char getCell(int row, int column)
    {

        if (row >=0 && row < boardSize && column >= 0 && column < boardSize)
            return board[row][column];
        else return '1';
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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class SOSBoard{
    protected char[][] grid;
    protected enum GameState {PLAYING, DRAW, S_WON, O_WON};
    protected GameState state;
    private int boardSize;
    protected char activePlayer;
    protected int sPlayerPoints, oPlayerPoints;
    protected boolean sPlayerIsAI, oPlayerIsAI;
    protected boolean gameRecordingEnabled;
    public String gameLogPath = "Recorded game.txt";
    protected int movesThisGame;
    Random rng = new Random();
    protected String gameModeName;
    public void recordGameOptions() {
            try {
                FileWriter fw = null;
                fw = new FileWriter("Recorded game.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("size " +grid.length+ " mode "+ gameModeName);
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int i = 0;
    }

        public void recordMove(int row, int column)
    {

        FileWriter fw = null;
        try {
            fw = new FileWriter(gameLogPath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(activePlayer + " "+row + " " +column);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String readFromFile()
    {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(gameLogPath))) {
            line = br.readLine();
            //String[] split = line.split(" ");
            //int boardSize = parseInt(split[1]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;

    }
    public SOSBoard(int size, boolean playerS_isAI, boolean playerO_isAI, boolean gameRecording_Enabled)
    {
        sPlayerIsAI = playerS_isAI;
        oPlayerIsAI = playerO_isAI;
        gameRecordingEnabled = gameRecording_Enabled;

        movesThisGame = 0;
        sPlayerPoints = 0;
        oPlayerPoints = 0;
        activePlayer = 'S';
        boardSize = size;

        state = GameState.PLAYING;
        grid = new char[size][size];
        for (int i = 0; i < size; i++)
            for (int j= 0; j < size; j++)
                grid[i][j] = ' ';
        if (gameRecordingEnabled)
            recordGameOptions();
        //updateState();
    }

    public String getState()
    {

          if (state == GameState.DRAW)
            return "DRAW";
        else if (state == GameState.O_WON)
            return "O WON";
        else if (state == GameState.S_WON)
            return "S WON";
        else
            return "Playing";

    }
    public void checkForGameWin(){
        int maximumPossibleMoves = grid.length * grid.length;
        if (movesThisGame > maximumPossibleMoves)
            state = GameState.DRAW;
        else state = GameState.PLAYING;
    }
    public void updateState(){

        checkForGameWin();
        if (state == GameState.PLAYING) {

            if ((activePlayer == 'S' && sPlayerIsAI) || (activePlayer == 'O' && oPlayerIsAI))
                makeAImove();
        }

    }

    public int checkSOS(int row, int col)
    {
        // adds points to score when player completes a S.O.S. sequence


        int pointsThisTurn = 0;

        if (getActivePlayer() == 'O' && grid[row][col] == 'O' && row >0)
            pointsThisTurn += checkSOS(row-1, col);

        if (getActivePlayer() == 'O' && grid[row][col] == 'O' && col >0)
            pointsThisTurn += checkSOS(row, col-1);


        if (getActivePlayer() == 'O' && grid[row][col] == 'O'  && row > 0 && col > 0 )
            pointsThisTurn+= checkSOS(row-1, col-1);

        if (row +2 < grid.length-1  )
        {
            if (grid[row][col] == 'S' && grid[row+1][col] == 'O' && grid[row+2][col] == 'S')
                pointsThisTurn += 1;
            if (col +2 < grid.length-1)
                if (grid[row][col] == 'S' && grid[row+1][col+1] == 'O' && grid[row+2][col+2] == 'S')
                    pointsThisTurn += 1;

        }
        //  bound check
        if (row -2 >= 0 )
        {
            if (grid[row][col] == 'S' && grid[row-1][col] == 'O' && grid[row-2][col] == 'S')
                pointsThisTurn += 1;
            if (col -2 >= 0)
                if (grid[row][col] == 'S' && grid[row-1][col-1] == 'O' && grid[row-2][col-2] == 'S')
                    pointsThisTurn += 1;



        }


        //  bound check
         if (col + 2 < grid.length-1)
            if (grid[row][col] == 'S' && grid[row][col +1] == 'O' && grid[row][col +2] == 'S')
                pointsThisTurn += 1;

        //  bound check
         if (col - 2 >= 0)
            if (grid[row][col] == 'S' && grid[row][col -1] == 'O' && grid[row][col -2] == 'S')
                pointsThisTurn += 1;

        if (getActivePlayer() == 'S')
            sPlayerPoints += pointsThisTurn;
        else if (getActivePlayer() == 'O')
        {
            if (pointsThisTurn >1)
                pointsThisTurn = 1;
            oPlayerPoints += pointsThisTurn;
        }

        return pointsThisTurn;
    }
    private void makeAImove(){

        if ( (activePlayer == 'S' && sPlayerIsAI) || (activePlayer== 'O' && oPlayerIsAI))
        {

            int row = rng.nextInt(grid.length);
            int col = rng.nextInt(grid.length);
            //row = 1;
            //col = 1;
            if (!makeMove(row,col))
                makeAImove();
        }





    }
    public boolean makeMove(int row, int column)
    {


        if ((row >=0 && row < grid.length && column >= 0 && column < grid.length) && grid[row][column] == ' ')
        {

            movesThisGame += 1;
            if (activePlayer == 'S')
            {
                grid[row][column] = 'S';
                sPlayerPoints+= checkSOS(row, column);
                //switchActivePlayer();
                //checkSOS(row, column, activePlayer);
                //if (CheckWin(row, column) == GameState.S_WON)
            }

            else if (activePlayer == 'O')
            {
                grid[row][column] = 'O' ;
                oPlayerPoints+= checkSOS(row, column);
                //switchActivePlayer();
            }

            if (gameRecordingEnabled)
                recordMove(row, column);
            switchActivePlayer();
            //updateState();

            return true;
        }
        else return false;


    }

    public int getBoardSize() {return boardSize;}
    public char getActivePlayer() {return activePlayer;}
    public char getCell(int row, int column)
    {
        char cell = ' ' ;
        if (row >=0 && row < boardSize && column >= 0 && column < boardSize)
            if (grid[row][column] == 'S')
                cell = 'S';
            else if (grid[row][column] == 'O')
                cell = 'O';
            else if (grid[row][column] == ' ')
                cell =  ' ';


        return cell;
    }
    public int getOplayerPoints()
    {
        return oPlayerPoints;
    }
    public int getSplayerPoints()
    {
        return sPlayerPoints;
    }
    public void switchActivePlayer()
    {
        if (activePlayer == 'S')
            activePlayer = 'O';
        else activePlayer = 'S';


    }


}

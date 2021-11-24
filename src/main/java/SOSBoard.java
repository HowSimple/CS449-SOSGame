import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SOSBoard{
    public enum Cell {EMPTY, S, O};
    protected char[][] grid;
    public enum GameState {PLAYING, DRAW, S_WON, O_WON};
    public GameState state;
    private int boardSize;
    protected char activePlayer;
    protected int sPlayerPoints;
    protected int oPlayerPoints;
    private boolean sPlayerIsAI;
    private boolean oPlayerIsAI;

    //protected int pointsNeededToWin = 1;
    protected int movesThisGame;
    Random rng = new Random();

    public SOSBoard(int size)
    {
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

    }
    public void setAIplayer(char player){
        
    }
    public void makeAImove(char AIplayer){

        int row = rng.nextInt(grid.length);
        int col = rng.nextInt(grid.length);
        if (!makeMove(row,col))
            makeAImove(AIplayer);


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
    public void updateState(){
        int maximumPossibleMoves = grid.length * grid.length;
        if (movesThisGame > maximumPossibleMoves)
            state = GameState.DRAW;
        else state = GameState.PLAYING;



    }

    public int checkSOS(int row, int col)
    {
        char turn = getActivePlayer();


        int offset = 0;
        int pointsThisTurn = 0;
        if (turn == 'O' && grid[row][col] != 'O' )
           pointsThisTurn += 0;
        if (turn == 'O' && grid[row][col] == 'O' && row >0) {
            pointsThisTurn += checkSOS(row-1, col);


        }
        if (turn == 'O' && grid[row][col] == 'O' && col >0) {
            pointsThisTurn += checkSOS(row, col-1);

        }


        if (turn == 'O' && grid[row][col] == 'O'  && row > 0 && col > 0 )
        {
            pointsThisTurn+= checkSOS(row-1, col-1);
        }



        //  bound check

        if (row +2 < grid.length-1  )
        {

            if (grid[row-offset][col] == 'S'
                    && grid[row-offset+1][col] == 'O'
                    && grid[row-offset+2][col] == 'S')
            {
                //sosFound = true;
                pointsThisTurn += 1;
            }
            if (col +2 < grid.length-1)
                if (grid[row-offset][col] == 'S'
                        && grid[row-offset+1][col+1] == 'O'
                        && grid[row-offset+2][col+2] == 'S')
                {
                    //sosFound = true;
                    pointsThisTurn += 1;
                }

        }
             //  bound check
        if (row -2 >= 0 )
        {
            if (grid[row-offset][col] == 'S'
                    && grid[row-offset-1][col] == 'O'
                    && grid[row-offset-2][col] == 'S')
            {
                //sosFound = true;
                pointsThisTurn += 1;
            }
            if (col -2 >= 0)
                if (grid[row-offset][col] == 'S'
                        && grid[row-offset-1][col-1] == 'O'
                        && grid[row-offset-2][col-2] == 'S')
                {
                    //sosFound = true;
                    pointsThisTurn += 1;
                }


        }


        //  bound check
         if (col + 2 < grid.length-1) {
            if (grid[row][col-offset] == 'S'
                    && grid[row][col-offset +1] == 'O'
                    && grid[row][col-offset +2] == 'S')
            {
                //sosFound = true;
                pointsThisTurn += 1;
            }
        }
        //  bound check
         if (col - 2 >= 0)
        {
            if (grid[row][col-offset] == 'S'
                    && grid[row][col-offset -1] == 'O'
                    && grid[row][col-offset -2] == 'S')
            {
                //sosFound = true;
                pointsThisTurn += 1;
            }


        }

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

    public boolean makeMove(int row, int column)
    {
        if ((row >=0 && row < grid.length && column >= 0 && column < grid.length) && grid[row][column] == ' ')
        {


            if (activePlayer == 'S')
            {
                grid[row][column] = 'S';
                sPlayerPoints+= checkSOS(row, column);
                movesThisGame += 1;
                //checkSOS(row, column, activePlayer);
                //if (CheckWin(row, column) == GameState.S_WON)


            }

            else if (activePlayer == 'O')
            {
                grid[row][column] = 'O' ;
                oPlayerPoints+= checkSOS(row, column);
                movesThisGame += 1;

            }


            updateState();
            switchActivePlayer();
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

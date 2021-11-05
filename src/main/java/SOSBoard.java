import java.util.ArrayList;
import java.util.List;

public class SOSBoard{
    public enum Cell {EMPTY, S, O};
    protected char[][] grid;
    public enum GameState {PLAYING, DRAW, S_WON, O_WON};
    public GameState state;
    private int boardSize;
    private char activePlayer;
    private int sPlayerPoints;
    private int oPlayerPoints;
    private int pointsNeededToWin = 1;
    private int totalMoves;

    public SOSBoard(int size)
    {
        totalMoves = 0;
        activePlayer = 'S';
        boardSize = size;
        state = GameState.PLAYING;
        grid = new char[size][size];
        for (int i = 0; i < size; i++)
            for (int j= 0; j < size; j++)
                grid[i][j] = ' ';

    }


    public GameState updateState(){
        int maximumPossibleMoves = grid.length * grid.length;
        if (sPlayerPoints >= pointsNeededToWin)
            state = GameState.S_WON;
        else if (oPlayerPoints >= pointsNeededToWin)
            state = GameState.O_WON;
        else if (totalMoves > maximumPossibleMoves)
            state = GameState.DRAW;
        else state = GameState.PLAYING;
        return state;


    }

    public int checkSOS(int row, int col)
    {
        char turn = getActivePlayer();

        //char adjacentToken = (turn == 'S') ? 'O' : 'S';
        //int offset = (turn == 'O') ? '1' : '0';
        boolean sosFound = false;
        int offset = 0;
        int pointsThisTurn = 0;
        if (turn == 'O' && grid[row][col] != 'O' )
            pointsThisTurn += 0;
        if (turn == 'O' && grid[row][col] == 'O' && row >0) {
            pointsThisTurn += checkSOS(row-1, col);
            //pointsThisTurn += checkSOS(row+1, col);

        }
        if (turn == 'O' && grid[row][col] == 'O' && col >0) {
            pointsThisTurn += checkSOS(row, col-1);
           // pointsThisTurn += checkSOS(row, col+1);

        }


        if (turn == 'O' && grid[row][col] == 'O'  && row > 0 && col > 0 )
        {
            pointsThisTurn+= checkSOS(row+1, col+1);
        }



        // up bound check

        if (row +2 < grid.length)
        {
            if (turn == 'O')
            {

            }
            if (grid[row-offset][col] == 'S'
                    && grid[row-offset+1][col] == 'O'
                    && grid[row-offset+2][col] == 'S')
            {
                sosFound = true;
                pointsThisTurn += 1;
            }



            if (col +2 < grid.length)
                if (grid[row-offset][col] == 'S'
                        && grid[row-offset+1][col+1] == 'O'
                        && grid[row-offset+2][col+2] == 'S')
                {
                    sosFound = true;
                    pointsThisTurn += 1;
                }
                    sosFound = true;
        }
        // down bound check
        if (row -2 >= 0 )
        {
            if (grid[row-offset][col] == 'S'
                    && grid[row-offset-1][col] == 'O'
                    && grid[row-offset-2][col] == 'S')
            {
                sosFound = true;
                pointsThisTurn += 1;
            }
            if (col -2 >= 0)
                if (grid[row-offset][col] == 'S'
                        && grid[row-offset-1][col-1] == 'O'
                        && grid[row-offset-2][col-2] == 'S')
                {
                    sosFound = true;
                    pointsThisTurn += 1;
                }


        }
        // right bound check
         if (col + 2 < grid.length) {
            if (grid[row][col-offset] == 'S'
                    && grid[row][col-offset +1] == 'O'
                    && grid[row][col-offset +2] == 'S')
            {
                sosFound = true;
                pointsThisTurn += 1;
            }
        }
        // left bound check
         if (col - 2 >= 0)
        {
            if (grid[row][col-offset] == 'S'
                    && grid[row][col-offset -1] == 'O'
                    && grid[row][col-offset -2] == 'S')
            {
                sosFound = true;
                pointsThisTurn += 1;
            }


        }

        if (grid[row][col] == 'S')
            sPlayerPoints += pointsThisTurn;
        else if (grid[row][col] == 'O')
            oPlayerPoints += pointsThisTurn;

        return pointsThisTurn;
    }

    public void makeMove(int row, int column)
    {
        if ((row >=0 && row < boardSize && column >= 0 && column < boardSize) && grid[row][column] == ' ')
        {
            checkSOS(row, column);

            if (activePlayer == 'S')
            {
                grid[row][column] = 'S';
                //checkSOS(row, column, activePlayer);
                //if (CheckWin(row, column) == GameState.S_WON)
                {
                    //TODO: report win
                }

            }

            else if (activePlayer == 'O')
            {
                grid[row][column] = 'O' ;
                //checkSOS(row, column, activePlayer);
             //   if (CheckWin(row, column) == GameState.O_WON)
                {
                    //TODO: report win
                }

            }



            switchActivePlayer();
        }



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

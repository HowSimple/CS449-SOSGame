

public class SOSBoard{
    public enum Cell {EMPTY, S, O};
    //private Cell[][] grid;
    protected char[][] grid;
    public enum GameState {PLAYING, DRAW, S_WON, O_WON};
    public GameState state;
    //public Tile[][] grid;
    private int boardSize;
    private char activePlayer;


    public SOSBoard(int size)
    {
        activePlayer = 'S';
        boardSize = size;
        state = GameState.PLAYING;
        grid = new char[size][size];
        for (int i = 0; i < size; i++)
            for (int j= 0; j < size; j++)
                grid[i][j] = ' ';

    }
    public GameState CheckWin(int x, int y)
    {

        // check column
        for (int i = 0; i < grid.length; i++)
        {
            if(grid[x][i] != getActivePlayer())
                break;
            if(i == i-1 && i == grid.length)
            {
                // win
            }

        }
        // check row
        for (int i = 0; i < grid.length; i++)
        {
            if(grid[i][y] != getActivePlayer())
                break;
            if(i == i-1 && i == grid.length)
            {
                // win
            }
        }

        // check diagonal


        return GameState.PLAYING;
    }
    public void makeMove(int row, int column)
    {
        if ((row >=0 && row < boardSize && column >= 0 && column < boardSize) && grid[row][column] == ' ')
        {
            if (activePlayer == 'S')
            {
                grid[row][column] = 'S';
                if (CheckWin(row, column) == GameState.S_WON)
                {
                    //TODO: report win
                }

            }

            else if (activePlayer == 'O')
            {
                grid[row][column] = 'O' ;
                if (CheckWin(row, column) == GameState.O_WON)
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
    public void switchActivePlayer()
    {
        if (activePlayer == 'S')
            activePlayer = 'O';
        else activePlayer = 'S';

    }

}

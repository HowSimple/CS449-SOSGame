public class SOSBoard{
    public enum Cell {EMPTY, S, O};
    //private Cell[][] grid;
    private char[][] grid;
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
    public void makeMove(int row, int column)
    {
        if ((row >=0 && row < boardSize && column >= 0 && column < boardSize) && grid[row][column] == ' ')
        {
            if (activePlayer == 'S')
                grid[row][column] = 'S';
            else if (activePlayer == 'O')
                grid[row][column] = 'O' ;
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

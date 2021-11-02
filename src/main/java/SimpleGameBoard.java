import java.util.ArrayList;
import java.util.List;

public class SimpleGameBoard extends SOSBoard{

    SimpleGameBoard(int size)
    {
        super(size);

    }
    	private boolean hasWon(char turn, int row, int column) {
		char adjacentToken = (turn=='S')? 'O': 'S';

        int offset = (turn== 'S')? '2' : '1';
        boolean winStatus = false;
        if (column+offset < grid.length)
        {
            if(grid[row][0] == adjacentToken && grid[row][1] == turn && grid[row][2] == adjacentToken)
                return true;

        }
        char[] rowTokens = new char[3];
            //rowTokens = new char[]{grid[row],grid[row] };
            if (column+offset < grid.length)
            {
                rowTokens[0] = grid[row][column];

            }
            if (String.valueOf(rowTokens) == "SOS")

        if(turn =='O' && column+1 < grid.length && column-1 >= 0)
        {

        }

        char[]

        
		return (grid[row][0] == adjacentToken // 3-in-the-row
				&& grid[row][1] == adjacentToken && grid[row][2] == adjacentToken
				|| grid[0][column] == adjacentToken // 3-in-the-column
						&& grid[1][column] == adjacentToken && grid[2][column] == adjacentToken
				|| row == column // 3-in-the-diagonal
						&& grid[0][0] == adjacentToken && grid[1][1] == adjacentToken && grid[2][2] == adjacentToken
				|| row + column == 2 // 3-in-the-opposite-diagonal
						&& grid[0][2] == adjacentToken && grid[1][1] == adjacentToken && grid[2][0] == adjacentToken);
	}

    public GameState CheckWin(int row, int col)
    {

        /*
            row
            for
                if S
                    O expected
                 else S expected







         */
        List<Integer> expectedIndices = new ArrayList<Integer>();
        char move = getActivePlayer();
        char expectedTiles;
        if(getActivePlayer() == 'S')
            expectedTiles = 'O';
        else if(getActivePlayer() == 'O')
            expectedTiles = 'S';
        //bounds check
        if (row+2 < grid.length )
        {
            expectedIndices.add(Integer.valueOf(row+1), Integer.valueOf(row+2);

        }
        if (row -2 >= 0 )
        {
            expectedIndices.add(Integer.valueOf(row -1), Integer.valueOf(row-2);
        }
        System.out.print(expectedIndices);
/*
        // check column
        for (int i = 0; i < grid.length; i++)
        {
           expectedIndices = new ArrayList<Integer>();


            //bounds check before/after move
            if(move == 'O')
                expectedIndices = new Integer[]{(row - 1), (row), (row + 1)  } ;
            else
                expectedIndices = new Integer[]{(row), (row+1), (row + 2)  } ;
            if(grid[x][i] != evaluatedTile)
                break;

            else if (grid[x][i] == )

            if(i == i-1 && i == grid.length)
            {
                // win
            }
            if (expected == 'S')
                expected = 'O';

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

*/
        return GameState.PLAYING;
    }

}

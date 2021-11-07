import java.util.ArrayList;
import java.util.List;

public class SimpleGameBoard extends SOSBoard{

    SimpleGameBoard(int size)
    {
        super(size);
    }
    public void updateState(){
        int  pointsNeededToWin = 1;
        int maximumPossibleMoves = grid.length * grid.length;
        if (sPlayerPoints >= pointsNeededToWin)
            state = GameState.S_WON;
        else if (oPlayerPoints >= pointsNeededToWin)
            state = GameState.O_WON;
        else if (movesThisGame > maximumPossibleMoves)
            state = GameState.DRAW;
        else state = GameState.PLAYING;
    }
}

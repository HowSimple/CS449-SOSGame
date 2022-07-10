import java.util.ArrayList;
import java.util.List;

public class SimpleGameBoard extends SOSBoard{

    SimpleGameBoard(int size, boolean playerS_isAI, boolean playerO_isAI, boolean enableRecording)
    {

        super(size, playerS_isAI, playerO_isAI, enableRecording);
        gameModeName = "simple";
        recordGameOptions();



    }
    public void checkForGameWin()
    {
        int  pointsNeededToWin = 1;
        int maximumPossibleMoves = grid.length * grid.length;

        if (sPlayerPoints >= pointsNeededToWin)
            state = GameState.S_WON;
        else if (oPlayerPoints >= pointsNeededToWin)
            state = GameState.O_WON;
        else if (movesThisGame <maximumPossibleMoves)
            state = GameState.PLAYING;
        else state = GameState.DRAW;
    }



}

public class GeneralGameBoard extends SOSBoard {

    public GeneralGameBoard(int size, boolean playerS_isAI, boolean playerO_isAI, boolean enableRecording) {
        super(size, playerS_isAI, playerO_isAI, enableRecording);
        gameModeName = "general";
        recordGameOptions();

    }

    public void checkForGameWin()
    {
        int maximumPossibleMoves = grid.length * grid.length;

        if (movesThisGame < maximumPossibleMoves)
            state = GameState.PLAYING;
        else if (sPlayerPoints > oPlayerPoints)
            state = GameState.S_WON;
        else if (oPlayerPoints > sPlayerPoints)
            state = GameState.O_WON;
        else
            state = GameState.DRAW;
    }

}

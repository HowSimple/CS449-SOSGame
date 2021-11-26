public class GeneralGameBoard extends SOSBoard {

    public GeneralGameBoard(int size, boolean playerS_isAI, boolean playerO_isAI)
    {
        super(size, playerS_isAI, playerO_isAI);
    }

    public void updateState(){
        int maximumPossibleMoves = grid.length * grid.length;
        makeAImove();
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

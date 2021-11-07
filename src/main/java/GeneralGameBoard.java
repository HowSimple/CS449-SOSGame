public class GeneralGameBoard extends SOSBoard {

    public GeneralGameBoard(int size) {
        super(size);
    }

    public void updateState(){
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

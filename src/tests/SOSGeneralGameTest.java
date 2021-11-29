

import static org.junit.jupiter.api.Assertions.*;

        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

class SOSGeneralGameTest {
    protected SOSBoard board;
    protected int testBoardSize;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        testBoardSize = 3;
        board = new GeneralGameBoard(testBoardSize, false, false);
    }

    @AfterEach
    void tearDown() throws Exception {
    }




    @Test
    void GamePointsIncreaseAfterSOS()
    {
        board.makeMove(0,0);
        board.makeMove(1,0);
        board.makeMove(2,0);

        assertEquals(1, board.checkSOS(2, 0));
        assertEquals(board.getSplayerPoints(), 1);


    }

    @Test
    void GeneralGameWinByS()
    {
        board.makeMove(0,0);
        board.updateState();
        board.makeMove(0,1);
        board.updateState();
        board.makeMove(0,2);

        board.updateState();
        board.makeMove(1,0);
        board.updateState();
        board.makeMove(1,1);
        board.updateState();
        board.makeMove(1,2);
        board.updateState();

        board.makeMove(2,0);
        board.updateState();
        board.makeMove(2,1);
        board.updateState();
        board.makeMove(2,2);
        board.updateState();
        //board.updateState();
        assertEquals(4, board.getSplayerPoints());
        assertEquals("S WON", board.getState());
    }
    @Test
    void GeneralGameWinBy0()
    {
        board = new GeneralGameBoard(4, false, false);
        board.makeMove(1,0);
        board.updateState();
        board.makeMove(0,0);

        board.updateState();
        board.makeMove(2,0);
        board.updateState();
        board.makeMove(3,0);
        board.updateState();
        assertEquals(0, board.getSplayerPoints());
        assertEquals(0, board.getOplayerPoints());
        board.makeMove(1,1);
        board.updateState();
        board.makeMove(0,1);
        board.updateState();

        board.makeMove(2,1);
        board.updateState();
        board.makeMove(3,1);
        board.updateState();
        assertEquals(0, board.getSplayerPoints());
        assertEquals(0, board.getOplayerPoints());
        board.makeMove(1,2);
        board.updateState();
        board.makeMove(0,2);
        board.updateState();



        board.makeMove(2,3);
        board.updateState();
        board.makeMove(2,2);
        board.updateState();
        assertEquals(0, board.getSplayerPoints());
        assertEquals(0, board.getOplayerPoints());
        board.makeMove(0,3);
        board.updateState();
        board.makeMove(3,3);
        board.updateState();
        board.makeMove(1,3);
        board.updateState();
        board.makeMove(3,2);
        board.updateState();



        assertEquals(0, board.getSplayerPoints());
        assertEquals(1, board.getOplayerPoints());

        assertEquals("O WON", board.getState());
    }
    @Test
    void GeneralGameDraw()
    {
        board = new GeneralGameBoard(4,false, false);
        board.makeMove(1,0);
        board.updateState();
        board.makeMove(0,0);

        board.updateState();
        board.makeMove(2,0);
        board.updateState();
        board.makeMove(3,0);
        board.updateState();
        assertEquals(0, board.getSplayerPoints());
        assertEquals(0, board.getOplayerPoints());
        board.makeMove(1,1);
        board.updateState();
        board.makeMove(0,1);

        board.updateState();
        board.makeMove(2,1);
        board.updateState();
        board.makeMove(3,1);
        board.updateState();
        assertEquals(0, board.getSplayerPoints());
        assertEquals(0, board.getOplayerPoints());
        board.makeMove(1,2);
        board.updateState();
        board.makeMove(0,2);
        board.updateState();

        board.makeMove(2,2);
        board.updateState();
        board.makeMove(3,2);
        board.updateState();
        assertEquals(0, board.getSplayerPoints());
        assertEquals(0, board.getOplayerPoints());
        board.makeMove(1,3);
        board.updateState();
        board.makeMove(0,3);
        board.updateState();

        board.makeMove(2,3);
        board.updateState();
        board.makeMove(3,3);
        board.updateState();
        assertEquals("DRAW", board.getState());
    }

}

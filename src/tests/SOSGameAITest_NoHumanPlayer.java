import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.assertEquals;


        import static org.junit.jupiter.api.Assertions.*;

        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

public class SOSGameAITest_NoHumanPlayer {
    protected SOSBoard board;
    protected int testBoardSize;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        testBoardSize = 8;
        board = new SimpleGameBoard(testBoardSize, true, true);
    }

    @AfterEach
    void tearDown() throws Exception {
    }


    @Test
    void makeAImove_01()
    {

        board.makeMove(3, 1);
        assertEquals('S' ,board.getCell(3,1));

        assertEquals(2,board.movesThisGame);

    }
    @Test
    void gameWithTwoAI_01()
    {
        assertEquals(1, board.movesThisGame);
        board.updateState();
        assertEquals(2, board.movesThisGame);
        //assertEquals('O',board.getActivePlayer());
        board.updateState();
        assertEquals(3,board.movesThisGame);

    }


}

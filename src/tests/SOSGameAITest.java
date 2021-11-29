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

public class SOSGameAITest {
    protected SOSBoard board;
    protected int testBoardSize;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        testBoardSize = 8;
        board = new SimpleGameBoard(testBoardSize, false, true);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void initializeBoard()
    {
        assertEquals(testBoardSize, board.getBoardSize());
        assertEquals(' ' , board.getCell(0,0));
        assertEquals(' ' , board.getCell(1,1));
        assertEquals(' ' , board.getCell(2,2));
    }
    @Test
    void getCell() {
        assertEquals( ' ',board.getCell(3,1));
        board.makeMove(3, 1);
        assertEquals('S' ,board.getCell(3,1));
    }
    @Test
    void makeAImove_01()
    {

        board.makeMove(3, 1);

        board.updateState();
        assertEquals('S' ,board.getCell(3,1));
        //assertEquals('O',board.getActivePlayer());

        assertEquals(2,board.movesThisGame);

    }
    @Test
    void makeAImove_02()
    {
        board = new SimpleGameBoard(testBoardSize, false, true);
        board.updateState();
        assertEquals('S', board.getActivePlayer());
        //board.makeAImove();
        board.makeMove(3, 1);

        board.updateState();
        assertEquals('O' ,board.getCell(3,1));
        //assertEquals('O',board.getActivePlayer());

        assertEquals(3,board.movesThisGame);

    }


}

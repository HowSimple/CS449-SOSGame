import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SOSBoardTest {
    protected SOSBoard board;
    protected int testBoardSize;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        board = new SOSBoard(testBoardSize);
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
    void makeMove()
    {

        board.makeMove(3, 1);
        assertEquals('S' ,board.getCell(3,1));
    }
    @Test
    void switchActivePlayer() {
        assertEquals('S', board.getActivePlayer());
        board.switchActivePlayer();
        assertEquals('O', board.getActivePlayer());
    }
    @Test
    void getActivePlayer() {
        assertEquals('S', board.getActivePlayer());
        board.switchActivePlayer();
        assertEquals('O', board.getActivePlayer());
    }

}

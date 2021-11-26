import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SOSSimpleBoardTest {
    protected SOSBoard board;
    protected int testBoardSize;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        testBoardSize = 8;
        board = new SimpleGameBoard(testBoardSize, false, false);
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

    }
    @Test
    void checkMoveWithNoSOS_1()
    {

        board.makeMove(2,2);
        assertEquals(0, board.checkSOS(2, 2));
        board.makeMove(2,3);
        assertEquals(0, board.checkSOS(2, 3));
        board.makeMove(3,3);
        assertEquals(0, board.checkSOS(3, 3));


    }
    @Test
    void checkHorizontalSOS_1_innerBoard()
    {
        board.makeMove(2,2);

        board.makeMove(3,2);
        board.makeMove(4,2);

        assertEquals(1, board.checkSOS(4, 2));


    }




    @Test
    void checkHorizontalSOS_2_edgeOfBoard1()
    {
        board.makeMove(0,0);
        board.makeMove(1,0);
        board.makeMove(2,0);

        assertEquals(1, board.checkSOS(2, 0));


    }


    @Test
    void checkVerticalSOS_1_innerBoard()
    {
        board.makeMove(2,2);
        board.makeMove(2,3);
        board.makeMove(2,4);


        assertEquals(1, board.checkSOS(2, 4));


    }

    @Test
    void checkVerticalSOS_2_edgeOfBoard()
    {
        board.makeMove(0,0);
        board.makeMove(0,1);
        board.makeMove(0,2);


        assertEquals(1, board.checkSOS(0, 2));


    }
    @Test
    void checkDiagonalSOS_1_edgeOfBoard()
    {
        board.makeMove(0,0);
        board.makeMove(1,1);
        board.makeMove(2,2);

        assertEquals(1, board.checkSOS(2, 2));


    }

    @Test
    void checkDiagonalSOS_2_innerBoard()
    {
        board.makeMove(2,2);
        board.makeMove(3,3);
        board.makeMove(4,4);

        assertEquals(1, board.checkSOS(4, 4));


    }



}

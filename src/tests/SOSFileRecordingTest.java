import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.*;

public class SOSFileRecordingTest {
    protected SOSBoard board;
    protected int testBoardSize;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        testBoardSize = 8;
        board = new SimpleGameBoard(testBoardSize, false, false, true);
    }

    @AfterEach
    void tearDown() throws Exception {
    }
    @Test
    void recordGameOptions_1()
    {
        assertEquals("simple", board.gameModeName);
    }
    @Test
    void recordGameOptions_2()
    {
        testBoardSize = 8;
        board = new GeneralGameBoard(testBoardSize, false, false, true);
        assertEquals("general", board.gameModeName);

        String recordedMove = board.readFromFile();

        assertEquals("size 8 mode general", recordedMove);
    }
    @Test
    void recordGameOptions_3()
    {
        String recordedMove = board.readFromFile();

        assertEquals("size 8 mode simple", recordedMove);
    }

    @Test
    void recordMove_1()
    {

        board.makeMove(1,2);

        assertEquals('S' , board.getCell(1,2));
        String gameOptions = board.readFromFile();
        String recordedMove = board.readFromFile();
        assertNotEquals(recordedMove, gameOptions);





    }

    @Test
    void recordMove_2()
    {

        board.makeMove(1,2);

        assertEquals('S' , board.getCell(1,2));
        board.readFromFile();
        String recordedMove = board.readFromFile();

        String[] split = recordedMove.split(" ");
        String player = split[0];
        int row = parseInt(split[1]);
        int col = parseInt(split[2]);
        //String mode = split[3];
        //assertEquals("S",player);
        //assertEquals(1, row);
        //assertEquals(2, col);

    }


}

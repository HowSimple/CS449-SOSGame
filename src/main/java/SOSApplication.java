import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class SOSApplication extends Application {

    int boardSize;
    String gameLogPath = "Recorded game.txt";
    SOSBoard game;
    Tile[][] tiles;
    GridPane boardGUI;
    BorderPane mainGUI;
    HBox modeControlsPane;
    VBox blueControlsPane, redControlsPane;
    Button newGame, replayGame;
    Color sPlayerColor, oPlayerColor;
    ToggleGroup redLetter, blueLetter, bluePlayer, redPlayer, mode;
    RadioButton bluePlayerS, bluePlayerO;
    RadioButton redPlayerS, redPlayerO ;
    RadioButton bluePlayerHuman, redPlayerHuman, bluePlayerComputer, redPlayerComputer;
    RadioButton simpleGameButton, generalGameButton;
    CheckBox recordGame;
    HBox gameStatusPane;
    Text gameStatus, bluePoints, redPoints, prompt;
    ComboBox<String> boardSizeSelect;


    private void newGameOptions(Stage stage)
    {

        newGame = new Button("New Game");
        newGame.disableProperty().bind(boardSizeSelect.valueProperty().isNull()
                .or(blueLetter.selectedToggleProperty().isNull())
                .or(redLetter.selectedToggleProperty().isNull() )
                .or(blueLetter.selectedToggleProperty().isNull())
                .or(redLetter.selectedToggleProperty().isNull() )
                .or(redPlayer.selectedToggleProperty().isNull() )
                .or(bluePlayer.selectedToggleProperty().isNull() )

        );
        replayGame = new Button("Replay");
        replayGame.setOnAction(e-> {
            replayGame();
        });
        newGame.setOnAction(e -> {
            // action starts a new game using board-size UI selection
            boardSize= Integer.parseInt (valueOf(boardSizeSelect.getValue().charAt(0)));
            initializeBoard(boardSize);
    });

        gameStatusPane.getChildren().addAll(newGame, replayGame,gameStatus);
    }
    public void replayGame()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(gameLogPath))) {
            String line = br.readLine();
            String[] split = line.split(" ");
            int boardSize = parseInt(split[1]);
            String mode = split[3];

            if (mode == "simple")
                game = new SimpleGameBoard(boardSize, false, false,false);
            else
                game = new GeneralGameBoard(boardSize, false, false, false);



            while ((line = br.readLine()) != null) {
                split = line.split(" ");
                int row = parseInt(split[1]);
                int col = parseInt(split[2]);

                game.makeMove(row,col);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void initializeControls()
    {
        bluePlayer = new ToggleGroup();
        redPlayer = new ToggleGroup();
        redPlayerComputer = new RadioButton("Computer");
        redPlayerHuman = new RadioButton("Human");
        bluePlayerHuman = new RadioButton("Human");
        bluePlayerHuman.setToggleGroup(bluePlayer);
        bluePlayerComputer = new RadioButton("Computer");
        bluePlayerComputer.setToggleGroup(bluePlayer);
        redPlayerComputer.setToggleGroup(redPlayer);
        redPlayerHuman.setToggleGroup(redPlayer);

        boardGUI = new GridPane();
        boardGUI.setPrefSize(755, 755);
        blueLetter = new ToggleGroup();
        bluePlayerS = new RadioButton("S");
        bluePlayerS.setToggleGroup(blueLetter);
        bluePlayerO = new RadioButton("O");
        bluePlayerO.setToggleGroup(blueLetter);
        blueControlsPane = new VBox();
        blueControlsPane.getChildren().addAll( new Text("Blue player"),bluePlayerHuman, bluePlayerS, bluePlayerO, bluePlayerComputer);

        redLetter = new ToggleGroup();

        redPlayerS = new RadioButton("S");
        redPlayerS.setToggleGroup(redLetter);
        redPlayerO = new RadioButton("O");
        redPlayerO.setToggleGroup(redLetter);
        redControlsPane = new VBox();
        redControlsPane.getChildren().addAll( new Text("Red player"), redPlayerHuman,redPlayerS, redPlayerO,redPlayerComputer);
        mode = new ToggleGroup();
        simpleGameButton = new RadioButton("Simple game");
        simpleGameButton.setToggleGroup(mode);
        generalGameButton = new RadioButton("General game");
        generalGameButton.setToggleGroup(mode);
        generalGameButton.setSelected(true);
        modeControlsPane = new HBox();
        modeControlsPane.getChildren().addAll( new Text("SOS"),simpleGameButton,generalGameButton);

        gameStatusPane = new HBox();
        gameStatusPane.setSpacing(30);
        gameStatus = new Text("Current Turn: ");
        boardSizeSelect = new ComboBox<String>();
        recordGame = new CheckBox("Record game");

        int minimumBoardSize = 3;
        int maximumBoardSize = 20;
        for (int i = minimumBoardSize; i < maximumBoardSize; i++)
        {
            String label = i +"x" + i;

            boardSizeSelect.getItems().add(label);
        }
        bluePoints = new Text();
        redPoints = new Text();
        prompt = new Text();
        gameStatusPane.getChildren().add(prompt);
        gameStatusPane.getChildren().remove(gameStatusPane.lookup("Click") );
        gameStatusPane.getChildren().addAll(recordGame, boardSizeSelect);

        mainGUI = new BorderPane();
        boardGUI.setAlignment(Pos.CENTER);
        mainGUI.setCenter(boardGUI);
        modeControlsPane.setAlignment(Pos.CENTER);
        blueControlsPane.setAlignment(Pos.CENTER);
        redControlsPane.setAlignment(Pos.CENTER);
        gameStatusPane.setAlignment(Pos.CENTER);

        mainGUI.setLeft(blueControlsPane);
        mainGUI.setRight(redControlsPane);
        mainGUI.setTop(modeControlsPane);
        mainGUI.setBottom(gameStatusPane);

    }
    private void initializeBoard(int board_size)
    {
        boardGUI.getChildren().removeAll();
        if (bluePlayerS.isSelected())
        {
            sPlayerColor = Color.BLUE;
            oPlayerColor = Color.RED;
        }
        else {
            sPlayerColor = Color.RED;
            oPlayerColor = Color.BLUE;
        }
        boolean sPlayerIsComputer = false, oPlayerIsComputer = false;
        if (redPlayerComputer.isSelected())
            if (sPlayerColor == Color.RED)
                sPlayerIsComputer = true;
            else
                oPlayerIsComputer = true;
        if (bluePlayerComputer.isSelected())
            if (sPlayerColor == Color.BLUE)
                sPlayerIsComputer = true;
            else oPlayerIsComputer = true;
        String mode;


                if (simpleGameButton.isSelected()){
                    game = new SimpleGameBoard(board_size, sPlayerIsComputer, oPlayerIsComputer, recordGame.isSelected() );
                    mode = "simple";
                }


        else
                {
                    game = new GeneralGameBoard(board_size, sPlayerIsComputer, oPlayerIsComputer, recordGame.isSelected() );
                    mode = "general";
                }
        if (recordGame.isSelected())
            try {
                FileWriter fw = null;
                fw = new FileWriter("Recorded game.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("size " +board_size+ " mode "+ mode);
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        tiles = new Tile[board_size][board_size];
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                //Tile t = new Tile()
                tiles[i][j] = new Tile();

                boardGUI.add(tiles[i][j], j, i);


            }
        }
        if (sPlayerIsComputer && oPlayerIsComputer)
            prompt.setText("Click the board to get the next computer move!");
        else
            prompt.setText("");
        game.updateState();
        updateBoard();

    }

    public class Tile extends StackPane {
        Text label;
        Rectangle border;
        public Tile()
        {
            super();
            setOnMouseClicked((event) -> handleClick(event));
            label = new Text( " ");
            label.setFont(Font.font(40));
            label.setFill(Color.BLACK);
            border = new Rectangle(50, 50);
            border.setFill(Color.WHITE);
            border.setStroke(Color.BLACK);
            getChildren().addAll(border, label);
            addEventFilter(MouseEvent.MOUSE_CLICKED, event -> handleClick(event));
        }
        public void setTile(String text)
        {
            label.setText(valueOf(text));
        }
        public String getTile()
        {
            return label.getText();
        }
        public void setColor(Color color)
        {
            border.setFill(color);
        }
    }

    public void updateBoard() {

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                tiles[i][j].setTile(valueOf(game.getCell(i,j)));
                if( game.getCell(i,j) == 'S')
                    tiles[i][j].setColor(sPlayerColor);
                else if (game.getCell(i,j) == 'O')
                    tiles[i][j].setColor(oPlayerColor);
            }

        }
    }

    public void clearBoard() {

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                tiles[i][j].setTile(" ");
            }

        }
    }

    public void handleClick(MouseEvent event) {

            if (game.getState() == "Playing")
            {
                Tile t = (Tile) event.getSource();
                int row = GridPane.getRowIndex(t);
                int col = GridPane.getColumnIndex(t);
                game.makeMove(row,col );
                game.updateState();

                t.setTile(valueOf(game.getCell(row, col)));
                if (game.getCell(row,col) == 'S')
                    t.setColor(sPlayerColor);
                else if (game.getCell(row,col) == 'O')
                    t.setColor(oPlayerColor);
                updateBoard();
                tiles[row][col].setTile(valueOf(game.getCell(row,col)));
                if (oPlayerColor == Color.BLUE)
                    bluePoints.setText(valueOf(game.getOplayerPoints()));
                else if (sPlayerColor == Color.BLUE)
                    bluePoints.setText(valueOf(game.getSplayerPoints()));
                if (oPlayerColor == Color.RED)
                    redPoints.setText(valueOf(game.getOplayerPoints()));
                else if (sPlayerColor == Color.RED)
                    redPoints.setText(valueOf(game.getSplayerPoints()));

                gameStatus.setText(game.getState());

            }



    }

    public void startGame(Stage stage) throws IOException {
        game = new SOSBoard(boardSize, bluePlayerComputer.isSelected(), redPlayerComputer.isSelected(), recordGame.isSelected());

        stage.close();
        //start(new Stage());
    }

    @Override
    public void start(Stage stage) throws IOException {
        //need to add separate screen for player to choose board size
        int boardSize = 9;
        //initializeGame(boardSize);
        initializeControls();
        newGameOptions(stage);


        Scene scene = new Scene(mainGUI , 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setWidth(700);
        stage.setHeight(700);




        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}



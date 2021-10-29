import javafx.application.Application;
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

import java.io.IOException;

public class SOSGameGUI extends Application {
    SOSBoard gameboard;
    Tile[][] tiles;
    GridPane boardGUI;
    BorderPane mainGUI;
    HBox modeControlsPane;
    VBox blueControlsPane, redControlsPane;
    Button newGame;
    RadioButton bluePlayerS, bluePlayerO;
    RadioButton redPlayerS, redPlayerO ;
    RadioButton simpleGameButton, generalGameButton;
    HBox gameStatusPane;
    Text gameStatus;
    ComboBox<String> boardSizeSelect;

    private void newGameOptions()
    {

         boardSizeSelect = new ComboBox<String>();
        int minimumBoardSize = 3;
        int maximumBoardSize = 20;

      /*
        EventHandler<ActionEvent> startButtonEvent = new EventHandler<ActionEvent>() {
          @Override
            public void handle(ActionEvent actionEvent) {
                //selected.setText(boardSizeSelect.getValue() + " selected");
                int boardSize;
                try {
                    boardSize = Integer.parseInt((String) boardSizeSelect.getValue());
                } catch (NumberFormatException e)
                {
                    boardSize = 0;
                }

                System.out.print(boardSize);

            }
        };
        newGame.setOnAction(startButtonEvent);

        //newGame.setOnAction(startButtonEvent);
        */
        for (int i = 3; i < 20; i++)
        {
            String label = i +"x" + i;
            boardSizeSelect.getItems().add(label);
        }

        boardSizeSelect.getSelectionModel().select("9x9");


    }
    private void initializeControls()
    {
        blueControlsPane = new VBox();
        bluePlayerS = new RadioButton("S");
        bluePlayerO = new RadioButton("O");
        ToggleGroup blue = new ToggleGroup();
        bluePlayerS.setToggleGroup(blue);
        bluePlayerO.setToggleGroup(blue);
        Text blueLabel = new Text("Blue player");
        blueControlsPane.getChildren().addAll(blueLabel, bluePlayerS, bluePlayerO);

        redControlsPane = new VBox();
        redPlayerS = new RadioButton("S");
        redPlayerO = new RadioButton("O");
        ToggleGroup red = new ToggleGroup();
        redPlayerS.setToggleGroup(red);
        redPlayerO.setToggleGroup(red);
        Text redLabel = new Text("Red player");
        redControlsPane.getChildren().addAll(redLabel, redPlayerS, redPlayerO);

        modeControlsPane = new HBox();
        Text gameLabel = new Text("SOS");
        simpleGameButton = new RadioButton("Simple game");
        generalGameButton = new RadioButton("General game");
        ToggleGroup mode = new ToggleGroup();
        simpleGameButton.setToggleGroup(mode);
        generalGameButton.setToggleGroup(mode);
        modeControlsPane.getChildren().addAll(gameLabel,simpleGameButton,generalGameButton);

        gameStatusPane = new HBox();
        gameStatus = new Text("Current Turn: ");


        gameStatusPane.setSpacing(30);
        gameStatusPane.getChildren().addAll(newGame, gameStatus);
        gameStatusPane.getChildren().add(boardSizeSelect);
        boardGUI = new GridPane();
        boardGUI.setPrefSize(755, 755);
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
        gameboard = new SOSBoard(board_size);
        boardGUI = new GridPane();
       // if (boardGUI != null)
        //    boardGUI.getChildren().clear();

        //SOSGame(board_size);


        tiles = new Tile[board_size][board_size];

        boardGUI.setPrefSize(755, 755);

        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                //Tile t = new Tile()
                tiles[i][j] = new Tile();

                boardGUI.add(tiles[i][j], j, i);


            }
        }
        updateBoard();
    }
    private void initializeGame()
    {



       //initializeBoard(board_size);
        newGameOptions();
        initializeControls();



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

    public class Tile extends StackPane {
        Text label;
        Rectangle border;
        public Tile()
        {
            super();

            setOnMouseClicked((event) -> handleClick(event));
            border = new Rectangle(50, 50);
            //border = new Rectangle(50, 50);
            border.setFill(Color.WHITE);
            border.setStroke(Color.BLACK);
            label = new Text("X");
            label.setFont(Font.font(40));
            label.setFill(Color.BLUE);
            getChildren().addAll(border, label);
            addEventFilter(MouseEvent.MOUSE_CLICKED, event -> handleClick(event));
        }
        public void setTile(String text)
        {
            label.setText(String.valueOf(text));
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
                tiles[i][j].setTile(String.valueOf(gameboard.getCell(i,j)));
            }

        }
    }
    public void handleClick(MouseEvent event) {


        Tile t = (Tile) event.getSource();

        int row = GridPane.getRowIndex(t);
        int col = GridPane.getColumnIndex(t);
        gameboard.makeMove(row,col );
        t.setColor(Color.GREY);
        t.setTile(String.valueOf(gameboard.getCell(row, col)));
        updateBoard();

    }

    public void prompt()
    {

        newGameOptions();
        initializeControls();
    }
    public void startGame()
    {


    }
    @Override
    public void start(Stage stage ) throws IOException {
        //need to add separate screen for player to choose board size
        newGame = new Button("New Game");
        newGame.setOnAction(e -> {
            //String label = boardSizeSelect.getSelectionModel().getSelectedItem();
            String sizeSelection = boardSizeSelect.getValue();
            int boardSize= Integer.parseInt (String.valueOf(boardSizeSelect.getValue().charAt(0)));
            //start(stage);


            try {
                restart(stage, boardSize);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            //newGame.setDisable(true);

        });
        prompt();
        int boardSize = 9;

        //initializeGame(boardSize);

        Scene scene = new Scene(mainGUI , 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setWidth(700);
        stage.setHeight(700);




        stage.show();
    }
    public void resetGame()
    {


    }
    public void restart(Stage stage, int boardSize) throws IOException {
       //stage.close();
        //initializeGame(boardSize);
        newGameOptions();
        initializeBoard(boardSize);
        initializeControls();
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


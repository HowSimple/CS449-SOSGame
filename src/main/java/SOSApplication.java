import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SOSApplication extends Application {
    SOSBoard game;
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

        newGame = new Button("New Game");
        gameStatusPane.setSpacing(30);
        gameStatusPane.getChildren().addAll(newGame,gameStatus);



        //controlsPane.getChildren().add(newGame);
        // event handlers







    }
    private void initializeBoard(int board_size)
    {
        boardGUI = new GridPane();
        //SOSGame(board_size);
        boardGUI.setPrefSize(755, 755);
        tiles = new Tile[board_size][board_size];
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                //Tile t = new Tile()
                tiles[i][j] = new Tile();

                //label.addEventHandler(MouseEvent.ANY, e -> tile.fireEvent(e));
                //label.setFont(Font.font(40));
                //gameBoard.add(new StackPane(tile, label), j, i);
                boardGUI.add(tiles[i][j], j, i);
                //tile.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> handleClick(event));
                //tile.setOnMouseClicked((event) -> handleClick(event));
                // tile.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> handleClick(event));


            }
        }
        updateBoard();
    }
    private void initializeGame(int board_size)
    {
        game = new SOSBoard(board_size);
        initializeBoard(board_size);
        initializeControls();


        mainGUI = new BorderPane();

        boardGUI.setAlignment(Pos.CENTER);
        mainGUI.setCenter(boardGUI);
        modeControlsPane.setAlignment(Pos.CENTER);
        blueControlsPane.setAlignment(Pos.CENTER);
        redControlsPane.setAlignment(Pos.CENTER);
        gameStatusPane.setAlignment(Pos.CENTER);
        //BorderPane.setAlignment(blueControlsPane, Pos.BASELINE_CENTER);
        //BorderPane.setAlignment(modeControlsPane, Pos.BASELINE_CENTER);
        //BorderPane.setAlignment(redControlsPane, Pos.BASELINE_CENTER);
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
                tiles[i][j].setTile(String.valueOf(game.getCell(i,j)));
            }

        }
    }
    public void handleClick(MouseEvent event) {


        Tile t = (Tile) event.getSource();

        int row = GridPane.getRowIndex(t);
        int col = GridPane.getColumnIndex(t);
        game.makeMove(row,col );
        t.setColor(Color.GREY);
        t.setTile(String.valueOf(game.getCell(row, col)));
        updateBoard();

    }


    @Override
    public void start(Stage stage) throws IOException {
        //need to add separate screen for player to choose board size
        int boardSize = 9;


        initializeGame(boardSize);
        //FXMLLoader fxmlLoader = new FXMLLoader(SOSGameApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(mainGUI , 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setWidth(700);
        stage.setHeight(700);

        //TilePane grid = new TilePane();
        //grid.setPadding()



        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


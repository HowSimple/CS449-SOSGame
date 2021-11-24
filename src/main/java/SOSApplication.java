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

import java.io.Console;
import java.io.IOException;

import static java.lang.String.valueOf;

public class SOSApplication extends Application {

    int boardSize;
    SOSBoard game;
    Tile[][] tiles;
    GridPane boardGUI;
    BorderPane mainGUI;
    HBox modeControlsPane;
    VBox blueControlsPane, redControlsPane;
    Button newGame;
    Color sPlayerColor, oPlayerColor;
    ToggleGroup red, blue, mode;
    RadioButton bluePlayerS, bluePlayerO;
    RadioButton redPlayerS, redPlayerO ;
    RadioButton simpleGameButton, generalGameButton;
    HBox gameStatusPane;
    Text gameStatus, bluePoints, redPoints;
    ComboBox<String> boardSizeSelect;


    private void newGameOptions(Stage stage)
    {
        //initializeControls();




        newGame = new Button("New Game");
        newGame.disableProperty().bind(boardSizeSelect.valueProperty().isNull()
                .or(blue.selectedToggleProperty().isNull())
                .or(red.selectedToggleProperty().isNull() )

        );
        newGame.setOnAction(e -> {

                boardSize= Integer.parseInt (valueOf(boardSizeSelect.getValue().charAt(0)));
               //boardSize =3;

            initializeBoard(boardSize);
            clearBoard();
               // newGame.setDisable(true);






    });
        gameStatusPane.getChildren().addAll(newGame, gameStatus);
    }
    private void initializeControls()
    {



        boardGUI = new GridPane();
        boardGUI.setPrefSize(755, 755);
        blueControlsPane = new VBox();
        bluePlayerS = new RadioButton("S");
        bluePlayerO = new RadioButton("O");
        blue = new ToggleGroup();
        bluePlayerS.setToggleGroup(blue);
        bluePlayerO.setToggleGroup(blue);
        Text blueLabel = new Text("Blue player");
        blueControlsPane.getChildren().addAll(blueLabel, bluePlayerS, bluePlayerO);

        redControlsPane = new VBox();
        redPlayerS = new RadioButton("S");
        redPlayerO = new RadioButton("O");
        red = new ToggleGroup();
        redPlayerS.setToggleGroup(red);
        redPlayerO.setToggleGroup(red);
        Text redLabel = new Text("Red player");
        redControlsPane.getChildren().addAll(redLabel, redPlayerS, redPlayerO);




        modeControlsPane = new HBox();
        Text gameLabel = new Text("SOS");
        simpleGameButton = new RadioButton("Simple game");
        simpleGameButton.setSelected(true);
        generalGameButton = new RadioButton("General game");
        ToggleGroup mode = new ToggleGroup();
        simpleGameButton.setToggleGroup(mode);
        generalGameButton.setToggleGroup(mode);
        modeControlsPane.getChildren().addAll(gameLabel,simpleGameButton,generalGameButton);

        gameStatusPane = new HBox();
        gameStatus = new Text("Current Turn: ");

        //newGame = new Button("New Game");
        gameStatusPane.setSpacing(30);


        boardSizeSelect = new ComboBox<String>();
        int minimumBoardSize = 3;
        int maximumBoardSize = 20;
        for (int i = minimumBoardSize; i < maximumBoardSize; i++)
        {
            String label = i +"x" + i;

            boardSizeSelect.getItems().add(label);
        }
        bluePoints = new Text();
        redPoints = new Text();

        gameStatusPane.getChildren().addAll(boardSizeSelect);

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

        //activePlayerColor = Color.BLUE;
        if (bluePlayerS.isSelected())
            sPlayerColor = Color.BLUE;
        else if( redPlayerS.isSelected())
            sPlayerColor = Color.RED;
        if (bluePlayerO.isSelected() && sPlayerColor != Color.BLUE)
            oPlayerColor = Color.BLUE;

        else if (redPlayerO.isSelected() && sPlayerColor != Color.RED)
             oPlayerColor = Color.RED;


        if (simpleGameButton.isSelected())
        {
           game = new SimpleGameBoard(board_size);

        }
        else if (generalGameButton.isSelected())
        {
           game = new GeneralGameBoard(board_size);
        }


        tiles = new Tile[board_size][board_size];
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                //Tile t = new Tile()
                tiles[i][j] = new Tile();

                boardGUI.add(tiles[i][j], j, i);


            }
        }
        updateBoard();
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
            label.setFill(Color.BLACK);
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


        //if (game.getState() != "O WON" && game.getState() != "S WON"&& game.getState() != "DRAW" )
        {
            Tile t = (Tile) event.getSource();
            int row = GridPane.getRowIndex(t);
            int col = GridPane.getColumnIndex(t);
            game.makeMove(row,col );

            // if(activePlayerColor == Color.BLUE)
            //     activePlayerColor = Color.RED;
            // else activePlayerColor = Color.BLUE;
            t.setTile(valueOf(game.getCell(row, col)));
            if (game.getCell(row,col) == 'S')
                t.setColor(sPlayerColor);
            else if (game.getCell(row,col) == 'O')
                t.setColor(oPlayerColor);
            //updateBoard();
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
        game = new SOSBoard(boardSize);

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



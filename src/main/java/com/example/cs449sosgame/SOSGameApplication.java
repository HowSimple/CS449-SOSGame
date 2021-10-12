package com.example.cs449sosgame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SOSGameApplication extends Application {
    SOSGame game;
    Tile[][] board;
    public Parent createBoard(int board_size) {

        GridPane gameBoard = new GridPane();
        SOSGame(board_size);
        gameBoard.setPrefSize(755, 755);
        board = new Tile[board_size][board_size];
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                //Tile t = new Tile()
                board[i][j] = new Tile();

                //label.addEventHandler(MouseEvent.ANY, e -> tile.fireEvent(e));
                //label.setFont(Font.font(40));
                //gameBoard.add(new StackPane(tile, label), j, i);
                gameBoard.add(new Tile(), j, i);
                //tile.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> handleClick(event));
                //tile.setOnMouseClicked((event) -> handleClick(event));
               // tile.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> handleClick(event));


            }
        }
        return gameBoard;
    }
    private class Tile extends StackPane {
        Text label;
        Rectangle border;
       public  Tile()
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
       public void setText(String text)
       {
           label.setText(text);
       }
       public String getText()
       {
           return label.getText();
       }
       public void setColor(Color color)
       {
           border.setFill(color);
       }


    }
    public void handleClick(MouseEvent event) {


        Tile t = (Tile) event.getSource();
        t.setColor(Color.BLACK);
        t.setText("O");
           // game.switchActivePlayer();
        // update backend grid




    }
    private void handleMove(Text text) {


    }



    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChooseGameOptions.fxml"));
        //comboBox.getItems().removeAll(comboBox.getItems());
        //comboBox.getItems().addAll("3x3", "4x4", "5x5", "6x6", "7x7", "8x8");
        //comboBox.getSelectionModel().select("6x6");



        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(createBoard(8) , 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(550);

        //TilePane grid = new TilePane();
        //grid.setPadding()



        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
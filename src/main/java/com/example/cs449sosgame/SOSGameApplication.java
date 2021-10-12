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

    public Parent createBoard(int board_size) {

        GridPane gameBoard = new GridPane();
        gameBoard.setPrefSize(755, 755);

        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {

                Rectangle tile = new Rectangle(50, 50);
                tile.setFill(Color.WHITE);
                tile.setStroke(Color.BLACK);

                Text label = new Text();
                label.addEventHandler(MouseEvent.ANY, e -> tile.fireEvent(e));
                label.setFont(Font.font(40));
                gameBoard.add(new StackPane(tile, label), j, i);
                tile.setOnMouseClicked((event) -> handleClick(event));

            }
        }
        return gameBoard;
    }
    public void handleClick(MouseEvent event) {

        Rectangle t = (Rectangle) event.getSource();
        t.setFill(Color.BLACK);

        game.switchActivePlayer();
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
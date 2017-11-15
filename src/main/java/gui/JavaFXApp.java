package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    private static final int MIN_WIDTH = 320;
    private static final int MIN_HEIGHT = 480;
    private static final int BUTTON_SPACING = 12;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Blackjack");

        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
        Scene scene = buildScene();
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private static Scene buildScene() {
        final FieldCanvas canvas = new FieldCanvas(MIN_WIDTH, MIN_HEIGHT);

        final Button takeButton = new Button("TAKE");
        takeButton.setDisable(true);
        takeButton.setOnAction(e -> canvas.playerTurnTake());

        final Button passButton = new Button("PASS");
        passButton.setDisable(true);
        takeButton.setOnAction(e -> canvas.playerTurnPass());

        final Button connectToServerButton = new Button("CONNECT");

        HBox buttonHorizontalBox = new HBox(BUTTON_SPACING, takeButton, passButton, connectToServerButton);
        buttonHorizontalBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(takeButton, Priority.ALWAYS);
        HBox.setHgrow(passButton, Priority.ALWAYS);
        HBox.setHgrow(connectToServerButton, Priority.ALWAYS);

        takeButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        passButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        connectToServerButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(buttonHorizontalBox);
        borderPane.setCenter(canvas);

        return new Scene(borderPane, MIN_WIDTH, MIN_HEIGHT);
    }
}
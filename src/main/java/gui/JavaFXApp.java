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

    private Stage primaryStage;
    private BorderPane rootLayout;
    private FieldCanvas field;
    private Button takeButton, passButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Blackjack");

        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);

        field = new FieldCanvas(MIN_WIDTH, MIN_HEIGHT);

        takeButton = new Button("TAKE");
        takeButton.setOnAction(e -> field.playerTurnTake());
        passButton = new Button("PASS");
        takeButton.setOnAction(e -> field.playerTurnPass());

        HBox buttonHorizontalBox = new HBox(10, takeButton, passButton);
        buttonHorizontalBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(takeButton, Priority.ALWAYS);
        HBox.setHgrow(passButton, Priority.ALWAYS);
        takeButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        passButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        rootLayout = new BorderPane();
        rootLayout.setTop(buttonHorizontalBox);
        rootLayout.setCenter(field);

        Scene scene = new Scene(rootLayout, MIN_WIDTH, MIN_HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}

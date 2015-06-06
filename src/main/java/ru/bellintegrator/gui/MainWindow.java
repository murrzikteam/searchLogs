package ru.bellintegrator.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by DOrdynskiy on 05.06.2015.
 */
public class MainWindow extends Application {
    Stage window;
    Scene scene;

    public void init(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Label label = new Label("My Label");
        Button button = new Button("Close");

        button.setOnAction(e -> {
            String result = Properties.display("Title of the window", "Message");
            System.out.println(result);
        });

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, button);
        scene = new Scene(layout, 300, 250);
        scene.getStylesheets().add("css/Evil.css");
        window.setScene(scene);
        window.setTitle("Title here");
        window.show();
    }
}

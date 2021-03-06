package ru.bellintegrator.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.bellintegrator.services.ConfiguratorBean;
import ru.bellintegrator.services.LogParser;

/**
 * Created by DOrdynskiy on 05.06.2015.
 */
public class MainWindow extends Application {
    Stage window;
    Scene scene;
    ConfiguratorBean config = new ConfiguratorBean();

    public void init(String [] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Button searchButton = new Button("Искать");
        searchButton.setDisable(true);
        Button propertiesButton = new Button("Параметры поиска");

        searchButton.setOnAction(e -> {
            LogParser.test(config);
        });

        propertiesButton.setOnAction(e -> {
            Properties.display(config);
            if ((config.getLogsFilePath() != null && !config.getLogsFilePath().isEmpty()) &&
                    (config.getResultsFilePath() != null && !config.getResultsFilePath().isEmpty()) &&
                    (config.getSearchKeywords() != null &&  !config.getSearchKeywords().isEmpty())) {
                searchButton.setDisable(false);
            } else {
                searchButton.setDisable(true);
            }
        });

        HBox layout = new HBox(2);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(searchButton, propertiesButton);
        scene = new Scene(layout, 350, 100);
        scene.getStylesheets().add("css/Evil.css");
        window.setMinWidth(350);
        window.setMaxWidth(100);
        window.setScene(scene);
        window.setTitle("Парсер логов");
        window.show();
    }
}

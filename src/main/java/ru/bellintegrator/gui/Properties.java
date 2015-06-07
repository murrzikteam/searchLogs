package ru.bellintegrator.gui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.bellintegrator.services.ConfiguratorBean;

/**
 * Created by DOrdynskiy on 05.06.2015.
 */
public class Properties {
    public static void display(ConfiguratorBean createdConfig) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Параметры поиска");

        // Пути к файлам логов и результатов поиска
        Label logPathLabel = new Label("Лог файл");
        Label resultsPathLabel = new Label("Файл с результатами поиска");
        VBox pathLabels = new VBox(2);
        pathLabels.getChildren().addAll(logPathLabel, resultsPathLabel);

        TextField logPathField = new TextField();
        logPathField.setPromptText("Введите путь к файлу с логами");
        TextField resultsPathField = new TextField();
        resultsPathField.setPromptText("Введите путь к файлу с результатами поиска");
        VBox pathFields = new VBox(2);
        pathFields.getChildren().addAll(logPathField, resultsPathField);

        Button openLogsButton = new Button("Найти");
        Button openResultsButton = new Button("Найти");
        VBox pathButtons = new VBox(2);
        pathButtons.getChildren().addAll(openLogsButton, openResultsButton);

        BorderPane pathPane = new BorderPane();
        pathPane.setLeft(pathLabels);
        pathPane.setCenter(pathFields);
        pathPane.setRight(pathButtons);

        // Ключевые слова для включения или исключения логов из результатов поиска
        Label keyWordsLabel = new Label("Ключевые слова для поиска логов:");
        ListView keyWordView = new ListView();
        keyWordView.setMinHeight(50);
        keyWordView.setEditable(true);
        VBox keyWordsBox = new VBox(2);
        keyWordsBox.getChildren().addAll(keyWordsLabel, keyWordView);

        Label badWordsLabel = new Label("Ключевые слова для исключения логов:");
        ListView badWordsView = new ListView();
        keyWordView.setEditable(true);
        VBox badWordsBox = new VBox(2);
        badWordsBox.getChildren().addAll(badWordsLabel, badWordsView);

        HBox wordsBox = new HBox(2);
        wordsBox.getChildren().addAll(keyWordsBox, badWordsBox);

        // Размер кеша хранимых результатов перед записью в файл
        Label cacheLabel = new Label("Размер кеша результатов");
        TextField cacheField = new TextField();
        Label cacheMeasureLabel = new Label("Mb");
        BorderPane cachePane = new BorderPane();
        cachePane.setLeft(cacheLabel);
        cachePane.setCenter(cacheField);
        cachePane.setRight(cacheMeasureLabel);

        // Текст, идентифицирующий окончание лога
        Label logsEndLabel = new Label("Текст, идентифицирующий окончание лога");
        TextField logsEndField = new TextField();
        HBox logsEndBox = new HBox(2);
        logsEndBox.getChildren().addAll(logsEndLabel, logsEndField);

        // Дополнительные слова для поиска логов
        Label regExpLabel = new Label("Регулярные выражения для поиска якорей логов:");
        ListView regExpView = new ListView();
        regExpView.setEditable(true);
        Label anchorLabel = new Label("Якоря логов:");
        ListView anchorView = new ListView();
        anchorView.setEditable(true);
        VBox anchorsBox = new VBox(4);
        anchorsBox.getChildren().addAll(regExpLabel, regExpView, anchorLabel, anchorView);

        // Кнопки управления
        Button loadDefaultsButton = new Button("Загрузить умолчания");
        Button editDefaultsButton = new Button("Сохранить в умолчания");
        Button submitButton = new Button("Применить");
        Button cancelButton = new Button("Отменить");
        HBox buttonsBox = new HBox(4);
        buttonsBox.getChildren().addAll(loadDefaultsButton, editDefaultsButton, submitButton, cancelButton);

        // Объединение панелей
        VBox panelsGroup = new VBox(6);
        panelsGroup.getChildren().addAll(pathPane, wordsBox, cachePane, logsEndBox, anchorsBox, buttonsBox);

        // События к элементам меню
        cancelButton.setOnAction(e -> {
            window.close();
        });

        Scene scene = new Scene(panelsGroup);
        scene.getStylesheets().add("css/Evil.css");
        window.setScene(scene);
        window.setWidth(500);
        window.setMinWidth(500);
        window.setHeight(700);
        window.setMinHeight(600);
        window.showAndWait();
    }
}

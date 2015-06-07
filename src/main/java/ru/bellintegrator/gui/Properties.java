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

/**
 * Created by DOrdynskiy on 05.06.2015.
 */
public class Properties {
    static String answer;

    public static void display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Параметры поиска");

        Button yesButton = new Button("yes");
        Button noButton = new Button("no");
        yesButton.setOnAction(e -> {
            answer = "true";
            window.close();
        });

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        TextField textField = new TextField("Def");
        CheckBox checkBox = new CheckBox("12");
        ListView listView = new ListView();
        listView.getItems().addAll("item 1", "item 2", "item 3");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<String> selected = listView.getSelectionModel().getSelectedItems();

        TreeItem<String> root, bucky, megan;
        root = new TreeItem<>();
        root.setExpanded(true);
        bucky = makeBranch("Bucky", root);
        makeBranch("thenewboston", bucky);
        makeBranch("YouTube", bucky);
        makeBranch("Chicken", bucky);


        megan = makeBranch("Megan", root);
        makeBranch("Glitter", megan);
        makeBranch("Makeup", megan);

        TreeView<String> treeView = new TreeView<>(root);
        treeView.setShowRoot(false);
        HBox hBox2 = new HBox(10);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().add(treeView);

        noButton.setOnAction(e -> {
            answer = textField.getText() + " - " + checkBox.isSelected() + " " + choiceBox.getValue();
            System.out.println();
            for(String item : selected) {
                System.out.println(item);
            }
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(yesButton);
        layout.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(noButton);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        checkBox.setSelected(true);
        TextField textField2 = new TextField();
        textField2.setPromptText("DefPrompt");
        Button button22 = new Button("22");
        //button22.setOnMousePressed(e -> button22.setStyle("-fx-background-color: linear-gradient(#55ff33, #004400)"));
        //button22.setOnMouseReleased(e -> button22.setStyle("-fx-background-color: linear-gradient(#55ff33, #007700)"));


        GridPane.setConstraints(listView, 0, 0);
        GridPane.setConstraints(checkBox, 1, 0);
        GridPane.setConstraints(treeView, 2, 0);
        GridPane.setConstraints(textField, 0, 1);
        GridPane.setConstraints(button22, 1, 1);
        GridPane.setConstraints(textField2, 2, 1);
        grid.getChildren().addAll(listView, checkBox, treeView, textField, button22, textField2);

        choiceBox.getItems().add("Apples");
        choiceBox.getItems().add("Bananas");
        choiceBox.getItems().addAll("Bacon", "Ham", "Meatballs");
        choiceBox.setValue("Bananas");
        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> System.out.println(newValue));
        HBox hBox1 = new HBox(10);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().add(choiceBox);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox2);
        borderPane.setLeft(layout);
        borderPane.setBottom(hBox);
        borderPane.setRight(hBox1);
        borderPane.setCenter(grid);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("css/Evil.css");
        window.setScene(scene);
        window.showAndWait();
    }

    public static TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}

package ua.com.alevel.messenger_nure.view.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainPage {

    @FXML
    private AnchorPane apMain;
    @FXML
    private Label label_login;

    @FXML
    private ScrollPane scrollPane;

    private VBox vBox = new VBox();


    @FXML
    private void initialize() {
        vBox.getChildren().addAll(
                createAnchorPane("1Zaur", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                createAnchorPane("Z2aur", "Потаолтідлавтдлоіватмоірмовиамоілваи"),
                createAnchorPane("Za3ur", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                createAnchorPane("Zau4r", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                createAnchorPane("Zaur5", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                createAnchorPane("Zau6r", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                createAnchorPane("Za8ur", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"),
                createAnchorPane("Za345ur", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                createAnchorPane("Zau23r", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                createAnchorPane("Zau123r", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                createAnchorPane("Zau43r", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                createAnchorPane("Zau345r", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
                createAnchorPane("Zau435r", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
        );


        scrollPane.setContent(vBox);

    }

    // Метод для создания AnchorPane с метками
    private AnchorPane createAnchorPane(String name, String message) {
        Label nameLabel = new Label(name);
        nameLabel.setFont(new Font(15));

        if (message.length() > 22) {
            message = message.substring(0, 22) + "...";
            System.out.println("message = " + message);
        }
        Label messageLabel = new Label(message);
        AnchorPane anchorPane = new AnchorPane(nameLabel, messageLabel);
        anchorPane.getStyleClass().add("color");
        AnchorPane.setTopAnchor(nameLabel, 0.0);
        AnchorPane.setTopAnchor(messageLabel, 40.0);
        anchorPane.setPrefSize(200, 60);
        anchorPane.setOnMouseClicked(mouseEvent -> {

        });

        return anchorPane;
    }

    @FXML
    private void open() {

    }

    public Label getLabel_login() {
        return label_login;
    }

    public void setLabel_login(Label label_login) {
        this.label_login = label_login;
    }

}

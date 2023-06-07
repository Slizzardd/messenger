package ua.com.alevel.messenger_nure.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.com.alevel.messenger_nure.MessengerApplication;
import ua.com.alevel.messenger_nure.persistence.dao.UserDao;
import ua.com.alevel.messenger_nure.persistence.dao.impl.UserDaoImpl;
import ua.com.alevel.messenger_nure.persistence.entity.user.User;

import java.io.IOException;

public class SignInController {

    private final UserDao userDao;
    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Label labelWelcome;

    public SignInController() {
        this.userDao = new UserDaoImpl();
    }

    @FXML
    public void initialize() {

    }

    @FXML
    private void login() {
        buttonSignIn.setOnAction(event -> {
            User user = userDao.findByLogin(tfUsername.getText());

            if (user.getPassword().equals(tfPassword.getText())) {
                buttonSignIn.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();

                try {
                    loader.setLocation(MessengerApplication.class.getResource("mainPage.fxml"));
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //Transfer data from this class to MainPage class
                MainPage mainPage = loader.getController();
                mainPage.getLabel_login().setText(user.getLogin());

                Parent root = (Parent) loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));


                stage.showAndWait();
            } else {
                labelWelcome.setText("ERROR, TRY AGAIN!");
            }
        });
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public TextField getTfUsername() {
        return tfUsername;
    }

    public void setTfUsername(TextField tfUsername) {
        this.tfUsername = tfUsername;
    }

    public TextField getTfPassword() {
        return tfPassword;
    }

    public void setTfPassword(TextField tfPassword) {
        this.tfPassword = tfPassword;
    }

    public Button getButtonSignIn() {
        return buttonSignIn;
    }

    public void setButtonSignIn(Button buttonSignIn) {
        this.buttonSignIn = buttonSignIn;
    }

    public Label getLabelWelcome() {
        return labelWelcome;
    }

    public void setLabelWelcome(Label labelWelcome) {
        this.labelWelcome = labelWelcome;
    }
}

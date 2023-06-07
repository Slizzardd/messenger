package ua.com.alevel.messenger_nure.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.com.alevel.messenger_nure.MessengerApplication;
import ua.com.alevel.messenger_nure.persistence.dao.UserDao;
import ua.com.alevel.messenger_nure.persistence.dao.impl.UserDaoImpl;
import ua.com.alevel.messenger_nure.persistence.entity.user.User;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {

    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;

    @FXML
    private Button buttonSignUp;

    @FXML
    private Button buttonGoSignIn;

    private final UserDao userDao;

    public SignUpController() {
        this.userDao = new UserDaoImpl();
    }

    @FXML
    private void createUser() {
        buttonSignUp.setOnAction(event -> {
            try {
                User user = new User();

                user.setLogin(tfUsername.getText());
                user.setPassword(tfPassword.getText());
                user.setEmail(tfEmail.getText());
                user.setPhoneNumber(tfPhoneNumber.getText());
                userDao.create(user);

                buttonSignUp.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                try {
                    loader.setLocation(MessengerApplication.class.getResource("signIn.fxml"));
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                SignInController signInController = loader.getController();
                signInController.getTfUsername().setText(user.getLogin());
                signInController.getTfPassword().setText(user.getPassword());
                stage.showAndWait();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    private void goToSignIn() {
        buttonGoSignIn.setOnAction(event -> {
            buttonGoSignIn.getScene().getWindow().hide();


            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(MessengerApplication.class.getResource("signIn.fxml"));
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    public TextField getTfUsername() {
        return tfUsername;
    }

    public void setTfUsername(TextField tfUsername) {
        this.tfUsername = tfUsername;
    }

    public TextField getTfPhoneNumber() {
        return tfPhoneNumber;
    }

    public void setTfPhoneNumber(TextField tfPhoneNumber) {
        this.tfPhoneNumber = tfPhoneNumber;
    }

    public TextField getTfEmail() {
        return tfEmail;
    }

    public void setTfEmail(TextField tfEmail) {
        this.tfEmail = tfEmail;
    }

    public TextField getTfPassword() {
        return tfPassword;
    }

    public void setTfPassword(TextField tfPassword) {
        this.tfPassword = tfPassword;
    }

    public Button getButtonSignUp() {
        return buttonSignUp;
    }

    public void setButtonSignUp(Button buttonSignUp) {
        this.buttonSignUp = buttonSignUp;
    }

    public Button getButtonGoSignIn() {
        return buttonGoSignIn;
    }

    public void setButtonGoSignIn(Button buttonGoSignIn) {
        this.buttonGoSignIn = buttonGoSignIn;
    }

    public UserDao getUserDao() {
        return userDao;
    }


}

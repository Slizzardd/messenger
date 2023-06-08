package ua.com.alevel.messenger_nure.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.com.alevel.messenger_nure.MessengerApplication;
import ua.com.alevel.messenger_nure.facade.UserFacade;
import ua.com.alevel.messenger_nure.facade.impl.UserFacadeImpl;
import ua.com.alevel.messenger_nure.persistence.entity.user.User;
import ua.com.alevel.messenger_nure.util.Security;

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

    private final UserFacade userFacade;

    public SignUpController() {
        this.userFacade = new UserFacadeImpl();
    }

    @FXML
    private void createUser() {
        buttonSignUp.setOnAction(event -> {
            try {
                User user = new User();

                user.setLogin(tfUsername.getText());
                user.setPassword(Security.md5Apache(tfPassword.getText()));
                user.setEmail(tfEmail.getText());
                user.setPhoneNumber(tfPhoneNumber.getText());
                userFacade.create(user);

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
                stage.showAndWait();

            } catch (SQLException | ClassNotFoundException e) {
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
}

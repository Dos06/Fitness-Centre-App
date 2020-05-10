package sample.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;

public class sampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonSignUp;

    @FXML
    private TextField textfieldLogin;

    @FXML
    private PasswordField passwordfieldPassword;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Label labelEmptyLoginPassword;

    @FXML
    void initialize() {
        buttonSignUp.setOnAction(actionEvent -> {
            buttonSignUp.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/FXML/signUp.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        buttonSignIn.setOnAction(actionEvent -> {
            String loginText = textfieldLogin.getText().trim(); // trim() - delete extra empty spaces
            String passwordText = passwordfieldPassword.getText().trim();

            // check if a user is the admin
            if (loginText.equals("admin") && passwordText.equals("admin")) {
                buttonSignIn.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/FXML/adminAccount.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }

            else if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText, passwordText);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            else  {
//                System.out.println("Login or/and Password = EMPTY");
//                labelEmptyLoginPassword.setText("Login or/and Password = EMPTY");
            }

        });
    }

    private void loginUser(String loginText, String passwordText) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);

        ResultSet rs = dbHandler.getUser(user);

        int counter = 0;
        while (rs.next()) {
            counter++;
        }

        if (counter >= 1) {
            buttonSignIn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/FXML/userAccount.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {
            Shake userLoginAnimation = new Shake(textfieldLogin);
            Shake userPasswordAnimation = new Shake(passwordfieldPassword);

            userLoginAnimation.playAnimation();
            userPasswordAnimation.playAnimation();
        }
    }
}


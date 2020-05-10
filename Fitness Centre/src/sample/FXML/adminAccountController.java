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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;

public class adminAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonExit;

    @FXML
    private ImageView imgLogo;

    @FXML
    private TextField textfieldActivityToAdd;

    @FXML
    private Button buttonAddActivity;

    @FXML
    private Button buttonDeleteUser;

    @FXML
    private TextField textfieldUserLoginToDelete;

    @FXML
    private Button buttonShowAllUsers;

    @FXML
    private TextField textfieldActivityToDelete;

    @FXML
    private Button buttonDeleteActivity;

    @FXML
    void initialize() {
        buttonExit.setOnAction(actionEvent -> {
            buttonExit.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/FXML/sample.fxml"));

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

        buttonShowAllUsers.setOnAction(actionEvent -> {
            buttonShowAllUsers.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/FXML/adminShowAllUsers.fxml"));

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

        buttonDeleteUser.setOnAction(actionEvent -> {
            String userLoginToDelete = textfieldUserLoginToDelete.getText().trim();

            if (!userLoginToDelete.equals("")) {
                try {
                    deleteUser(userLoginToDelete);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonAddActivity.setOnAction(actionEvent -> {
            String activityToAdd = textfieldActivityToAdd.getText().trim();

            if (!activityToAdd.equals("")) {
                try {
                    addActivity(activityToAdd);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonDeleteActivity.setOnAction(actionEvent -> {
            String activityToDelete = textfieldActivityToDelete.getText().trim();

            if (!activityToDelete.equals("")) {
                try {
                    deleteActivity(activityToDelete);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void deleteUser(String userLoginToDelete) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        User user = new User();
        user.setLogin(userLoginToDelete);

        dbHandler.deleteUser(user);
    }

    private void addActivity(String activityToAdd) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        dbHandler.addActivity(activityToAdd);
    }

    private void deleteActivity(String activityToDelete) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        dbHandler.deleteActivity(activityToDelete);
    }
}

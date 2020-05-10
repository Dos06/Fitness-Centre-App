package sample.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;

public class userAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonExit;

    @FXML
    private ImageView imgLogo;

    @FXML
    private TextField textfieldLoginProof;

    @FXML
    private ComboBox<String> comboboxEdit;

    @FXML
    private PasswordField passwordfieldPassword;

    @FXML
    private Button buttonEdit;

    @FXML
    private TextField textfieldEdit;

    @FXML
    private Button buttonShowUsers;

    @FXML
    private ComboBox<String> comboboxActivity;

    ObservableList<String> obsList = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Connection connection = dbHandler.getDbConnection();

        ResultSet activities = dbHandler.getActivities();

        while (activities.next()) {
            String activity = activities.getString("name");
            obsList.add(activity);
        }

        comboboxActivity.setItems(obsList);
        comboboxEdit.setItems(FXCollections.observableArrayList("name", "surname", "login", "password"));

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

        buttonShowUsers.setOnAction(actionEvent -> {
            buttonShowUsers.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/FXML/userShowUsers.fxml"));

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

        buttonEdit.setOnAction(actionEvent -> {
            if (!textfieldLoginProof.getText().trim().equals("") && !passwordfieldPassword.getText().trim().equals("")) {
                User user = new User();
                user.setLogin(textfieldLoginProof.getText().trim());
                user.setPassword(passwordfieldPassword.getText().trim());
                ResultSet resultSet = dbHandler.getUser(user);

                if (!comboboxEdit.getValue().equals("") && !textfieldEdit.getText().trim().equals("")) {
                    String column = comboboxEdit.getValue();
                    String toEdit = textfieldEdit.getText().trim();


//                        String login = resultSet.getString("login");
//                        String pass = resultSet.getString("password");
                    String login = user.getLogin();
                    String pass = user.getPassword();
                    dbHandler.updateRecord(login, pass, column, toEdit);

                    if (!comboboxActivity.getValue().equals("")) {
                        String activityToEdit = comboboxActivity.getValue();

                        dbHandler.updateRecord(login, pass,"activity", activityToEdit);
                    }
                }
                else if (comboboxEdit.getValue().equals("") && textfieldEdit.getText().trim().equals("")) {
                    String activityToEdit = comboboxActivity.getValue();

                    String login = user.getLogin();
                    String pass = user.getPassword();

                    dbHandler.updateRecord(login, pass,"activity", activityToEdit);
                }
            }
        });
    }
}

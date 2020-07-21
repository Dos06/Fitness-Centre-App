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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;

public class adminShowAllUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonExit;

    @FXML
    private ImageView imgLogo;

    @FXML
    private TableView<User> tableViewUsers;

    @FXML
    private TableColumn<User, Integer> col_id;

    @FXML
    private TableColumn<User, String> col_name;

    @FXML
    private TableColumn<User, String> col_surname;

    @FXML
    private TableColumn<User, String> col_login;

    @FXML
    private TableColumn<User, String> col_password;

    @FXML
    private TableColumn<User, String> col_gender;

    @FXML
    private TableColumn<User, Integer> col_age;

    @FXML
    private TableColumn<User, String> col_activity;

    ObservableList<User> obsList = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Connection connection = dbHandler.getDbConnection();

        ResultSet allUsers = dbHandler.getAllUsers();

        while (allUsers.next()) {
            User user = new User();
            user.setId(allUsers.getInt("id"));
            user.setName(allUsers.getString("name"));
            user.setSurname(allUsers.getString("surname"));
            user.setLogin(allUsers.getString("login"));
            user.setPassword(allUsers.getString("password"));
            user.setGender(allUsers.getString("gender"));
            user.setAge(allUsers.getInt("age"));
            user.setActivity(allUsers.getString("activity"));
            obsList.add(user);
        }

        tableViewUsers.setItems(obsList);

        buttonExit.setOnAction(actionEvent -> {
            buttonExit.getScene().getWindow().hide();

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
        });

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_activity.setCellValueFactory(new PropertyValueFactory<>("activity"));

//        try {
////            String query = "SELECT"
////
////            Connection connection = DbConnector.getConnection();
////            ResultSet resultSet = connection.createStatement(executeQuery(query));
//
//        }
    }
}

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;

public class userShowUsersController {

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
    private TableColumn<User, String> col_gender;

    @FXML
    private TableColumn<User, Integer> col_age;

    @FXML
    private TableColumn<User, String> col_activity;

    @FXML
    private ComboBox<String> comboboxActivity;

    @FXML
    private Button buttonShow;

    ObservableList<User> tableList = FXCollections.observableArrayList();
    ObservableList<String> activityList = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Connection connection = dbHandler.getDbConnection();

        ResultSet activities = dbHandler.getActivities();

        while (activities.next()) {
            String activity = activities.getString("name");
            activityList.add(activity);
        }
        comboboxActivity.setItems(activityList);

        buttonExit.setOnAction(actionEvent -> {
            buttonExit.getScene().getWindow().hide();

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
        });

        buttonShow.setOnAction(actionEvent -> {
            // clear the table firstly
            for ( int i = 0; i < tableViewUsers.getItems().size(); i++) {
                tableViewUsers.getItems().clear();
            }


            String activity = comboboxActivity.getValue();

            ResultSet allUsers = null;
            try {
                allUsers = dbHandler.getUsersByActivity(activity);
                while (allUsers.next()) {
                    User user = new User();
                    user.setId(allUsers.getInt("id"));
                    user.setName(allUsers.getString("name"));
                    user.setSurname(allUsers.getString("surname"));
                    user.setGender(allUsers.getString("gender"));
                    user.setAge(allUsers.getInt("age"));
                    user.setActivity(allUsers.getString("activity"));
                    tableList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            tableViewUsers.setItems(tableList);
        });

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_activity.setCellValueFactory(new PropertyValueFactory<>("activity"));

    }
}


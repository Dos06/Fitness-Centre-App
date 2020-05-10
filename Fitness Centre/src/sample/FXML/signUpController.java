package sample.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class signUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonExit;

    @FXML
    private ImageView imgLogo;

    @FXML
    private RadioButton radioMale;

    @FXML
    private TextField textfieldLogin;

    @FXML
    private PasswordField passwordfieldPassword;

    @FXML
    private Button buttonSignUp;

    @FXML
    private TextField textfieldName;

    @FXML
    private TextField textfieldSurname;

    @FXML
    private RadioButton radioFemale;

    @FXML
    private TextField textfieldAge;

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
//        comboboxActivity.setItems(FXCollections.observableArrayList("Gym", "SwimmingPool"));

        // Listener for choiceBox, comboBox
//        comboboxActivity.getSelectionModel().selectedItemProperty().addListener(
//                (v, oldValue, newValue) -> System.out.println(newValue) );

        // Listener for radioButtons

        final ToggleGroup group = new ToggleGroup();
        radioMale.setToggleGroup(group);
        radioFemale.setToggleGroup(group);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                if (radioMale.isSelected()) {
                    radioFemale.setSelected(false);
                }
                if (radioFemale.isSelected()) {
                    radioMale.setSelected(false);
                }
            }
        });



        buttonSignUp.setOnAction(actionEvent -> {

            createUserFromFields();

            if(isInt(textfieldAge)) {
                buttonSignUp.getScene().getWindow().hide();

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
            }
        });

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
    }

    private boolean isInt(TextField textfieldAge) {
        try {
            int age = Integer.parseInt(textfieldAge.getText());
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }

    private void createUserFromFields() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        // to check if Age is a number
        if(isInt(textfieldAge)) {
            String name = textfieldName.getText();
            String surname = textfieldSurname.getText();
            String login = textfieldLogin.getText();
            String password = passwordfieldPassword.getText();
            int age = Integer.parseInt(textfieldAge.getText());
            String activity = comboboxActivity.getValue();



            String gender = "";
            if (radioMale.isSelected()) {
                gender = "Male";
            }
            else if (radioFemale.isSelected()) {
                gender = "Female";
            }

            User user = new User(name, surname, login, password, gender, age, activity);
            System.out.println(user.toString());
            dbHandler.signUpUser(user);


            buttonSignUp.getScene().getWindow().hide();

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
        }
    }
}



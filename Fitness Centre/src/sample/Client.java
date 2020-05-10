package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client extends Application {
    public static void main(String[] args) {
        /*
             Socket socket = new Socket("127.0.0.1", 8000);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         */
        try (Phone phone = new Phone("127.0.0.1", 8000);) {

            System.out.println("Connected to the server.");
//            String request = "Almaty";
//            System.out.println("Request: " + request);

//            phone.writeLine(request);
//            String response = phone.readLine();
//            System.out.println("Response: " + response);
            launch(args);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/sample.fxml"));
        primaryStage.setTitle("Fitness Centre");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}

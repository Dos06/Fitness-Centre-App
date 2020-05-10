package sample;

import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8000);) {
            System.out.println("Server started.");

            while (true) {
                Phone phone = new Phone(serverSocket);
                // Runnable (method run())
                new Thread(() -> {
//                    String request = phone.readLine();
//                    System.out.println("Request: " + request);
//                    String response = (int) (Math.random() * 30 - 10) + "";
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    phone.writeLine(response);
//                    System.out.println("Response: " + response);
                    try {
                        phone.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

package com.example.employeemanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 669, 387);
        stage.setTitle("Employee Management Systems - Login");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }



    public static void main(String[] args) {
        launch();
    }
}
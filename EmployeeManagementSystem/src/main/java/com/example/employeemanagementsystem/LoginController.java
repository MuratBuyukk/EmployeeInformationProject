package com.example.employeemanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {



    @FXML
    private Button loginLoginBTN;

    @FXML
    private PasswordField loginPasswordTF;

    @FXML
    private TextField loginUserNameTF;




    DatabaseTransaction databaseTransaction = new DatabaseTransaction();
    public void loginAction(ActionEvent actionEvent){
        String username = loginUserNameTF.getText();
        String password = loginPasswordTF.getText();

        boolean loginIsSuccess = databaseTransaction.makeLogin(username,password);

        if (loginIsSuccess){
            System.out.println("Login is success");
            loginLoginBTN.getScene().getWindow().hide();
            Stage panel = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Panel.fxml"));
                Scene scene = new Scene(root);
                panel.setScene(scene);
                panel.show();
                panel.setTitle("Employee Management Systems - Panel");
                panel.setResizable(false);


            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            System.out.println("Failed login");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password");
            alert.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginPasswordTF.setStyle("-fx-text-inner-color: #000000;");
        loginUserNameTF.setStyle("-fx-text-inner-color: #000000;");
    }
}
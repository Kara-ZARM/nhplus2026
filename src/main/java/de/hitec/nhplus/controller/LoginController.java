package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.UserDao;
import de.hitec.nhplus.model.User;
import de.hitec.nhplus.utils.PasswordUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label labelError;

    private UserDao dao;

    @FXML
    public void initialize(){
        dao = DaoFactory.getDaoFactory().createUserDao();
    }

    @FXML
    public void handleLoginAttempt(){
        String username = this.textFieldUsername.getText();
        String password = this.passwordField.getText();
        String password_hash = null;
        String salt = null;
        try {
            User user = dao.findByUsername(username);
            if(user==null){
                showError("Wrong username.");
                return;
            }
            password_hash = user.getPasswordHash();
            salt = user.getSalt();
            if(PasswordUtil.verify(password, salt, password_hash)){
                System.out.println("Login successful.");
                //stage.setScene(mainScene);
            } else {
                showError("Wrong password.");
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void showError(String message){
        labelError.setVisible(true);
        labelError.setText(message);
    }
}

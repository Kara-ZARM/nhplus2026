package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.UserDao;
import de.hitec.nhplus.model.User;
import de.hitec.nhplus.utils.MessageUtil;
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
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

/**
 * The <code>LoginController</code> contains the logic that handles the login and logout process.
 * Old SHA-256 Code commented out for documentation reasons.
 */

public class LoginController {

    private static User currentUser = null;

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

    /**
     * The <code>handleLoginAttempt</code> method handles login attempts.
     * It checks if the entered username in <code>textFieldUsername</code> exists and if it does,
     * verifies if the calculated hash from the password in <code>passwordField</code> matches the stored hash.
     * If the user does not exist, the <code>labelError</code> shows a "Wrong username" message.
     * If the password does not match the stored hash, the <code>labelError</code> shows a "Wrong password" message.
     * If everything is correct it stores the user that is logging in in <code>currentUser</code>,
     * updates the <code>last_login</code> column of the user that is logging in,
     * and sets the scene to <code>MainWindowView.fxml</code>.
     */
    @FXML
    public void handleLoginAttempt(){
        String username = this.textFieldUsername.getText();
        String password = this.passwordField.getText();
        String password_hash = null;
        //String salt = null;
        try {
            User user = dao.findByUsername(username);
            if(user==null){
                MessageUtil.showError(labelError,"Wrong username.");
                return;
            }
            password_hash = user.getPasswordHash();
            //salt = user.getSalt();

            //if(PasswordUtil.verify(password, salt, password_hash)){
            if(BCrypt.checkpw(password, password_hash)){
                currentUser = user;
                dao.getLastLoginUpdateStatement(user);
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/de/hitec/nhplus/MainWindowView.fxml"));

                Parent root = loader.load();

                Scene mainScene = new Scene(root);

                Stage stage = (Stage) buttonLogin.getScene().getWindow();
                stage.setScene(mainScene);
            } else {
                MessageUtil.showError(labelError,"Wrong password.");
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * <code>getCurrentUser</code> gets the currently logged in <code>User</code>.
     * @return <code>User</code>
     */
    public static User getCurrentUser(){return currentUser;}

    /**
     * <code>resetCurrentUser</code> sets the currently logged in <code>User</code> to null, for the purpose of clearing the cache.
     */
    public static void resetCurrentUser(){
        currentUser = null;
    }
}

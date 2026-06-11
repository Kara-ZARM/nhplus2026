package de.hitec.nhplus.controller;

import de.hitec.nhplus.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

import static de.hitec.nhplus.model.User.Role.ADMIN;

public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonUsers;

    @FXML
    public void initialize(){
        if(LoginController.getCurrentUser().getRole() != ADMIN){
            buttonUsers.setDisable(true);
            buttonUsers.setVisible(false);
        }
    }

    @FXML
    private void handleShowAllPatient(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void handleShowAllCaregiver(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllCaregiverView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void handleShowAllTreatments(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllTreatmentView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * <code>handleLogout</code> handles the logout process, calls <code>resetCurrentUser</code> from <code>LoginController</code> and sets the scene to <code>LoginView.fxml</code>.
     * @throws IOException
     */
    @FXML
    private void handleLogout() throws IOException {
        LoginController.resetCurrentUser();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/de/hitec/nhplus/LoginView.fxml")
        );
        Parent root = loader.load();

        Scene mainScene = new Scene(root);

        Stage stage = (Stage) buttonLogout.getScene().getWindow();
        stage.setScene(mainScene);
    }

    @FXML
    private void handleShowAllUsers(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllUserView.fxml"));
        try{
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
}

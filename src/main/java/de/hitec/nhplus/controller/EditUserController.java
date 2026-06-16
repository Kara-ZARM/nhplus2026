package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.UserDao;
import de.hitec.nhplus.logging.DBLogger;
import de.hitec.nhplus.logging.LogEntry;
import de.hitec.nhplus.logging.OperationType;
import de.hitec.nhplus.model.User;
import de.hitec.nhplus.utils.AlertBuilder;
import de.hitec.nhplus.utils.MessageUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class EditUserController {

    @FXML
    private ComboBox<String> comboBoxUserSelect;

    @FXML
    private ComboBox<String> comboBoxRoleSelect;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private PasswordField passwordFieldEntry;

    @FXML
    private PasswordField passwordFieldConfirm;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Label labelError;

    private UserDao dao;
    private AllUserController parentController;
    private final ObservableList<String> userSelection = FXCollections.observableArrayList();
    private final ObservableList<String> roleSelection = FXCollections.observableArrayList();
    private ArrayList<User> userList;

    public void initialize(){
        comboBoxUserSelect.setItems(userSelection);
        comboBoxUserSelect.getSelectionModel().select(0);
        this.createUserComboBoxData();
        comboBoxRoleSelect.setItems(roleSelection);
        comboBoxRoleSelect.getSelectionModel().select(0);
        this.createRoleComboBoxData();
        this.buttonDelete.setDisable(true);
        this.buttonUpdate.setText("Create User");
    }

    public void setParentController(AllUserController parentController) {
        this.parentController = parentController;
    }

    private void createUserComboBoxData(){
        userSelection.clear();
        userSelection.add("Create new user");

        UserDao dao = DaoFactory.getDaoFactory().createUserDao();
        try{
            userList = (ArrayList<User>) dao.readAll();
            for(User user: userList){
                this.userSelection.add(formatUserDisplayName(user));
            }
            comboBoxUserSelect.setItems(userSelection);
            comboBoxUserSelect.getSelectionModel().selectFirst();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    private String formatUserDisplayName(User user){
        return String.format("%s", user.getUsername());
    }

    @FXML
    public void handleUserComboBox() throws SQLException {
        UserDao dao = DaoFactory.getDaoFactory().createUserDao();
        String selectedUser = this.comboBoxUserSelect.getSelectionModel().getSelectedItem();
        //this.dao = DaoFactory.getDaoFactory().createUserDao();
        if (selectedUser == null || selectedUser.equals("Create new user")){
            buttonUpdate.setText("Create User");
            buttonDelete.setDisable(true);
            clearAllFields();
        }
        else {
            buttonUpdate.setText("Update User");
            buttonDelete.setDisable(false);
            User user = dao.findByUsername(selectedUser);
            textFieldUsername.setText(user.getUsername());
            comboBoxRoleSelect.setValue(user.getRoleName());
        }
    }

    private void createRoleComboBoxData(){
        roleSelection.clear();
        for(User.Role role : User.Role.values()){
            roleSelection.add(role.name());
        }
        comboBoxRoleSelect.setItems(roleSelection);
        comboBoxRoleSelect.getSelectionModel().select(1);
    }

    @FXML
    private void handleUserDelete() throws SQLException {
        UserDao dao = DaoFactory.getDaoFactory().createUserDao();
        User selectedUser = dao.findByUsername(this.comboBoxUserSelect.getSelectionModel().getSelectedItem());

        if(selectedUser.getUid()==LoginController.getCurrentUser().getUid()){
            MessageUtil.showError(labelError,"Der eingeloggte Nutzer darf nicht gelöscht werden!");
        } else {
            Optional<ButtonType> result = AlertBuilder.alertForDelete();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (selectedUser != null) {
                    try {
                        DaoFactory.getDaoFactory().createUserDao().deleteById(selectedUser.getUid());
                        DBLogger.log(new LogEntry(
                                OperationType.DELETE,
                                "user",
                                selectedUser.getUid(),
                                LoginController.getCurrentUser().getUsername()));
                        updateAllUserViews();
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }

    @FXML
    private void handleUserUpdate() throws SQLException {
        UserDao dao = DaoFactory.getDaoFactory().createUserDao();
        User selectedUser = dao.findByUsername(this.comboBoxUserSelect.getSelectionModel().getSelectedItem());
        //Case: Create new user
        if(selectedUser == null || Objects.equals(selectedUser.getUsername(), "Create new user")){
            if (checkIfUsernameEmpty()) {
                return;
            } else if (!checkPasswordValidity()) {
                return;
            } else {
                dao.create(new User(textFieldUsername.getText(),BCrypt.hashpw(passwordFieldEntry.getText(),BCrypt.gensalt()),User.Role.valueOf(comboBoxRoleSelect.getSelectionModel().getSelectedItem())));
                DBLogger.log(new LogEntry(
                        OperationType.CREATE,
                        "user",
                        dao.getLastCreatedId(),
                        LoginController.getCurrentUser().getUsername()));
                updateAllUserViews();
                clearAllFields();
                MessageUtil.showSuccess(labelError,"Benutzer*in erfolgreich angelegt!");
            }
        }
        //Case: Update user
        else {
            if(!Objects.equals(passwordFieldEntry.getText(), "") || !Objects.equals(passwordFieldConfirm.getText(), "")){
                if(!checkPasswordValidity()){
                    return;
                } else {
                    selectedUser.setPasswordHash(BCrypt.hashpw(passwordFieldEntry.getText(), BCrypt.gensalt()));
                }
            }
            if(checkIfUsernameEmpty()){
                return;
            }
            if((LoginController.getCurrentUser().getUid()==selectedUser.getUid()) && (!Objects.equals(selectedUser.getRoleName(), comboBoxRoleSelect.getSelectionModel().getSelectedItem()))){
                MessageUtil.showError(labelError,"Der eingeloggte Nutzer darf seine Rolle nicht ändern!");
                return;
            }
            selectedUser.setUsername(textFieldUsername.getText());
            selectedUser.setRole(User.Role.valueOf(comboBoxRoleSelect.getSelectionModel().getSelectedItem()));
            DaoFactory.getDaoFactory().createUserDao().update(selectedUser);
            DBLogger.log(new LogEntry(
                    OperationType.UPDATE,
                    "user",
                    selectedUser.getUid(),
                    LoginController.getCurrentUser().getUsername()));
            updateAllUserViews();
            MessageUtil.showSuccess(labelError,"Benutzer*in erfolgreich aktualisiert!");
        }
    }

    private boolean checkIfUsernameEmpty(){
        if(Objects.equals(textFieldUsername.getText(), "")) {
            MessageUtil.showError(labelError, "Benutzername darf nicht leer sein!");
            return true;
        } else {
            return false;
        }
    }

    private boolean checkPasswordValidity(){
        if (Objects.equals(passwordFieldEntry.getText(), "")&& Objects.equals(passwordFieldConfirm.getText(), "")) {
            MessageUtil.showError(labelError,"Das Passwort darf nicht leer sein!");
            return false;
        } else if ((!Objects.equals(passwordFieldEntry.getText(), "") && Objects.equals(passwordFieldConfirm.getText(), "")) || (!Objects.equals(passwordFieldConfirm.getText(), "") && Objects.equals(passwordFieldEntry.getText(), ""))) {
            MessageUtil.showError(labelError, "Beide Passwortfelder müssen ausgefüllt sein!");
            return false;
        } else if (!Objects.equals(passwordFieldEntry.getText(), passwordFieldConfirm.getText())) {
            MessageUtil.showError(labelError, "Passwörter stimmen nicht überein!");
            return false;
        } else if (passwordFieldEntry.getText().length()<10){
            MessageUtil.showError(labelError,"Das Passwort muss mindestens 10 Zeichen lang sein!");
            return false;
        } else {
            return true;
        }
    }

    private void updateAllUserViews(){
        this.createUserComboBoxData();
        if (parentController != null) {
            parentController.refreshUserTableView();
        }
    }

    private void clearAllFields(){
        textFieldUsername.setText("");
        passwordFieldEntry.setText("");
        passwordFieldConfirm.setText("");
        comboBoxRoleSelect.getSelectionModel().select("USER");
        labelError.setText("");
        labelError.setVisible(false);
    }
}

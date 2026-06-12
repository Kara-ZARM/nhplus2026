package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.UserDao;
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
            textFieldUsername.setText(null);
            comboBoxRoleSelect.getSelectionModel().select("USER");
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
            MessageUtil.showError(labelError,"Der eingeloggte Nutzer kann nicht gelöscht werden!");
        } else {
            Optional<ButtonType> result = AlertBuilder.alertForDelete();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (selectedUser != null) {
                    try {
                        DaoFactory.getDaoFactory().createUserDao().deleteById(selectedUser.getUid());
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
        if(Objects.equals(textFieldUsername.getText(), "")){
            MessageUtil.showError(labelError,"Benutzername darf nicht leer sein!");
        } else {
            if(!Objects.equals(textFieldUsername.getText(), selectedUser.getUsername()) || !Objects.equals(comboBoxRoleSelect.getSelectionModel().getSelectedItem(), selectedUser.getRoleName())){
                selectedUser.setUsername(textFieldUsername.getText());
                //IMP: insert failsafe so that current user cannot change from admin role!!!
                selectedUser.setRole(User.Role.valueOf(comboBoxRoleSelect.getSelectionModel().getSelectedItem()));
                DaoFactory.getDaoFactory().createUserDao().update(selectedUser);
                updateAllUserViews();
            }
            if((passwordFieldEntry.getText()!=""&&passwordFieldConfirm.getText()=="") || (passwordFieldConfirm.getText()!=""&&passwordFieldEntry.getText()=="")){
                MessageUtil.showError(labelError,"Beide Passwortfelder müssen ausgefüllt sein!");
            } else if (!Objects.equals(passwordFieldEntry.getText(), passwordFieldConfirm.getText())) {
                MessageUtil.showError(labelError,"Passwörter stimmen nicht überein!");
            } else {
                selectedUser.setPasswordHash(BCrypt.hashpw(passwordFieldEntry.getText(),BCrypt.gensalt()));
                DaoFactory.getDaoFactory().createUserDao().getPasswordUpdateStatement(selectedUser);
            }
        }
    }

    private void updateAllUserViews(){
        this.createUserComboBoxData();
        if (parentController != null) {
            parentController.refreshUserTableView();
        }
    }
}

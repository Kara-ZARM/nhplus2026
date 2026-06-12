package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.UserDao;
import de.hitec.nhplus.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

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

    private UserDao dao;
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
    private void handleUserDelete(){

    }
}

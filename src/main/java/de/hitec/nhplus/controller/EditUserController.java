package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.UserDao;
import de.hitec.nhplus.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class EditUserController {

    @FXML
    private ComboBox<String> comboBoxUserSelect;

    @FXML
    private ComboBox<String> comboBoxRoleSelect;

    private final ObservableList<String> userSelection = FXCollections.observableArrayList();
    private ArrayList<User> userList;

    public void initialize(){
        comboBoxUserSelect.setItems(userSelection);
        comboBoxUserSelect.getSelectionModel().select(0);
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
}

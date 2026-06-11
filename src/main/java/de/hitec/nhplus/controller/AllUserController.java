package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.UserDao;
import de.hitec.nhplus.model.User;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.SQLException;

public class AllUserController {

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, Integer> columnId;

    @FXML
    private TableColumn<User, String> columnUsername;

    @FXML
    private TableColumn<User, String> columnRole;

    @FXML
    private TableColumn<User, String> columnCreatedAt;

    @FXML
    private TableColumn<User, String> columnLastLogin;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonDelete;

    private final ObservableList<User> users = FXCollections.observableArrayList();
    private UserDao dao;

    public void initialize(){
        this.readAllAndShowInTableView();

        this.columnId.setCellValueFactory(new PropertyValueFactory<>("uid"));
        this.columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        this.columnUsername.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnRole.setCellValueFactory(new PropertyValueFactory<>("roleName"));
        this.columnRole.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        this.columnCreatedAt.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnLastLogin.setCellValueFactory(new PropertyValueFactory<>("lastLogin"));
        this.columnLastLogin.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tableView.setItems(this.users);
    }

    private void readAllAndShowInTableView(){
        this.users.clear();
        this.dao = DaoFactory.getDaoFactory().createUserDao();
        try{
            this.users.addAll(this.dao.readAll());
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}

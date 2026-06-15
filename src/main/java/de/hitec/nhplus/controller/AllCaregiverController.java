package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.CaregiverDao;
import de.hitec.nhplus.model.Caregiver;
import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.utils.AlertBuilder;
import de.hitec.nhplus.utils.DateConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static de.hitec.nhplus.model.User.Role.ADMIN;

/**
 * The <code>AllCaregiverController</code> contains the entire logic of the caregiver view. It determines which data is displayed and how to react to events.
 */
public class AllCaregiverController {
    @FXML
    private TableView<Caregiver> tableView;

    @FXML
    private TableColumn<Caregiver, Integer> columnId;

    @FXML
    private TableColumn<Caregiver, String> columnFirstName;

    @FXML
    private TableColumn<Caregiver, String> columnSurname;

    @FXML
    private TableColumn<Caregiver, String> columnPhoneNumber;

    @FXML
    private TableColumn<Caregiver, String> columnDateOfBirth;

    @FXML
    private TableColumn<Caregiver, String> columnStreet;

    @FXML
    private TableColumn<Caregiver, String> columnPostalCode;

    @FXML
    private TableColumn<Caregiver, String> columnCity;

    @FXML
    private TableColumn<Caregiver, String> columnTaxId;

    @FXML
    private TableColumn<Caregiver, String> columnQualification;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonAdd;

    @FXML
    private TextField textFieldSurname;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldPhoneNumber;

    @FXML
    private TextField textFieldDateOfBirth;

    @FXML
    private TextField textFieldStreet;

    @FXML
    private TextField textFieldPostalCode;

    @FXML
    private TextField textFieldCity;

    @FXML
    private TextField textFieldTaxId;

    @FXML
    private TextField textFieldQualification;

    @FXML
    private HBox HboxInsert;

    private final ObservableList<Caregiver> caregiver = FXCollections.observableArrayList();
    private CaregiverDao dao;

    /**
     * When <code>initialize()</code> gets called, all fields are already initialized. For example from the FXMLLoader
     * after loading an FXML-File. At this point of the lifecycle of the Controller, the fields can be accessed and
     * configured.
     * Depending on the <code>role</code> of the currently logged in <code>User</code>, some features will be enabled / disabled.
     */

    public void initialize() {
        this.readAllAndShowInTableView();

        this.columnId.setCellValueFactory(new PropertyValueFactory<>("cid"));

        // CellValueFactory to show property values in TableView
        this.columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        // CellFactory to write property values from with in the TableView
        this.columnFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        this.columnSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        this.columnPhoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());

        this.columnDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        this.columnDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn());

        this.columnStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        this.columnStreet.setCellFactory(TextFieldTableCell.forTableColumn());

        this.columnPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalcode"));
        this.columnPostalCode.setCellFactory(TextFieldTableCell.forTableColumn());

        this.columnCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        this.columnCity.setCellFactory(TextFieldTableCell.forTableColumn());

        this.columnTaxId.setCellValueFactory(new PropertyValueFactory<>("taxid"));
        this.columnTaxId.setCellFactory(TextFieldTableCell.forTableColumn());

        this.columnQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        this.columnQualification.setCellFactory(TextFieldTableCell.forTableColumn());

        //Anzeigen der Daten
        this.tableView.setItems(this.caregiver);

        this.buttonDelete.setDisable(true);
        this.tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Caregiver>() {
            @Override
            public void changed(ObservableValue<? extends Caregiver> observableValue, Caregiver oldCaregiver, Caregiver newCaregiver) {

                AllCaregiverController.this.buttonDelete.setDisable(newCaregiver == null);
            }
        });

        this.buttonAdd.setDisable(true);
        ChangeListener<String> inputNewCaregiverListener = (observableValue, oldText, newText) ->
                AllCaregiverController.this.buttonAdd.setDisable(!AllCaregiverController.this.areInputDataValid());
        this.textFieldSurname.textProperty().addListener(inputNewCaregiverListener);
        this.textFieldFirstName.textProperty().addListener(inputNewCaregiverListener);
        this.textFieldPhoneNumber.textProperty().addListener(inputNewCaregiverListener);
        this.textFieldDateOfBirth.textProperty().addListener(inputNewCaregiverListener);
        this.textFieldStreet.textProperty().addListener(inputNewCaregiverListener);
        this.textFieldPostalCode.textProperty().addListener(inputNewCaregiverListener);
        this.textFieldCity.textProperty().addListener(inputNewCaregiverListener);
        this.textFieldTaxId.textProperty().addListener(inputNewCaregiverListener);
        this.textFieldQualification.textProperty().addListener(inputNewCaregiverListener);

        if(LoginController.getCurrentUser().getRole() != ADMIN){
            HboxInsert.setDisable(true);
            HboxInsert.setVisible(false);
            tableView.setEditable(false);
            columnDateOfBirth.setVisible(false);
            columnStreet.setVisible(false);
            columnPostalCode.setVisible(false);
            columnCity.setVisible(false);
            columnTaxId.setVisible(false);
            columnQualification.setVisible(false);
        }
    }

    /**
     * When a cell of a column was changed, this method will be called, to persist the change.
     * The method first validates whether the new input is valid (not blank). If the input is invalid,
     * a warning dialog is displayed and the change is reverted in the UI. If the input is valid,
     * the corresponding patient attribute is updated via a switch statement and the change is
     * persisted to the database.
     *
     * @param event Event including the changed object and the change.
     */

    public void handleOnEdit(TableColumn.CellEditEvent<Caregiver, String> event) {
        String newValue = event.getNewValue();

        if (newValue == null || newValue.isBlank()) {

            Optional<ButtonType> result = AlertBuilder.alertBuildForEdits();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                event.getTableView().refresh();
            }
            return;
        }

        Caregiver caregiver = event.getRowValue();
        String columnId = event.getTableColumn().getId();

        if (columnId == null) {
            return;
        }
        switch (columnId) {
            case "columnSurname" -> caregiver.setSurname(newValue);
            case "columnFirstName" -> caregiver.setFirstName(newValue);
            case "columnDateOfBirth" -> caregiver.setDateOfBirth(newValue);
            case "columnPhoneNumber" -> caregiver.setPhoneNumber(newValue);
            case "columnStreet" -> caregiver.setStreet(newValue);
            case "columnPostalCode" -> caregiver.setPostalcode(newValue);
            case "columnCity" -> caregiver.setCity(newValue);
            case "columnTaxId" -> caregiver.setTaxid(newValue);
            case "columnQualification" -> caregiver.setQualification(newValue);
            default -> {
                return;
            }
        }
        this.doUpdate(event);
    }

    /**
     * Updates a caregiver by calling the method <code>update()</code> of {@link CaregiverDao}.
     *
     * @param event Event including the changed object and the change.
     */
    private void doUpdate(TableColumn.CellEditEvent<Caregiver, String> event) {
        try {
            this.dao.update(event.getRowValue());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Reloads all caregiver to the table by clearing the list of all caregiver and filling it again by all persisted
     * caregiver, delivered by {@link CaregiverDao}.
     */
    private void readAllAndShowInTableView() {
        this.caregiver.clear();
        this.dao = DaoFactory.getDaoFactory().createCaregiverDao();
        try {
            this.caregiver.addAll(this.dao.readAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * This method handles events fired by the button to delete caregiver. It calls {@link CaregiverDao} to delete the
     * caregiver from the database and removes the object from the list, which is the data source of the
     * <code>TableView</code>.
     */
    @FXML
    public void handleDelete() {

        Optional<ButtonType> result = AlertBuilder.alertForDelete();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Caregiver selectedItem = this.tableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try {
                    DaoFactory.getDaoFactory().createCaregiverDao().deleteById(selectedItem.getCid());
                    this.tableView.getItems().remove(selectedItem);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * This method handles the events fired by the button to add a caregiver. It collects the data from the
     * <code>TextField</code>s, creates an object of class <code>Caregiver</code> of it and passes the object to
     * {@link CaregiverDao} to persist the data.
     */
    @FXML
    public void handleAdd() {
        String surname = this.textFieldSurname.getText();
        String firstName = this.textFieldFirstName.getText();
        String phone = this.textFieldPhoneNumber.getText();
        String birthday = this.textFieldDateOfBirth.getText();
        LocalDate date = DateConverter.convertStringToLocalDate(birthday);
        String street = this.textFieldStreet.getText();
        String postalCode = this.textFieldPostalCode.getText();
        String city = this.textFieldCity.getText();
        String taxclass = this.textFieldTaxId.getText();
        String qualification = this.textFieldQualification.getText();
        try {
            this.dao.create(new Caregiver(firstName, surname, date, street, postalCode, city, taxclass, phone, qualification));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    /**
     * Clears all contents from all <code>TextField</code>s.
     */
    private void clearTextfields() {
        this.textFieldFirstName.clear();
        this.textFieldSurname.clear();
        this.textFieldDateOfBirth.clear();
        this.textFieldPhoneNumber.clear();
        this.textFieldStreet.clear();
        this.textFieldPostalCode.clear();
        this.textFieldCity.clear();
        this.textFieldTaxId.clear();
        this.textFieldQualification.clear();
    }

    private boolean areInputDataValid() {
        if (!this.textFieldDateOfBirth.getText().isBlank()) {
            try {
                DateConverter.convertStringToLocalDate(this.textFieldDateOfBirth.getText());
            } catch (Exception exception) {
                return false;
            }
        }

        return !this.textFieldFirstName.getText().isBlank() && !this.textFieldSurname.getText().isBlank() &&
                !this.textFieldDateOfBirth.getText().isBlank() && !this.textFieldPhoneNumber.getText().isBlank()
                && !this.textFieldStreet.getText().isBlank() && !this.textFieldPostalCode.getText().isBlank()
                && !this.textFieldCity.getText().isBlank() && !this.textFieldTaxId.getText().isBlank()
                && !this.textFieldQualification.getText().isBlank();
    }


}

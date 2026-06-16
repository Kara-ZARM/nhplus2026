package de.hitec.nhplus.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
/**
 * This Class holds methods to build alerts.
 * Alerts get fired while editing and trying to save an empty field, when delete patient or caregiver data and when a wrong format for a Date is used.
 */

public class AlertBuilder {

    /**
     * This method handles events fired when editing fields for patients or caregivers. It opens an alert when a user tries to save an empty field.
     */

    public static Optional<ButtonType> alertBuildForEdits(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Leere Felder können nicht gespeichert werden!");

        return alert.showAndWait();
    }

    /**
     * This method handles events fired by the button to delete patients or Caregivers. It opens an alert to reassure the infinitely deletion of the patients data.
     */

    public static Optional<ButtonType> alertForDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Daten endgültig löschen?");

        return alert.showAndWait();
    }
    /**
     * This method opens an alert when a user input for a Date is in the wrong Format.
     */

    public static Optional<ButtonType> alertForWrongDateFormat(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Ungültiges Format, Bitte neue Eingabe im Format: yyyy-MM-dd");

        return alert.showAndWait();
    }

}

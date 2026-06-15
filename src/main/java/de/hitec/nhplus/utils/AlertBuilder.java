package de.hitec.nhplus.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
/**
 * This Class holds methods to build alerts when trying to delete or edit a patients or caregivers data
 */

public class AlertBuilder {

    /**
     * This method handles events fired when editing fields for patients or caregivers. It opens an alert when user tries to save an empty field.
     * On confirmation (press 'OK')  {@link } is called.
     */

    public static Optional<ButtonType> alertBuildForEdits(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Leere Felder können nicht gespeichert werden!");

        return alert.showAndWait();
    }

    /**
     * This method handles events fired by the button to delete patients or Caregivers. It opens an alert to reassure the infinitely deletion of the patients data.
     * On confirmation (press 'OK')  {@link } is called.
     */

    public static Optional<ButtonType> alertForDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Daten endgültig löschen?");

        return alert.showAndWait();
    }

    public static Optional<ButtonType> alertForWrongDateFormat(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Ungültiges Format, Bitte neue Eingabe im Format: yyyy-MM-dd");

        return alert.showAndWait();
    }

}

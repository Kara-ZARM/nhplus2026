package de.hitec.nhplus.utils;

import javafx.scene.control.Label;

import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;

public abstract class MessageUtil {
    /**
     * <code>showError</code> displays an error at the position of <code>labelError</code>.
     * @param message is the message that is being displayed.
     */
    public static void showError(Label label, String message){
        label.setVisible(true);
        label.setText(message);
        label.setTextFill(RED);
    }

    public static void showSuccess(Label label, String message){
        label.setVisible(true);
        label.setText(message);
        label.setTextFill(GREEN);
    }
}

package de.hitec.nhplus.utils;

import javafx.scene.control.Label;

public abstract class MessageUtil {
    /**
     * <code>showError</code> displays an error at the position of <code>labelError</code>.
     * @param message is the message that is being displayed.
     */
    public static void showError(Label label, String message){
        label.setVisible(true);
        label.setText(message);
    }
}

package ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogManager {
    public static void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("title");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showConfirmationDialog(String title, String message, DialogOption yes, DialogOption no) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            yes.execute();
        } else {
            no.execute();
        }
    }
}

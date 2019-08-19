package ui.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static tools.Constants.WINDOW_HEIGHT;
import static tools.Constants.WINDOW_WIDTH;

public class MainController {

    @FXML
    public VBox dragTarget;

    @FXML
    public Button addTestButton;

    @FXML
    void onClickAddButton() {
        Stage window = (Stage) dragTarget.getScene().getWindow();

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Wybierz folder z testownikiem");
        File defaultDirectory = new File(System.getProperty("user.home"));
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(window);

        if (selectedDirectory != null) {
            PathHolder.folderPath = selectedDirectory.getPath();
            startMetadataScreen();
        }
    }

    @FXML
    void onDragOver(DragEvent event) {
        if (event.getGestureSource() != dragTarget
                && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    @FXML
    void onDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            PathHolder.folderPath = db.getFiles().get(0).getPath();
            success = true;
        }

        event.setDropCompleted(success);
        event.consume();

        if (success)
            startMetadataScreen();
    }

    private void startMetadataScreen() {
        Stage window = (Stage) dragTarget.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/metadata.fxml"));

        try {
            Scene scene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ui.metadataInput;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tools.TestConverter;
import tools.model.Metadata;
import ui.DialogManager;
import ui.main.PathHolder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import static tools.Constants.*;

public class MetadataController implements Initializable {
    @FXML
    public VBox root;

    @FXML
    public Label pathLabel;

    @FXML
    public TextField testNameField;

    @FXML
    public TextField authorsField;

    @FXML
    public TextField teacherField;

    @FXML
    public TextField universityField;

    @FXML
    public TextField facultyField;

    @FXML
    public TextField specializationField;

    @FXML
    public ComboBox<String> encodingComboBox;

    @FXML
    public ComboBox<String> yearComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pathLabel.setText("Testownik: "+PathHolder.folderPath);
        Platform.runLater( () -> root.requestFocus() );
        buildComboBoxes();
    }

    private void buildComboBoxes() {
        int year = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = year - 15; i <= year; i++) {
            yearComboBox.getItems().add(Integer.toString(i));
        }

        encodingComboBox.getItems().addAll("UTF8", "UTF16", "windows-1250");

        encodingComboBox.setVisibleRowCount(3);
        yearComboBox.setVisibleRowCount(3);
    }

    private Boolean verifyFields() {
        if (encodingComboBox.getValue() == null) {
            DialogManager.showErrorDialog("Błąd", "Wybierz rodzaj kodowania");
            return false;
        }

        if (testNameField.getText().isEmpty()) {
            DialogManager.showErrorDialog("Błąd", "Musisz podać nazwę testu");
            return false;
        }

        if (universityField.getText().isEmpty()) {
            DialogManager.showErrorDialog("Błąd", "Musisz podać nazwę uczelni");
            return false;
        }

        if (facultyField.getText().isEmpty()) {
            DialogManager.showErrorDialog("Błąd", "Musisz podać nazwę wydziału");
            return false;
        }

        if (specializationField.getText().isEmpty()) {
            DialogManager.showErrorDialog("Błąd", "Musisz podać nazwę kierunku");
            return false;
        }

        return true;
    }

    private String concatenateLocalPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(universityField.getCharacters());
        sb.append("/");
        sb.append(facultyField.getCharacters());
        sb.append("/");
        sb.append(specializationField.getCharacters());

        if (!teacherField.getText().isEmpty()) {
            sb.append("/");
            sb.append(teacherField.getCharacters());
        }

        if (yearComboBox.getValue() != null) {
            sb.append("/");
            sb.append(yearComboBox.getValue());
        }

        return sb.toString();
    }

    @FXML
    public void onExport() {
        if (verifyFields()) {
            Metadata metadata = new Metadata(testNameField.getText(), concatenateLocalPath(), authorsField.getText());
            String encoding = UTF8;

            switch (encodingComboBox.getValue()) {
                case "UTF8":
                    encoding = UTF8;
                    break;
                case "UTF16":
                    encoding = UTF16;
                    break;
                case "windows-1250":
                    encoding = WIN1250;
                    break;
            }

            Boolean success = true;
            try {
                TestConverter tc = new TestConverter(PathHolder.folderPath, encoding);
                tc.convertToZip(metadata);
            } catch (Exception e) {
                DialogManager.showErrorDialog("Błąd parsowania", e.getMessage());
                onCancel();
                success = false;
            }

            if (success) {
                DialogManager.showConfirmationDialog("Sukces",
                        "Udało się przekształcić tekst, otworzyć folder z paczką?",
                        () -> {
                            try {
                                Desktop.getDesktop().open(new File(PathHolder.folderPath));
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                onCancel();
                            }
                        }, this::onCancel);
            }
        }
    }

    @FXML
    public void onCancel() {
        Stage window = (Stage) root.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/main.fxml"));

        try {
            Scene scene = new Scene(loader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

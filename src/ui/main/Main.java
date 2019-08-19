package ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.DialogManager;

import static tools.Constants.*;

public class Main extends Application {
    @Override
    public void start(Stage window) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/main.fxml"));
        Parent root = loader.load();

        window.setOnCloseRequest(e -> {showExitDialog(window); e.consume();});

        window.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));

        window.setTitle(APP_NAME);
        window.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private void showExitDialog(Stage window) {
        DialogManager.showConfirmationDialog
                ("Wyjście","Czy na pewno chcesz wyjść?", window::close, () -> System.out.println("Cancelled"));
    }
}

package start;

/**
 * Created by 4 on 28.02.2016.
 */
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root, 1280, 670);

        stage.setMinWidth(1280);
        stage.setMinHeight(670);
        stage.getIcons().add(new Image("/images/iconApp.png"));
        primaryStage.setResizable(false);
        scene.getStylesheets().add("/styles/main.css");
        stage.setTitle("LOWN");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
package start;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import objects.Car;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 4 on 02.03.2016.
 */
public class Store {
    private static Scene sceneStore;
    private static Parent rootStore;

    public static ArrayList<Car> Cars = new ArrayList<Car>();

    public void showScene() {
        File file = new File("./src/main/resources/files/store");
        try {
            Scanner out = new Scanner(file);
            String[] car;
            out.nextLine();
            while (out.hasNextLine()){
                car=out.nextLine().split(";");
                System.out.println("");
                Cars.add(new Car(car[0],car[1],(Double.parseDouble(car[2])), (Double.parseDouble(car[3])),
                        (Double.parseDouble(car[4])),(Double.parseDouble(car[5])),(Double.parseDouble(car[6])),
                        (Boolean.parseBoolean(car[7])),(Boolean.parseBoolean(car[8]))));
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при чтении файла" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }




        //
        //  Cars.add(new Car("SABRE", "./resources/images/sabre.png", 5, 130, 3000, 76, 159, false, false));
//        Cars.add(new Car("BANHEE", "./resources/images/banhee.png", 10, 180, 6000, 77, 174, false, false));
        try {
            rootStore = FXMLLoader.load(getClass().getResource("/fxml/store.fxml"));

        } catch (IOException e) {
            System.out.println("Ошибка подключения Store.fxml !!");
        }

        sceneStore = new Scene(rootStore, 1290, 680);
        sceneStore.getStylesheets().add("/styles/store.css");
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneStore);

        sceneStore.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root, 1290, 680);

                    scene.getStylesheets().add("/styles/main.css");
                    MainApp.stage.setTitle("BLOWN");
                    MainApp.stage.setScene(scene);
                }
            }
        });
    }
}

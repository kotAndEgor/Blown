package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * Created by Егор on 29.02.2016.
 */
public class SettingController {
    @FXML
    private RadioButton radioBtnUp;
    @FXML
    private RadioButton radioBtnDown;
    public static String controll = "up";

    @FXML
    private void initialize(){
        File file = new File("./src/main/resources/files/setting");
        try {
            Scanner in = new Scanner(file);
            while(in.hasNext()){
                controll = in.nextLine();
            }
            in.close();
            if(controll.equals("up")) {
                radioBtnUp.setSelected(true);
            }else{
                if(controll.equals("down")){
                    radioBtnDown.setSelected(true);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при чтении файла" + e);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла" + e);
        }
    }

    public static String getControll(){
        File file = new File("./src/main/resources/files/setting");
        try {
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                controll = in.nextLine();
            }
        } catch (FileNotFoundException e) {

        }
        return controll;
    }

    public void checkRadio(ActionEvent actionEvent) {
        if(radioBtnUp.isSelected()){
            controll = "up";
        }
        if(radioBtnDown.isSelected()){
            controll = "down";
        }
    }
}

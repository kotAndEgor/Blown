package start;


import controllers.SettingController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Created by 4 on 02.03.2016.
 */
public class NewGame{

    final static int HEIGHT = 1280;
    final static int WIDTH = 680;

    final static Image BACKGROUND_IMAGE1 = new Image("/images/road1.jpg");
    final static Image BACKGROUND_IMAGE2 = new Image("/images/road2.jpg");
    final static Image BACKGROUND_IMAGE3 = new Image("/images/road3.jpg");

    private ImageView background1;
    private ImageView background2;
    private ImageView background3;

    private Scene sceneNewGame;
    private Group root;
    private Timeline timeline;
    private KeyFrame keyFrame;
    private KeyCode[] controll = new KeyCode[4];//массив в котором коды клавиш, которые используются для управления

    double offsetY = 1;//смещение по Y
    double speedCar = 5;//скорость машины, необходимо получить из файла

    public void showScene(){

        identifyControll();//определяем управление

        background1 = new ImageView(BACKGROUND_IMAGE1);
        background2 = new ImageView(BACKGROUND_IMAGE2);
        background3 = new ImageView(BACKGROUND_IMAGE3);

        root = new Group();
        root.getChildren().add(0, background1); root.getChildren().get(0).setLayoutY(0);
        root.getChildren().add(1, background2);
        root.getChildren().add(2, background3);

        sceneNewGame = new Scene(root, HEIGHT, WIDTH);
        MainApp.stage.setTitle("BLOWN");
        MainApp.stage.setScene(sceneNewGame);

        //обработчик событий для клавишь клавиатуры в игре
        sceneNewGame.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.ESCAPE){
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
                    timeline.stop();

                }else{
                    //необходимо настраивать + нужен класс CAR доработанный
                    if(event.getCode() == controll[0]){
                        if(speedCar > 1){
                            resetTimer(timeline, speedCar);
                            speedCar -= 0.05;

                        }
                    }else{
                        if(event.getCode() == controll[2]){
                            if(speedCar < 5){
                                resetTimer(timeline, speedCar);
                                speedCar += 0.05;
                            }
                        }
                    }
                }

            }
        });
        keyFrame = new KeyFrame(Duration.millis(speedCar), ae -> moveBackground());
        timeline = new Timeline(new KeyFrame(Duration.millis(speedCar), ae -> moveBackground()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //для изменения времени, обновление таймера
    private void resetTimer(Timeline timer, double timerInterval) {
        keyFrame = new KeyFrame(Duration.millis(timerInterval),ae -> moveBackground());
        timer.stop();
        timer.getKeyFrames().setAll(keyFrame);
        timer.play();
    }

    //меняем слои, выбираем из 3 слуйчайны и подставляем + двигаем все слои
    public void moveBackground(){
        if(root.getChildren().get(0).getLayoutY() == 0){
            int randomChildren = (int)(Math.random() * 2) + 1;// второй(1) или третий(2) слой берем
            root.getChildren().get(randomChildren).setLayoutY(-720);
        }else{
            if(root.getChildren().get(1).getLayoutY() == 0){
                int range = (int)(Math.random() * 2);
                int randomChildren = (range == 0) ? 0 : 2;//первый(0) или третий(2) слой берем
                root.getChildren().get(randomChildren).setLayoutY(-720);
            }else{
                if(root.getChildren().get(2).getLayoutY() == 0){
                    int randomChildren = (int)(Math.random() * 2);//первый(0) или второй(1) слой берем
                    root.getChildren().get(randomChildren).setLayoutY(-720);
                }
            }
        }
        root.getChildren().get(0).setLayoutY((root.getChildren().get(0).getLayoutY() + offsetY));
        root.getChildren().get(1).setLayoutY((root.getChildren().get(1).getLayoutY() + offsetY));
        root.getChildren().get(2).setLayoutY((root.getChildren().get(2).getLayoutY() + offsetY));

    }

    //получаем вид управления, который выбран в игре в setting
    public void identifyControll(){
        if(SettingController.controll == "up" || SettingController.controll == ""){
            controll[0] = KeyCode.W; controll[1] = KeyCode.A;
            controll[2] = KeyCode.S; controll[3] = KeyCode.D;
        }else{
            controll[0] = KeyCode.UP; controll[1] = KeyCode.LEFT;
            controll[2] = KeyCode.DOWN; controll[3] = KeyCode.RIGHT;
        }
    }



}

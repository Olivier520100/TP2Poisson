package ca.qc.bdeb.sim203.tp2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class MainJavaFX extends Application {

    private HashMap<String, String> dictionary = new HashMap<>();
    private ArrayList<String> botMessages = new ArrayList<>();
    private ArrayList<String> userMessages = new ArrayList<>();
    private int height = 750;
    private int width = 450;

    double currentx = 0;
    double currenty = 50;

    long lasttime = 0;




    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)throws Exception {

        int h = 640;
        int w = 960;

        Pane root = new Pane();
        Scene scene = new Scene(root, w, h);
        Canvas canvas = new Canvas(w, h);
        root.getChildren().add(canvas);
        GraphicsContext context = canvas.getGraphicsContext2D();

        Square sqr1 = new Square(50, 50, 20, 20);



        scene.setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.LEFT ){
                sqr1.moveLeft();

            } else if (e.getCode()  == KeyCode.RIGHT){
                sqr1.moveRight();

            }
            if (e.getCode() == KeyCode.SPACE ){
                System.out.println("Jump");
                sqr1.jump();

            }
        });
        scene.setOnKeyReleased(e->{
            if (e.getCode() == KeyCode.LEFT ){
                sqr1.stopMove();

            } else if (e.getCode()  == KeyCode.RIGHT){
                sqr1.stopMove();

            }

        });

        AnimationTimer tm = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lasttime == 0) { // Check to ensure we only set lasttime during the first iteration
                    lasttime = now;
                    return;
                }
                double dt = (now - lasttime) * 1e-9;

                context.clearRect(0, 0, w, h);

                sqr1.update(dt,w,h);

                sqr1.draw(context);

                lasttime = now;
            }
        };


        tm.start();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}

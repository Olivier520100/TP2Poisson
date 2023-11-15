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

        Game game = new Game(w,h);

        scene.setOnKeyPressed(e->{

            if (e.getCode()==KeyCode.RIGHT){
                game.rightPress();
            } else if (e.getCode()==KeyCode.LEFT) {
                game.leftPress();
            } else if (e.getCode()==KeyCode.UP){
                game.upPress();
            } else if (e.getCode()==KeyCode.DOWN){
                game.downPress();
            }

        });
        scene.setOnKeyReleased(e->{

            if (e.getCode()==KeyCode.RIGHT || e.getCode()==KeyCode.LEFT){
                game.horizontalRelease();
            } else if (e.getCode()==KeyCode.UP || e.getCode()==KeyCode.DOWN){
                game.verticalRelease();
            }


        });
        game.drawGame(context);
        AnimationTimer tm = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lasttime == 0) { // Check to ensure we only set lasttime during the first iteration
                    lasttime = now;
                    return;
                }
                double dt = (now - lasttime) * 1e-9;
                context.clearRect(0,0,w,h);
                game.updateGame(dt, w, h);
                game.drawGame(context);

                lasttime = now;
            }
        };


        tm.start();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}

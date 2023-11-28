package ca.qc.bdeb.sim203.tp2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainJavaFX extends Application {

    long lasttime = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)throws Exception {

        int h = 520;
        int w = 900;

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
            }
            if (e.getCode()==KeyCode.UP){
                game.upPress();
            } else if (e.getCode()==KeyCode.DOWN){
                game.downPress();
            }
            if (e.getCode()==KeyCode.SPACE){
                game.spacePress();
            }

        });
        scene.setOnKeyReleased(e->{

            if (e.getCode()==KeyCode.RIGHT || e.getCode()==KeyCode.LEFT){
                game.horizontalRelease();
            }
            if (e.getCode()==KeyCode.UP || e.getCode()==KeyCode.DOWN){
                game.verticalRelease();
            }
            if (e.getCode()==KeyCode.SPACE){
                game.spaceRelease();
            }
        });
        scene.setOnMouseClicked(e->{
            game.screenClick(e.getX(),e.getY());
        });
        AnimationTimer tm = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lasttime == 0) { // Check to ensure we only set lasttime during the first iteration
                    lasttime = now;
                    return;
                }
                double dt = (now - lasttime) * 1e-9;
                game.update(context,dt);

                lasttime = now;
            }
        };


        tm.start();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}

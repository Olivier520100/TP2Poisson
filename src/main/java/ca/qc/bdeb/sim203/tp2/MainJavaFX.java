package ca.qc.bdeb.sim203.tp2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @Authors Olivier Saint-Vincent et Joe El-Hachem
 * @DA 2238102 et 2265609
 * @DESCRIPTION Charlotte la Barbotte est un jeu de poisson de style side scrolling shoot-em-ups.
 */

public class MainJavaFX extends Application {

    private static final int WINDOW_HEIGHT = 520;
    private static final int WINDOW_WIDTH = 900;
    private long lastUpdateTime = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext context = canvas.getGraphicsContext2D();

        Game game = new Game(WINDOW_WIDTH, WINDOW_HEIGHT);
        inputHandling(scene, game);
        startGameLoop(context, game);


        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private void inputHandling(Scene scene, Game game) {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case RIGHT -> game.rightPress();
                case LEFT -> game.leftPress();
                case UP -> game.upPress();
                case DOWN -> game.downPress();
                case SPACE -> game.spacePress();
                case D -> game.debug();
                case Q -> game.qPress();
                case W -> game.wPress();
                case E -> game.ePress();
                case R -> game.rPress();
                case T -> game.tPress();
                case ESCAPE -> Platform.exit();
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT) {
                game.horizontalRelease();
            }
            if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
                game.verticalRelease();
            }
            if (e.getCode() == KeyCode.SPACE) {
                game.spaceRelease();
            }
        });

        scene.setOnMouseClicked(e -> game.screenClick(e.getX(), e.getY()));
    }

    private void startGameLoop(GraphicsContext context, Game game) {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdateTime == 0) {
                    lastUpdateTime = now;
                    return;
                }
                double deltaTime = (now - lastUpdateTime) * 1e-9;
                game.update(context, deltaTime);
                lastUpdateTime = now;
            }
        };
        gameLoop.start();
    }
}
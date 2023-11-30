package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class Game {
    int levelNumber = 1;
    int maxHealth = 4;
    int lastHealth = 4;
    Level level;
    double width;
    double height;
    boolean inGame = false;
    Menu menu = new Menu();


    public void downPress() {
        level.downPress();
    }

    public void upPress() {
        level.upPress();
    }

    public void leftPress() {
        level.leftPress();
    }

    public void rightPress() {
        level.rightPress();
    }

    public void verticalRelease() {
        level.verticalRelease();
    }

    public void horizontalRelease() {
        level.horizontalRelease();
    }

    public void spacePress() {
        level.spacePress();
    }

    public void spaceRelease() {
        level.spaceRelease();
    }
    public void debug() { level.debug();}
    public void screenClick(double x, double y) {
        if (!inGame) {
            menu.windowClick(x, y);
        }
    }

    public Game(double width, double height) {
        this.width = width;
        this.height = height;
        level = new Level(width, height, levelNumber,maxHealth);
    }

    public void update(GraphicsContext context, double dt) {

        if (inGame) {
            if (!level.isLevelEnd()) {
                context.clearRect(0, 0, width, height);
                level.updateGame(dt, width, height);
                level.drawGame(context);
            } else {
                if (!level.isLevelDead()) {
                    levelNumber += 1;
                    lastHealth = level.getHealth();
                    level = new Level(width, height, levelNumber,lastHealth);
                } else {
                    menu = new Menu();
                    inGame = false;
                    levelNumber = 1;
                    level = new Level(width, height, levelNumber,maxHealth);
                }
            }

        } else {
            menu.draw(context);
            inGame = menu.isToGame();
        }
    }

    public void qPress() {
        level.setProjectileType1();
    }

    public void wPress() {
        level.setProjectileType2();
    }

    public void ePress() {
        level.setProjectileType3();
    }

    public void rPress() {
        level.setMaxHealth();
    }

    public void tPress() {
        level.nextLevel();
    }
}

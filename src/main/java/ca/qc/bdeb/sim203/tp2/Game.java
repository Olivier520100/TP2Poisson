package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class Game {
    int levelNumber = 1;
    Level level;

    double width;
    double height;

    public void downPress(){
        level.downPress();
    }
    public void upPress(){
        level.upPress();
    }
    public void leftPress(){
        level.leftPress();
    }
    public void rightPress(){
        level.rightPress();
    }
    public void verticalRelease(){
        level.verticalRelease();
    }
    public void horizontalRelease(){
        level.horizontalRelease();
    }
    public void spacePress(){
        level.spacePress();
    }
    public void spaceRelease(){ level.spaceRelease(); }

    public Game(double width, double height) {
        this.width = width;
        this.height = height;
        level = new Level(width,height, levelNumber);
    }
    void update(GraphicsContext context, double dt){
        if (!level.isLevelEnd()) {
            context.clearRect(0, 0, width, height);
            level.updateGame(dt, width, height);
            level.drawGame(context);
        } else {
            levelNumber+=1;
            level = new Level(width,height, levelNumber);
        }
    }
}

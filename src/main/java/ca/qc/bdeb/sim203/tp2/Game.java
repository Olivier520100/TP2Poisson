package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class Game {

    Player player;

    Camera camera;

    public Game(double width, double height) {
        player = new Player(50,50,50,50);
        camera = new Camera(0,0,width, height);

    }

    public void downPress(){
        player.moveDown();
    }
    public void upPress(){
        player.moveUp();
    }
    public void leftPress(){
        player.moveLeft();
    }
    public void rightPress(){
        player.moveRight();
    }
    public void verticalRelease(){
        player.stopMoveVertical();
    }
    public void horizontalRelease(){
        player.stopMoveHorizontal();
    }
    public void updateGame(double dt,double width, double height){
        player.update(dt,width,height,camera);
    }
    public void drawGame(GraphicsContext context){
        player.draw(context,camera);

    }



}

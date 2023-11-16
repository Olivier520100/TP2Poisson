package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class Game {

    Player player;

    Camera camera;
    GameObject []go = new GameObject[40];

    public Game(double width, double height) {
        player = new Player(50,50,50,50);
        camera = new Camera(0,0,width, height);
        int x = 0;
        for (int i = 0; i < go.length; i++) {
            go[i] = new GameObject(x, height - 50, 20, 50);
            x += 100;
        }
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

        for (GameObject gameObject : go) {
            gameObject.draw(context, camera);
        }

    }



}

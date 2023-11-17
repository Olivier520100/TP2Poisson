package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class Level {

    Player player;
    Enemy []enemies;

    Baril baril;

    int niveau;
    Camera camera;
    GameObject []go = new GameObject[40];

    double lastspawn = 0;

    double respawntime;

    public Level(double width, double height, int niveau) {
        player = new Player(50,50,50,50);
        camera = new Camera(0,0,width, height);
        this.niveau = niveau;
        respawntime = 0.75 + 1/(Math.sqrt(niveau));
        enemyCreation();
        int x = 0;
        for (int i = 0; i < go.length; i++) {
            go[i] = new GameObject(x, height - 50, 20, 50);
            x += 100;
        }
        baril = new Baril(width/2,height/2,100,100,height);
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
        lastspawn+=dt;
        if (lastspawn > respawntime){
            lastspawn=0;
            enemyCreation();
        }
        player.update(dt,width,height,camera);
        player.objectCollision(enemies,baril);
        enemyUpdate(dt,width,height,camera);
        baril.update(dt,width,height,camera);
    }
    public void drawGame(GraphicsContext context){


        for (GameObject gameObject : go) {
            gameObject.draw(context, camera);
        }
        player.draw(context,camera);
        enemyDraw(context,camera);
        baril.draw(context,camera);
    }
    public void enemyUpdate(double dt, double width, double height,Camera camera){
        for (Enemy enemy : enemies) {
            enemy.update(dt,width,height,camera);

        }
    }
    public void enemyDraw(GraphicsContext context, Camera camera){
        for (Enemy enemy : enemies) {
            enemy.draw(context, camera);
        }
    }


    public void enemyCreation(){
        Random rand = (new Random());
        int enemycount = rand.nextInt(1,6);
        enemies = new Enemy[enemycount];
        for (int i = 0; i < enemies.length; i++) {
            //Make constant for the 50 below please
            enemies[i] = new Enemy(camera.getX()+ camera.getWidth()+50, rand.nextDouble(camera.getHeight()/5,4*camera.getHeight()/5), 50, 50, niveau);
        }
    }



}

package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Random;

public class Level {

    Player player;
    ArrayList<Enemy> enemies;
    Baril baril;

    ArrayList<Projectile> projectiles = new ArrayList<>();

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
        baril = new Baril(width/2,height/2,70,83,height);
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
    public void spacePress(){ player.shootDown(); }
    public void spaceRelease(){ player.shootRelease(); }

    public void updateGame(double dt,double width, double height){
        lastspawn+=dt;
        if (lastspawn > respawntime){
            lastspawn=0;
            enemyCreation();
        }
        player.update(dt,width,height,camera,projectiles,enemies,baril);
        enemyUpdate(dt,width,height,camera);
        baril.update(dt,width,height,camera);
        projectileUpdate(dt,width,height,camera,enemies);
    }
    public void drawGame(GraphicsContext context){


        for (GameObject gameObject : go) {
            gameObject.draw(context, camera);
        }
        player.draw(context,camera);
        enemyDraw(context,camera);
        baril.draw(context,camera);
        projectileDraw(context,camera);
    }
    public void enemyUpdate(double dt, double width, double height, Camera camera){
        for (Enemy enemy : enemies) {
            enemy.update(dt, width, height, camera);
        }
    }

    public void enemyDraw(GraphicsContext context, Camera camera){
        for (Enemy enemy : enemies) {
            enemy.draw(context, camera);
        }
    }

    public void projectileUpdate(double dt, double width, double height, Camera camera, ArrayList<Enemy> enemies){
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile projectile = projectiles.get(i);
            projectile.update(dt, width, height, camera, enemies);
            if (projectile.isUsed() || projectile.isOob()) {
                projectiles.remove(i);
            }
        }
    }

    public void projectileDraw(GraphicsContext context, Camera camera){
        for (Projectile projectile : projectiles) {
            projectile.draw(context, camera);
        }
    }


    public void enemyCreation(){
        Random rand = (new Random());
        int enemycount = rand.nextInt(1,6);
        enemies = new ArrayList<>();
        for (int i = 0; i < enemycount; i++) {
            //Make constant for the 50 below please
            enemies.add(new Enemy(camera.getX() + camera.getWidth() + 50, rand.nextDouble(camera.getHeight()/5, 4*camera.getHeight()/5), 50, 50, niveau));
        }
    }



}

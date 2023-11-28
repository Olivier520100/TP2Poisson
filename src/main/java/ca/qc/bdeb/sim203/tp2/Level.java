package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Level {

    Player player;
    ArrayList<Enemy> enemies;
    Baril baril;
    MainText levelStart;
    MainText levelDeadText;
    ArrayList<Projectile> projectiles = new ArrayList<>();
    double levellength;
    int niveau;
    Camera camera;
    ArrayList<BackgroundElement> backgroundElements = new ArrayList<>();

    double lastspawn = 0;
    double displaytime = 4;

    double respawntime;

    boolean levelEnd = false;
    Bar topbar = new Bar();

    boolean levelDead = false;

    public Level(double width, double height, int niveau) {
        player = new Player(50,50);
        camera = new Camera(0,0,width, height);
        this.niveau = niveau;
        respawntime = 0.75 + 1/(Math.sqrt(niveau));
        levellength = 8 * width;
        double placementbaril = ((new Random()).nextDouble(20,100)/100)*levellength;
        baril = new Baril(placementbaril,height/2, height);
        enemyCreation();
        backgroundElementsCreation(height);
        levelStart = new MainText("Niveau " + niveau, width,height);
        levelDeadText = new MainText("Mort", width,height);
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
    public void spacePress(){
        player.shootDown();
    }
    public void spaceRelease(){ player.shootRelease(); }

    public void updateGame(double dt,double width, double height){
        displaytime-=dt;
        levelEndCheck();
        levelDeadCheck();


        if (!levelEnd && !levelDead) {
            lastspawn += dt;
            if (lastspawn > respawntime) {
                lastspawn = 0;
                enemyCreation();
            }
            player.update(dt, width, height, camera, projectiles, enemies, baril,levellength);
            enemyUpdate(dt, width, height, camera, projectiles);
            baril.update(dt, width, height, camera);
            projectileUpdate(dt, width, height, camera, enemies);
            topbar.setHeartsleft(player.getHealth());
            topbar.setCurrent(player.getPT());
        }
        if (levelDead){
            displaytime = 4;
        }

    }
    public void drawGame(GraphicsContext context){

        context.setFill((Color.LIGHTBLUE));
        context.fillRect(0,0,camera.getWidth(), camera.getHeight());
        for (BackgroundElement backgroundElement : backgroundElements) {
            backgroundElement.draw(context, camera);
        }
        player.draw(context,camera);
        enemyDraw(context,camera);
        baril.draw(context,camera);
        projectileDraw(context,camera);
        topbar.draw(context);

        if (displaytime > 0){
            levelStart.draw(context, camera,0);
        }
        if (levelDead){
            if (displaytime > 0){
                levelDeadText.draw(context, camera,player.getX());
            } else {
                levelEnd = true;
            }
        } else {
            if (displaytime > 0){
                levelStart.draw(context, camera,0);
            }
        }
    }
    public void enemyUpdate(double dt, double width, double height, Camera camera, ArrayList<Projectile> projectiles){
        for (Enemy enemy : enemies) {
            enemy.update(dt, width, height, camera,projectiles);
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
            if (projectile.isUsed()/* || (projectile.isOob()*/) {
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
            double enemyheight = rand.nextDouble(50,120);
            double enemywidth = enemyheight/120 * 104;
            enemies.add(new Enemy(camera.getX() + camera.getWidth() + 50, rand.nextDouble(camera.getHeight()/5, 4*camera.getHeight()/5), enemyheight, enemywidth, niveau));
        }
    }
    public void levelEndCheck(){
        levelEnd = (player.getX() > levellength);
    }
    public void levelDeadCheck(){
        levelDead = (player.isDead());
    }

    public void backgroundElementsCreation(double height){
        int x = 0;
        while (x < levellength*9/8) {
            backgroundElements.add((new BackgroundElement(x, height)));
            x += (new Random()).nextInt(50,100)+80;
        }
    }

    public boolean isLevelEnd() {
        return levelEnd;
    }
    public boolean isLevelDead(){
        return levelDead;
    }
}

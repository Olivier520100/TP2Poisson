package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Player extends Actor{


    Image movingImage;
    Image damagedImage;
    boolean horizontalPressed = false;
    boolean verticalPressed = false;

    boolean directionRight = false;
    boolean directionUp = false;

    boolean shootPressed = false;

    double invisibilitytimer = 0;
    final double invisibilityconst = 2;
    double shoottimer = 0;
    final double shootconst = 0.5;
    final int maxHealth = 5;

    ProjectileLauncher projectileLauncher;


    public Player(double x, double y){
        super(x, y, 102, 90);
        health = 5;
        projectileLauncher = new ProjectileLauncher();
        baseImage = new Image("./charlotte.png");
        movingImage = new Image("./charlotte-avant.png");
        damagedImage = new Image("./charlotte-outch.png");

    }

    void moveLeft() {
        horizontalPressed = true;
        directionRight = false;

    }

    void moveRight() {
        horizontalPressed = true;
        directionRight = true;
    }

    void stopMoveHorizontal() {
        horizontalPressed = false;

    }

    void moveUp() {
        verticalPressed = true;
        directionUp = true;

    }

    void moveDown() {
        verticalPressed = true;
        directionUp = false;
    }

    void shootDown() {
        shootPressed=true;
    }
    void shootRelease() {
        shootPressed=false;
    }

    void stopMoveVertical() {
        verticalPressed = false;
    }

    @Override
    void calculatedx(double dt) {
        if (horizontalPressed) {
            if (Math.abs(speedX) < maximumSpeed) {
                if (directionRight) {
                    speedX += acceleration * dt;
                    if (speedX > maximumSpeed) {
                        speedX = maximumSpeed;
                    }
                } else {
                    speedX -= acceleration * dt;
                    if (Math.abs(speedX) > maximumSpeed) {
                        speedX = -maximumSpeed;
                    }
                }
            }
        } else {
            if (speedX > 0) {
                speedX -= acceleration * dt;
                if (speedX < 0) {
                    speedX = 0;
                }
            } else if (speedX < 0) {
                speedX += acceleration * dt;
                if (speedX > 0) {
                    speedX = 0;
                }
            }

        }
    }
    @Override
    void calculatedy(double dt) {
        if (verticalPressed) {
            if (Math.abs(speedY) < maximumSpeed) {
                if (directionUp) {
                    speedY -= acceleration * dt;
                } else {
                    speedY += acceleration * dt;
                }
            }
        } else {
            if (speedY > 0) {
                speedY -= acceleration * dt;
                if (speedY < 0) {
                    speedY = 0;
                }
            } else if (speedY < 0) {
                speedY += acceleration * dt;
                if (speedY > 0) {
                    speedY = 0;
                }
            }

        }
    }
    @Override
    public void checkCollision(double screenWidth, double screenHeight, Camera camera) {
        if (y + height > screenHeight) {
            y = screenHeight - height;
            speedY = 0;
        } else if (y < 0) {
            y = 0;
            speedY = 0;
        }
        if (x < camera.getX()) {
            x = camera.getX();
            speedX = 0;
        }

    }
    void shoot(ArrayList<Projectile> projectiles){
        if (shootPressed && shoottimer < 0) {
            projectileLauncher.shoot(x + width, y + height / 4, projectiles);
            shoottimer = shootconst;
        }
    }

    void update(double dt, double screenWidth, double screenheight, Camera camera, ArrayList<Projectile> projectiles, ArrayList<Enemy> enemies, Baril baril) {
        invisibilitytimer-=dt;
        shoottimer-=dt;

        physicsCalculate(dt);
        checkCollision(screenWidth, screenheight, camera);
        moveObject(dt);
        objectCollision(enemies,baril);
        shoot(projectiles);
        moveCamera(camera,dt);
    }
    void objectCollision(ArrayList<Enemy> enemies,Baril baril){
        boolean hit = false;
        if (invisibilitytimer < 0) {
            for (Enemy enemy : enemies) {
                hit = checkCollisionWithObject(enemy);
                if (hit) {
                    invisibilitytimer=invisibilityconst;
                    health-=1;
                    System.out.println("Hit");
                    break;
                }
            }

        }
        if (!baril.isOuvert()){
            if (checkCollisionWithObject(baril)){
                baril.setOuvert(true);
                projectileLauncher.setCurrent(baril.getProjectileInside());
            }

        }
    }
    @Override
    void draw(GraphicsContext context, Camera camera) {

        context.setFill((Color.GREEN));
        context.fillText("Health: " + health, 20,20);
        double displayx = x - camera.getX();
        if (speedX > 0 && health >= maxHealth) {
            context.drawImage(movingImage, displayx, y);
        } else if (health >= maxHealth){
            context.drawImage(baseImage, displayx, y);
        } else {
            context.drawImage(damagedImage,displayx,y);
        }

    }
    void moveCamera(Camera camera, double dt){
        if (((x - camera.getX()) >= camera.getWidth()/5)){
            camera.setX(camera.getX()+speedX*dt);
        }
    }

}

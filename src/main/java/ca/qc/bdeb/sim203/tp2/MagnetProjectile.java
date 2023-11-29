package ca.qc.bdeb.sim203.tp2;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class MagnetProjectile extends Projectile {

    double forceX = 0;
    double forceY = 0;

    public MagnetProjectile(double x, double y) {
        super(x, y, 35, 29);
        System.out.println("MAGNET!");
        speedX = 300;
        baseImage = new Image("./sardines.png");
    }

    void calculateForce(Enemy enemy) {
        if (x < enemy.getX()) {
            double dx = enemy.getX() - x;
            double dy = enemy.getY() - y;
            double distanceSquared = dx * dx + dy * dy;
            double distance = Math.sqrt(distanceSquared);
           /* if (distance < 0.01){

                distance = 0.01;
            }

            */
            double forceTotal = (1000 * 200 * 100) / distanceSquared;
            forceX += forceTotal * (dx / distance);
            forceY += forceTotal * (dy / distance);
        }
    }

    @Override
    void calculateDx(double dt) {
        speedX += dt * forceX;


        if (speedX < 300){
            speedX = 300;
        }else if (speedX > 500){
            speedX = 500 ;
        }



    }

    @Override
    void calculateDy(double dt) {
        speedY += dt * forceY;

        if (speedY > 500){
            speedY = 500;
        }else if (speedY < -500){
            speedY = -500;
        }



    }

    void sumForces(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            calculateForce(enemy);
        }
    }

    void resetForces() {
        forceY = 0;
        forceX = 0;
    }

    void update(double dt, double screenWidth, double screenheight, Camera camera, ArrayList<Enemy> enemies) {
        resetForces();
        sumForces(enemies);
        physicsCalculate(dt);
        checkCollision(screenWidth, screenheight, camera);
        moveObject(dt);
        outOfBounds(camera);
        objectCollision(enemies);
    }

    public void checkCollision(double screenWidth, double screenHeight, Camera camera) {
        if (y + height > screenHeight) {
            y = screenHeight - height;
            speedY = -speedY;
        } else if (y < 0) {
            y = 0;
            speedY = -speedY;
        }

    }
}


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
        imageDeBase = new Image("./sardines.png");
    }

    void calculateForce(Enemy enemy) {
        if (x < enemy.getX()) {
            double dx = enemy.getX() - x;
            double dy = enemy.getY() - y;
            double distanceSquared = dx * dx + dy * dy;
            double distance = Math.sqrt(distanceSquared);
            if (distance < 0.01){

                distance = 0.01;
            }
            double forceTotal = (1000 * 200 * 100) / distanceSquared;
            forceX += forceTotal * (dx / distance);
            forceY += forceTotal * (dy / distance);
        }
    }

    @Override
    void calculateDx(double dt) {
        speedX += dt * forceX;
        speedX = capValues(speedX,300,500);
    }

    @Override
    void calculateDy(double dt) {
        speedY += dt * forceY;
        speedY =  capValues(speedY,-500,500);
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

    public void preprocess(ArrayList<Enemy> enemies) {
        resetForces();
        sumForces(enemies);

    }
    void update(double dt, double screenheight) {
        super.update(dt);
        checkCollision(screenheight);

    }

    public void checkCollision(double screenHeight) {
        if (y + height > screenHeight) {
            y = screenHeight - height;
            speedY = -speedY;
        } else if (y < 0) {
            y = 0;
            speedY = -speedY;
        }

    }

    private double capValues(double value, double lowerCap, double upperCap){


        if (value < lowerCap){
            value = lowerCap;
        }else if (value > upperCap){
            value = upperCap;
        }

        return value;
    }

}


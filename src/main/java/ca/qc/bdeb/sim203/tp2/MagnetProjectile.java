package ca.qc.bdeb.sim203.tp2;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class MagnetProjectile extends Projectile {

    double forceX = 0;
    double forceY = 0;

    public MagnetProjectile(double x, double y) {
        super(x, y, 35, 29);

        System.out.println("MAGNET!");
        vitesseX = 300;

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
        vitesseX += dt * forceX;
        vitesseX = capValues(vitesseX,300,500);
    }

    @Override
    void calculateDy(double dt) {
        vitesseY += dt * forceY;
        vitesseY =  capValues(vitesseY,-500,500);
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
        if (y + hauteur > screenHeight) {
            y = screenHeight - hauteur;
            vitesseY = -vitesseY;
        } else if (y < 0) {
            y = 0;
            vitesseY = -vitesseY;
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


package ca.qc.bdeb.sim203.tp2;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class MagnetProjectile extends Projectile {

    double forceX = 0;
    double forceY = 0;

    /**
     * Constructeur
     *
     * @param x
     * @param y
     */
    public MagnetProjectile(double x, double y) {
        super(x, y, 35, 29);

        System.out.println("MAGNET!");
        vitesseX = 300;

        imageDeBase = new Image("./sardines.png");
    }

    /**
     * Calcule la force exerce par un enemy
     *
     * @param enemy
     */
    void calculateForce(Enemy enemy) {
        if (x < enemy.getX()) {
            double dx = enemy.getX() - x;
            double dy = enemy.getY() - y;
            double distanceAuCarre = dx * dx + dy * dy;
            double distance = Math.sqrt(distanceAuCarre);
            if (distance < 0.01) {

                distance = 0.01;
            }
            double forceTotal = (1000 * 200 * 100) / distanceAuCarre;
            forceX += forceTotal * (dx / distance);
            forceY += forceTotal * (dy / distance);
        }
    }

    /**
     * calcul de dx avec force
     *
     * @param dt Temps écoulé depuis le dernier calcul.
     */
    @Override
    void calculateDx(double dt) {
        vitesseX += dt * forceX;
        vitesseX = capValues(vitesseX, 300, 500);
    }

    @Override
    void calculateDy(double dt) {
        vitesseY += dt * forceY;
        vitesseY = capValues(vitesseY, -500, 500);
    }

    void sumForces(ArrayList<Enemy> ennemis) {
        for (Enemy ennemi : ennemis) {
            calculateForce(ennemi);
        }
    }

    void resetForces() {
        forceY = 0;
        forceX = 0;
    }

    public void preprocess(ArrayList<Enemy> ennemis) {
        resetForces();
        sumForces(ennemis);

    }

    void update(double dt, double hauteurEcran) {
        super.update(dt);
        checkCollision(hauteurEcran);

    }

    public void checkCollision(double hauteurEcran) {
        if (y + hauteur > hauteurEcran) {
            y = hauteurEcran - hauteur;
            vitesseY = -vitesseY;
        } else if (y < 0) {
            y = 0;
            vitesseY = -vitesseY;
        }

    }

    private double capValues(double valeur, double minimum, double maximum) {


        if (valeur < minimum) {
            valeur = minimum;
        } else if (valeur > maximum) {
            valeur = maximum;
        }

        return valeur;
    }

}


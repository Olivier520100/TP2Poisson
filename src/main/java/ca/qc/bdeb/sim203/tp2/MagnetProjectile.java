package ca.qc.bdeb.sim203.tp2;

import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Classe représentant un projectile magnétique dans le jeu.
 * Gère le comportement du projectile, notamment son interaction avec les ennemis.
 */
public class MagnetProjectile extends Projectile {

    private static final double DEFAULT_SPEED_X = 300;
    private static final double MINIMUM_DISTANCE = 0.01;
    private static final double FORCE_CONSTANT = 1000 * 200 * 100;
    private static final String IMAGE_PATH = "./sardines.png";
    private static final double WIDTH = 35;
    private static final double HEIGHT = 29;
    private static final double SPEED_CAP_LOWER_X = 300;
    private static final double SPEED_CAP_UPPER_X = 500;
    private static final double SPEED_CAP_LOWER_Y = -500;
    private static final double SPEED_CAP_UPPER_Y = 500;

    private double forceX = 0;
    private double forceY = 0;

    public MagnetProjectile(double x, double y) {
        super(x, y, WIDTH, HEIGHT);
        System.out.println("MAGNET!");
        speedX = DEFAULT_SPEED_X;
        baseImage = new Image(IMAGE_PATH);
    }

    /**
     * Calcule la force exercée par un ennemi sur le projectile.
     *
     * @param enemy L'ennemi qui influence le projectile.
     */
    void calculateForce(Enemy enemy) {
        if (x < enemy.getX()) {
            double dx = enemy.getX() - x;
            double dy = enemy.getY() - y;
            double distanceSquared = dx * dx + dy * dy;
            double distance = Math.max(Math.sqrt(distanceSquared), MINIMUM_DISTANCE);
            double forceTotal = FORCE_CONSTANT / distanceSquared;
            forceX += forceTotal * (dx / distance);
            forceY += forceTotal * (dy / distance);
        }
    }

    @Override
    void calculateDx(double dt) {
        speedX += dt * forceX;
        speedX = capValues(speedX, SPEED_CAP_LOWER_X, SPEED_CAP_UPPER_X);
    }

    @Override
    void calculateDy(double dt) {
        speedY += dt * forceY;
        speedY = capValues(speedY, SPEED_CAP_LOWER_Y, SPEED_CAP_UPPER_Y);
    }

    /**
     * Calcule la somme des forces exercées par tous les ennemis.
     *
     * @param enemies Liste des ennemis affectant le projectile.
     */
    void sumForces(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            calculateForce(enemy);
        }
    }

    /**
     * Réinitialise les forces à zéro.
     */
    void resetForces() {
        forceX = 0;
        forceY = 0;
    }

    /**
     * Prépare le projectile en calculant les forces avant son mouvement.
     *
     * @param enemies Liste des ennemis.
     */
    public void preprocess(ArrayList<Enemy> enemies) {
        resetForces();
        sumForces(enemies);
    }

    /**
     * Met à jour l'état du projectile, y compris le traitement des collisions.
     *
     * @param dt Le delta de temps pour la mise à jour.
     * @param screenHeight La hauteur de l'écran pour vérifier les collisions.
     */
    void update(double dt, double screenHeight) {
        super.update(dt);
        checkCollision(screenHeight);
    }

    /**
     * Vérifie et traite les collisions avec les bords de l'écran.
     *
     * @param screenHeight La hauteur de l'écran.
     */
    public void checkCollision(double screenHeight) {
        if (y + height > screenHeight) {
            y = screenHeight - height;
        } else if (y < 0) {
            y = 0;
        }
        speedY = -speedY; // Common action in both conditions
    }

    /**
     * Plafonne une valeur entre des limites définies.
     *
     * @param value La valeur à plafonner.
     * @param lowerCap La limite inférieure.
     * @param upperCap La limite supérieure.
     * @return La valeur ajustée.
     */
    private double capValues(double value, double lowerCap, double upperCap) {
        if (value < lowerCap) {
            return lowerCap;
        } else if (value > upperCap) {
            return upperCap;
        }
        return value;
    }
}

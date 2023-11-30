package ca.qc.bdeb.sim203.tp2;

import javafx.scene.image.Image;

import java.util.Random;

/**
 * Classe représentant un projectile triple dans un jeu.
 * Ce projectile a un mouvement ondulatoire unique.
 */
public class TripleProjectile extends Projectile {
    private double sinCoefficient; // Coefficient pour le mouvement sinusoïdal
    private double periode; // Période du mouvement sinusoïdal
    private double tempsDepuisLeDebut; // Temps écoulé depuis le lancement du projectile
    private double initialY; // Position Y initiale

    /*
     * @param x Position X initiale du projectile.
     * @param y Position Y initiale du projectile.
     */
    public TripleProjectile(double x, double y) {
        super(x, y, 20, 36);
        vitesseX = 500;
        sinCoefficient = (Math.pow(-1, (new Random()).nextInt(0, 2))) * (new Random()).nextDouble(30, 60); // Coefficient sinusoïdal aléatoire
        periode = (new Random()).nextDouble(1, 3); // Période sinusoïdale aléatoire
        initialY = y;
        imageDeBase = new Image("./hippocampe.png");
    }

    /**
     * Calcule le nouveau positionnement en fonction du temps et de la vitesse.
     *
     * @param dt Temps écoulé depuis la dernière update
     */
    void deplacerObjet(double dt) {
        tempsDepuisLeDebut += dt;
        y = sinCoefficient * Math.sin(((2 * Math.PI) / periode) * (tempsDepuisLeDebut)) + initialY;
        x += vitesseX * dt;
    }
}

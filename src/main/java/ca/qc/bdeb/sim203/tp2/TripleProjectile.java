package ca.qc.bdeb.sim203.tp2;

import javafx.scene.image.Image;

import java.util.Random;

public class TripleProjectile extends Projectile {
    private double sinCoefficient;
    private double periode;
    private double tempsDepuisLeDebut;
    private double initialY;

    public TripleProjectile(double x, double y) {
        super(x, y, 20, 36);
        vitesseX = 500;
        sinCoefficient = (Math.pow(-1, (new Random()).nextInt(0, 2))) * (new Random()).nextDouble(30, 60);
        periode = (new Random()).nextDouble(1, 3);
        initialY = y;
        imageDeBase = new Image("./hippocampe.png");
    }
    void deplacerObjet(double dt) {
        tempsDepuisLeDebut += dt;
        y = sinCoefficient * Math.sin(((2 * Math.PI) / periode) * (tempsDepuisLeDebut)) + initialY;
        x += vitesseX * dt;
    }
}



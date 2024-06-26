package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Baril extends MovableObject {

    private final Image barilOuvert;
    private final ProjectileType projectileDisponible;
    private boolean ouvert = false;
    private final double decalageFonction;
    private final double coefficientSin;

    public boolean isOuvert() {
        return ouvert;
    }

    public void setOuvert(boolean ouvert) {
        this.ouvert = ouvert;
    }

    private double tempsDepuisLeDebut = 0;

    /**
     * Ceci est le contructeur du baril
     *
     * @param x            la postion en x initial du baril
     * @param y            la position en y initial du baril
     * @param hauteurEcran
     */
    public Baril(double x, double y, double hauteurEcran) {
        super(x, y, 70, 83);
        coefficientSin = (hauteurEcran - hauteur) / 2;
        decalageFonction = (3 / (2 * Math.PI)) * Math.asin((y - coefficientSin) / coefficientSin);
        if ((new Random()).nextInt(0, 2) == 0) {
            projectileDisponible = ProjectileType.TRIPLE;
        } else {
            projectileDisponible = ProjectileType.MAGNET;
        }
        imageDeBase = new Image("./baril.png");
        barilOuvert = new Image("./baril-ouvert.png");


    }

    /**
     * Cette méthode calcule le y du baril par rapport à un variation de temps
     *
     * @param dt Temps écoulé depuis le dernier calcul.
     */
    @Override
    public void deplacerObjet(double dt) {
        y = coefficientSin * Math.sin(((2 * Math.PI) / 3) * (tempsDepuisLeDebut - decalageFonction)) + coefficientSin;
    }

    public void update(double dt) {
        deplacerObjet(dt);
        updateTime(dt);
    }

    public void draw(GraphicsContext context, Camera camera) {
        double displayX = x - camera.getX();
        if (ouvert) {
            context.drawImage(barilOuvert, displayX, y);
        } else {
            context.drawImage(imageDeBase, displayX, y);
        }
    }

    public ProjectileType getProjectileDisponible() {
        return projectileDisponible;
    }

    public void updateTime(double dt) {
        tempsDepuisLeDebut += dt;
    }
}

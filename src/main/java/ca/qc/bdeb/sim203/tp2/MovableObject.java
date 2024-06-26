package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

/**
 * Cette classe représente un objet qui bouge dans le jeu.
 */
public class MovableObject extends GameObject {

    static boolean debug;
    double vitesseX = 0;
    double vitesseY = 0;
    double acceleration = 0;
    double vitesseMaximum = 300;

    /**
     * @param x       Position en x de l'objet.
     * @param y       Position en y de l'objet.
     * @param largeur Largeur de l'objet.
     * @param hauteur Hauteur de l'objet.
     */
    public MovableObject(double x, double y, double largeur, double hauteur) {
        super(x, y, largeur, hauteur);
    }

    /**
     * Calcule le déplacement en x en fonction du temps écoulé.
     *
     * @param dt Temps écoulé depuis le dernier calcul.
     */
    void calculateDx(double dt) {
        vitesseX += acceleration * dt;
    }

    /**
     * Affiche un cadre de débogage autour de l'objet si le mode debug est activé.
     *
     * @param context Contexte graphique sur lequel dessiner.
     * @param camera  La caméra utilisée pour l'affichage.
     */
    void drawDebug(GraphicsContext context, Camera camera) {
        if (debug) {
            context.strokeRect(x - camera.getX(), y, width, hauteur);
        }
    }

    /**
     * Calcule le déplacement en y en fonction du temps écoulé.
     *
     * @param dt Temps écoulé depuis le dernier calcul.
     */
    void calculateDy(double dt) {

    }

    /**
     * Déplace l'objet en fonction de sa vitesse et du temps écoulé.
     *
     * @param dt Temps écoulé depuis le dernier calcul.
     */
    void deplacerObjet(double dt) {
        x += vitesseX * dt;
        y += vitesseY * dt;
    }

    /**
     * calcul physic
     *
     * @param dt
     */

    void physicsCalculate(double dt) {
        calculateDx(dt);
        calculateDy(dt);
    }


    void update(double dt) {
        physicsCalculate(dt);
        deplacerObjet(dt);
    }

    @Override
    void draw(GraphicsContext context, Camera camera) {
        double displayX = x - camera.getX();
        context.drawImage(imageDeBase, displayX, y, width, hauteur);
    }
}

package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Cette classe représente un bouton dans l'interface utilisateur du jeu.
 * Elle gère l'affichage et les interactions du bouton.
 */
public class Bouton {

    private final double hauteur = 40;
    private final double largeur = 120;

    private final double x;
    private final double y;


    private final Image buttonImage;

    /**
     * Constructeur pour créer un nouveau bouton.
     *
     * @param cheminButtonImage Le chemin vers l'image du bouton.
     * @param x                 La position en x du bouton.
     * @param y                 La position en y du bouton.
     */
    public Bouton(String cheminButtonImage, double x, double y) {

        this.buttonImage = new Image(cheminButtonImage);
        this.x = x;
        this.y = y;
    }

    /**
     * Vérifie si le bouton a été cliqué.
     *
     * @param clickX La coordonnée x du clic.
     * @param clickY La coordonnée y du clic.
     * @return true si le bouton a été cliqué, false sinon.
     */
    public boolean clique(double clickX, double clickY) {
        return clickX > x && clickX < x + largeur && clickY > y && clickY < y + hauteur;
    }

    /**
     * Dessine le bouton sur le contexte graphique fourni.
     *
     * @param context Le contexte graphique sur lequel dessiner le bouton.
     */
    public void draw(GraphicsContext context) {
        context.drawImage(buttonImage, x, y);
    }
}

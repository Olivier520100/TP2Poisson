package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Cette classe représente un bouton dans l'interface utilisateur du jeu.
 * Elle gère l'affichage et les interactions du bouton.
 */
public class Bouton {

    private double height = 40;
    private double width = 120;

    private double x;
    private double y;

    private boolean active;

    private Image buttonImage;

    /**
     * Constructeur pour créer un nouveau bouton.
     *
     * @param active Indique si le bouton est actif.
     * @param buttonImagePath Le chemin vers l'image du bouton.
     * @param x La position en x du bouton.
     * @param y La position en y du bouton.
     */
    public Bouton(boolean active, String buttonImagePath, double x, double y) {
        this.active = active;
        this.buttonImage = new Image(buttonImagePath);
        this.x = x;
        this.y = y;
    }

    /**
     * Dessine le bouton sur le contexte graphique fourni.
     *
     * @param context Le contexte graphique sur lequel dessiner le bouton.
     */
    public void draw(GraphicsContext context) {
        context.drawImage(buttonImage, x, y);
    }

    /**
     * Vérifie si le bouton a été cliqué.
     *
     * @param clickX La coordonnée x du clic.
     * @param clickY La coordonnée y du clic.
     * @return true si le bouton a été cliqué, false sinon.
     */
    public boolean clicked(double clickX, double clickY) {
        return clickX > x && clickX < x + width && clickY > y && clickY < y + height;
    }
}

package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Cette classe représente un texte principal à afficher dans le jeu.
 * Elle gère la mise en forme et l'affichage du texte.
 */
public class MainText {
    private String text;
    private double width;
    private double height;
    private double fontHeight = 100;

    /**
     * Constructeur pour créer un nouveau texte principal.
     *
     * @param text Le texte à afficher.
     * @param width La largeur de la zone où le texte sera affiché.
     * @param height La hauteur de la zone où le texte sera affiché.
     */
    public MainText(String text, double width, double height) {
        this.text = text;
        this.width = width;
        this.height = height;
    }

    /**
     * Dessine le texte sur le contexte graphique fourni.
     *
     * @param context Le contexte graphique sur lequel dessiner le texte.
     * @param camera La caméra utilisée pour l'affichage.
     * @param offset Le décalage horizontal pour l'affichage du texte.
     * @param color La couleur du texte.
     */
    void draw(GraphicsContext context, Camera camera, double offset, Color color) {
        context.setFill(color);
        context.setFont(Font.font(fontHeight));
        context.fillText(text, offset + width / 3 - camera.getX(), height / 2);
    }
}

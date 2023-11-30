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
    private double largeur;
    private double hauteur;
    private double fontHeight = 100;

    /**
     * Constructeur pour créer un nouveau texte principal.
     *
     * @param text Le texte à afficher.
     * @param largeur La largeur de la zone où le texte sera affiché.
     * @param hauteur La hauteur de la zone où le texte sera affiché.
     */
    public MainText(String text, double largeur, double hauteur) {
        this.text = text;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    /**
     * Dessine le texte sur le contexte graphique fourni.
     *
     * @param context Le contexte graphique sur lequel dessiner le texte.
     * @param color La couleur du texte.
     */
    void draw(GraphicsContext context, Color color) {
        context.setFill(color);
        context.setFont(Font.font(fontHeight));
        context.fillText(text, largeur / 5, hauteur / 2);
    }
}

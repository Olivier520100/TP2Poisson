package ca.qc.bdeb.sim203.tp2;

public class Actor extends MovableObject {

    /**
     * Constructeur de la classe Acteur.
     *
     * @param x       La coordonnée x de l'acteur.
     * @param y       La coordonnée y de l'acteur.
     * @param largeur La largeur de l'acteur.
     * @param hauteur La hauteur de l'acteur.
     */
    public Actor(double x, double y, double largeur, double hauteur) {
        super(x, y, largeur, hauteur);
    }
    /**
     * Vérifie s'il y a des collisions entre l'acteur et les éléments du jeu.
     *
     * @param largeurEcran La largeur de l'écran.
     * @param hauteurEcran La hauteur de l'écran.
     * @param camera       La caméra utilisée pour ajuster la vue.
     */
    public void verifierCollision(double largeurEcran, double hauteurEcran, Camera camera) {
    }
}

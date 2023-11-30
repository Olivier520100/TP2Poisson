package ca.qc.bdeb.sim203.tp2;

public class Camera {

    private double x;
    private double y;
    private double hauteur;
    private double largeur;

    public void setX(double x) {
        this.x = x;
    }

    public Camera(double x, double y, double largeur, double hauteur) {
        this.x = x;
        this.y = y;
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHauteur() {
        return hauteur;
    }

    public double getLargeur() {
        return largeur;
    }

}

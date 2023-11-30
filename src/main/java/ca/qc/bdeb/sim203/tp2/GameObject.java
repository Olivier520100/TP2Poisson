package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {

    Image imageDeBase;

    double x;
    double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHauteur() {
        return hauteur;
    }

    public double getWidth() {
        return width;
    }

    double hauteur;
    double width;
    
    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }



    public GameObject(double x, double y, double width, double hauteur) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.hauteur = hauteur;

    }

    public void setY(double y) {
        this.y = y;
    }

    void draw(GraphicsContext context, Camera camera) {
        double displayX = x - camera.getX();
        context.drawImage(imageDeBase, displayX, y);
    }


}

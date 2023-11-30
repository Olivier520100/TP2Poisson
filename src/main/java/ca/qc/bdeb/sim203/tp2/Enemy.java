package ca.qc.bdeb.sim203.tp2;

import javafx.scene.image.Image;

import java.util.Random;

public class Enemy extends Actor {

    public Enemy(double x, double y, double largeur, double hauteur, double niveau) {
        super(x, y, largeur, hauteur);
        vitesseX = -(100 * Math.pow(niveau, 0.333) + 200);
        acceleration = -500;
        vitesseY = (new Random()).nextInt(-100, 100);
        imageDeBase = new Image("./poisson" + (new Random()).nextInt(1, 6) + ".png");
    }

}

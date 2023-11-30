package ca.qc.bdeb.sim203.tp2;

import javafx.scene.image.Image;

import java.util.Random;

public class Enemy extends Actor {

    public Enemy(double x, double y, double width, double height, double niveau) {
        super(x, y, width, height);
        speedX = -(100 * Math.pow(niveau, 0.333) + 200);
        acceleration = -500;
        speedY = (new Random()).nextInt(-100, 100);
        imageDeBase = new Image("./poisson" + (new Random()).nextInt(1, 6) + ".png");
    }

}

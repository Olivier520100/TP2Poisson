package ca.qc.bdeb.sim203.tp2;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class TripleProjectile extends Projectile {
    double sinCoefficient;
    double period;
    double timesincestart;
    double initialY;
    final double PI = 3.1415926535;


    public TripleProjectile(double x, double y) {
        super(x, y, 20, 36);
        speedX = 500;
        sinCoefficient = (double) (Math.pow(-1, (new Random()).nextInt(0, 2))) * (new Random()).nextDouble(30, 60);
        period = (new Random()).nextDouble(1, 3);
        initialY = y;
        baseImage = new Image("./hippocampe.png");
    }

    void moveObject(double dt) {
        timesincestart += dt;
        y = sinCoefficient * Math.sin(((2 * PI) / period) * (timesincestart)) + initialY;
        x += speedX * dt;
    }
}



package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class BasicProjectile extends Projectile{

    public BasicProjectile(double x, double y) {
        super(x, y, 35, 36);
        speedX = 500;
    }


}

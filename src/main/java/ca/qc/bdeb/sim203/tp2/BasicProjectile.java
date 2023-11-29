package ca.qc.bdeb.sim203.tp2;

import javafx.scene.image.Image;

public class BasicProjectile extends Projectile {





    public BasicProjectile(double x, double y) {
        super(x, y, 35, 36);
        speedX = 500;
        baseImage = new Image("./etoile.png");

    }


}

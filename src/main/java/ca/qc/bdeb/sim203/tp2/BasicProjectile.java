package ca.qc.bdeb.sim203.tp2;

import javafx.scene.image.Image;

public class BasicProjectile extends Projectile {
    public BasicProjectile(double x, double y) {
        super(x, y, 35, 36);
        vitesseX = 800;
        imageDeBase = new Image("./etoile.png");
    }
}

package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Baril extends MovableObject {

    Image openImage;
    ProjectileType projectileInside;
    boolean open = false;
   private final double functionOffset;
   private final double sinCoefficient;

   public boolean isOpen() {
        return open;
    }
    public void setOpen(boolean open) {
        this.open = open;
    }

   private double timeSinceStart = 0;

    public Baril(double x, double y, double screenHeight) {
        super(x, y, 70, 83);
        sinCoefficient = (screenHeight - height) / 2;
        functionOffset = (3 / (2 * Math.PI)) * Math.asin((y - sinCoefficient) / sinCoefficient);
        if ((new Random()).nextInt(0, 2) == 0) {
            projectileInside = ProjectileType.TRIPLE;
        } else {
            projectileInside = ProjectileType.MAGNET;
        }
        imageDeBase = new Image("./baril.png");
        openImage = new Image("./baril-ouvert.png");


    }
    public ProjectileType getProjectileInside() {
        return projectileInside;
    }
    public void updateTime(double dt) {
        timeSinceStart += dt;
    }
    @Override
    public void moveObject(double dt) {
        y = sinCoefficient * Math.sin(((2 * Math.PI) / 3) * (timeSinceStart - functionOffset)) + sinCoefficient;
    }

     public void update(double dt, double screenWidth, double screenheight, Camera camera) {
        moveObject(dt);
        updateTime(dt);
    }

    public void draw(GraphicsContext context, Camera camera) {
        double displayX = x - camera.getX();
        if (open) {
            context.drawImage(openImage, displayX, y);
        } else {
            context.drawImage(imageDeBase, displayX, y);
        }
    }

}

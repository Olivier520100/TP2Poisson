package ca.qc.bdeb.sim203.tp2;

import java.util.ArrayList;

public class Projectile extends MovableObject {

    boolean used = false;
    boolean outOfBounds = false;

    public Projectile(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public boolean isUsed() {
        return used;
    }



    void outOfBounds(Camera camera) {
        if (x > camera.getX() + camera.getWidth()) {
            outOfBounds = true;
        }
    }

    void update(double dt, double screenWidth, double screenheight, Camera camera, ArrayList<Enemy> enemies) {


        physicsCalculate(dt);
        checkCollision(screenWidth, screenheight, camera);
        moveObject(dt);
        outOfBounds(camera);

    }
}



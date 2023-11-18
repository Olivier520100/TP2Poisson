package ca.qc.bdeb.sim203.tp2;

import java.util.ArrayList;

public class Projectile extends MovableObject{

    boolean used = false;
    boolean oob = false;

    public Projectile(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public boolean isUsed() {
        return used;
    }

    void objectCollision(ArrayList<Enemy> enemies){
            for (int i = enemies.size() - 1; i >= 0; i--) {
                Enemy enemy = enemies.get(i);
                if (checkCollisionWithObject(enemy)) {
                    enemies.remove(i);
                    System.out.println("Hit enemy");
                    used = true;
                    break;
                }

            }

    }
    void outOfBounds(Camera camera){
        if (x > camera.getX()+camera.getWidth()){
            oob = true;
        }
    }

    public boolean isOob() {
        return oob;
    }

    void update(double dt, double screenWidth, double screenheight, Camera camera, ArrayList<Enemy> enemies) {


        physicsCalculate(dt);
        checkCollision(screenWidth, screenheight, camera);
        moveObject(dt);
        outOfBounds(camera);
        objectCollision(enemies);

    }
}



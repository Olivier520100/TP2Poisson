package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class MovableObject extends GameObject {

    static boolean debug;
    double speedX = 0;
    double speedY = 0;
    double acceleration = 1000;
    double maximumSpeed = 300;

    public boolean checkCollisionWithObject(MovableObject moveableObject) {

        return x < moveableObject.getX() + moveableObject.getWidth() && x + width > moveableObject.getX() && y < moveableObject.getY() + moveableObject.getHeight() && y + height > moveableObject.getY();
    }

    public MovableObject(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    void calculateDx(double dt) {

    }
    void drawDebug(GraphicsContext context, Camera camera)
    {
        if (debug) {
            context.strokeRect(x - camera.getX(), y, width, height);
        }

    }
    void calculateDy(double dt) {

    }

    void moveObject(double dt) {

        x += speedX * dt;
        y += speedY * dt;

    }

    void physicsCalculate(double dt) {

        calculateDx(dt);
        calculateDy(dt);
    }

    void checkCollision(double screenWidth, double screenHeight, Camera camera) {

    }

    void update(double dt, double screenWidth, double screenheight, Camera camera) {
        physicsCalculate(dt);
        checkCollision(screenWidth, screenheight, camera);
        moveObject(dt);
    }

}

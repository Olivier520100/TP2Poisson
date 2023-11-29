package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class MovableObject extends GameObject {

    static boolean debug;
    double speedX = 0;
    double speedY = 0;
    double acceleration = 0;
    double maximumSpeed = 300;

    public MovableObject(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    void calculateDx(double dt) {
        speedX += acceleration * dt ;
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

    void update(double dt) {
        physicsCalculate(dt);
        moveObject(dt);
    }
    @Override
    void draw(GraphicsContext context, Camera camera) {
        double displayX = x - camera.getX();
        context.drawImage(baseImage, displayX, y, width, height);
    }

}

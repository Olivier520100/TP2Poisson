package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameObject {


    double x;
    double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    double height;
    double width;

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    void draw(GraphicsContext context, Camera camera) {
        context.setFill((Color.BLUE));

        context.fillRect(x - camera.getX(), y, width, height);
    }

}

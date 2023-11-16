package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class GameObject {


    double x;
    double y;

    double height;
    double width;

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    void draw(GraphicsContext context, Camera camera) {
        context.fillRect(x - camera.getX(), y, width, height);
    }

}

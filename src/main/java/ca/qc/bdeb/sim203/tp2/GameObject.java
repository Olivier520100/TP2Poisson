package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GameObject {

    Image baseImage;

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

    Color color;

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void setY(double y) {
        this.y = y;
    }

    void draw(GraphicsContext context, Camera camera) {
        double displayx = x - camera.getX();
        context.drawImage(baseImage, displayx, y);
    }

}

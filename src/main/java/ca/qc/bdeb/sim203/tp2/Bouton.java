package ca.qc.bdeb.sim203.tp2;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bouton {

    double height = 40;
    double width = 120;

    double x;
    double y;

    boolean active;

    Image buttonImage;


    public Bouton(boolean active, String buttonImagePath, double x, double y) {
        this.active = active;
        this.buttonImage = new Image(buttonImagePath);
        this.x = x;
        this.y = y;
    }

    public void draw(GraphicsContext context) {
        context.drawImage(buttonImage, x, y);

    }

    public boolean clicked(double clickX, double clickY) {
        return clickX > x && clickX < x + width && clickY > y && clickY < y + height;

    }


}

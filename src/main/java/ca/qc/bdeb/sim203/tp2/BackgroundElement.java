package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;

public class BackgroundElement extends GameObject {

    public BackgroundElement(double x, double hauteurScene) {
        super(x, hauteurScene - 119, 80, 119);
        initialiser();
    }

    private void initialiser() {
        imageDeBase = new Image("./decor" + new Random().nextInt(1, 7) + ".png");
    }

   public void draw(GraphicsContext context, Camera camera) {
        double affichageX = x - camera.getX();
        context.drawImage(imageDeBase, affichageX, y);
    }
}


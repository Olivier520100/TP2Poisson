package ca.qc.bdeb.sim203.tp2;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;

public class BackgroundElement extends GameObject {

    public BackgroundElement(double x, double frameHeight) {
        super(x, frameHeight - 119, 80, 119);
        initialize();
    }

    private void initialize() {
        baseImage = new Image("./decor" + new Random().nextInt(1, 7) + ".png");
    }

   public void draw(GraphicsContext context, Camera camera) {
        double displayX = x - camera.getX();
        context.drawImage(baseImage, displayX, y);
    }
}


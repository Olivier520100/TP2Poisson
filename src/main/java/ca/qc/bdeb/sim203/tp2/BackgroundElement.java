package ca.qc.bdeb.sim203.tp2;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;

public class BackgroundElement extends GameObject {

    private double hue;
    private double saturation;
    private double brightness;
    private ColorAdjust colorAdjust;

    public BackgroundElement(double x, double frameHeight) {
        super(x, frameHeight - 119, 80, 119);
        initialize();
    }

    private void initialize() {
        baseImage = new Image("./decor" + new Random().nextInt(1, 7) + ".png");
        initializeColorAdjust();
    }

    private void initializeColorAdjust() {
        hue = new Random().nextInt(190, 270);
        saturation = 0.84;
        brightness = 1.0;
        colorAdjust = new ColorAdjust();
        colorAdjust.setHue(hue);
        colorAdjust.setBrightness(brightness);
        colorAdjust.setSaturation(saturation);
    }


   public void draw(GraphicsContext context, Camera camera) {
        double displayX = x - camera.getX();
        context.setEffect(colorAdjust);
        context.drawImage(baseImage, displayX, y);
        context.setEffect(new ColorAdjust());
    }
}


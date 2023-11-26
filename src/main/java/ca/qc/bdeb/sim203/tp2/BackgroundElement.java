package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;

import java.util.Random;

public class BackgroundElement extends GameObject{


    double hue;
    double saturation;
    double brightness;
    ColorAdjust ca;

    public BackgroundElement(double x,double frameHeight) {

        super(x,frameHeight-119,80,119);

        baseImage = new Image("./decor" + (new Random()).nextInt(1,7) + ".png");

        hue = (new Random()).nextInt(190,270);
        saturation = 0.84;
        brightness = 1.0;
        ca = new ColorAdjust();
        ca.setHue(hue);
        ca.setBrightness(brightness);
        ca.setSaturation(saturation);



    }
    void draw(GraphicsContext context, Camera camera) {
        double displayx = x - camera.getX();
        context.setEffect(ca);
        context.drawImage(baseImage,displayx,y);
        context.setEffect(new ColorAdjust());
    }
}

package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class BackgroundElement extends GameObject {

    public BackgroundElement(double x, double hauteurScene) {
        super(x, hauteurScene - 119, 80, 119);
        initialiser();
    }

    /**
     * Choisir image aleatoire
     */
    private void initialiser() {
        imageDeBase = new Image("./decor" + new Random().nextInt(1, 7) + ".png");
    }


}


package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bar {
    int heartsLeft = 4;

     final int pixelsAdded = 20;

     final int valueToAlign = pixelsAdded + 10;

    ProjectileType current = ProjectileType.BASIC;
    Image heartImage = new Image("./heart.png");
    Image basicImage = new Image("./etoile.png");
    Image tripleImage = new Image("./hippocampe.png");
    Image magnetImage = new Image("./sardines.png");

    public void setHeartsLeft(int heartsLeft) {
        this.heartsLeft = heartsLeft;
    }

    public void setCurrent(ProjectileType current) {
        this.current = current;
    }

    void draw(GraphicsContext context) {
        int xdrawn = 0;
        for (int i = 0; i < heartsLeft; i++) {
            xdrawn = i * 24;
            context.drawImage(heartImage, pixelsAdded + xdrawn, valueToAlign);
        }
        xdrawn += valueToAlign;
        switch (current) {
            case BASIC -> context.drawImage(basicImage, pixelsAdded + xdrawn, pixelsAdded);
            case TRIPLE -> context.drawImage(tripleImage, pixelsAdded + xdrawn, pixelsAdded);
            case MAGNET -> context.drawImage(magnetImage, pixelsAdded + xdrawn, pixelsAdded);
        }
    }
}

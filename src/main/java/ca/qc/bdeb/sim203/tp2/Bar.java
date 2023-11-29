package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bar {
     private int heartsLeft = 4;

     private final int PIXELS_ADDED = 20;

     private final int VALUE_TO_ALIGN = PIXELS_ADDED + 10;

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

   public void draw(GraphicsContext context) {
        int xdrawn = 0;
        for (int i = 0; i < heartsLeft; i++) {
            xdrawn = i * 24;
            context.drawImage(heartImage, PIXELS_ADDED + xdrawn, VALUE_TO_ALIGN);
        }
        xdrawn += VALUE_TO_ALIGN;
        switch (current) {
            case BASIC -> context.drawImage(basicImage, PIXELS_ADDED + xdrawn, PIXELS_ADDED);
            case TRIPLE -> context.drawImage(tripleImage, PIXELS_ADDED + xdrawn, PIXELS_ADDED);
            case MAGNET -> context.drawImage(magnetImage, PIXELS_ADDED + xdrawn, PIXELS_ADDED);
        }
    }
}

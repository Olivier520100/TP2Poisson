package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Bar {
     private double heartsLeft = 4;

     private final double MAX_HP = 4;

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
        int xdrawn = 25;

        double percentageOfBar = heartsLeft/MAX_HP * 150;


        context.setFill(Color.WHITE);
        context.fillRect(xdrawn,VALUE_TO_ALIGN,percentageOfBar, basicImage.getHeight());
        context.setStroke(Color.WHITE);
        context.strokeRect(xdrawn,VALUE_TO_ALIGN,150, basicImage.getHeight());








       switch (current) {
            case BASIC -> context.drawImage(basicImage, PIXELS_ADDED + 160 , VALUE_TO_ALIGN);
            case TRIPLE -> context.drawImage(tripleImage, PIXELS_ADDED + 160, VALUE_TO_ALIGN);
            case MAGNET -> context.drawImage(magnetImage, PIXELS_ADDED + 160 , VALUE_TO_ALIGN);
        }
    }
}

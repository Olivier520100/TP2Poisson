package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bar {
    int heartsleft = 4;

    ProjectileType current = ProjectileType.BASIC;
    Image heartImage = new Image("./heart.png");
    Image basicImage = new Image("./etoile.png");
    Image tripleImage = new Image("./hippocampe.png");
    Image magnetImage = new Image("./sardines.png");
    public void setHeartsleft(int heartsleft) {
        this.heartsleft = heartsleft;
    }

    public void setCurrent(ProjectileType current) {
        this.current = current;
    }

    void draw(GraphicsContext context){
        int xdrawn = 0;
        for (int i = 0; i < heartsleft; i++) {
            xdrawn = i*24;
            context.drawImage(heartImage,20+xdrawn, 30);
        }
        xdrawn+=30;
        switch (current) {
            case BASIC -> context.drawImage(basicImage,20+xdrawn,20);
            case TRIPLE -> context.drawImage(tripleImage,20+xdrawn,20);
            case MAGNET -> context.drawImage(magnetImage,20+xdrawn,20);
        }
    }
}

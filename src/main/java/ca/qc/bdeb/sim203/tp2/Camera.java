package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class Camera {

    double x;
    double y;
    double height;
    double width;

    public Camera(double x, double y, double height, double width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void Draw(GraphicsContext context, Player player,){

//        for (Ship ship : ships) {
//            ship.draw(context);
//        }
        player.draw(context);

    }

}

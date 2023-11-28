package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Actor {



    public Enemy(double x, double y, double width, double height, double niveau) {
        super(x, y, width, height);
        speedX = -(100 * Math.pow(niveau, 0.333) + 200);
        acceleration = 500;
        speedY = (new Random()).nextInt(-100, 100);
        baseImage = new Image("./poisson" + (new Random()).nextInt(1, 6) + ".png");
    }









    void calculatedx(double dt) {
        speedX -= acceleration * dt ;
    }

    void update(double dt, double screenWidth, double screenheight, Camera camera, ArrayList<Projectile> projectiles) {

        physicsCalculate(dt);
        checkCollision(screenWidth, screenheight, camera);
        moveObject(dt);

    }

    @Override
    void draw(GraphicsContext context, Camera camera) {

        double displayx = x - camera.getX();
        context.drawImage(baseImage, displayx, y, width, height);

    }

}

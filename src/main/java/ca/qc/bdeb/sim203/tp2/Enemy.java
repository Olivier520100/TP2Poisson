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









    void calculateDx(double dt) {
        speedX -= acceleration * dt ;
    }

    void update(double dt, double screenWidth, double screenHeight, Camera camera, ArrayList<Projectile> projectiles) { // why is this here?

        physicsCalculate(dt);
        checkCollision(screenWidth, screenHeight, camera);
        moveObject(dt);

    }

    @Override
    void draw(GraphicsContext context, Camera camera) {

        double displayX = x - camera.getX();
        context.drawImage(baseImage, displayX, y, width, height);

    }

}

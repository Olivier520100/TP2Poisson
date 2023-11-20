package ca.qc.bdeb.sim203.tp2;

import javafx.scene.ImageCursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Actor{

    double forceX;
    double forceY;
    public Enemy(double x, double y, double width, double height, double niveau) {
        super(x, y, width, height);
        speedX = -(100*Math.pow(niveau,0.333) +  200);
        acceleration = 500;
        speedY = (new Random()).nextInt(-100,100);
        baseImage = new Image("./poisson" + (new Random()).nextInt(1,6) + ".png");
    }

    void calculateForce(Projectile projectile){
        if (projectile.getX()<x) {
            double dx = projectile.getX() - x;
            double dy = projectile.getY() - y;
            double distanceSquared = dx * dx + dy * dy;
            double distance = Math.sqrt(distanceSquared);
            double forceTotal = (1000 * 200 * 100) / distanceSquared;
            forceX += forceTotal * (dx / distance);
            forceY += forceTotal * (dy / distance);
        }
    }

    @Override
    void calculatedy(double dt) {
        speedY += dt*forceY;
    }
    void sumForces(ArrayList<Projectile> projectiles) {
        for (Projectile projectile : projectiles) {
            if (projectile instanceof Attractable) {
                calculateForce(projectile);
            }
        }
    }
    void resetForces(){
        forceY = 0;
        forceX = 0;
    }

    void calculatedx(double dt) {
        speedX-=acceleration*dt + dt*forceX;
    }

    void update(double dt, double screenWidth, double screenheight, Camera camera, ArrayList<Projectile> projectiles) {
        resetForces();
        sumForces(projectiles);
        physicsCalculate(dt);
        checkCollision(screenWidth, screenheight, camera);
        moveObject(dt);

    }
    @Override
    void draw(GraphicsContext context, Camera camera) {

        double displayx = x - camera.getX();
        context.drawImage(baseImage,displayx,y,width,height);

    }

}

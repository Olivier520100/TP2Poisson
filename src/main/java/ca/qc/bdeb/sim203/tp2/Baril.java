package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Baril extends MovableObject{


    ProjectileType projectileInside;
    boolean ouvert = false;
    double functionOffset;
    double sinCoefficient;

    public boolean isOuvert() {
        return ouvert;
    }

    public void setOuvert(boolean ouvert) {
        this.ouvert = ouvert;
    }

    final double PI = 3.141592653589;
    double timesincestart = 0;
    public Baril(double x, double y, double width, double height, double screenHeight) {
        super(x, y, width, height);
        sinCoefficient=(screenHeight-height)/2;
        functionOffset=(3/(2*PI))*Math.asin((y-sinCoefficient)/sinCoefficient);
        if ((new Random()).nextInt(0, 2)==0) {
            projectileInside = ProjectileType.TRIPLE;
        } else {
            projectileInside = ProjectileType.TRIPLE;
        }

    }

    public ProjectileType getProjectileInside() {
        return projectileInside;
    }

    void updateTime(double dt){
        timesincestart+=dt;
    }
    @Override
    void moveObject(double dt) {

        y = sinCoefficient*Math.sin(((2*PI)/3)*(timesincestart-functionOffset))+sinCoefficient;

    }
    void update(double dt, double screenWidth, double screenheight, Camera camera) {
        moveObject(dt);
        updateTime(dt);
        checkCollision(screenWidth, screenheight, camera);

    }
    void draw(GraphicsContext context, Camera camera) {
        context.setFill((Color.PURPLE));
        if (ouvert) {
            context.setFill((Color.PURPLE));
        } else {
            context.setFill((Color.YELLOW));

        }
        context.fillRect(x - camera.getX(), y, width, height);

    }

}

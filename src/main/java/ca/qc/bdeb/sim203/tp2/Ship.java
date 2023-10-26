package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class Ship {

    double x;
    double y;

    double height;
    double width;

    double dx = 0;
    double dy= 0;
    boolean onFloor = false;




    public Ship(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }
    void moveSquare(double dt){

        x=x+dx;
        y=y+dy;

    }

    void jump(){

    }
    void physicsCalculate(double dt) {

    }

    void checkCollision(double screenwidth, double screenheight){
        if (!onFloor){
            if (x+width > screenwidth){
                dx = 0;
                x=screenwidth-width;
            } else if (x < 0) {
                dx = 0;
                x=0;
            } else if (y+height > screenheight){
                dy = 0;
                y=screenheight-height;
                onFloor = true;
            } else if (y < 0) {
                dy = 0;
                y=screenheight-height;
            }
        }

    }

    void update(double dt,double screenwidth, double screenheight) {
        physicsCalculate(dt);
        checkCollision(screenwidth,screenheight);
        moveSquare(dt);
    }

    void draw(GraphicsContext context){

        context.fillRect(x,y, width, height);
    }

}

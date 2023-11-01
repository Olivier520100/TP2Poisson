package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class Player extends Ship{


    double x;
    double y;

    double height;
    double width;

    double speedx = 0;
    double speedy = 0;
    final double  horizontalacceleration = 1000;
    final double  maximumhorizontalacceleration = 300;

    boolean horizontalPressed = false;

    boolean directionRight = false;


    public Player(double x, double y, double width, double height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }
    void movePlayer(double dt){

        x=x+speedx;
        y=y+speedy;

    }
    void moveLeft(){
        horizontalPressed = true;
        directionRight = false;
    }
    void moveRight(){
        horizontalPressed = true;
        directionRight = true;
    }
    void stopMove(){
        horizontalPressed = false;
    }

    void calculatedx(double dt){
        if (horizontalPressed){

            if (directionRight){
                speedx+=horizontalacceleration*dt;
            } else {
                speedx-=horizontalacceleration*dt;
            }

        } else {

            if (directionRight){
                speedx-=horizontalacceleration*dt;
            } else {
                speedx+=horizontalacceleration*dt;
            }

        }
    }

    void physicsCalculate(double dt) {

    }

    void checkCollision(double screenwidth, double screenheight){
            if (x+width > screenwidth){
                speedx = 0;
            } else if (x < 0) {
                speedx = 0;
            } else if (y+height > screenheight){
                speedy = 0;
            } else if (y < 0) {
                speedy = 0;
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

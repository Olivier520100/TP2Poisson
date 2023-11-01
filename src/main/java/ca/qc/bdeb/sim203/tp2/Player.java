package ca.qc.bdeb.sim203.tp2;

import javafx.geometry.VerticalDirection;
import javafx.scene.canvas.GraphicsContext;


public class Player extends Ship{


    double x;
    double y;

    double height;
    double width;

    double speedx = 0;
    double speedy = 0;
    final double  horizontalacceleration = 100;
    final double  maximumhorizontalacceleration = 30;

    boolean horizontalPressed = false;
    boolean verticalPressed = false;

    boolean directionRight = false;
    boolean directionUp = false;


    public Player(double x, double y, double width, double height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }
    void movePlayer(){

        x+=speedx;
        y+=speedy;

    }
    void moveLeft(){
        horizontalPressed = true;
        directionRight = false;

    }
    void moveRight(){
        horizontalPressed = true;
        directionRight = true;
    }
    void stopMoveHorizontal(){
        horizontalPressed = false;

    }
    void moveUp(){
        verticalPressed = true;
        directionUp = true;

    }
    void moveDown(){
        verticalPressed = true;
        directionUp = false;
    }
    void stopMoveVertical(){
        verticalPressed = false;

    }

    void calculatedx(double dt){
        if (horizontalPressed){
            if (Math.abs(speedx)<maximumhorizontalacceleration){
                if (directionRight){
                    speedx+=horizontalacceleration*dt;
                    if (speedx > maximumhorizontalacceleration) {
                        speedx = maximumhorizontalacceleration;
                    }
                } else {
                    speedx-=horizontalacceleration*dt;
                    if (Math.abs(speedx) > maximumhorizontalacceleration) {
                        speedx = -maximumhorizontalacceleration;
                    }
                }
            }
        } else {
            if (speedx > 0) {
                speedx -= horizontalacceleration * dt;
                if (speedx < 0) {
                    speedx = 0;
                }
            }
            else if (speedx < 0){
                speedx+=horizontalacceleration*dt;
                if (speedx > 0) {
                    speedx = 0;
                }
            }

        }
    }
    void calculatedy(double dt){
        if (verticalPressed){
            if (Math.abs(speedy)<maximumhorizontalacceleration) {
                if (directionUp) {
                    speedy -= horizontalacceleration * dt;
                } else {
                    speedy += horizontalacceleration * dt;
                }
            }
        } else {
            if (speedy > 0) {
                speedy -= horizontalacceleration * dt;
                if (speedy < 0) {
                    speedy = 0;
                }
            }
            else if (speedy < 0){
                speedy+=horizontalacceleration*dt;
                if (speedy > 0) {
                    speedy = 0;
                }
            }

        }
    }

    void physicsCalculate(double dt) {

        calculatedx(dt);
        calculatedy(dt);
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
        movePlayer();
    }

    void draw(GraphicsContext context){

        context.fillRect(x,y, width, height);
    }


}

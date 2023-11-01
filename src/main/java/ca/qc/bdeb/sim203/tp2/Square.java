package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class Square {
    double x;
    double y;


    double height;
    double width;

    double dx = 0;
    double dy = 0;
    final double g = 9.81;
    double jumpSpeed = -7;
    double lastJump = 0;
    boolean onFloor = false;
    double rightspeed = 200;

    boolean currentDirectionRight = false;
    boolean currentDirectionLeft = false;

    boolean currentDirectionNone = false;
    boolean stopped = true;


    public Square(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    void moveSquare(double dt) {
        if (stopped && onFloor) {

            if (currentDirectionLeft) {
                dx -= dx * 0.1;
                if (dx > 0) {
                    dx = 0;
                    currentDirectionNone = true;
                    currentDirectionLeft = false;

                }
            } else if (currentDirectionRight) {
                dx -= (dx * 0.1);
                if (dx < 0) {
                    dx = 0;
                    currentDirectionNone = true;
                    currentDirectionRight = false;
                }
            }
        }
        x = x + dx * dt;
        y = y + dy;
    }

    void jump() {
        if (onFloor) {
            dy = jumpSpeed;
            lastJump = 0;
            onFloor = false;
            System.out.println("Jump");
        }
    }

    void physicsCalculate(double dt) {
        if (!onFloor) {
            dy += g * dt;
        }

    }

    void moveLeft() {
        stopped = false;
        currentDirectionLeft = true;
        currentDirectionRight = false;
        currentDirectionNone = false;
        dx = -rightspeed;

        System.out.println("Left");

    }

    void moveRight() {
        stopped = false;
        currentDirectionLeft = false;
        currentDirectionRight = true;
        currentDirectionNone = false;
        dx = rightspeed;
        System.out.println("Right");

    }

    void stopMove() {


        onFloor = false;
        stopped = true;


    }

    void checkCollision(double screenwidth, double screenheight) {
        if (!onFloor) {
            if (x + width > screenwidth) {
                dx = 0;
                x = screenwidth - width;
            } else if (x < 0) {
                dx = 0;
                x = 0;
            } else if (y + height > screenheight) {
                dy = 0;
                y = screenheight - height;
                onFloor = true;
            } else if (y < 0) {
                dy = 0;
                y = screenheight - height;
            }
        }

    }

    void update(double dt, double screenwidth, double screenheight) {
        physicsCalculate(dt);
        checkCollision(screenwidth, screenheight);
        moveSquare(dt);


    }

    void draw(GraphicsContext context) {
        context.fillRect(x, y, width, height);
    }

}

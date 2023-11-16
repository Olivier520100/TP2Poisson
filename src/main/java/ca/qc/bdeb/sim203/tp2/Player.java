package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;


public class Player extends Actor{

    boolean horizontalPressed = false;
    boolean verticalPressed = false;

    boolean directionRight = false;
    boolean directionUp = false;


    public Player(double x, double y, double width, double height){
        super(x, y, width, height);

    }

    void moveLeft() {
        horizontalPressed = true;
        directionRight = false;

    }

    void moveRight() {
        horizontalPressed = true;
        directionRight = true;
    }

    void stopMoveHorizontal() {
        horizontalPressed = false;

    }

    void moveUp() {
        verticalPressed = true;
        directionUp = true;

    }

    void moveDown() {
        verticalPressed = true;
        directionUp = false;
    }

    void stopMoveVertical() {
        verticalPressed = false;
    }

    @Override
    void calculatedx(double dt) {
        if (horizontalPressed) {
            if (Math.abs(speedX) < maximumAcceleration) {
                if (directionRight) {
                    speedX += acceleration * dt;
                    if (speedX > maximumAcceleration) {
                        speedX = maximumAcceleration;
                    }
                } else {
                    speedX -= acceleration * dt;
                    if (Math.abs(speedX) > maximumAcceleration) {
                        speedX = -maximumAcceleration;
                    }
                }
            }
        } else {
            if (speedX > 0) {
                speedX -= acceleration * dt;
                if (speedX < 0) {
                    speedX = 0;
                }
            } else if (speedX < 0) {
                speedX += acceleration * dt;
                if (speedX > 0) {
                    speedX = 0;
                }
            }

        }
    }
    @Override
    void calculatedy(double dt) {
        if (verticalPressed) {
            if (Math.abs(speedY) < maximumAcceleration) {
                if (directionUp) {
                    speedY -= acceleration * dt;
                } else {
                    speedY += acceleration * dt;
                }
            }
        } else {
            if (speedY > 0) {
                speedY -= acceleration * dt;
                if (speedY < 0) {
                    speedY = 0;
                }
            } else if (speedY < 0) {
                speedY += acceleration * dt;
                if (speedY > 0) {
                    speedY = 0;
                }
            }

        }
    }
    @Override
    void checkCollision(double screenWidth, double screenHeight, Camera camera) {
        if (y + height > screenHeight) {
            y = screenHeight - height;
            speedY = 0;
        } else if (y < 0) {
            y = 0;
            speedY = 0;
        }
        if (x < camera.getX()) {
            x = camera.getX();
            speedX = 0;
        }

    }
    @Override
    void update(double dt, double screenWidth, double screenheight, Camera camera) {
        physicsCalculate(dt);
        checkCollision(screenWidth, screenheight, camera);
        moveObject();
        moveCamera(camera);
    }
    @Override
    void draw(GraphicsContext context, Camera camera) {
        System.out.println("Position x: " + x);
        System.out.println("Position y: " + y);
        context.fillRect(x - camera.getX(), y, width, height);
    }
    void moveCamera(Camera camera){
        if (((x - camera.getX()) >= camera.getWidth()/5)){
            camera.setX(camera.getX()+speedX);
        }
    }

}

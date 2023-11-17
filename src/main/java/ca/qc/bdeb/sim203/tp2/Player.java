package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Player extends Actor{

    boolean horizontalPressed = false;
    boolean verticalPressed = false;

    boolean directionRight = false;
    boolean directionUp = false;

    double invisibilitytimer = 0;
    final double invisibilityconst = 2;

    public Player(double x, double y, double width, double height){
        super(x, y, width, height);
        health = 5;

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
            if (Math.abs(speedX) < maximumSpeed) {
                if (directionRight) {
                    speedX += acceleration * dt;
                    if (speedX > maximumSpeed) {
                        speedX = maximumSpeed;
                    }
                } else {
                    speedX -= acceleration * dt;
                    if (Math.abs(speedX) > maximumSpeed) {
                        speedX = -maximumSpeed;
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
            if (Math.abs(speedY) < maximumSpeed) {
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
    public void checkCollision(double screenWidth, double screenHeight, Camera camera) {
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
        invisibilitytimer-=dt;
        physicsCalculate(dt);
        checkCollision(screenWidth, screenheight, camera);
        moveObject(dt);
        moveCamera(camera,dt);
    }
    void objectCollision(Enemy enemies[],Baril baril){
        boolean hit = false;
        if (invisibilitytimer < 0) {
            for (Enemy enemy : enemies) {
                hit = checkCollisionWithObject(enemy);
                if (hit) {
                    invisibilitytimer=invisibilityconst;
                    health-=1;
                    System.out.println("Hit");
                    break;
                }
            }

        }
        if (!baril.isOuvert()){
            baril.setOuvert(checkCollisionWithObject(baril));
        }
    }
    @Override
    void draw(GraphicsContext context, Camera camera) {
        context.setFill((Color.GREEN));
        context.fillText("Health: " + health, 20,20);
        context.fillRect(x - camera.getX(), y, width, height);
    }
    void moveCamera(Camera camera, double dt){
        if (((x - camera.getX()) >= camera.getWidth()/5)){
            camera.setX(camera.getX()+speedX*dt);
        }
    }

}

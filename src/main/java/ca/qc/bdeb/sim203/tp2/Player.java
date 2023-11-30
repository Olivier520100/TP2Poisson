package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Player extends Actor {


    Image movingImage;
    Image damagedImage;
    boolean horizontalPressed = false;
    boolean verticalPressed = false;
    boolean directionRight = false;
    boolean directionUp = false;
    boolean shootPressed = false;
    double invisibilityTimer = 0;
    final double invisibilityConst = 2;
    double shootTimer = 0;
    final double shootConst = 0.5;
    boolean flicker = false;
    int flickerCount = 0;
    ProjectileLauncher projectileLauncher;
    int health;

    public int getHealth() {
        return health;
    }

    public Player(double x, double y,int health) {
        super(x, y, 102, 90);

        this.health = health;
        projectileLauncher = new ProjectileLauncher();
        acceleration = 1000;
        imageDeBase = new Image("charlotte.png");
        movingImage = new Image("charlotte-avant.png");
        damagedImage = new Image("charlotte-outch.png");

    }

    void moveLeft() {
        horizontalPressed = true;
        directionRight = false;

    }

    ProjectileType getPT() {
        return projectileLauncher.getCurrent();
    }
    void setPT(ProjectileType pt) {
        projectileLauncher.setCurrent(pt);
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

    void shootDown() {
        shootPressed = true;
    }

    void shootRelease() {
        shootPressed = false;
    }

    void stopMoveVertical() {
        verticalPressed = false;
    }

    double calculateSpeed(double dt, boolean pressed, double currentSpeed, boolean direction){
        if (pressed) {
            if (Math.abs(currentSpeed) < vitesseMaximum) {
                if (direction) {
                    currentSpeed += acceleration * dt;
                    if (currentSpeed > vitesseMaximum) {
                        currentSpeed = vitesseMaximum;
                    }
                } else {
                    currentSpeed -= acceleration * dt;
                    if (Math.abs(currentSpeed) > vitesseMaximum) {
                        currentSpeed = -vitesseMaximum;
                    }
                }
            }
        } else {
            if (currentSpeed > 0) {
                currentSpeed -= acceleration * dt;
                if (currentSpeed < 0) {
                    currentSpeed = 0;
                }
            } else if (currentSpeed < 0) {
                currentSpeed += acceleration * dt;
                if (currentSpeed > 0) {
                    currentSpeed = 0;
                }
            }

        }
        return currentSpeed;
    }
    @Override
    void calculateDx(double dt) {
        vitesseX = calculateSpeed(dt, horizontalPressed,vitesseX, directionRight);
    }

    @Override
    void calculateDy(double dt) {
        vitesseY = calculateSpeed(dt, verticalPressed,vitesseY, !directionUp);
    }

    @Override
    public void verifierCollision(double largeurEcran, double hauteurEcran, Camera camera) {
        if (y + hauteur > hauteurEcran) {
            y = hauteurEcran - hauteur;
            vitesseY = 0;
        } else if (y < 0) {
            y = 0;
            vitesseY = 0;
        }
        if (x < camera.getX()) {
            x = camera.getX();
            vitesseX = 0;
        }
    }

    void shoot() {
        if (shootPressed && shootTimer < 0) {
            projectileLauncher.shoot(x + width, y + hauteur / 4);
            shootTimer = shootConst;
        }
    }

    public ProjectileLauncher getProjectileLauncher() {
        return projectileLauncher;
    }
    void update(double dt, double largeurEcran, double hauteurEcran, Camera camera) {
        super.update(dt);
        verifierCollision(largeurEcran, hauteurEcran, camera);
        invisibilityTimer -= dt;
        shootTimer -= dt;
        shoot();
    }
    void enemyHit(boolean hitBoolean) {
        if (invisibilityTimer < 0 && hitBoolean) {
            invisibilityTimer = invisibilityConst;
            health-=1;
        }
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    void draw(GraphicsContext context, Camera camera) {
        double displayX = x - camera.getX();
        if (vitesseX > 0 && invisibilityTimer < 0) {
            context.drawImage(movingImage, displayX, y);
        } else if (invisibilityTimer > 0) {
            if (flicker) {
                context.drawImage(damagedImage, displayX, y);
            }
            flickerCount += 1;
            if (flickerCount > 15) {
                flicker = !flicker;
                flickerCount = 0;
            }
        } else {
            context.drawImage(imageDeBase, displayX, y);
        }
    }

    boolean isDead() {
        return (health <= 0);
    }
}

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

    @Override
    void calculateDx(double dt) {
        if (horizontalPressed) {
            if (Math.abs(speedX) < vitesseMaximum) {
                if (directionRight) {
                    speedX += acceleration * dt;
                    if (speedX > vitesseMaximum) {
                        speedX = vitesseMaximum;
                    }
                } else {
                    speedX -= acceleration * dt;
                    if (Math.abs(speedX) > vitesseMaximum) {
                        speedX = -vitesseMaximum;
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
    void calculateDy(double dt) {
        if (verticalPressed) {
            if (Math.abs(speedY) < vitesseMaximum) {
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
    public void verifierCollision(double largeurEcran, double hauteurEcran, Camera camera) {
        if (y + height > hauteurEcran) {
            y = hauteurEcran - height;
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

    void shoot() {
        if (shootPressed && shootTimer < 0) {
            projectileLauncher.shoot(x + width, y + height / 4);
            shootTimer = shootConst;
        }
    }

    public ProjectileLauncher getProjectileLauncher() {
        return projectileLauncher;
    }

    void update(double dt, double screenWidth, double screenheight, Camera camera, double levelLength) {
        super.update(dt);
        verifierCollision(screenWidth, screenheight, camera);
        invisibilityTimer -= dt;
        shootTimer -= dt;
        shoot();
        moveCamera(camera, dt, levelLength);
    }
    void enemyHit(boolean hitBoolean) {
        if (invisibilityTimer < 0 && hitBoolean == true) {
            invisibilityTimer = invisibilityConst;
            health-=1;
        }
    }
    @Override
    void draw(GraphicsContext context, Camera camera) {
        double displayX = x - camera.getX();
        if (speedX > 0 && invisibilityTimer < 0) {
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

    void moveCamera(Camera camera, double dt, double levellength) {
        if ((camera.getX() + camera.getWidth()) < levellength) {
            if (((x - camera.getX()) >= camera.getWidth() / 5)) {
                camera.setX(camera.getX() + speedX * dt);
            }
        }
    }

    boolean isDead() {
        return (health <= 0);
    }
}

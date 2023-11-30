package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Player extends Actor {


    private Image imageEnMovement;
    private Image poissonBlesse;
    private boolean horizontalPressed = false;
    private boolean verticalPressed = false;
    private boolean directionRight = false;
    private boolean directionUp = false;
    private boolean shootPressed = false;
    private double tempsInvisibilite = 0;
    private final double constanteInvisibilite = 2;
    private double shootTimer = 0;
    private  final double shootConst = 0.5;
    private boolean flicker = false;
    private  int flickerCount = 0;
    private ProjectileLauncher projectileLauncher;
    private  int vie;

    /**
     *
     * @param x
     * @param y
     * @param vie
     */
    public Player(double x, double y,int vie) {
        super(x, y, 102, 90);

        this.vie = vie;
        projectileLauncher = new ProjectileLauncher();
        acceleration = 1000;
        imageDeBase = new Image("charlotte.png");
        imageEnMovement = new Image("charlotte-avant.png");
        poissonBlesse = new Image("charlotte-outch.png");

    }

    double calculateSpeed(double dt, boolean pressed, double vitesse, boolean direction){
        if (pressed) {
            if (Math.abs(vitesse) < vitesseMaximum) {
                if (direction) {
                    vitesse += acceleration * dt;
                    if (vitesse > vitesseMaximum) {
                        vitesse = vitesseMaximum;
                    }
                } else {
                    vitesse -= acceleration * dt;
                    if (Math.abs(vitesse) > vitesseMaximum) {
                        vitesse = -vitesseMaximum;
                    }
                }
            }
        } else {
            if (vitesse > 0) {
                vitesse -= acceleration * dt;
                if (vitesse < 0) {
                    vitesse = 0;
                }
            } else if (vitesse < 0) {
                vitesse += acceleration * dt;
                if (vitesse > 0) {
                    vitesse = 0;
                }
            }

        }
        return vitesse;
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
        tempsInvisibilite -= dt;
        shootTimer -= dt;
        shoot();
    }

    void enemyHit(boolean hitBoolean) {
        if (tempsInvisibilite < 0 && hitBoolean) {
            tempsInvisibilite = constanteInvisibilite;
            vie -=1;
        }
    }
    @Override
    void draw(GraphicsContext context, Camera camera) {
        double displayX = x - camera.getX();
        if (vitesseX > 0 && tempsInvisibilite < 0) {
            context.drawImage(imageEnMovement, displayX, y);
        } else if (tempsInvisibilite > 0) {
            if (flicker) {
                context.drawImage(poissonBlesse, displayX, y);
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

    public void setVie(int vie) {
        this.vie = vie;
    }

    boolean isDead() {
        return (vie <= 0);
    }

    public int getVie() {
        return vie;
    }
}

package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Player extends Actor {


    private final Image imageEnMovement;
    private final Image poissonBlesse;
    private boolean horizontalPressed = false;
    private boolean verticalPressed = false;
    private boolean directionRight = false;
    private boolean directionUp = false;
    private boolean shootPressed = false;
    private double tempsInvisibilite = 0;
    private final double constanteInvisibilite = 2;
    private double shootTimer = 0;
    private final double shootConst = 0.5;
    private boolean flicker = false;
    private int flickerCount = 0;
    private final ProjectileLauncher projectileLauncher;
    private int vie;

    /**
     * Constructeur principale
     *
     * @param x   La coordonnée x de l'acteur.
     * @param y   La coordonnée y de l'acteur.
     * @param vie Le nombre de vie de l'acteur
     */
    public Player(double x, double y, int vie) {
        super(x, y, 102, 90);

        this.vie = vie;
        projectileLauncher = new ProjectileLauncher();
        acceleration = 1000;
        imageDeBase = new Image("charlotte.png");
        imageEnMovement = new Image("charlotte-avant.png");
        poissonBlesse = new Image("charlotte-outch.png");

    }

    /**
     * Cette méthode permet de calculer la variation de la vitesse du joeur
     *
     * @param dt        Variation de temps
     * @param pressed   boolean qui dit si une touche est enclenché
     * @param vitesse   vitesse initiale de l'acteur
     * @param direction direction de la vitesse
     * @return vitesse final
     */
    public double calculateSpeed(double dt, boolean pressed, double vitesse, boolean direction) {
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
    public void calculateDx(double dt) {
        vitesseX = calculateSpeed(dt, horizontalPressed, vitesseX, directionRight);
    }

    @Override
    public void calculateDy(double dt) {
        vitesseY = calculateSpeed(dt, verticalPressed, vitesseY, !directionUp);
    }

    /**
     * Verifie si l'acteur est en collision
     *
     * @param largeurEcran La largeur de l'écran.
     * @param hauteurEcran La hauteur de l'écran.
     * @param camera       La caméra utilisée pour ajuster la vue.
     */
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

    /**
     * La méthode qui permet au joeur d'enclenché le processus de tirer
     */
    public void shoot() {
        if (shootPressed && shootTimer < 0) {
            projectileLauncher.shoot(x + width, y + hauteur / 4);
            shootTimer = shootConst;
        }
    }

    /**
     * La méthode classique update, rien de special
     *
     * @param dt           variation de temp
     * @param largeurEcran largeur de l'écran
     * @param hauteurEcran hauteur de l'écran
     * @param camera       camera
     */
    public void update(double dt, double largeurEcran, double hauteurEcran, Camera camera) {
        super.update(dt);
        verifierCollision(largeurEcran, hauteurEcran, camera);
        tempsInvisibilite -= dt;
        shootTimer -= dt;
        shoot();
    }

    /**
     * Cette méthode check si le joueur est entré en contact avec un ennemi
     *
     * @param hitBoolean Boolean qui nous dit s'il y a contact ou non
     */
    public void enemyHit(boolean hitBoolean) {
        if (tempsInvisibilite < 0 && hitBoolean) {
            tempsInvisibilite = constanteInvisibilite;
            vie -= 1;
        }
    }

    @Override
    public void draw(GraphicsContext context, Camera camera) {
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

    public void moveLeft() {
        horizontalPressed = true;
        directionRight = false;

    }

    public ProjectileType getPT() {
        return projectileLauncher.getCurrent();
    }

    public void setPt(ProjectileType pt) {
        projectileLauncher.setCurrent(pt);
    }

    public void moveRight() {
        horizontalPressed = true;
        directionRight = true;
    }

    public void stopMoveHorizontal() {
        horizontalPressed = false;

    }

    public void moveUp() {
        verticalPressed = true;
        directionUp = true;

    }

    public void moveDown() {
        verticalPressed = true;
        directionUp = false;
    }

    public void shootDown() {
        shootPressed = true;
    }

    public void shootRelease() {
        shootPressed = false;
    }

    public void stopMoveVertical() {
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

    public ProjectileLauncher getProjectileLauncher() {
        return projectileLauncher;
    }
}

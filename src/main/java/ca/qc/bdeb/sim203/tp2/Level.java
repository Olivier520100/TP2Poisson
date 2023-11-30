package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Random;

import static ca.qc.bdeb.sim203.tp2.ProjectileType.*;

public class Level {
    private Player joueur;
    private ArrayList<Enemy> ennemis;
    private Baril baril;
    private MainText textDebutNiveau;
    private MainText textFinNiveau;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private double longueurNiveau;
    private int nombreNiveau;
    private Camera camera;
    private ArrayList<BackgroundElement> objetsDuFond = new ArrayList<>();


    private double tempsDepuisDernierAjout = 0;
    private double displayTime = 4;

    private double respawnTime;


    private boolean finNiveau = false;
    private Bar barDeVie = new Bar();

    private Color colorFond;

    private boolean joeurMort = false;

    private boolean debug = false;

    private final int VIES_MAXIMUM = 4;

    public Level(double largeur, double hauteur, int nombreNiveau, int vie) {
        MovableObject.debug = false;
        ennemis = new ArrayList<>();

        joueur = new Player(0, 260, vie);
        camera = new Camera(0, 0, largeur, hauteur);
        this.nombreNiveau = nombreNiveau;
        respawnTime = 0.75 + 1 / (Math.sqrt(nombreNiveau));
        longueurNiveau = 8 * largeur;
        colorFond = Color.hsb((new Random()).nextInt(190, 270), 0.84, 1);
        double placementBaril = ((new Random()).nextDouble(20, 60) / 100) * longueurNiveau;
        baril = new Baril(placementBaril, hauteur / 2, hauteur);
        creationDesEnnemis();
        creationObjetsDuFond(hauteur);
        textDebutNiveau = new MainText("NIVEAU " + nombreNiveau, largeur, hauteur);
        textFinNiveau = new MainText("FIN DE PARTIE  ", largeur, hauteur);

    }

    public void updateGame(double dt, double largeur, double hauteur) {
        displayTime -= dt;

        collectionDesDechets();

        if (!finNiveau && !joeurMort) {
            checkFinNiveau();
            checkJoueurMort();
            if (joeurMort) {
                displayTime = 4;
            }
            tempsDepuisDernierAjout += dt;
            if (tempsDepuisDernierAjout > respawnTime) {
                tempsDepuisDernierAjout = 0;
                creationDesEnnemis();
            }
            joueur.update(dt, largeur, hauteur, camera);
            ennemiUpdate(dt);
            baril.update(dt);
            projectileUpdate(dt, hauteur);

            GameObjectHandler.ajouterProjectiles(joueur, projectiles);
            GameObjectHandler.bougerCamera(joueur, camera, longueurNiveau);
            checkCollisions();
            barDeVie.setViesRestantes(joueur.getHealth());
            barDeVie.setActuel(joueur.getPT());

        }
        if (joeurMort) {
            if (displayTime < 0) {
                finNiveau = true;
            }
        }


    }

    public void drawGame(GraphicsContext context) {

        arrierePlanDraw(context, camera);

        drawObjet(joueur, context, camera);

        ennemiDraw(context, camera);

        drawObjet(baril, context, camera);

        projectileDraw(context);

        barDeVie.draw(context);

        if (debug) {
            context.setFont(Font.font(12));
            context.fillText("NB Poissons : " + ennemis.size(), 25, 100);
            context.fillText("NB Projectiles : " + projectiles.size(), 25, 120);
            context.fillText("Position Charlotte : " + (joueur.getX() / longueurNiveau * 100) + "%", 25, 140);
        }

        if (joeurMort) {
            if (displayTime > 0) {
                textFinNiveau.draw(context, Color.RED);
            }
        } else {
            if (displayTime > 0) {
                textDebutNiveau.draw(context, Color.WHITE);
            }
        }
    }

    public void drawObjet(MovableObject go, GraphicsContext context, Camera camera) {
        go.draw(context, camera);
        go.drawDebug(context, camera);
    }

    public void drawObjet(GameObject go, GraphicsContext context, Camera camera) {
        go.draw(context, camera);
    }

    public void ennemiUpdate(double dt) {
        for (Enemy enemy : ennemis) {
            enemy.update(dt);
        }
    }

    public void ennemiDraw(GraphicsContext context, Camera camera) {
        for (Enemy enemy : ennemis) {
            drawObjet(enemy, context, camera);
        }
    }

    public void arrierePlanDraw(GraphicsContext context, Camera camera) {
        context.setFill(colorFond);
        context.fillRect(0, 0, camera.getLargeur(), camera.getHauteur());
        for (BackgroundElement backgroundElement : objetsDuFond) {
            drawObjet(backgroundElement, context, camera);
        }
    }

    public void projectileUpdate(double dt, double height) {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile projectile = projectiles.get(i);
            if (projectile instanceof MagnetProjectile) {
                GameObjectHandler.preprocessMagnetic(((MagnetProjectile) projectile), ennemis);
                ((MagnetProjectile) projectile).update(dt, height);
            } else {
                projectile.update(dt);
            }
        }
    }

    public void projectileDraw(GraphicsContext context) {
        for (Projectile projectile : projectiles) {
            drawObjet(projectile, context, camera);
        }
    }

    public void collectionDesDechets() {
        GameObjectHandler.supprimerEnnemis(ennemis, camera);
        GameObjectHandler.supprimerProjectiles(projectiles, camera);
    }

    public void creationDesEnnemis() {
        GameObjectHandler.ajouterEnnemi(ennemis, camera, nombreNiveau);
    }

    public void checkFinNiveau() {
        finNiveau = (joueur.getX() > longueurNiveau);
    }

    public void checkJoueurMort() {
        joeurMort = (joueur.isDead());
    }

    public void creationObjetsDuFond(double height) {
        GameObjectHandler.creerBackgroundElements(objetsDuFond, height, longueurNiveau);
    }

    public boolean isFinNiveau() {
        return finNiveau;
    }

    public boolean isJoeurMort() {
        return joeurMort;
    }

    public int getHealth() {
        return joueur.getHealth();
    }

    public void checkCollisions() {
        GameObjectHandler.genererCollisionsJoueurEnnemi(joueur, ennemis);
        GameObjectHandler.genererCollisionsProjectileEnnemi(projectiles, ennemis);
        GameObjectHandler.genererCollisionsJoueurBaril(joueur, baril);
    }

    public void setProjectileType1() {
        if (debug) {
            GameObjectHandler.setProjectile(joueur, BASIC);

        }
    }

    public void setProjectileType2() {
        if (debug) {
            GameObjectHandler.setProjectile(joueur, TRIPLE);
        }

    }

    public void setProjectileType3() {
        if (debug) {
            GameObjectHandler.setProjectile(joueur, MAGNET);
        }

    }

    public void setVIES_MAXIMUM() {
        if (debug) {
            GameObjectHandler.setMaxHealth(joueur, VIES_MAXIMUM);
        }
    }

    public void downPress() {
        joueur.moveDown();
    }

    public void upPress() {
        joueur.moveUp();
    }

    public void leftPress() {
        joueur.moveLeft();
    }

    public void rightPress() {
        joueur.moveRight();
    }

    public void verticalRelease() {
        joueur.stopMoveVertical();
    }

    public void horizontalRelease() {
        joueur.stopMoveHorizontal();
    }

    public void spacePress() {
        joueur.shootDown();
    }

    public void spaceRelease() {
        joueur.shootRelease();
    }

    public void debug() {
        MovableObject.debug = !MovableObject.debug;
        debug = MovableObject.debug;
    }

}

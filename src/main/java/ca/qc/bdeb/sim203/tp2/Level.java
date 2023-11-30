package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Random;

import static ca.qc.bdeb.sim203.tp2.ProjectileType.*;

public class Level {
    Player joueur;
    ArrayList<Enemy> enemies;
    Baril baril;
    MainText textDebutNiveau;
    MainText textFinNiveau;
    ArrayList<Projectile> projectiles = new ArrayList<>();
    double longueurNiveau;
    int nombreNiveau;
    Camera camera;
    ArrayList<BackgroundElement> objetsDuFond = new ArrayList<>();


    double tempsDepuisDernierAjout = 0;
    double displayTime = 4;

    double respawnTime;


    boolean levelEnd = false;
    Bar topBar = new Bar();

    Color colorFond;

    boolean levelDead = false;

    boolean debug = false;

    final int MAXHEALTH = 4;

    public Level(double width, double height, int nombreNiveau, int health) {
        MovableObject.debug = false;
        enemies = new ArrayList<>();

        joueur = new Player(0, 260,health);
        camera = new Camera(0, 0, width, height);
        this.nombreNiveau = nombreNiveau;
        respawnTime = 0.75 + 1 / (Math.sqrt(nombreNiveau));
        longueurNiveau = 8 * width;
        colorFond  = Color.hsb((new Random()).nextInt(190,270),0.84,1);
        double placementBaril = ((new Random()).nextDouble(20, 60) / 100) * longueurNiveau;
        baril = new Baril(placementBaril, height / 2, height);
        enemyCreation();
        backgroundElementsCreation(height);
        textDebutNiveau = new MainText("NIVEAU " + nombreNiveau, width, height);
        textFinNiveau = new MainText("FIN DE PARTIE  ", width, height);

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
    public void debug(){
        MovableObject.debug = !MovableObject.debug;
        debug = MovableObject.debug;
    }

    public void updateGame(double dt, double width, double height) {
        displayTime -= dt;

        garbageCollection();

        if (!levelEnd && !levelDead) {
            levelEndCheck();
            levelDeadCheck();
            if (levelDead) {
                displayTime = 4;
            }
            tempsDepuisDernierAjout += dt;
            if (tempsDepuisDernierAjout > respawnTime) {
                tempsDepuisDernierAjout = 0;
                enemyCreation();
            }
            joueur.update(dt, width, height, camera);
            enemyUpdate(dt);
            baril.update(dt);
            projectileUpdate(dt, height);

            GameObjectHandler.addProjectiles(joueur,projectiles);
            GameObjectHandler.moveCamera(joueur,camera, longueurNiveau);
            checkCollisions();
            topBar.setViesRestantes(joueur.getHealth());
            topBar.setActuel(joueur.getPT());

        }
        if (levelDead) {
            if (displayTime < 0) {
                levelEnd = true;
            }
        }


    }

    public void drawGame(GraphicsContext context) {

        bgDraw(context,camera);

        drawObject(joueur, context, camera);

        enemyDraw(context, camera);

        drawObject(baril, context, camera);

        projectileDraw(context);

        topBar.draw(context);

        if (debug){
            context.setFont(Font.font(12));
            context.fillText("NB Poissons : " + enemies.size(),25, 100 );
            context.fillText("NB Projectiles : " + projectiles.size(), 25, 120);
            context.fillText("Position Charlotte : " + ( joueur.getX()/ longueurNiveau * 100 ) + "%", 25, 140);
        }

        if (levelDead) {
            if (displayTime > 0) {
                textFinNiveau.draw(context, camera, joueur.getX() - 350,Color.RED);
            }
        } else {
            if (displayTime > 0) {
                textDebutNiveau.draw(context, camera, 0, Color.WHITE);
            }
        }
    }

    public void drawObject(MovableObject go, GraphicsContext context, Camera camera){
        go.draw(context, camera);
        go.drawDebug(context, camera);
    }
    public void drawObject(GameObject go, GraphicsContext context, Camera camera){
        go.draw(context, camera);
    }
    public void enemyUpdate(double dt) {
        for (Enemy enemy : enemies) {
            enemy.update(dt);
        }
    }

    public void enemyDraw(GraphicsContext context, Camera camera) {
        for (Enemy enemy : enemies) {
            drawObject(enemy,context,camera);
        }
    }
    public void bgDraw(GraphicsContext context, Camera camera) {
        context.setFill(colorFond);
        context.fillRect(0, 0, camera.getLargeur(), camera.getHauteur());
        for (BackgroundElement backgroundElement : objetsDuFond) {
            drawObject(backgroundElement,context,camera);
        }
    }

    public void projectileUpdate(double dt, double height) {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile projectile = projectiles.get(i);
            if (projectile instanceof MagnetProjectile) {
                GameObjectHandler.preprocessMagnetic(((MagnetProjectile) projectile),enemies);
                ((MagnetProjectile) projectile).update(dt, height);
            } else {
                projectile.update(dt);
            }
        }
    }

    public void projectileDraw(GraphicsContext context) {
        for (Projectile projectile : projectiles) {
            drawObject(projectile,context,camera);
        }
    }

    public void garbageCollection(){
        GameObjectHandler.garbageCollectEnemies(enemies, camera);
        GameObjectHandler.garbageCollectProjectiles(projectiles, camera);
    }

    public void enemyCreation() {
        GameObjectHandler.addEnemies(enemies,camera, nombreNiveau);
    }

    public void levelEndCheck() {
        levelEnd = (joueur.getX() > longueurNiveau);
    }

    public void levelDeadCheck() {
        levelDead = (joueur.isDead());
    }

    public void backgroundElementsCreation(double height) {
        GameObjectHandler.createBackgroundElements(objetsDuFond, height, longueurNiveau);
    }

    public boolean isLevelEnd() {
        return levelEnd;
    }

    public boolean isLevelDead() {
        return levelDead;
    }

    public int getHealth(){
        return joueur.getHealth();
    }
    public void checkCollisions(){
        GameObjectHandler.playerEnemy(joueur,enemies);
        GameObjectHandler.projectileEnemy(projectiles,enemies);
        GameObjectHandler.playerBaril(joueur,baril);
    }

    public void setProjectileType1() {
        if (debug){
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
    public void setMaxHealth() {
        if (debug) {
            GameObjectHandler.setMaxHealth(joueur,MAXHEALTH);
        }
    }

}

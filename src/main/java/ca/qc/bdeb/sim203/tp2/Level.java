package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Level {
    Player player;
    ArrayList<Enemy> enemies;
    Baril baril;
    MainText levelStart;
    MainText levelDeadText;
    ArrayList<Projectile> projectiles = new ArrayList<>();
    double levelLength;
    int levelNumber;
    Camera camera;
    ArrayList<BackgroundElement> backgroundElements = new ArrayList<>();

    private final int RANDOM_ENEMY_HEIGHT_ORIGIN = 50;

    double lastSpawn = 0;
    double displayTime = 4;

    double respawnTime;

    boolean levelEnd = false;
    Bar topBar = new Bar();

    Color colorFond;

    boolean levelDead = false;



    public Level(double width, double height, int levelNumber, int health) {

        player = new Player(0, 260,health);
        camera = new Camera(0, 0, width, height);
        this.levelNumber = levelNumber;
        respawnTime = 0.75 + 1 / (Math.sqrt(levelNumber));
        levelLength = 8 * width;
        colorFond  = Color.hsb((new Random()).nextInt(190,270),0.84,1);
        double placementBaril = ((new Random()).nextDouble(20, 60) / 100) * levelLength;
        baril = new Baril(placementBaril, height / 2, height);
        enemyCreation();
        backgroundElementsCreation(height);
        levelStart = new MainText("Niveau " + levelNumber, width, height);
        levelDeadText = new MainText("Mort", width, height);
    }

    public void downPress() {
        player.moveDown();
    }

    public void upPress() {
        player.moveUp();
    }

    public void leftPress() {
        player.moveLeft();
    }

    public void rightPress() {
        player.moveRight();
    }

    public void verticalRelease() {
        player.stopMoveVertical();
    }

    public void horizontalRelease() {
        player.stopMoveHorizontal();
    }

    public void spacePress() {
        player.shootDown();
    }

    public void spaceRelease() {
        player.shootRelease();
    }
    public void debug(){
        MovableObject.debug = !MovableObject.debug;
    }

    public void updateGame(double dt, double width, double height) {
        displayTime -= dt;

        if (!levelEnd && !levelDead) {
            levelEndCheck();
            levelDeadCheck();
            if (levelDead) {
                displayTime = 4;
            }
            lastSpawn += dt;
            if (lastSpawn > respawnTime) {
                lastSpawn = 0;
                enemyCreation();
            }
            player.update(dt, width, height, camera, levelLength);
            enemyUpdate(dt);
            baril.update(dt, width, height, camera);
            projectileUpdate(dt, height);

            GameObjectHandler.addProjectiles(player,projectiles);
            checkCollisions();
            topBar.setHeartsLeft(player.getHealth());
            topBar.setCurrent(player.getPT());

        }
        if (levelDead) {
            if (displayTime < 0) {
                levelEnd = true;
            }
        }


    }

    public void drawGame(GraphicsContext context) {

        context.setFill(colorFond);
        context.fillRect(0, 0, camera.getWidth(), camera.getHeight());
        for (BackgroundElement backgroundElement : backgroundElements) {
            backgroundElement.draw(context, camera);
        }
        player.draw(context, camera);
        player.drawDebug(context, camera);
        enemyDraw(context, camera);
        baril.draw(context, camera);
        projectileDraw(context, camera);
        topBar.draw(context);



        if (levelDead) {
            if (displayTime > 0) {
                levelDeadText.draw(context, camera, player.getX());
            }
        } else {
            if (displayTime > 0) {
                levelStart.draw(context, camera, 0);
            }
        }
    }

    public void enemyUpdate(double dt) {
        for (Enemy enemy : enemies) {
            enemy.update(dt);
        }
    }

    public void enemyDraw(GraphicsContext context, Camera camera) {
        for (Enemy enemy : enemies) {
            enemy.draw(context, camera);
            enemy.drawDebug(context, camera);
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

    public void projectileDraw(GraphicsContext context, Camera camera) {
        for (Projectile projectile : projectiles) {
            projectile.draw(context, camera);
            projectile.drawDebug(context, camera);

        }
    }


    public void enemyCreation() {
        Random rand = (new Random());
        int enemyCount = rand.nextInt(1, 6);
        enemies = new ArrayList<>();

        for (int i = 0; i < enemyCount; i++) {

            double enemyHeight = rand.nextDouble(RANDOM_ENEMY_HEIGHT_ORIGIN, 120);
            double enemyWidth = enemyHeight / 120 * 104;
            enemies.add(new Enemy(camera.getX() + camera.getWidth() + 50, rand.nextDouble(
                    camera.getHeight() / 5, 4 * camera.getHeight() / 5), enemyHeight, enemyWidth, levelNumber));
        }
    }

    public void levelEndCheck() {
        levelEnd = (player.getX() > levelLength);
    }

    public void levelDeadCheck() {
        levelDead = (player.isDead());
    }

    public void backgroundElementsCreation(double height) {
        int x = 0;
        while (x < levelLength * 9 / 8) {
            backgroundElements.add((new BackgroundElement(x, height)));
            x += (new Random()).nextInt(50, 100) + 80; // explain pls
        }
    }

    public boolean isLevelEnd() {
        return levelEnd;
    }

    public boolean isLevelDead() {
        return levelDead;
    }

    public int getHealth(){
        return player.getHealth();
    }
    public void checkCollisions(){
        GameObjectHandler.playerEnemy(player,enemies);
        GameObjectHandler.projectileEnemy(projectiles,enemies);
        GameObjectHandler.playerBaril(player,baril);
    }
}

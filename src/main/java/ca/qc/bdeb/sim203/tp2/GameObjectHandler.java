package ca.qc.bdeb.sim203.tp2;

import java.util.ArrayList;
import java.util.Random;

public class GameObjectHandler {


    private GameObjectHandler() {}

    static void playerEnemy(Player player, ArrayList<Enemy> enemies){
        boolean hit;
        for (Enemy enemy : enemies) {
            hit = checkCollisionWithObject(player,enemy);
            if ((hit)){
                player.enemyHit(true);
            }
        }
        player.enemyHit(false);
    }
    static void playerBaril(Player player, Baril baril){
        if (!(baril.isOuvert())) {
            boolean hit = checkCollisionWithObject(player, baril);
            if ((hit)) {
                player.setPT(baril.getProjectileDisponible());
                baril.setOuvert(true);
            }
        }

    }

    static boolean checkCollisionWithObject(MovableObject moveableObject1, MovableObject moveableObject2) {

        return moveableObject1.getX() < moveableObject2.getX() + moveableObject2.getWidth() && moveableObject1.getX() + moveableObject1.getWidth() > moveableObject2.getX() && moveableObject1.getY() < moveableObject2.getY() + moveableObject2.getHauteur() && moveableObject1.getY() + moveableObject1.getHauteur() > moveableObject2.getY();
    }
    static void projectileEnemy(ArrayList<Projectile> projectiles, ArrayList<Enemy> enemies) {
        for (Projectile projectile : projectiles){
            for (int i = enemies.size() - 1; i >= 0; i--) {
                Enemy enemy = enemies.get(i);
                if (checkCollisionWithObject(projectile,enemy)) {
                    enemies.remove(i);
                }
            }
        }
    }
    static void addProjectiles(Player player,ArrayList<Projectile> projectiles){
        projectiles.addAll(player.getProjectileLauncher().getProjectilesInside());
        player.getProjectileLauncher().getProjectilesInside().clear();
    }
    static void preprocessMagnetic(MagnetProjectile mP, ArrayList<Enemy> enemies){
        mP.preprocess(enemies);
    }
    static void setProjectile(Player player, ProjectileType projectileType){
        player.setPT(projectileType);
    }

    public static void setMaxHealth(Player player, int maxhealth) {
        player.setHealth(maxhealth);
    }

    public static void addEnemies(ArrayList<Enemy> enemies, Camera camera,int levelNumber){
        Random rand = (new Random());
        int enemyCount = rand.nextInt(1, 6);
        ArrayList<Enemy> enemiestoadd = new ArrayList<>();
        final int RANDOM_ENEMY_HEIGHT_ORIGIN = 50;
        for (int i = 0; i < enemyCount; i++) {

            double enemyHeight = rand.nextDouble(RANDOM_ENEMY_HEIGHT_ORIGIN, 120);
            double enemyWidth = enemyHeight / 120 * 104;
            enemiestoadd.add(new Enemy(camera.getX() + camera.getLargeur() + 50, rand.nextDouble(
                    camera.getHauteur() / 5, 4 * camera.getHauteur() / 5), enemyHeight, enemyWidth, levelNumber));
        }
        enemies.addAll(enemiestoadd);

    }
    public static void garbageCollectEnemies(ArrayList<Enemy> enemies, Camera camera){
        for (int i = enemies.size() - 1; i >= 0; i--) {
            if (enemies.get(i).getX() + enemies.get(i).getWidth()< camera.getX()){
                enemies.remove(i);
            }
        }
    }
    public static void garbageCollectProjectiles(ArrayList<Projectile> projectiles, Camera camera){
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            if (projectiles.get(i).getX() > camera.getX() + camera.getLargeur()){
                projectiles.remove(i);
            }
        }
    }



}

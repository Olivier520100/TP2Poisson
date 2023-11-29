package ca.qc.bdeb.sim203.tp2;

import java.util.ArrayList;

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
        if (!(baril.isOpen())) {
            boolean hit = checkCollisionWithObject(player, baril);
            if ((hit)) {
                player.setPT(baril.getProjectileInside());
                baril.setOpen(true);
            }
        }

    }

    static boolean checkCollisionWithObject(MovableObject moveableObject1, MovableObject moveableObject2) {

        return moveableObject1.getX() < moveableObject2.getX() + moveableObject2.getWidth() && moveableObject1.getX() + moveableObject1.getWidth() > moveableObject2.getX() && moveableObject1.getY() < moveableObject2.getY() + moveableObject2.getHeight() && moveableObject1.getY() + moveableObject1.getHeight() > moveableObject2.getY();
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
    /*
    void outOfBounds(Camera camera) {
        if (x > camera.getX() + camera.getWidth()) {
            outOfBounds = true;
        }
    }
    */

}

package ca.qc.bdeb.sim203.tp2;

import java.util.ArrayList;
import java.util.Random;

public class GameObjectHandler {


    private GameObjectHandler() {}

    public static void genererCollisionsJoueurEnnemi(Player joueur, ArrayList<Enemy> ennemi){
        boolean touche;
        for (Enemy enemie : ennemi) {
            touche = genererCollisionsObjet(joueur,enemie);
            if ((touche)){
                joueur.enemyHit(true);
            }
        }
        joueur.enemyHit(false);
    }
    public static void genererCollisionsJoueurBaril(Player joueur, Baril baril){
        if (!(baril.isOuvert())) {
            boolean touche = genererCollisionsObjet(joueur, baril);
            if ((touche)) {
                joueur.setPt(baril.getProjectileDisponible());
                baril.setOuvert(true);
            }
        }

    }

    private static boolean genererCollisionsObjet(MovableObject objet1, MovableObject objet2) {

        return objet1.getX() < objet2.getX() + objet2.getWidth() && objet1.getX() + objet1.getWidth() > objet2.getX() && objet1.getY() < objet2.getY() + objet2.getHauteur() && objet1.getY() + objet1.getHauteur() > objet2.getY();
    }
    public static void genererCollisionsProjectileEnnemi(ArrayList<Projectile> projectiles, ArrayList<Enemy> ennemis) {
        for (Projectile projectile : projectiles){
            for (int i = ennemis.size() - 1; i >= 0; i--) {
                Enemy ennemi = ennemis.get(i);
                if (genererCollisionsObjet(projectile,ennemi)) {
                    ennemis.remove(i);
                }
            }
        }
    }
   public static void ajouterProjectiles(Player joueur, ArrayList<Projectile> projectiles){
        projectiles.addAll(joueur.getProjectileLauncher().getProjectilesDisponibles());
        joueur.getProjectileLauncher().getProjectilesDisponibles().clear();
    }
    public static void ajouterEnnemi(ArrayList<Enemy> ennemis, Camera camera, int numeroNiveau){
        Random rand = (new Random());
        int nombreEnnemis = rand.nextInt(1, 6);
        ArrayList<Enemy> ennemiAAjouter = new ArrayList<>();
        final int RANDOM_ENNEMI_TAILLE_ORIGIN = 50;
        for (int i = 0; i < nombreEnnemis; i++) {

            double hauteurEnnemi = rand.nextDouble(RANDOM_ENNEMI_TAILLE_ORIGIN, 120);
            double largeurEnnemi = hauteurEnnemi / 120 * 104;
            ennemiAAjouter.add(new Enemy(camera.getX() + camera.getLargeur() + 50, rand.nextDouble(
                    camera.getHauteur() / 5, 4 * camera.getHauteur() / 5), hauteurEnnemi, largeurEnnemi, numeroNiveau));
        }
        ennemis.addAll(ennemiAAjouter);

    }

    public static void creerBackgroundElements(ArrayList<BackgroundElement> backgroundElements, double hauteur, double tailleNiveau){
        int x = 0;
        while (x < tailleNiveau * 9 / 8) {
            backgroundElements.add((new BackgroundElement(x, hauteur)));
            x += (new Random()).nextInt(50, 100) + 80; // explain pls
        }
    }
    public static void supprimerEnnemis(ArrayList<Enemy> ennemis, Camera camera){
        for (int i = ennemis.size() - 1; i >= 0; i--) {
            if (ennemis.get(i).getX() + ennemis.get(i).getWidth()< camera.getX()){
                ennemis.remove(i);
            }
        }
    }
    public static void supprimerProjectiles(ArrayList<Projectile> projectiles, Camera camera){
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            if (projectiles.get(i).getX() > camera.getX() + camera.getLargeur()){
                projectiles.remove(i);
            }
        }
    }
    public static void bougerCamera(Player player, Camera camera, double levellength){
        if ((camera.getX() + camera.getLargeur()) < levellength) {
            if (((player.getX() - camera.getX()) >= camera.getLargeur() / 5)) {
                camera.setX(player.getX()+- camera.getLargeur()/5);
            }
        }
    }

    public static void preprocessMagnetic(MagnetProjectile mP, ArrayList<Enemy> enemies){
        mP.preprocess(enemies);
    }
    public static void setProjectile(Player joueur, ProjectileType projectileType){
        joueur.setPt(projectileType);
    }

    public static void setMaxHealth(Player joueur, int vieMaximum) {
        joueur.setVie(vieMaximum);
    }



}

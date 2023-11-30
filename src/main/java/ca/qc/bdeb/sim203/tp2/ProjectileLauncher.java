package ca.qc.bdeb.sim203.tp2;

import java.util.ArrayList;

/**
 * Cette classe représente un lanceur de projectiles dans le jeu.
 * Elle gère les différents types de projectiles et leur lancement.
 */
public class ProjectileLauncher {
    private ProjectileType current;
    private ArrayList<Projectile> projectilesInside = new ArrayList<>();

    /**
     * Constructeur pour créer un nouveau lanceur de projectiles.
     * Initialise le type de projectile par défaut à BASIC.
     */
    public ProjectileLauncher() {
        current = ProjectileType.BASIC;
    }

    /**
     * Définit le type de projectile actuel pour le lanceur.
     *
     * @param current Le type de projectile à utiliser.
     */
    public void setCurrent(ProjectileType current) {
        this.current = current;
    }

    /**
     * Récupère la liste des projectiles actuellement dans le lanceur.
     *
     * @return La liste des projectiles.
     */
    public ArrayList<Projectile> getProjectilesInside() {
        return projectilesInside;
    }

    /**
     * Tire un projectile du type actuellement sélectionné.
     * Les projectiles sont lancés à partir des coordonnées spécifiées.
     *
     * @param x La coordonnée x d'où le projectile est tiré.
     * @param y La coordonnée y d'où le projectile est tiré.
     */
    public void shoot(double x, double y) {
        projectilesInside.clear();
        switch (current) {
            case BASIC -> projectilesInside.add(new BasicProjectile(x, y));
            case TRIPLE -> {
                for (int i = 0; i < 3; i++) {
                    projectilesInside.add(new TripleProjectile(x, y));
                }
            }
            case MAGNET -> projectilesInside.add(new MagnetProjectile(x, y));
        }
    }

    /**
     * Récupère le type de projectile actuellement sélectionné pour le lanceur.
     *
     * @return Le type de projectile actuel.
     */
    public ProjectileType getCurrent() {
        return current;
    }
}

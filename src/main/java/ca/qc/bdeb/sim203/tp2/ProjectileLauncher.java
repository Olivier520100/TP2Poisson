package ca.qc.bdeb.sim203.tp2;

import java.util.ArrayList;

/**
 *
 * Elle gère les différents types de projectiles et leur lancement.
 */
public class ProjectileLauncher {
    private ProjectileType current;
    private ArrayList<Projectile> projectilesDisponibles = new ArrayList<>();

    /**
     * Constructor et set le type a basic
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
     *
     *
     * @return La liste des projectiles.
     */
    public ArrayList<Projectile> getProjectilesDisponibles() {
        return projectilesDisponibles;
    }

    /**
     * Tire un projectile du type actuellement sélectionné.
     *
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public void shoot(double x, double y) {
        projectilesDisponibles.clear();
        switch (current) {
            case BASIC -> projectilesDisponibles.add(new BasicProjectile(x, y));
            case TRIPLE -> {
                for (int i = 0; i < 3; i++) {
                    projectilesDisponibles.add(new TripleProjectile(x, y));
                }
            }
            case MAGNET -> projectilesDisponibles.add(new MagnetProjectile(x, y));
        }
    }

    /**
     *
     *
     * @return Le type de projectile actuel.
     */
    public ProjectileType getCurrent() {
        return current;
    }
}

package ca.qc.bdeb.sim203.tp2;

import java.util.ArrayList;

public class ProjectileLauncher {
    private ProjectileType current;

    public ProjectileLauncher() {
        current = ProjectileType.BASIC;
    }

    public void setCurrent(ProjectileType current) {
        this.current = current;
    }

    public void shoot(double x, double y, ArrayList<Projectile> projectiles) {
        switch (current) {
            case BASIC -> projectiles.add(new BasicProjectile(x, y));
            case TRIPLE -> {
                for (int i = 0; i < 3; i++) {
                    projectiles.add(new TripleProjectile(x, y));
                }
            }
            case MAGNET -> projectiles.add(new MagnetProjectile(x, y));
        }
    }
}

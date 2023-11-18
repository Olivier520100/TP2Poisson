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

    public void shoot(double x, double y, ArrayList<Projectile> projectiles){
        if (current==ProjectileType.BASIC) {
            projectiles.add(new BasicProjectile(x, y));
        } else if (current==ProjectileType.TRIPLE) {
            projectiles.add(new TripleProjectile(x, y));
            projectiles.add(new TripleProjectile(x, y));
            projectiles.add(new TripleProjectile(x, y));
        }
    }
}

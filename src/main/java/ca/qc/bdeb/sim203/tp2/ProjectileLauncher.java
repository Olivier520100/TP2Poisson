package ca.qc.bdeb.sim203.tp2;

import java.util.ArrayList;

public class ProjectileLauncher {
    private ProjectileType current;

    ArrayList<Projectile> projectilesInside = new ArrayList<>();

    public ProjectileLauncher() {
        current = ProjectileType.BASIC;
    }

    public void setCurrent(ProjectileType current) {
        this.current = current;
    }

    public ArrayList<Projectile> getProjectilesInside() {
        return projectilesInside;
    }

    public void setProjectilesInside(ArrayList<Projectile> projectilesInside) {
        this.projectilesInside = projectilesInside;
    }

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

    public ProjectileType getCurrent() {
        return current;
    }
}

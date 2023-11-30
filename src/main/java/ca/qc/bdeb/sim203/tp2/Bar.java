package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bar {
    private double viesRestantes = 4;

    private final double MAX_HP = 4;

    private final int PIXELS_AJOUTES = 20;

    private final int VALEUR_ALIGNEMENT = PIXELS_AJOUTES + 10;

    private ProjectileType actuel = ProjectileType.BASIC;

    private final Image imageBasique = new Image("./etoile.png");
    private final Image imageHippocampe = new Image("./hippocampe.png");
    private final Image imageSardines = new Image("./sardines.png");

    public void setViesRestantes(int viesRestantes) {
        this.viesRestantes = viesRestantes;
    }

    public void setActuel(ProjectileType actuel) {
        this.actuel = actuel;
    }

    /**
     * Cette methode permet de dessiner la barre de vie sur l'écran. Elle prend en consideration le pourcentage de vie
     * restant du joueur, et elle dessine le type de projectile utilisé aussi.
     *
     * @param context
     */
    public void draw(GraphicsContext context) {
        final int xDessin = 25;
        final double largeurBarVie = 150;
        final double positionImageProjectile = 10 + largeurBarVie;
        double percentageOfBar = viesRestantes / MAX_HP * 150;


        context.setFill(Color.WHITE);
        context.fillRect(xDessin, VALEUR_ALIGNEMENT, percentageOfBar, imageBasique.getHeight());
        context.setStroke(Color.WHITE);
        context.strokeRect(xDessin, VALEUR_ALIGNEMENT, 150, imageBasique.getHeight());


        switch (actuel) {
            case BASIC -> context.drawImage(imageBasique, PIXELS_AJOUTES + positionImageProjectile, VALEUR_ALIGNEMENT);
            case TRIPLE ->
                    context.drawImage(imageHippocampe, PIXELS_AJOUTES + positionImageProjectile, VALEUR_ALIGNEMENT);
            case MAGNET ->
                    context.drawImage(imageSardines, PIXELS_AJOUTES + positionImageProjectile, VALEUR_ALIGNEMENT);
        }
    }
}

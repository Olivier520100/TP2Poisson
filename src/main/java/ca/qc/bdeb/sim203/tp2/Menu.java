package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * Cette classe représente l'écran du menu dans le jeu.
 * Elle gère l'affichage et les interactions dans le menu principal.
 */
public class Menu {
    private Image randomEnemy;
    private Bouton retourBouton = new Bouton("boutonretour.png", 400, 400);
    private Bouton jouer = new Bouton("boutoncommencer.png", 80, 80);
    private Bouton infoBouton = new Bouton("boutoninfo.png", 400, 400);

    private boolean inInfo = false;
    private boolean toGame = false;

    private Image backgroundInfo = new Image("./info.png");
    private Image backgroundMain = new Image("./logo.png");

    /**
     * Traite les événements de clics dans la fenêtre.
     * Cette méthode décide des actions à effectuer en fonction de l'endroit cliqué.
     *
     * @param x La coordonnée x du clic.
     * @param y La coordonnée y du clic.
     */
    public void windowClick(double x, double y) {
        if (inInfo) {
            inInfo = !(infoBouton.clique(x, y));
        } else {
            if (!(inInfo = retourBouton.clique(x, y))) {
                toGame = jouer.clique(x, y);
            } else {
                randomEnemy = new Image("./poisson" + new Random().nextInt(1, 6) + ".png");
            }
        }
    }

    /**
     * Dessine le menu sur le contexte graphique fourni.
     * Cette méthode gère l'affichage de l'écran d'information et de l'écran principal du menu.
     *
     * @param context Le contexte graphique sur lequel dessiner.
     */
    public void draw(GraphicsContext context) {
        context.clearRect(0, 0, 900, 520);

        if (inInfo) {
            context.drawImage(backgroundInfo, 0, 0);
            context.drawImage(randomEnemy, 350, 100);
            retourBouton.draw(context);
        } else {
            context.drawImage(backgroundMain, 0, 0, 900, 520);
            jouer.draw(context);
            infoBouton.draw(context);
        }
    }

    /**
     * Vérifie si le jeu est prêt à commencer.
     * Cette méthode permet de savoir si l'utilisateur a choisi de démarrer le jeu.
     *
     * @return true si le jeu est prêt à commencer, false sinon.
     */
    public boolean isToGame() {
        return toGame;
    }
}

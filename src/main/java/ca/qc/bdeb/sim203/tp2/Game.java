package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;

public class Game {
    private int numeroNiveau = 1;
    private final int vieMaximum = 4;
    private int vieRestante = 4;
    private Level niveau;
    private final double largeur;
    private final double hauteur;
    private boolean inGame = false;

    private boolean debug = false;
    private Menu menu = new Menu();

    public Game(double largeur, double hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        niveau = new Level(largeur, hauteur, numeroNiveau, vieMaximum);
    }

    /**
     * set debug to opposee de debug courant
     */
    public void debug() {
        niveau.debug();
        debug = !debug;
    }

    /**
     * click sur bouton dans menu
     *
     * @param x
     * @param y
     */
    public void screenClick(double x, double y) {
        if (!inGame) {
            menu.windowClick(x, y);
        }
    }


    /**
     * update jeu, si inGame alors niveau, sinon menu
     *
     * @param context
     * @param dt
     */
    public void update(GraphicsContext context, double dt) {

        if (inGame) {
            if (!niveau.isFinNiveau()) {
                context.clearRect(0, 0, largeur, hauteur);
                niveau.updateGame(dt, largeur, hauteur);
                niveau.drawGame(context);
            } else {
                if (!niveau.isJoeurMort()) {
                    augmenterNiveau();
                } else {
                    menu = new Menu();
                    inGame = false;
                    numeroNiveau = 1;
                    niveau = new Level(largeur, hauteur, numeroNiveau, vieMaximum);
                }
            }

        } else {
            menu.draw(context);
            inGame = menu.isToGame();
        }
    }

    public void qPress() {
        niveau.setProjectileType1();
    }

    public void tPress() {
        if (debug) {
            debug = false;
            augmenterNiveau();
        }
    }

    public void augmenterNiveau() {
        numeroNiveau += 1;
        vieRestante = niveau.getHealth();
        niveau = new Level(largeur, hauteur, numeroNiveau, vieRestante);

    }

    public void wPress() {
        niveau.setProjectileType2();
    }

    public void ePress() {
        niveau.setProjectileType3();
    }

    public void rPress() {
        niveau.setVIES_MAXIMUM();
    }

    public void downPress() {
        niveau.downPress();
    }

    public void upPress() {
        niveau.upPress();
    }

    public void leftPress() {
        niveau.leftPress();
    }

    public void rightPress() {
        niveau.rightPress();
    }

    public void verticalRelease() {
        niveau.verticalRelease();
    }

    public void horizontalRelease() {
        niveau.horizontalRelease();
    }

    public void spacePress() {
        niveau.spacePress();
    }

    public void spaceRelease() {
        niveau.spaceRelease();
    }
}

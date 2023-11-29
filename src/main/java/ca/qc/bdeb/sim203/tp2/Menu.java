package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Menu {

    private Bouton retourBouton = new Bouton(true, "./bouton3.png", 400, 400);
    private Bouton jouer = new Bouton(true, "./bouton.png", 80, 80);

    private Bouton infoBouton = new Bouton(false, "./bouton2.png", 400, 400);


    private boolean inInfo = false;
    private boolean toGame = false;

    Image backgroundInfo = new Image("./info.png");
    Image backgroundMain = new Image("./logo.png");


    public void windowClick(double x, double y) {
        if (inInfo) {
            inInfo = !(infoBouton.clicked(x, y));
        } else {
            if (!(inInfo = retourBouton.clicked(x, y))) {
                toGame = jouer.clicked(x, y);
            }
        }
    }

    public void draw(GraphicsContext context) {
        if (inInfo) {
            context.clearRect(0, 0, 900, 520);
            context.drawImage(backgroundInfo, 0, 0);

            retourBouton.draw(context);
        } else {
            context.clearRect(0, 0, 900, 520);
            context.drawImage(backgroundMain, 0, 0, 900, 520);

            jouer.draw(context);
            infoBouton.draw(context);
        }
    }

    public boolean isToGame() {
        return toGame;
    }


}

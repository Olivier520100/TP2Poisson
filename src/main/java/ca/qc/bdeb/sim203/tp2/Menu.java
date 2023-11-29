package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Menu {


    private Image randomEnemy ;
    Bouton retourBouton = new Bouton(false, "boutonretour.png", 400, 400);
    Bouton jouer = new Bouton(true, "boutoncommencer.png", 80, 80);

    Bouton infoBouton = new Bouton(true, "boutoninfo.png", 400, 400);


    boolean inInfo = false;

    boolean toGame = false;

    Image backgroundInfo = new Image("./info.png");
    Image backgroundMain = new Image("./logo.png");


    public void windowClick(double x, do    uble y) {
        if (inInfo) {
            inInfo = !(infoBouton.clicked(x, y));
        } else {
            if (!(inInfo = retourBouton.clicked(x, y))) {
                toGame = jouer.clicked(x, y);
            }else {
                randomEnemy = new Image("./poisson" + (new Random()).nextInt(1, 6) + ".png");
            }
        }
    }
    public void draw(GraphicsContext context) {
        if (inInfo) {
            context.clearRect(0, 0, 900, 520);
            context.drawImage(backgroundInfo, 0, 0);


            context.drawImage(randomEnemy, 350, 100);

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

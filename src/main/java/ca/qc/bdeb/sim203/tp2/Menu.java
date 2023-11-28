package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Menu {

    Bouton info =new Bouton(true,"./bouton3.png",30,30);
    Bouton jouer = new Bouton(true,"./bouton.png", 200,30);

    Bouton retour = new Bouton(false, "./bouton2.png",30,30);

    boolean inInfo = false;
    boolean toGame = false;

    Image backgroundInfo = new Image("./info.png");

    void windowClick(double x, double y){
        if (inInfo ){
           inInfo = !(retour.clicked(x,y));
        } else {
            if(!(inInfo = info.clicked(x, y))){
                toGame = jouer.clicked(x,y);
            }
        }
    }
    void draw(GraphicsContext context){
        if (inInfo){
            context.drawImage(backgroundInfo,0,0);

            info.draw(context);
        } else {
            context.clearRect(0,0,900,520);
            jouer.draw(context);
            retour.draw(context);
        }
    }
    boolean isToGame(){
        return toGame;
    }


}

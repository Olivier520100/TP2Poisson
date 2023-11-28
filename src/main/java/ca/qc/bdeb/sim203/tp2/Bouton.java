package ca.qc.bdeb.sim203.tp2;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bouton {

    boolean pressed = false;
    int height = 40;
    int width = 120;      ]

    int x;
    int y;

    boolean active;

    Image buttonimage;
    

    public Bouton(boolean active, String buttonImagePath, int x, int y) {
        this.active = active;
        this.buttonimage = new Image(buttonImagePath);
        this.x = x;
        this.y = y;
    }
    public void draw(GraphicsContext context){
        context.drawImage(buttonimage,x,y);

    }

    public void clickSet(int clickx, int clicky){
        if (clickx> x && clickx < x+width && clicky > y && clicky < y+height)     {
            pressed=true;
        }

    }
    public boolean getPressed(){
        return  pressed;
    }

}

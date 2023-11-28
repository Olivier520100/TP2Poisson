package ca.qc.bdeb.sim203.tp2;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bouton {

    double height = 40;
    double width = 120;

    double x;
    double y;

    boolean active;

    Image buttonimage;
    

    public Bouton(boolean active, String buttonImagePath, double x, double y) {
        this.active = active;
        this.buttonimage = new Image(buttonImagePath);
        this.x = x;
        this.y = y;
    }
    public void draw(GraphicsContext context){
        context.drawImage(buttonimage,x,y);

    }

    public boolean clicked(double clickx, double clicky){
        return clickx > x && clickx < x + width && clicky > y && clicky < y + height;

    }




}

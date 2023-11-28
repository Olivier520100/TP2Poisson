package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainText {
    String text;
    double width;
    double height;
    double fontheight = 100;

    public MainText(String text, double width, double height) {
        this.text = text;
        this.width = width;
        this.height = height;

    }

    void draw(GraphicsContext context, Camera camera, double offset) {
        context.setFill(Color.WHITE);
        context.setFont(Font.font(fontheight));
        context.fillText(text, offset + width / 3 - camera.getX(), height / 2);
    }

}

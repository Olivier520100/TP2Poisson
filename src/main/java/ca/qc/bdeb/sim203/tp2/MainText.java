package ca.qc.bdeb.sim203.tp2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainText {
    String text;
    double width;
    double height;
    double fontHeight = 100;

    public MainText(String text, double width, double height) {
        this.text = text;
        this.width = width;
        this.height = height;

    }

    void draw(GraphicsContext context, Camera camera, double offset, Color color) {
        context.setFill(color);
        context.setFont(Font.font(fontHeight));
        context.fillText(text, offset + width / 3 - camera.getX(), height / 2);
    }

}

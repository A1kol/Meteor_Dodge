package org.example.meteorgame;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
    private Rectangle shape;
    private boolean left, right;

    public Player(double x, double y) {
        shape = new Rectangle(40,40, Color.BLUE);
        shape.setTranslateX(x);
        shape.setTranslateY(y);
    }

    public void update() {
        if(left && shape.getTranslateX() > 0)
            shape.setTranslateX(shape.getTranslateX() - 5);

        if(right && shape.getTranslateX() < 560)
            shape.setTranslateX(shape.getTranslateX() + 5);
    }

    public void handleKeyPressed(javafx.scene.input.KeyEvent e) {
        if(e.getCode() == KeyCode.LEFT)
            left = true;

        if(e.getCode() == KeyCode.RIGHT)
            right = true;
    }

    public void handleKeyReleased(javafx.scene.input.KeyEvent e) {
        if (e.getCode() == KeyCode.LEFT)
            left = false;

        if (e.getCode() == KeyCode.RIGHT)
            right = false;
    }


    public Rectangle getShape() {
        return shape;
    }
}

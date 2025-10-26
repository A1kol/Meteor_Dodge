package org.example.meteorgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Meteor {
    private Circle shape;
    private double speed;

    public Meteor(double x, double y) {
        shape = new Circle(20, Color.RED);
        shape.setTranslateX(x);
        shape.setTranslateY(y);
        speed = 3 + Math.random() * 4;
    }

    public void update() {
        shape.setTranslateY(shape.getTranslateY() + speed);
    }

    public boolean isOutOfScreen() {
        return shape.getTranslateY() > 800;
    }

    public Circle getShape() {
        return shape;
    }
}


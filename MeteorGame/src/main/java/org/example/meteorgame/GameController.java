package org.example.meteorgame;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameController {
    @FXML private Pane gamePane;
    @FXML private Button restartButton;
    @FXML private Label scoreLabel;

    private Player player;
    private List<Meteor> meteors = new ArrayList<>();
    private Long lastMeteorTime = 0L;
    private boolean gameRunning = true;
    private int score = 0;

    @FXML
    public void initialize() {
        player = new Player(280, 720);
        gamePane.getChildren().add(player.getShape());

        gamePane.setOnKeyPressed(e -> player.handleKeyPressed(e));
        gamePane.setOnKeyReleased(e -> player.handleKeyReleased(e));
        javafx.application.Platform.runLater(() -> gamePane.requestFocus());

        restartButton.setVisible(false);
        restartButton.setOnAction(e -> restartGame());

        score = 0;
        scoreLabel.setText("Счёт: 0");

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now);
            }
        };
        timer.start();
    }

    private void update(long now) {
        if (!gameRunning) return;

        if (now - lastMeteorTime > 1_000_000_000) {
            Meteor m = new Meteor(Math.random() * 580, -50);
            meteors.add(m);
            gamePane.getChildren().add(m.getShape());
            lastMeteorTime = now;
        }

        Iterator<Meteor> iter = meteors.iterator();
        while (iter.hasNext()) {
            Meteor m = iter.next();
            m.update();
            if (m.isOutOfScreen()) {
                gamePane.getChildren().remove(m.getShape());
                iter.remove();
                score++;
                scoreLabel.setText("Счёт: " + score);
            } else if (m.getShape().getBoundsInParent().intersects(player.getShape().getBoundsInParent())) {
                gameRunning = false;
                restartButton.setVisible(true);
            }
        }

        player.update();
    }

    private void restartGame() {
        for (Meteor m : meteors) {
            gamePane.getChildren().remove(m.getShape());
        }
        meteors.clear();

        player.getShape().setTranslateX(280);
        player.getShape().setTranslateY(720);

        score = 0;
        scoreLabel.setText("Счёт: 0");

        restartButton.setVisible(false);
        gameRunning = true;

        gamePane.requestFocus();
    }
}

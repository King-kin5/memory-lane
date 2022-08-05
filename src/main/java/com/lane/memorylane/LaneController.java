package com.lane.memorylane;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.net.URL;
import java.util.*;

public class LaneController implements Initializable {
    ArrayList<String> possibleButtons = new ArrayList<>(Arrays.asList("button0", "button1", "button2", "button3", "button4",
            "button5", "button6", "button7", "button8"));

    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<String> pattern = new ArrayList<>();
    int patternOrder = 0;

    Random random = new Random();
    int counter = 0;
    int turn = 1;

@FXML
private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;

    @FXML
    private Button Start;

    @FXML
    private Text text;

    @FXML
    void buttonClicked(ActionEvent event) {

        if (((Control) event.getSource()).getId().equals(pattern.get(counter))) {
            ;
            text.setText("correct" + counter);
            Button button = buttons.get(getIndexofButton(event));
            changeButtonColor(button, "-fx-base:lightgreen");
            counter++;
        } else {
            Button button = buttons.get(getIndexofButton(event));
            changeButtonColor(button, "-fx-base:red");
            text.setText("Wrong");
            return;
        }
        if (counter == turn) {
            nextTurn();

        }


    }

    private void nextTurn() {
        counter = 0;
        turn++;
        pattern.add(possibleButtons.get(random.nextInt(possibleButtons.size())));
        showPattern();
        System.out.println(pattern);
    }

    private int getIndexofButton(ActionEvent event) {
        String buttonId = ((Control) event.getSource()).getId();
        return Integer.parseInt(buttonId.substring(buttonId.length() - 1));
    }

    private int getIndexofButton(String button) {
        return Integer.parseInt(button.substring(button.length() - 1));
    }


    @FXML
    void onStart(ActionEvent event) {
        pattern.clear();

        pattern.add(possibleButtons.get(random.nextInt(possibleButtons.size())));
        showPattern();
        System.out.println(pattern);

        counter = 0;
        turn = 1;
    }

    private void showPattern() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event1 -> {
                showNext();
            }));
            timeline.setCycleCount(pattern.size());
            timeline.play();
        });
        pause.play();

    }

    private void showNext() {
        Button button = buttons.get(getIndexofButton(pattern.get(patternOrder)));
        changeButtonColor(button, "-fx-base:grey");
        patternOrder++;

        if (patternOrder == turn) {
            patternOrder = 0;
        }
    }

    private void changeButtonColor(Button button, String color) {
        button.setStyle(color);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> {
            button.setStyle(null);
        });
        pause.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons.addAll(Arrays.asList(button0,button1,button2,button3,button4,button5,button6,button7,button8));
    }
}

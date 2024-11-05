package com.example.clicker.service;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;

public class ClickerController {

    @Getter
    private static ClickerController instance;

    {
        instance = this;
    }

    private final ClickerAdapter clickerAdapter = ClickerAdapter.getClickerAdapter();

    @FXML
    @Getter
    private Label errorDelayText;

    @FXML
    @Getter
    private Label errorIntervalClickText;


    @FXML
    @Getter
    private Label errorSetWidthText;

    @FXML
    @Getter
    private Label errorSetHeightText;

    @FXML
    @Getter
    private Label startedOrStopped;

    @FXML
    @Getter
    private TextField delayTextField;

    @FXML
    @Getter
    private TextField intervalClickTextField;

    @FXML
    @Getter
    private TextField setWidthTextField;

    @FXML
    @Getter
    private TextField setHeightTextField;

    @FXML
    @Getter
    private Label errorCountClickText;

    @FXML
    @Getter
    private TextField countClickField;

    @FXML
    protected void onStartButtonClick() {
        clickerAdapter.doStartOrStop();
    }


}

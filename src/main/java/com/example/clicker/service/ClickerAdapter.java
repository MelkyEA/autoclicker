package com.example.clicker.service;

import lombok.Getter;
import lombok.Setter;

import static com.example.clicker.service.utils.Constants.CLICKER_OFF;
import static com.example.clicker.service.utils.Constants.ERROR_VALID_FIELDS;


public class ClickerAdapter {


    private final ClickerStart clickerStart = ClickerStart.getClickerStart();



    @Getter
    private static ClickerAdapter clickerAdapter = new ClickerAdapter();


    public void doStartOrStop(){
        String text = ClickerController.getInstance().getStartedOrStopped().getText();
        if(text == null || text.isEmpty() || text.equalsIgnoreCase(CLICKER_OFF) || text.equalsIgnoreCase(ERROR_VALID_FIELDS)){
            clickerStart.start();
        } else {
            clickerStart.stop();
        }
    }
}

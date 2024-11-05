package com.example.clicker.service;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.HashSet;
import java.util.Set;


public class KeyListener implements NativeKeyListener {

    private final Set<Integer> pressedKeys = new HashSet<>();


    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        if (checkPressedCtrlAltX()) {
            ClickerAdapter.getClickerAdapter().doStartOrStop();
            pressedKeys.clear();
        }

        ClickerStart clickerStart = ClickerStart.getClickerStart();

        if(clickerStart.isPointOnScreen()){
            if(checkPressedCtrlAltS()){
                clickerStart.setXAndYOnScreen(MouseListener.getMouseX(), MouseListener.getMouseY());
                pressedKeys.clear();
            }
        }

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    private boolean checkPressedCtrlAltX() {
        return pressedKeys.contains(NativeKeyEvent.VC_CONTROL) && pressedKeys.contains(NativeKeyEvent.VC_ALT) && pressedKeys.contains(NativeKeyEvent.VC_X);
    }

    private boolean checkPressedCtrlAltS() {
        return pressedKeys.contains(NativeKeyEvent.VC_CONTROL) && pressedKeys.contains(NativeKeyEvent.VC_ALT) && pressedKeys.contains(NativeKeyEvent.VC_S);
    }

}

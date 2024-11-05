package com.example.clicker.service;


import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;
import lombok.Getter;

public class MouseListener implements NativeMouseListener, NativeMouseMotionListener {

    @Getter
    private static int mouseX, mouseY;


    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
    }
}

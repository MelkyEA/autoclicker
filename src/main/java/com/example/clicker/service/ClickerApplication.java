package com.example.clicker.service;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

public class ClickerApplication extends Application {

    @Getter
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClickerApplication.class.getResource("clicker-view.fxml"));

        primaryStage = stage;

        Scene scene = new Scene(fxmlLoader.load(), 550, 320);
        stage.setTitle("Clicker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException("Unable to register native hook.", e);
        }
        GlobalScreen.addNativeKeyListener(new KeyListener());
        MouseListener mouseListener = new MouseListener();
        GlobalScreen.addNativeMouseListener(mouseListener);
        GlobalScreen.addNativeMouseMotionListener(mouseListener);

        launch();
    }
}
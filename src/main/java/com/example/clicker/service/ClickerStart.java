package com.example.clicker.service;

import javafx.application.Platform;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Paint;
import javafx.scene.robot.Robot;
import lombok.Data;
import lombok.Getter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Duration;
import java.util.concurrent.ScheduledFuture;

import static com.example.clicker.service.utils.Constants.*;

@Data
public class ClickerStart {

    @Getter
    private static ClickerStart clickerStart = new ClickerStart();

    private Long delay;
    private Long intervalClick;
    private Long x;
    private Long y;
    private Long countClick;

    private boolean isStarted = false;
    private ScheduledFuture<?> scheduledFuture;
    @Getter
    private boolean isPointOnScreen = false;

    private ThreadPoolTaskScheduler taskScheduler;

    {
        taskScheduler
                = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("Clicker");
        taskScheduler.initialize();
    }

    public void start() {
        if (!isStarted) {

            boolean isError = false;

            ClickerController clickerController = ClickerController.getInstance();

            String errorDelayText, errorIntervalClickText, errorSetWidthText, errorSetHeightText, errorCountClickText;

            String delayString = clickerController.getDelayTextField().getText();
            if (delayString == null || (delayString.isEmpty() || delayString.equals("0"))) {
                errorDelayText = ERROR_FIELD_TEXT;
                isError = true;
            } else {
                errorDelayText = null;
                setDelay(Long.valueOf(delayString));
            }

            String intervalClickString = clickerController.getIntervalClickTextField().getText();
            if (intervalClickString == null || (intervalClickString.isEmpty() || intervalClickString.equals("0"))) {
                errorIntervalClickText = ERROR_FIELD_TEXT;
                isError = true;
            } else {
                errorIntervalClickText = null;
                setIntervalClick(Long.valueOf(intervalClickString));
            }

            String widthString = clickerController.getSetXTextField().getText();
            if (widthString == null || widthString.isEmpty()) {
                errorSetWidthText = ERROR_PXLS_TEXT;
                isError = true;
            } else {
                errorSetWidthText = null;
                clickerStart.setX(Long.valueOf(widthString));
            }

            String heightString = clickerController.getSetYTextField().getText();
            if (heightString == null || heightString.isEmpty()) {
                errorSetHeightText = ERROR_PXLS_TEXT;
                isError = true;
            } else {
                errorSetHeightText = null;
                clickerStart.setY(Long.valueOf(heightString));
            }

            String countClickString = clickerController.getCountClickField().getText();
            if (countClickString == null || (countClickString.isEmpty() || countClickString.equals("0"))) {
                errorCountClickText = ERROR_FIELD_TEXT;
                isError = true;
            } else {
                errorCountClickText = null;
                clickerStart.setCountClick(Long.valueOf(countClickString));
            }

            if (!isError) {
                isStarted = true;
                Platform.runLater(() -> {
                            clickerController.getStartedOrStopped().setText(CLICKER_ON);
                            ClickerController.getInstance().getStartedOrStopped().setTextFill(Paint.valueOf("GREEN"));
                            clickerController.getErrorDelayText().setText("");
                            clickerController.getErrorIntervalClickText().setText("");
                            clickerController.getErrorSetWidthText().setText("");
                            clickerController.getErrorSetHeightText().setText("");
                            clickerController.getErrorCountClickText().setText("");
                        }
                );
                scheduledFuture = taskScheduler.scheduleWithFixedDelay(this::click, Duration.ofSeconds(delay));
            } else {
                Platform.runLater(() -> {
                            if (errorDelayText != null && !errorDelayText.isBlank()) {
                                clickerController.getErrorDelayText().setText(errorDelayText);
                            }

                            if (errorIntervalClickText != null && !errorIntervalClickText.isBlank()) {
                                clickerController.getErrorIntervalClickText().setText(errorIntervalClickText);
                            }

                            if (errorSetWidthText != null && !errorSetWidthText.isBlank()) {
                                clickerController.getErrorSetWidthText().setText(errorSetWidthText);
                            }

                            if (errorSetHeightText != null && !errorSetHeightText.isBlank()) {
                                clickerController.getErrorSetHeightText().setText(errorSetHeightText);
                            }

                            if (errorCountClickText != null && !errorCountClickText.isBlank()) {
                                clickerController.getErrorCountClickText().setText(errorCountClickText);
                            }

                            clickerController.getStartedOrStopped().setText(ERROR_VALID_FIELDS);
                            ClickerController.getInstance().getStartedOrStopped().setTextFill(Paint.valueOf("RED"));
                        }
                );
            }
        }
    }

    public void stop() {
        scheduledFuture.cancel(false);
        Platform.runLater(() -> {
                    ClickerController.getInstance().getStartedOrStopped().setText(CLICKER_OFF);
                    ClickerController.getInstance().getStartedOrStopped().setTextFill(Paint.valueOf("RED"));
                }
        );
        isStarted = !isStarted;
    }

    public void setChoosePointOnScreen() {
        isPointOnScreen = true;
    }

    public void setXAndYOnScreen(long x, long y) {
        this.x = x;
        this.y = y;
        Platform.runLater(() -> {
                    ClickerController.getInstance().getSetXTextField().setText(String.valueOf(x));
                    ClickerController.getInstance().getSetYTextField().setText(String.valueOf(y));
                }
        );

        Platform.runLater(() -> {
                    ClickerApplication.getPrimaryStage().setIconified(false);
                }
        );

        isPointOnScreen = false;
    }

    public void click() {
        Platform.runLater(() -> {
            Robot rb = new Robot();
            for (int i = 0; i < countClick; i++) {
                rb.mouseMove(x, y);
                rb.mousePress(MouseButton.PRIMARY);
                rb.mouseRelease(MouseButton.PRIMARY);
                try {
                    Thread.sleep(intervalClick);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

module com.example.clicker {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires spring.context;
    requires spring.core;
    requires spring.boot;
    requires spring.aop;
    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter;
    requires spring.boot.starter.logging;
    requires spring.expression;
    requires spring.jcl;
    requires com.github.kwhat.jnativehook;

    exports com.example.clicker.service;
    opens com.example.clicker.service to javafx.fxml;
}
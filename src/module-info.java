module FrontEnd {
    requires java.desktop;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;
    requires org.junit.jupiter.api;

    exports view;
    exports model.grid;
    exports model.cell;
    exports controller;
    exports Enums;
    exports Resources;
}

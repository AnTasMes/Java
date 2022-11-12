module antas.tech {
    requires javafx.controls;
    requires javafx.fxml;

    opens antas.tech to javafx.fxml;
    exports antas.tech;
}

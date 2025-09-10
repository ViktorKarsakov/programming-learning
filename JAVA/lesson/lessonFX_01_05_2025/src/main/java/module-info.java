module org.example.lessonfx_01_05_2025 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.lessonfx_01_05_2025 to javafx.fxml;
    exports org.example.lessonfx_01_05_2025;
}
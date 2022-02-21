module com.example.practicapokemonexamen {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.practicapokemonexamen to javafx.fxml;
    exports com.example.practicapokemonexamen;
}
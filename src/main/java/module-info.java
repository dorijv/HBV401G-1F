module hi.verkefni {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.junit.jupiter.api;

    opens hi.verkefni.vidmot to javafx.fxml;
    exports hi.verkefni.vidmot;
}
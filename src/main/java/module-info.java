module hi.verkefni {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    opens hi.verkefni.vidmot to javafx.fxml;
    exports hi.verkefni.vidmot;
    exports hi.verkefni.test;
}
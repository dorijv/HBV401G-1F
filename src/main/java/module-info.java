module hi.verkefni {
    requires javafx.controls;
    requires javafx.fxml;


    opens hi.verkefni.vidmot to javafx.fxml;
    exports hi.verkefni.vidmot;
}
package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Flight;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class HelloController {
    public TextField fxDep;
    public TextField fxDest;
    private FlightController ft = new FlightController();
    @FXML
    private Label welcomeText;

    // API : XXX F í lokin

    @FXML
    protected void onHelloButtonClick() throws Exception {
        welcomeText.setText("Welcome to JavaFX Application!");
        //ft.getFlight(fxDep.getText(), fxDest.getText());
        ArrayList<Flight> temp = ft.getFlight("Reykjavík", "Vestmannaeyjar");
        Flight tempp = temp.get(1);
        System.out.println(ft.createBooking("2911963149", tempp.getFlightID(), tempp.getDepartureTime(),
                "Vilhjálmsson", "Halldór"));
    }
}
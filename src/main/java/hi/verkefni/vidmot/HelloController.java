package hi.verkefni.vidmot;

import hi.verkefni.vinnsla.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TextField fxDep;
    public TextField fxDest;
    public ListView fxDisplayFlights;
    public Button fxFlightButton;
    public Text fxDispPrice;
    public Slider fxPriceSlider;
    public TextField fxSeats;
    private FlightController ft = new FlightController();
    @FXML
    private Label welcomeText;

    // Aukavinnsla
    public ObservableList<Flight> currentItemSelected;
    private BookingController bookingController;

    // API : XXX F í lokin

    @FXML
    protected void onHelloButtonClick() throws Exception {
        welcomeText.setText("Welcome to JavaFX Application!");
        ft.getFlight(fxDep.getText(), fxDest.getText());
        ArrayList<Flight> temp = ft.getFlight("Reykjavík", "Vestmannaeyjar");
        Flight tempp = temp.get(1);
        System.out.println(ft.createBooking("2911963148", tempp.getFlightID(), tempp.getDepartureTime(),
                "Vilhjálmsson", "Halldór"));
        ft.findBooking("2911963149");
    }

    public void findFlightsHandler(ActionEvent actionEvent) throws Exception {
        ArrayList<Flight> gogn;
        if (fxPriceSlider.getValue() == 0 && fxSeats.getText() == "1") {
            gogn = ft.getFlight(fxDep.getText(), fxDest.getText());
        } else {
            gogn = ft.getFlight(fxDep.getText(), fxDest.getText(), (float)fxPriceSlider.getValue(),Integer.parseInt(fxSeats.getText()));
        }
        if (fxDep.getText() == "" && fxDest.getText() == "") gogn = ft.getAllFlights();
        ObservableList<Flight> gogn2 = FXCollections.observableList(gogn);
        fxDisplayFlights.setItems(gogn2);
    }

    public void breytaValHandler(MouseEvent mouseEvent) {
        fxDispPrice.setText((int) fxPriceSlider.getValue() + " EUR");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            bookingController = skodaDialog();
        } catch(IOException e) {
            e.printStackTrace();
        }

        fxDisplayFlights.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if(click.getClickCount() == 2) {
                    currentItemSelected = fxDisplayFlights.getSelectionModel().getSelectedItems();
                    Flight flightid = currentItemSelected.get(0);
                    bookingController.fxDep.setText(flightid.getDepartureLoc());
                    bookingController.fxFlightID.setText(flightid.getFlightID());
                    bookingController.fxDest.setText(flightid.getDestination());
                    bookingController.fxPrice.setText((int)flightid.getPrice()+" EUR");
                    bookingController.fxTime.setText(flightid.getDepartureTime() + "");
                    bookingController.birtaGlugga();
                }
            }
        });
    }

    private BookingController skodaDialog() throws java.io.IOException {
        FXMLLoader fLoader = new FXMLLoader(getClass().getResource("bookingView.fxml"));
        fLoader.load();
        return fLoader.getController();
    }
}
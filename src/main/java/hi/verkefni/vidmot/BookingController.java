package hi.verkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    private FlightController ft = new FlightController();
    public TextField fxFlightID;
    public TextField fxDep;
    public TextField fxDest;
    public TextField fxPrice;
    public TextField fxTime;
    public TextField fxSSN;
    public TextField fxLast;
    public TextField fxFirst;
    public Text fxConfNR;
    public AnchorPane bookPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxTime.setDisable(true);
        fxPrice.setDisable(true);
        fxFlightID.setDisable(true);
        fxDest.setDisable(true);
        fxDep.setDisable(true);
    }

    public void birtaGlugga(){
        DialogPane p = new DialogPane();
        bookPane.setVisible(true);
        p.setContent(bookPane);
        Dialog<ButtonType> d = new Dialog<>();
        d.setDialogPane(p);
        d.setResultConverter(b -> {
            //if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            //    return b;
            //} else {
                return null;
            //}
        });
        ButtonType iLagi = hnapparILagiHaettaVid(d);
        Optional<ButtonType> result = d.showAndWait();
        if (result.isPresent()) {
            d.close();
        }
    }

    private ButtonType hnapparILagiHaettaVid(Dialog<ButtonType> d) {
        ButtonType iLagi = new ButtonType("oki",
                ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(iLagi);
        return iLagi;
    }

    public void createBookingHandler(ActionEvent actionEvent) throws Exception {
        String bkr = ft.createBooking(fxSSN.getText(), fxFlightID.getText(), LocalDateTime.parse(fxTime.getText()),
                fxLast.getText(), fxFirst.getText());
        fxConfNR.setText("Your confirmation number is: " + bkr);
        Button but = (Button) actionEvent.getSource();
        but.setDisable(true);
    }
}

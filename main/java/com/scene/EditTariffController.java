package com.scene;

import java.io.IOException;
import com.command.CommandResult;
import com.tariff.Tariff;
import com.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditTariffController {
    private User admin;
    private Tariff tariff;


    @FXML
    private TextField  editFieldMB;

    @FXML
    private TextField  editFieldMinutesByOperator;

    @FXML
    private TextField  editFieldMinutesOtherOperators;

    @FXML
    private TextField  editFieldName;

    @FXML
    private TextField  editFieldPrice;

    @FXML
    private TextField  editFieldPriceExtra100MB;

    @FXML
    private TextField  editFieldPriceExtraMinute;

    @FXML
    private TextField  editFieldPriceExtraSMS;

    @FXML
    private TextField  editFieldSMS;

    @FXML
    private TextField  editFieldSpecialCondition;

    @FXML
    private Label succeedLabel;

    @FXML
    private Label errorLabel;

    @FXML
    void editTariff(ActionEvent event) throws IOException {
        CommandResult<String> result;
        result = admin.pressButton(6, new String[]{editFieldName.getText(), editFieldPrice.getText(), editFieldMinutesByOperator.getText(),
                editFieldMinutesOtherOperators.getText(), editFieldSMS.getText(), editFieldMB.getText(), editFieldPriceExtraMinute.getText(),
                editFieldPriceExtraSMS.getText(), editFieldPriceExtra100MB.getText(), editFieldSpecialCondition.getText(), tariff.getID() } );

        if (!result.isSucceed()){
            succeedLabel.setText("");
            errorLabel.setText(result.getResult());
        }
        else {
            errorLabel.setText("");
            succeedLabel.setText("The tariff has been edited successfully");
        }

    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("menu-edit-tariff.fxml"));
        Parent root = loader.load();
        MenuEditTariffController controller = loader.getController();
        controller.initialize(admin);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize(User user, Tariff tariff) {
        this.admin = user;
        this.tariff = tariff;
        editFieldName.setText(tariff.getName());
        editFieldMB.setText(tariff.getMobileData());
        editFieldSMS.setText(tariff.getSMS());
        editFieldPriceExtra100MB.setText(tariff.getPriceAdditionalMB());
        editFieldMinutesOtherOperators.setText(tariff.getMinunesOtherOperators());
        editFieldSpecialCondition.setText(tariff.getSpecialCondition());
        editFieldPriceExtraSMS.setText(tariff.getPriceAdditionalSMS());
        editFieldPriceExtraMinute.setText(tariff.getPriceAdditionalMinute());
        editFieldPrice.setText(tariff.getSubscriptionFee());
        editFieldMinutesByOperator.setText(tariff.getMinutesByOperator());

    }

}

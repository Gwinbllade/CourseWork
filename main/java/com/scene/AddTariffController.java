package com.scene;

import java.io.IOException;
import com.command.CommandResult;
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

public class AddTariffController {
    User user;

    @FXML
    private TextField addFieldMB;

    @FXML
    private TextField addFieldMinutesByOperator;

    @FXML
    private TextField addFieldMinutesOtherOperators;

    @FXML
    private TextField addFieldName;

    @FXML
    private TextField addFieldPrice;

    @FXML
    private TextField addFieldPriceExtra100MB;

    @FXML
    private TextField addFieldPriceExtraMinute;

    @FXML
    private TextField addFieldPriceExtraSMS;

    @FXML
    private TextField addFieldSMS;

    @FXML
    private TextField addFieldSpecialCondition;

    @FXML
    private Label succeedLabel;



    @FXML
    private Label errorLabel;

    @FXML
    void addTariff(ActionEvent event) throws IOException {
        CommandResult<String> result = user.pressButton(5,new String[]{addFieldName.getText(),addFieldPrice.getText(), addFieldMinutesByOperator.getText(),
        addFieldMinutesOtherOperators.getText(),addFieldSMS.getText(),addFieldMB.getText(),addFieldPriceExtraMinute.getText(),
        addFieldPriceExtraSMS.getText(), addFieldPriceExtra100MB.getText(), addFieldSpecialCondition.getText()});
        if (!result.isSucceed()){
            succeedLabel.setText("");
            errorLabel.setText(result.getResult());
        }
        else {
            errorLabel.setText("");
            succeedLabel.setText("Tariff successfully added");
        }
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("menu-edit-tariff.fxml"));
        Parent root = loader.load();
        MenuEditTariffController controller = loader.getController();
        controller.initialize(user);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize(User user) {
        this.user = user;
    }

}

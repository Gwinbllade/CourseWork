package com.scene;


import com.user.Subscriber;
import com.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;



public class SubscriberMenu {
    Subscriber subscriber ;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label NameLabel;

    @FXML
    private Label SurnameLabel;

    @FXML
    private Label TariffIDLabel;

    @FXML
    private Label patronymicLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    void chooseTariff(ActionEvent event) throws IOException {

        FXMLLoader loader = new  FXMLLoader(getClass().getResource("choose-tariff.fxml"));
        root = loader.load();
        ChooseTariffController controller = loader.getController();
        controller.initialize(subscriber);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        subscriber.pressButton(0, new String[]{subscriber.getLoginUser()});
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("log-in.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void initialize(User user) {
        subscriber = (Subscriber) user;
        NameLabel.setText(subscriber.getName());
        SurnameLabel.setText(subscriber.getSurname());
        patronymicLabel.setText(subscriber.getPatronymic());
        phoneNumberLabel.setText(subscriber.getPhone());
        TariffIDLabel.setText(subscriber.getTariffName());
    }
}

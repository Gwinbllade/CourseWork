package com.scene;

import com.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminMenu {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User admin;

    @FXML
    void displaySubscribers(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("list-subscribers.fxml"));
        root = loader.load();
        SubscribersListController controller = loader.getController();
        controller.initialize(admin);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void editTariff(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-edit-tariff.fxml"));
        root = loader.load();
        MenuEditTariffController controller = loader.getController();
        controller.initialize(admin);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        admin.pressButton(0,new String[] {admin.getLoginUser()});
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("log-in.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize(User user) {
        this.admin = user;

    }
}

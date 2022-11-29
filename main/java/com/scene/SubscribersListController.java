package com.scene;

import java.io.IOException;
import com.user.Subscriber;
import com.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SubscribersListController {
    User user;
    private final ObservableList<Subscriber> list = FXCollections.observableArrayList();


    @FXML
    private TableColumn<Subscriber, String> clientName;

    @FXML
    private TableColumn<Subscriber, String> tariffName;

    @FXML
    private TableColumn<Subscriber, String> tariffID;

    @FXML
    private TableColumn<Subscriber, String> clientPatronymic;

    @FXML
    private TableColumn<Subscriber, String> clientPhone;

    @FXML
    private TableColumn<Subscriber, String> clientSurname;

    @FXML
    private Label countSubscribersLabel;


    @FXML
    private TableView<Subscriber> subscriberTable;



    @FXML
    private TableColumn<Subscriber, String> userID;

    @FXML
    void initialize(User user) {
        this.user = user;

        for (Object subs : user.displayInfo(3,null)) {
            this.list.add( (Subscriber) subs);
        }

        userID.setCellValueFactory(new PropertyValueFactory<Subscriber,String>("userID"));
        tariffName.setCellValueFactory(new PropertyValueFactory<Subscriber,String>("tariffName"));
        clientSurname.setCellValueFactory(new PropertyValueFactory<Subscriber,String>("surname"));
        clientPhone.setCellValueFactory(new PropertyValueFactory<Subscriber,String>("phone"));
        clientPatronymic.setCellValueFactory(new PropertyValueFactory<Subscriber,String>("patronymic"));
        clientName.setCellValueFactory(new PropertyValueFactory<Subscriber,String>("name"));
        tariffID.setCellValueFactory(new PropertyValueFactory<Subscriber,String>("tariffID"));
        subscriberTable.setItems(list);

        countSubscribersLabel.setText("Count subscribers: " + list.size());


    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("admin-menu.fxml"));
        Parent root = loader.load();
        AdminMenu controller = loader.getController();
        controller.initialize(this.user);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();
    }

}

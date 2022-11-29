package com.scene;

import java.io.IOException;

import com.tariff.Tariff;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MenuEditTariffController {

    User user;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ObservableList<Tariff> list = FXCollections.observableArrayList();


    @FXML
    private TextField minMinutes;

    @FXML
    private TextField minMinutesOtherOperator;

    @FXML
    private TextField minMobileDate;

    @FXML
    private TextField minNumberSMS;

    @FXML
    private TextField fieldMaxSF;

    @FXML
    private TextField fieldMinSF;

    @FXML
    private Label yourTariffLabel;

    //-----------------------------------------------------Table------------------------------------------------
    @FXML
    private TableColumn<Tariff, String> callMinOtherOperator;

    @FXML
    private TableColumn<Tariff, String> callMinutes;

    @FXML
    private TableColumn<Tariff, String> mb;

    @FXML
    private TableColumn<Tariff, String> nameTariff;

    @FXML
    private TableColumn<Tariff, String> priceEx100MB;

    @FXML
    private TableColumn<Tariff, String> priceExMin;

    @FXML
    private TableColumn<Tariff, String> priceExSMS;

    @FXML
    private TableColumn<Tariff, String> priceTariff;

    @FXML
    private TableColumn<Tariff, String> sms;

    @FXML
    private TableColumn<Tariff, String> specialCondition;

    @FXML
    private TableView<Tariff> tariffTable;
//---------------------------------------------------------------------------------------------------------

    @FXML
    void exit(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("admin-menu.fxml"));
        root = loader.load();
        AdminMenu controller = loader.getController();
        controller.initialize(user);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showTairffs(ActionEvent event) {
        list = FXCollections.observableArrayList();
        errorLabel.setText("");
        list = FXCollections.observableArrayList();
        try {
            for (Object tariff : this.user.displayInfo(1, new String[]{fieldMinSF.getText(), fieldMaxSF.getText(),
                    minNumberSMS.getText(), minMinutes.getText(), minMinutesOtherOperator.getText(), minMobileDate.getText()})) {
                list.add((Tariff) tariff);
            }

            nameTariff.setCellValueFactory(new PropertyValueFactory<Tariff, String>("name"));
            priceTariff.setCellValueFactory(new PropertyValueFactory<Tariff, String>("subscriptionFee"));
            callMinutes.setCellValueFactory(new PropertyValueFactory<Tariff, String>("minutesByOperator"));
            callMinOtherOperator.setCellValueFactory(new PropertyValueFactory<Tariff, String>("minunesOtherOperators"));
            sms.setCellValueFactory(new PropertyValueFactory<Tariff, String>("SMS"));
            mb.setCellValueFactory(new PropertyValueFactory<Tariff, String>("mobileData"));
            priceExMin.setCellValueFactory(new PropertyValueFactory<Tariff, String>("priceAdditionalMinute"));
            priceExSMS.setCellValueFactory(new PropertyValueFactory<Tariff, String>("priceAdditionalSMS"));
            priceEx100MB.setCellValueFactory(new PropertyValueFactory<Tariff, String>("priceAdditionalMB"));
            specialCondition.setCellValueFactory(new PropertyValueFactory<Tariff, String>("specialCondition"));
            tariffTable.setItems(list);

            if(list.size() ==0){
                errorLabel.setText("Sorry!!! There are no tariffs for such parameters");
            }
        }   catch (Exception e){
            errorLabel.setText("Error the search parameters are entered incorrectly");
        }
    }


    @FXML
    private Label errorLabel;

    @FXML
    void addTariff(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("add-tariff.fxml"));
        root = loader.load();
        AddTariffController controller = loader.getController();
        controller.initialize(user);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void deleteTariff(ActionEvent event) {

        try {
            Tariff selectTariff = (Tariff) tariffTable.getSelectionModel().getSelectedItem();
            user.pressButton(4, new String[]{user.getLoginUser(), selectTariff.getID()});
        } catch (Exception e){
            errorLabel.setText("Error, please select a tariff from the table");
        }

        this.showListTariff();

    }

    @FXML
    void editTariff(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("edit-tariff.fxml"));
            root = loader.load();
            EditTariffController controller = loader.getController();
            Tariff selectTariff = (Tariff) tariffTable.getSelectionModel().getSelectedItem();
            controller.initialize(user,selectTariff);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.setTitle("Mobile company");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
        errorLabel.setText("Error, please select a tariff from the table");
        }


    }

    private void showListTariff(){
        list = FXCollections.observableArrayList();
        for (Object tariff : this.user.displayInfo(1,new String[]{"","","","","",""} )) {
            list.add((Tariff) tariff);
        }

        if (list.size() == 0){
            errorLabel.setText("There is no tariff for such parameters");
        }

        nameTariff.setCellValueFactory(new PropertyValueFactory<Tariff,String>("name") );
        priceTariff.setCellValueFactory(new PropertyValueFactory<Tariff,String>("subscriptionFee") );
        callMinutes.setCellValueFactory(new PropertyValueFactory<Tariff,String>("minutesByOperator") );
        callMinOtherOperator.setCellValueFactory(new PropertyValueFactory<Tariff,String>("minunesOtherOperators") );
        sms.setCellValueFactory(new PropertyValueFactory<Tariff,String>("SMS") );
        mb.setCellValueFactory(new PropertyValueFactory<Tariff,String>("mobileData") );
        priceExMin.setCellValueFactory(new PropertyValueFactory<Tariff,String>("priceAdditionalMinute") );
        priceExSMS.setCellValueFactory(new PropertyValueFactory<Tariff,String>("priceAdditionalSMS") );
        priceEx100MB.setCellValueFactory(new PropertyValueFactory<Tariff,String>("priceAdditionalMB") );
        specialCondition.setCellValueFactory(new PropertyValueFactory<Tariff,String>("specialCondition") );
        tariffTable.setItems(list);

    }


    @FXML
    void initialize(User user) {
        errorLabel.setText("");

        this.user = user;

        this.showListTariff();
    }




}


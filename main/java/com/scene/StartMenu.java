package com.scene;

import java.io.IOException;
import java.util.Objects;

import com.command.*;
import com.database.DataBase;
import com.user.Subscriber;
import com.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartMenu {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final DataBase database = new DataBase();
    User user = new User();
    public StartMenu(){
        user.setCommands(0, new LogOut(database));
        user.setCommands(1, new Register(database));
        user.setCommands(2,new LogIn(database));
        user.setCommands(3,new ConnectionToBD(database));
        user.pressButton(3, new String[]{"jdbc:sqlserver://localhost:1433;instance=DESKTOP-SN8BAOR;databaseName=MobileСompany;encrypt=true;trustServerCertificate=true",
                "sa","12345678"});
    }

    @FXML
    private Label errorLabel;

    @FXML
    private TextField loginFiled;

    @FXML
    private PasswordField passwordField;


    @FXML
    void exit(ActionEvent event) {
        user.pressButton(0,new String[] {user.getLoginUser()});
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    void LogIn(ActionEvent event) throws IOException {
        CommandResult<String> result = user.pressButton(2,new String[] {loginFiled.getText(), passwordField.getText()} );
        if (!result.isSucceed()){
            errorLabel.setText(result.getResult());
        }
        else{
            if (Objects.equals(result.getUserLogin(), "admin")) {
                User admin = new User();
                admin.setLoginUser(result.getUserLogin());
                admin.setCommands(0, new LogOut(database));
                admin.setDisplayCommand(1,new DisplayTariff(database));
                admin.setCommands(2, new ConnectionToBD(database));
                admin.setDisplayCommand(3,new DisplaySubscribers(database));
                admin.setCommands(4,new DeleteTariff(database));
                admin.setCommands(5,new AddTariff(database));
                admin.setCommands(6, new EditTariff(database));

                FXMLLoader loader = new  FXMLLoader(getClass().getResource("admin-menu.fxml"));
                root = loader.load();
                AdminMenu controller = loader.getController();
                controller.initialize(admin);

            }
            else {
                Subscriber subscriber = new Subscriber();
                subscriber.setLoginUser(result.getUserLogin());
                subscriber.setCommands(0, new LogOut(database));
                subscriber.setDisplayCommand(1,new DisplayTariff(database));
                subscriber.setCommands(2, new ChooseTariff(database));


                subscriber.setInformation(database.userInformation(subscriber.getLoginUser()));

                FXMLLoader loader = new  FXMLLoader(getClass().getResource("subscriber-menu.fxml"));
                root = loader.load();
                SubscriberMenu controller = loader.getController();
                controller.initialize(subscriber);

            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Mobile company");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("register.fxml"));
        root = loader.load();
        RegisterController controller = loader.getController();

        controller.setUser(user);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();
    }


}

package com.scene;

import com.command.CommandResult;
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

import java.io.IOException;
import java.util.Objects;

public class RegisterController {
    User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Label errorLabelRegister;

    @FXML
    private TextField loginFiledPatronymic;

    @FXML
    private TextField loginFiledName;

    @FXML
    private TextField loginFiledPhone;

    @FXML
    private TextField loginFiledSurname;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label succeedLabel;


    @FXML
    void createAccount(ActionEvent event) throws IOException {
        CommandResult<String> result = user.pressButton(1, new String[] {loginFiledName.getText(), loginFiledSurname.getText(),loginFiledPatronymic.getText(),
                loginFiledPhone.getText(),passwordField.getText()});
        if (!result.isSucceed()){
            succeedLabel.setText("");
            errorLabelRegister.setText(result.getResult());
        }
        else{
            errorLabelRegister.setText("");
            succeedLabel.setText("Account created");
        }

    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("log-in.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Mobile company");
        stage.setScene(scene);
        stage.show();
    }


}

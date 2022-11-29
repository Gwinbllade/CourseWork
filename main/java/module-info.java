module com.mobile {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.user to javafx.fxml;
    opens com.tariff to javafx.fxml;
    opens com.scene to javafx.fxml;

    exports com.user;
    exports com.tariff;
    exports com.scene;

}
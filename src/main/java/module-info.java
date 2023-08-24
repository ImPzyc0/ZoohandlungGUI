module com.zoohandlung.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.zoohandlung.main to javafx.fxml;
    exports com.zoohandlung.main;
}
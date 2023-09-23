module com.zoohandlung.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.zoohandlung.main to javafx.fxml;
    exports com.zoohandlung.main;
    exports com.zoohandlung;
    opens com.zoohandlung to javafx.fxml;
}
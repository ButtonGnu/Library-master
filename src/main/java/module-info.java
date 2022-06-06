module com.example.lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;


    opens com.example.lab5 to javafx.fxml;
    exports com.example.lab5;
    exports com.example.lab5.users;
    opens com.example.lab5.users to javafx.fxml;
}
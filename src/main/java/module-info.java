module org.example.codelab6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.codelab6 to javafx.fxml;
    exports org.example.codelab6;
}
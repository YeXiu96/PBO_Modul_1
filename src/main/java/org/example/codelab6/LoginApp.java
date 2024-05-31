package org.example.codelab6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #f0f8ff; -fx-border-color: #4682b4; -fx-border-radius: 10; -fx-padding: 20;");


        Label scenetitle = new Label("Halaman Login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setStyle("-fx-text-fill: #4682b4;");
        grid.add(scenetitle, 0, 0, 2, 1);


        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);


        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);


        Button btn = new Button("Sign in");
        btn.setStyle("-fx-background-color: #4682b4; -fx-text-fill: white;");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);


        btn.setOnAction(event -> {
            String username = userTextField.getText();
            String password = pwBox.getText();


            if (username.equals("radit") && password.equals("password")) {
                primaryStage.setScene(new Scene(new SuccessPage(), 300, 200));
            } else {
                Label errorLabel = new Label("Password atau Username Salah");
                errorLabel.setTextFill(Color.RED);
                grid.add(errorLabel, 1, 3);
            }
        });


        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setTitle("Login Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private class SuccessPage extends VBox {
        public SuccessPage() {
            setAlignment(Pos.CENTER);
            setPadding(new Insets(25, 25, 25, 25));
            setSpacing(10);
            setStyle("-fx-background-color: #f0f8ff; -fx-border-color: #4682b4; -fx-border-radius: 10; -fx-padding: 20;");

            Label welcomeLabel = new Label("Halo Radit!");
            welcomeLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            welcomeLabel.setStyle("-fx-text-fill: #4682b4;");
            getChildren().add(welcomeLabel);

            Button backButton = new Button("Kembali");
            backButton.setStyle("-fx-background-color: #4682b4; -fx-text-fill: white;");
            backButton.setOnAction(event -> {
                Stage currentStage = (Stage) backButton.getScene().getWindow();
                currentStage.setScene(new Scene(new LoginApp().createLoginPage(), 400, 300));
            });
            getChildren().add(backButton);
        }
    }

    private GridPane createLoginPage() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #f0f8ff; -fx-border-color: #4682b4; -fx-border-radius: 10; -fx-padding: 20;");


        Label scenetitle = new Label("Halaman Login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setStyle("-fx-text-fill: #4682b4;");
        grid.add(scenetitle, 0, 0, 2, 1);


        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);


        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);


        Button btn = new Button("Sign in");
        btn.setStyle("-fx-background-color: #4682b4; -fx-text-fill: white;");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);


        btn.setOnAction(event -> {
            String username = userTextField.getText();
            String password = pwBox.getText();


            if (username.equals("radit") && password.equals("password")) {
                Stage currentStage = (Stage) btn.getScene().getWindow();
                currentStage.setScene(new Scene(new SuccessPage(), 300, 200));
            } else {
                Label errorLabel = new Label("Password atau Username Salah");
                errorLabel.setTextFill(Color.RED);
                grid.add(errorLabel, 1, 3);
            }
        });

        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

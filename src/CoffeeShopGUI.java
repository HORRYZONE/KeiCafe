import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class CoffeeShopGUI extends Application {
    private Map<String, String> userDatabase = new HashMap<>();
    private Stage primaryStage;
    private GridPane loginGridPane;
    private GridPane signUpGridPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Coffee Shop");

        loginGridPane = createGridPane();
        addLoginUIControls(loginGridPane);

        signUpGridPane = createGridPane();
        addSignUpUIControls(signUpGridPane);

        Scene scene = new Scene(loginGridPane, 400, 250);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        return gridPane;
    }

    private void addLoginUIControls(GridPane gridPane) {
        Label titleLabel = new Label("Coffee Shop - Login");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        gridPane.add(titleLabel, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        gridPane.add(usernameLabel, 0, 1);

        TextField usernameTextField = new TextField();
        gridPane.add(usernameTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        gridPane.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordField, 1, 2);

        Button loginButton = new Button("Login");
        gridPane.add(loginButton, 1, 3);

        loginButton.setOnAction(e -> login(usernameTextField.getText(), passwordField.getText()));

        Label signUpLabel = new Label("Don't have an account?");
        Button signUpLink = new Button("Sign Up");
        HBox signUpHBox = new HBox(10, signUpLabel, signUpLink);
        signUpHBox.setAlignment(Pos.CENTER);
        gridPane.add(signUpHBox, 1, 4);

        signUpLink.setOnAction(e -> primaryStage.setScene(new Scene(signUpGridPane, 400, 250)));
    }

    private void addSignUpUIControls(GridPane gridPane) {
        Label titleLabel = new Label("Coffee Shop - Sign Up");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        gridPane.add(titleLabel, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        gridPane.add(usernameLabel, 0, 1);

        TextField usernameTextField = new TextField();
        gridPane.add(usernameTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        gridPane.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordField, 1, 2);

        Button signUpButton = new Button("Sign Up");
        gridPane.add(signUpButton, 1, 3);

        signUpButton.setOnAction(e -> signUp(usernameTextField.getText(), passwordField.getText()));

        Label loginLabel = new Label("Already have an account?");
        Button loginLink = new Button("Login");
        HBox loginHBox = new HBox(10, loginLabel, loginLink);
        loginHBox.setAlignment(Pos.CENTER);
        gridPane.add(loginHBox, 1, 4);

        loginLink.setOnAction(e -> primaryStage.setScene(new Scene(loginGridPane, 400, 250)));
    }

    private void signUp(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Sign Up Error", "Please enter a username and password.");
            return;
        }

        if (userDatabase.containsKey(username)) {
            showAlert("Sign Up Error", "Username already exists. Please choose a different username.");
            return;
        }

        userDatabase.put(username, password);
        showAlert("Sign Up Successful", "Sign up successful. You can now log in.");
        primaryStage.setScene(new Scene(loginGridPane, 400, 250));
    }

    private void login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Login Error", "Please enter a username and password.");
            return;
        }

        if (userDatabase.containsKey(username)) {
            String storedPassword = userDatabase.get(username);
            if (storedPassword.equals(password)){
                showAlert("Login Successful", "Welcome, " + username + "!");
            } else {
                showAlert("Login Error", "Invalid password. Please try again.");
            }
        } else {
            showAlert("Login Error", "Username not found. Please sign up first.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
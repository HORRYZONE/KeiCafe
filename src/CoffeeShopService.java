import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class CoffeeShopService {
    private Map<String, String> userDatabase = new HashMap<>();
    private Stage primaryStage;
    private Scene signUpScene;
    private Scene loginScene;

    public GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        return gridPane;
    }

    public void addSignUpUIControls(GridPane gridPane) {
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
        Button switchToLoginButton = new Button("Switch to Login");

        HBox buttonHBox = new HBox(10, signUpButton, switchToLoginButton);
        buttonHBox.setAlignment(Pos.CENTER);
        gridPane.add(buttonHBox, 1, 3);

        signUpButton.setOnAction(e -> signUp(usernameTextField.getText(), passwordField.getText()));
        switchToLoginButton.setOnAction(e -> primaryStage.setScene(loginScene));
    }

    public void addLoginUIControls(GridPane gridPane) {
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
        Button switchToSignUpButton = new Button("Switch to Sign Up");

        HBox buttonHBox = new HBox(10, loginButton, switchToSignUpButton);
        buttonHBox.setAlignment(Pos.CENTER);
        gridPane.add(buttonHBox, 1, 3);

        loginButton.setOnAction(e -> login(usernameTextField.getText(), passwordField.getText()));
        switchToSignUpButton.setOnAction(e -> primaryStage.setScene(signUpScene));
    }

    public void setScenes(Scene signUpScene, Scene loginScene) {
        this.signUpScene = signUpScene;
        this.loginScene = loginScene;
    }

    public void signUp(String username, String password) {
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
        primaryStage.setScene(loginScene);
    }

    public void login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Login Error", "Please enter a username and password.");
            return;
        }

        if (userDatabase.containsKey(username)) {
            String storedPassword = userDatabase.get(username);
            if (storedPassword.equals(password)) {
                showAlert("Login Successful", "Welcome, " + username + "!");
            } else {
                showAlert("Login Error", "Invalid password. Please try again.");
            }
        } else {
            showAlert("Login Error", "Username not found. Please sign up first.");
        }
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
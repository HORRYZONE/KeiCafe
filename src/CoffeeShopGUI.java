import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CoffeeShopGUI extends Application {
    private Map<String, String> userDatabase = new HashMap<>();
    private Stage primaryStage;
    private Stack<Pane> pageStack;
    private StackPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Coffee Shop");

        pageStack = new Stack<>();
        root = new StackPane();
        root.setPadding(new Insets(20));

        showLoginScreen();

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    private void pushPage(Pane page) {
        pageStack.push(page);
        root.getChildren().clear();
        root.getChildren().add(page);
    }

    private void popPage() {
        if (!pageStack.isEmpty()) {
            pageStack.pop();
            if (!pageStack.isEmpty()) {
                root.getChildren().clear();
                root.getChildren().add(pageStack.peek());
            }
        }
    }

    private Pane createLoginRoot() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

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
        Hyperlink signUpLink = new Hyperlink("Sign Up");
        HBox signUpHBox = new HBox(10, signUpLabel, signUpLink);
        signUpHBox.setAlignment(Pos.CENTER);
        gridPane.add(signUpHBox, 1, 4);

        signUpLink.setOnAction(e -> showSignUpScreen());

        return gridPane;
    }

    private Pane createSignUpRoot() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

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
        Hyperlink loginLink = new Hyperlink("Login");
        HBox loginHBox = new HBox(10, loginLabel, loginLink);
        loginHBox.setAlignment(Pos.CENTER);
        gridPane.add(loginHBox, 1, 4);

        loginLink.setOnAction(e -> popPage());

        return gridPane;
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
        popPage();
    }

    private void login(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            showAlert("Login Error", "Please enter a username and password.");
            return;
        }

        if (userDatabase.containsKey(username)) {
            String storedPassword = userDatabase.get(username);
            if (storedPassword.equals(password)) {
                showAlert("Login Successful", "Login successful. Welcome, " + username + "!");
                // Perform any actions after successful login
                // For example, you can open a new window or load a new scene
                // The code below just clears the fields
                //usernameTextField.clear();
                //passwordField.clear();
                return;
            }
        }

        showAlert("Login Error", "Invalid username or password.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showLoginScreen() {
        Pane loginRoot = createLoginRoot();
        pushPage(loginRoot);
    }

    private void showSignUpScreen() {
        Pane signUpRoot = createSignUpRoot();
        pushPage(signUpRoot);
    }
}
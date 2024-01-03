import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CoffeeShopApplication {
    private Stage primaryStage;
    private Scene signUpScene;
    private Scene loginScene;
    private CoffeeShopService coffeeShopService;

    public CoffeeShopApplication(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Coffee Shop");
        this.coffeeShopService = new CoffeeShopService();
    }

    public void run() {
        GridPane signUpGridPane = coffeeShopService.createGridPane();
        coffeeShopService.addSignUpUIControls(signUpGridPane);

        GridPane loginGridPane = coffeeShopService.createGridPane();
        coffeeShopService.addLoginUIControls(loginGridPane);

        signUpScene = new Scene(signUpGridPane, 400, 250);
        loginScene = new Scene(loginGridPane, 400, 250);

        coffeeShopService.setScenes(signUpScene, loginScene);

        primaryStage.setScene(signUpScene);
        primaryStage.show();
    }
}
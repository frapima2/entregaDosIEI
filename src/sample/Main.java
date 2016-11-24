package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        String exePath = "C:\\Users\\Javi\\Desktop\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://www.pccomponentes.com");
        searchPCComponentes(driver);
        driver.quit();
        launch(args);
    }

    public static void searchPCComponentes(WebDriver driver){
        WebElement aux = driver.findElement(By.className("pccom-icon"));
        aux.click();
        WebDriverWait waiting = new WebDriverWait(driver, 10);
        waiting.until( ExpectedConditions.presenceOfElementLocated( By.className("menu-principal__superfamilia") ) );
        aux = driver.findElement(By.className("menu-principal__superfamilia"));
        aux.click();
    }
}

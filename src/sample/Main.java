package sample;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Buscador smartphone");
        primaryStage.setScene(new Scene(root, 455, 240));
        primaryStage.show();
    }


    public static void main(String[] args) {

        String exePath = "C:\\Users\\Javi\\Desktop\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://www.fnac.es");
        WebElement aux = driver.findElement(By.className("js-HeaderSearch-input"));
        aux.sendKeys("Moviles"+"\n");
        aux = driver.findElement(By.cssSelector("a[href*='http://www.fnac.es/telefono-MP3-GPS/Smartphones-Libres/s39887']"));
        aux.click();
        aux = driver.findElement(By.cssSelector("a[href*='http://busqueda.tv-informatica-telefonia-foto.fnac.es/Smartphones-Libres/Smartphones-LG/n39894']"));
        aux.click();
        aux = driver.findElement(By.className("fake-box"));
        aux.click();
        WebElement next = driver.findElement(By.className("actionNext"));
        System.out.println(next.isDisplayed());
        while(next.isDisplayed()){
            next.click();
        }
        launch(args);
    }


}

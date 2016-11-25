package sample;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import java.util.ArrayList;
        import java.util.List;
        import javafx.scene.layout.Pane;

        import org.openqa.selenium.By;
        import org.openqa.selenium.JavascriptExecutor;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;
        import org.openqa.selenium.interactions.Actions;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.stage.Stage;
        import javafx.scene.Scene;
        import javafx.scene.layout.Pane;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Pane menu = (Pane)FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Buscar Telefonos");
        primaryStage.setScene(new Scene(menu, 612, 513));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    public void fnac(){
        String exePath = "C:\\chromedriver.exe";
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
    }
}

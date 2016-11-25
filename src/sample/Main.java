package sample;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import java.util.ArrayList;
        import java.util.List;
        import javafx.scene.layout.Pane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Pane menu = (Pane)FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Buscar Telefonos");
        primaryStage.setScene(new Scene(menu, 500, 240));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    public void fnac(){
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
    }
}

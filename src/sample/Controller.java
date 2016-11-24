package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class Controller {



    public static void chrome(){

        String exePath = "C:\\Users\\Javi\\Desktop\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://www.pccomponentes.com");
        //searchPCComponentes(driver);

        // Paso 1 introducir la cadena de búsqueda
        String searchText="Móviles\n";
        WebElement searchInputBox=driver.findElement(By.name("query"));
        searchInputBox.sendKeys(searchText);
        System.out.println("Paso 1 pasado");

        // Paso 2 esperar a los resultados de búsqueda
        WebDriverWait waiting = new WebDriverWait(driver, 10);
        waiting.until( ExpectedConditions.presenceOfElementLocated(By.id("resultados-busqueda") ) );
        System.out.println("Paso 2 pasado");

        // Paso 3 buscar el elemento ver más
        WebElement elementoMas = driver.findElement(By.xpath("//*[@id='filterMenuLateral']/div/div/div[17]/a/b"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,100)", "");
        elementoMas.click();
        System.out.println("Paso 3 pasado");

        // Paso 4 Cerrar la ventana de cookies
        driver.findElement(By.xpath("//*[@id='resultados-busqueda']/div[5]/div/div/div[2]/button")).click();
        System.out.println("Paso 4 pasado");

        // Paso 5 esperar a que salga el radio botón de LG y hacer scroll
        jse.executeScript("window.scrollBy(0,100)", "");
        waiting = new WebDriverWait(driver, 10);
        waiting.until( ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='acc-fil-0']/div/ul/li[2]/a")));
        WebElement element = driver.findElement(By.xpath("//a[@data-id='3']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,100)", "");
        System.out.println("Paso 5 pasado");

        // Paso 6 pulsar sobre el radio botón de los teléfonos LG
        element.click();
        System.out.println("Paso 6 pasado");

        // Paso 7 esperar a que muestre los telefonos LG
			/*waiting = new WebDriverWait(driver, 10);
			waiting.until(ExpectedConditions.presenceOfElementLocated(By.className("GTM-productClick enlace-superpuesto")));*/
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Paso 7 pasado");

        // Paso 8 Obtener todos los elementos que aparecen en la primera página
        WebElement mas = driver.findElement(By.xpath("//*[@id='btnMore']"));

        while(mas.isDisplayed()){
            actions.moveToElement(mas);
            actions.perform();
            jse.executeScript("window.scrollBy(0,100)", "");
            mas.click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        ArrayList<WebElement> resultados2= (ArrayList<WebElement>)
                driver.findElements(By.xpath("//*[contains(@class,'GTM-productClick enlace-superpuesto')]"));
        System.out.println("Resultados " + resultados2.size());
        System.out.println("Paso 8 pasado");

        // Paso 9 Iterar sobre la lista para obtener las características de los artículos
        WebElement actual_Elemento, navegacion2;
        for (int i=0; i< resultados2.size(); i++) {
            actual_Elemento = resultados2.get(i); // elemento actual de la lista.
            System.out.println("Elemento: " + i);
            System.out.println("Nombre: " + actual_Elemento.getAttribute("data-name").toString());
            System.out.println("Precio: " + actual_Elemento.getAttribute("data-price").toString() );
            // si está disponible o no, se busca en tarjeta-articulo__elementos-adicionales
            //navegacion2 = actual_Elemento.findElement(By.className("tarjeta-articulo__elementos-adicionales"));
            //System.out.println("Por navegación 2 " + navegacion2.getText()); // el texto indica si está disponible o no
            System.out.println("-------------------------------------------");
        }
        System.out.println("Paso 9 pasado");

        driver.quit();
    }

}

package sample;

import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class Controller {

    @FXML
    private TableView<Producto> resultados;
    @FXML
    private TableColumn<Producto, String> c_marca;
    @FXML
    private TableColumn<Producto, String> c_modelo;
    @FXML
    private TableColumn<Producto, String> c_precio;
    @FXML
    private TableColumn<Producto, String> c_web;



    @FXML
    private ComboBox comboBox;

    @FXML
    private Button buscar, cerrar;

    @FXML
    private CheckBox pc, fn;

    String marca;

    ObservableList<Producto> vacia = FXCollections.observableArrayList();

    public void llenarTabla(){
        c_marca.setCellValueFactory(param -> new
                ReadOnlyObjectWrapper<>(param.getValue().getMarca()));
        c_modelo.setCellValueFactory(param -> new
                ReadOnlyObjectWrapper<>(param.getValue().getModelo()));
        c_precio.setCellValueFactory(param -> new
                ReadOnlyObjectWrapper<>(param.getValue().getPrecio()));
        c_web.setCellValueFactory(param -> new
                ReadOnlyObjectWrapper<>(param.getValue().getWeb()));
    }

    public void rellenarCb(){
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Apple",
                        "Huawei",
                        "Leonovo",
                        "LG",
                        "Motorola",
                        "OnePlus",
                        "Samsung",
                        "Sony"
                );

        comboBox.setItems(options);

    }

    public void vaciar(){
        ObservableList<Producto> vacia = FXCollections.observableArrayList();
        resultados.setItems(vacia);
        rellenarCb();
    }

    public void buscar(){
        vaciar();
        if(pc.isSelected()){buscarPc(); System.out.println("Hecho");}
        if(fn.isSelected()){buscarFn();System.out.println("Hecho");}

    }

    public void buscarPc(){
        String web = "PCComponentes";
        String exePath = "src\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-minimized");
        WebDriver driver = new ChromeDriver(options);

        marca = comboBox.getValue().toString();
        switch(marca){
            case "Apple":  driver.get("http://www.pccomponentes.com/iphone");
                break;
            case "Huawei": driver.get("http://www.pccomponentes.com/smartphone-moviles-huawei");
                break;
            case "Leonovo": driver.get("http://www.pccomponentes.com/smartphone-moviles/lenovo");
                break;
            case "LG": driver.get("http://www.pccomponentes.com/smartphone-moviles-lg");
                break;
            case "Motorola": driver.get("http://www.pccomponentes.com/movil-motorola");
                break;
            case "OnePlus": driver.get("http://www.pccomponentes.com/smartphone-moviles/oneplus");
                break;
            case "Samsung": driver.get("http://www.pccomponentes.com/smartphone-moviles-samsung");
                break;
            case "Sony": driver.get("http://www.pccomponentes.com/sony-xperia");
                break;
            default: return;
        }


        //Paso 4 Cerrar la ventana de cookies
        driver.findElement(By.xpath("//*[@id=\'familia-secundaria\']/div[5]/div/div/div[2]/button")).click();
        //System.out.println("Popup Cookies cerrado");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Paso 8 Obtener todos los elementos que aparecen en la primera página
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        Actions actions = new Actions(driver);
        WebElement mas = driver.findElement(By.xpath("//*[@id='btnMore']"));
        actions.moveToElement(mas);
        WebElement cerrar_sub = driver.findElement(By.xpath("//*[@id=\'chicago-body\']/a"));

        while(mas.isDisplayed()){
            if(cerrar_sub.isDisplayed()){
                actions.moveToElement(cerrar_sub);
                actions.perform();
                cerrar_sub.click();
            }
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
        //System.out.println("Paso 8 pasado");
        String modelo;
        String precio;

        // Paso 9 Iterar sobre la lista para obtener las características de los artículos
        WebElement actual_Elemento, navegacion2;
        for (int i=0; i< resultados2.size(); i++) {
            actual_Elemento = resultados2.get(i); // elemento actual de la lista.
            modelo = actual_Elemento.getAttribute("data-name").toString();
            precio = actual_Elemento.getAttribute("data-price").toString();
            resultados.getItems().add(new Producto(marca,modelo,precio,web));
        }

        llenarTabla();
        driver.quit();
    }

    String nombre;
    String precio;

    public void buscarFn(){
        marca = comboBox.getValue().toString();
        String web = "Fnac";
        String exePath = "src\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);

        driver.get("http://www.fnac.es");

        WebElement aux = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[8]/a"));
        aux.click();
        aux = driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div[2]/dl[2]/dd[1]/a"));
        aux.click();

        switch(marca){
            case "Apple":  driver.get("http://busqueda.tv-informatica-telefonia-foto.fnac.es/Smartphones-Libres/Smartphones-Apple/n39890");
                break;
            case "Huawei": driver.get("http://busqueda.tv-informatica-telefonia-foto.fnac.es/Smartphones-Libres/Smartphones-Huawei/n39896");
                break;
            case "Leonovo": driver.get("http://busqueda.tv-informatica-telefonia-foto.fnac.es/Smartphones-Libres/Smartphones-Lenovo-Motorola/n40116");
                break;
            case "LG": driver.get("http://busqueda.tv-informatica-telefonia-foto.fnac.es/Smartphones-Libres/Smartphones-LG/n39894");
                break;
            case "Motorola": driver.get("http://busqueda.tv-informatica-telefonia-foto.fnac.es/Smartphones-Libres/Smartphones-Lenovo-Motorola/n40116");
                break;
            case "OnePlus": driver.get("http://busqueda.tv-informatica-telefonia-foto.fnac.es/Smartphones-Libres/Smartphones-Sony/n39895");
                break;
            case "Samsung": driver.get("http://busqueda.tv-informatica-telefonia-foto.fnac.es/Smartphones-Libres/Smartphones-Samsung/n39889");
                break;
            case "Sony": driver.get("http://busqueda.tv-informatica-telefonia-foto.fnac.es/Smartphones-Libres/Smartphones-Sony/n39895");
                break;
            default: return;
        }

        WebElement next = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div[2]/span[2]/ul/li[3]/a"));
        List<WebElement> elementos = driver.findElements(By.className("Article-itemGroup"));

        for(int i=0; i<elementos.size(); i++){
            try{
                nombre = elementos.get(i).findElement(By.xpath(".//*[@class='Article-desc']/a")).getText();
                precio = elementos.get(i).findElement(By.xpath(".//div[3]/div/div[2]/div/div[1]/a")).getText();
                System.out.println(nombre + "-" + precio);
                resultados.getItems().add(new Producto(marca, nombre, precio, web));
            }
            catch(Exception e){

            }
        }

        while(next.isDisplayed()){

            next.click();
            try{
                waitForAjax(driver);
            }
            catch (InterruptedException i){
                System.out.print("ERROR DE CONEXION");
            }

            elementos = driver.findElements(By.className("Article-itemGroup"));
            for(int i=0; i<elementos.size(); i++){
                try{
                    nombre = elementos.get(i).findElement(By.xpath(".//*[@class='Article-desc']/a")).getText();
                    precio = elementos.get(i).findElement(By.xpath(".//div[3]/div/div[2]/div/div[1]/a")).getText();
                    System.out.println(nombre + "-" + precio);
                    resultados.getItems().add(new Producto(marca, nombre, precio, web));
                }
                catch(Exception e){

                }
            }
        }
        driver.quit();

        llenarTabla();

    }

    public static void waitForAjax(WebDriver driver) throws InterruptedException
    {
        while (true)
        { Boolean ajaxIsComplete = (Boolean)
                ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");
            if (ajaxIsComplete){
                break;
            }
            Thread.sleep(100);
        }
    }
}

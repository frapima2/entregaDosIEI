package sample;

/**
 * Created by Marcos on 25/11/2016.
 */
public class Producto {

    String marca;
    String modelo;
    String precio;
    String web;

    public Producto(String marca, String modelo, String precio, String web) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.web = web;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}

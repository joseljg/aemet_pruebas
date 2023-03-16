package es.joseljg.aemet_pruebas;

import java.io.Serializable;
import java.util.Objects;

public class Estacion implements Serializable {

    // atributos
    private String latitud;
    private String provincia;
    private int altitud;
    private String indicativo;
    private String nombre;
    private String indsinop;
    private String longitud;


    //--------------------------------------------------------

    public Estacion(String latitud, String provincia, int altitud, String indicativo, String nombre, String indsinop, String longitud) {
        this.latitud = latitud;
        this.provincia = provincia;
        this.altitud = altitud;
        this.indicativo = indicativo;
        this.nombre = nombre;
        this.indsinop = indsinop;
        this.longitud = longitud;
    }

    public Estacion() {
        this.latitud = "";
        this.provincia = "";
        this.altitud = 0;
        this.indicativo = "";
        this.nombre = "";
        this.indsinop = "";
        this.longitud = "";
    }


    //----------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estacion)) return false;
        Estacion estacion = (Estacion) o;
        return Objects.equals(indicativo, estacion.indicativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indicativo);
    }
    //-----------------------------------------------------------


    public String getIndicativo() {
        return indicativo;
    }

    public void setIndicativo(String indicativo) {
        this.indicativo = indicativo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIndsinop() {
        return indsinop;
    }

    public void setIndsinop(String indsinop) {
        this.indsinop = indsinop;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getAltitud() {
        return altitud;
    }

    public void setAltitud(int altitud) {
        this.altitud = altitud;
    }
    //---------------------------------------------------------------

    @Override
    public String toString() {
        return "Estacion{" +
                "latitud='" + latitud + '\'' +
                ", provincia='" + provincia + '\'' +
                ", altitud=" + altitud +
                ", indicativo='" + indicativo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", indsinop='" + indsinop + '\'' +
                ", longitud='" + longitud + '\'' +
                '}';
    }


    //--------------------------------------------------------------

}

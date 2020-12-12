package objetos;

import enumeracion.categoria;
import enumeracion.color;
import java.util.ArrayList;
import java.util.List;

public class Pieza {

    private color color;
    private categoria tipo;
    private Integer ubicacion;
    private ArrayList<Integer> movDisponible;
    private ArrayList<Integer> comidaDisponible;
    private List<Integer> lugaresProtegidos;
    private List<Integer> crucePeon;//Este atributo considera todas las ubicaciones a las que el peon puede comer cruzado si hay alguien ahí aunque al momento de recibir el tablero no hay nadie   
    
    public List<Integer> getCrucePeon() {
        return crucePeon;
    }

    public void setCrucePeon(List<Integer> crucePeon) {
        this.crucePeon = crucePeon;
    }
    
     
    public color getColor() {
        return color;
    }

    public void setColor(color color) {
        this.color = color;
    }

    public categoria getTipo() {
        return tipo;
    }

    public void setTipo(categoria tipo) {
        this.tipo = tipo;
    }

    public Integer getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Integer ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<Integer> getMovDisponible() {
        return movDisponible;
    }

    public void setMovDisponible(ArrayList<Integer> movDisponible) {
        this.movDisponible = movDisponible;
    }

    public ArrayList<Integer> getComidaDisponible() {
        return comidaDisponible;
    }

    public void setComidaDisponible(ArrayList<Integer> comidaDisponible) {
        this.comidaDisponible = comidaDisponible;
    }

    public List<Integer> getLugaresProtegidos() {
        return lugaresProtegidos;
    }

    public void setLugaresProtegidos(List<Integer> lugaresProtegidos) {
        this.lugaresProtegidos = lugaresProtegidos;
    }

   

    @Override
    public String toString() {
        return "Pieza{" + "color=" + color + ", tipo=" + tipo + ", ubicacion=" + ubicacion + '}';
    }

}

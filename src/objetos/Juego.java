package objetos;

import enumeracion.color;
import java.util.List;

public class Juego {

    private Tablero tablero;
    private color colorMio;
    private List<Situacion> situacion;

    public List<Situacion> getSituacion() {
        return situacion;
    }

    public void setSituacion(List<Situacion> situacion) {
        this.situacion = situacion;
    }

    
    
    public color getColorMio() {
        return colorMio;
    }

    public void setColorMio(color colorMio) {
        this.colorMio = colorMio;
    }

    public Juego() {
        Tablero tablero = new Tablero();
        this.tablero = tablero;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

}

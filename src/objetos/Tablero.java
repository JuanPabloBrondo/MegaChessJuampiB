package objetos;

import java.util.ArrayList;

public class Tablero {

    private String[][] ubicaciones;
    private Pieza[][] ubicacionesPieza;
    private boolean[][] rivalProtege;
    private boolean[][] yoProtejo;//true cuando una ubicación de una pieza nuestra está al alcance de otra pieza nuestra en caso de que coman a la primera
    private boolean[][] dobleProteccion;//true cuando dos piezas pueden llegar a esa ubicación
    private Pieza[][] rivalPuedeComer;//Así sería ideal--->  private List<Pieza>[][] rivalPuedeComer;  //Son las piezas rivales que pueden comer nuestras piezas en la ubicacion de nuestra pieza
    private ArrayList<Integer> filasConReinaPropia;

    public ArrayList<Integer> getFilasConReinaPropia() {
        return filasConReinaPropia;
    }

    public void setFilasConReinaPropia(ArrayList<Integer> filasConReinaPropia) {
        this.filasConReinaPropia = filasConReinaPropia;
    }

    public boolean[][] getYoProtejo() {
        return yoProtejo;
    }

    public void setYoProtejo(boolean[][] yoProtejo) {
        this.yoProtejo = yoProtejo;
    }

    public boolean[][] getDobleProteccion() {
        return dobleProteccion;
    }

    public void setDobleProteccion(boolean[][] dobleProteccion) {
        this.dobleProteccion = dobleProteccion;
    }

    public Pieza[][] getRivalPuedeComer() {
        return rivalPuedeComer;
    }

    public void setRivalPuedeComer(Pieza[][] rivalPuedeComer) {
        this.rivalPuedeComer = rivalPuedeComer;
    }

    public boolean[][] getRivalProtege() {
        return rivalProtege;
    }

    public void setRivalProtege(boolean[][] rivalProtege) {
        this.rivalProtege = rivalProtege;
    }

    public Pieza[][] getUbicacionesPieza() {
        return ubicacionesPieza;
    }

    public void setUbicacionesPieza(Pieza[][] ubicacionesPieza) {
        this.ubicacionesPieza = ubicacionesPieza;
    }

    public void setUbicaciones(String[][] ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public String[][] getUbicaciones() {
        return ubicaciones;
    }

}

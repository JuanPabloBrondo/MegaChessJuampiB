package service;

import enumeracion.categoria;
import enumeracion.color;
import java.util.ArrayList;
import java.util.List;
import objetos.Pieza;
import objetos.Tablero;
import utilidades.utilidades;

public class piezaService {

    //Definir las piezas  
    public static Pieza mapearPieza(String letra, Integer ubicacion) {
        Pieza pieza = new Pieza();
        switch (letra) {
            case "Q":
                pieza.setColor(color.BLANCO);
                pieza.setTipo(categoria.REINA);
                break;
            case "K":
                pieza.setColor(color.BLANCO);
                pieza.setTipo(categoria.REY);
                break;
            case "R":
                pieza.setColor(color.BLANCO);
                pieza.setTipo(categoria.TORRE);
                break;
            case "H":
                pieza.setColor(color.BLANCO);
                pieza.setTipo(categoria.CABALLO);
                break;
            case "B":
                pieza.setColor(color.BLANCO);
                pieza.setTipo(categoria.ALFIL);
                break;
            case "P":
                pieza.setColor(color.BLANCO);
                pieza.setTipo(categoria.PEON);
                break;
            case "q":
                pieza.setColor(color.NEGRO);
                pieza.setTipo(categoria.REINA);
                break;
            case "k":
                pieza.setColor(color.NEGRO);
                pieza.setTipo(categoria.REY);
                break;
            case "r":
                pieza.setColor(color.NEGRO);
                pieza.setTipo(categoria.TORRE);
                break;
            case "h":
                pieza.setColor(color.NEGRO);
                pieza.setTipo(categoria.CABALLO);
                break;
            case "b":
                pieza.setColor(color.NEGRO);
                pieza.setTipo(categoria.ALFIL);
                break;
            case "p":
                pieza.setColor(color.NEGRO);
                pieza.setTipo(categoria.PEON);
                break;
        }
        pieza.setUbicacion(ubicacion);
        return pieza;
    }

    //Informar qué movimientos pueden hacer y que comidas de fichas pueden hacer
    public static Pieza movimientosDisponibles(Pieza pieza, Pieza[][] ubicacionesPiezas) {

        switch (pieza.getTipo()) {
            case PEON:
                pieza = infoPeon(pieza, ubicacionesPiezas);
                break;
            case TORRE:
                pieza = infoTorre(pieza, ubicacionesPiezas);
                break;
            case ALFIL:
                pieza = infoAlfil(pieza, ubicacionesPiezas);
                break;
            case REINA:
                pieza = infoReina(pieza, ubicacionesPiezas);
                break;
            case REY:
                pieza = infoRey(pieza, ubicacionesPiezas);
                break;
            case CABALLO:
                pieza = infoCaballo(pieza, ubicacionesPiezas);
                break;
        }
        return pieza;
    }

    //Devuelven una pieza con la info de donde puede mover y donde puede comer
    public static Pieza infoPeon(Pieza pieza, Pieza[][] ubicacionesPiezas) {
        Integer fila = utilidades.fila(pieza.getUbicacion());
        Integer columna = utilidades.columna(pieza.getUbicacion());
        ArrayList<Integer> movDisponible = new ArrayList<Integer>();
        ArrayList<Integer> comidaDisponible = new ArrayList<Integer>();
        List<Integer> lugaresProtegidos = new ArrayList<Integer>();
        List<Integer> crucePeon = new ArrayList<Integer>();
        Integer movimiento = 0;

        if (pieza.getColor().equals(color.BLANCO)) {
            //En esta tanda se carga a dónde se puede mover el peon
            if (utilidades.dentroDelTablero(fila - 1, columna) && ubicacionesPiezas[fila - 1][columna] == null) {
                movimiento = (fila - 1) * 16 + columna;
                movDisponible.add(movimiento);

                //Los siguientes 2 if consideran cuando los peones pueden hacer doble paso
                if (fila == 13 && ubicacionesPiezas[fila - 2][columna] == null) {
                    movimiento = (fila - 2) * 16 + columna;
                    movDisponible.add(movimiento);
                }
                //El tercer requisito es para que si el peon de atras se mueve uno después no pueda hacer 
                //el doble paso (igual tiene el error de que si matan al peon de atras antes que al de adelante el peon de adelante no podrá hacer doble paso)            
                //Siempre qeu los peones esten en las primeras 2 lineas pueden dar 2 pasos
                if (fila == 12 && ubicacionesPiezas[fila - 2][columna] == null /*&& ubicacionesPiezas[fila + 1][columna] != null && ubicacionesPiezas[fila + 1][columna].getTipo().equals(categoria.PEON)*/) {
                    movimiento = (fila - 2) * 16 + columna;
                    movDisponible.add(movimiento);
                }
            }

            //En esta tanda se muestra a donde puede comer el peon
            if (utilidades.dentroDelTablero(fila - 1, columna - 1)) {
                if (ubicacionesPiezas[fila - 1][columna - 1] != null && ubicacionesPiezas[fila - 1][columna - 1].getColor().equals(color.NEGRO)) {
                    movimiento = utilidades.ubicacion(fila - 1, columna - 1);
                    comidaDisponible.add(movimiento);
                } else if (ubicacionesPiezas[fila - 1][columna - 1] != null && ubicacionesPiezas[fila - 1][columna - 1].getColor().equals(color.BLANCO)) {
                    movimiento = utilidades.ubicacion(fila - 1, columna - 1);
                    lugaresProtegidos.add(movimiento);
                } else {
                    movimiento = utilidades.ubicacion(fila - 1, columna - 1);
                    crucePeon.add(movimiento);
                }
            }
            if (utilidades.dentroDelTablero(fila - 1, columna + 1)) {
                if (ubicacionesPiezas[fila - 1][columna + 1] != null && ubicacionesPiezas[fila - 1][columna + 1].getColor().equals(color.NEGRO)) {
                    movimiento = (fila - 1) * 16 + columna + 1;
                    comidaDisponible.add(movimiento);
                } else if (ubicacionesPiezas[fila - 1][columna + 1] != null && ubicacionesPiezas[fila - 1][columna + 1].getColor().equals(color.BLANCO)) {
                    movimiento = utilidades.ubicacion(fila - 1, columna + 1);
                    lugaresProtegidos.add(movimiento);
                } else {
                    movimiento = (fila - 1) * 16 + columna + 1;
                    crucePeon.add(movimiento);
                }
            }
        } else {
            //Se repite lo de arriba pero para peones negros ya que se mueven verticalmente en sentidos opuestos 
            if (utilidades.dentroDelTablero(fila + 1, columna) && ubicacionesPiezas[fila + 1][columna] == null) {
                movimiento = (fila + 1) * 16 + columna;
                movDisponible.add(movimiento);

                if (fila == 2 && ubicacionesPiezas[fila + 2][columna] == null) {
                    movimiento = (fila + 2) * 16 + columna;
                    movDisponible.add(movimiento);
                }

                //Siempre qeu los peones esten en las primeras 2 lineas pueden dar 2 pasos
                if (fila == 3 && ubicacionesPiezas[fila + 2][columna] == null /*&& ubicacionesPiezas[fila - 1][columna] != null && ubicacionesPiezas[fila - 1][columna].getTipo().equals(categoria.PEON)*/) {
                    movimiento = (fila + 2) * 16 + columna;
                    movDisponible.add(movimiento);
                }
            }

            if (utilidades.dentroDelTablero(fila + 1, columna - 1)) {
                if (ubicacionesPiezas[fila + 1][columna - 1] != null && ubicacionesPiezas[fila + 1][columna - 1].getColor().equals(color.BLANCO)) {
                    movimiento = (fila + 1) * 16 + columna - 1;
                    comidaDisponible.add(movimiento);
                } else if (ubicacionesPiezas[fila + 1][columna - 1] != null && ubicacionesPiezas[fila + 1][columna - 1].getColor().equals(color.NEGRO)) {
                    movimiento = utilidades.ubicacion(fila + 1, columna - 1);
                    lugaresProtegidos.add(movimiento);
                } else {
                    movimiento = (fila + 1) * 16 + columna - 1;
                    crucePeon.add(movimiento);
                }
            }
            if (utilidades.dentroDelTablero(fila + 1, columna + 1)) {
                if (ubicacionesPiezas[fila + 1][columna + 1] != null && ubicacionesPiezas[fila + 1][columna + 1].getColor().equals(color.BLANCO)) {
                    movimiento = (fila + 1) * 16 + columna + 1;
                    comidaDisponible.add(movimiento);
                } else if (ubicacionesPiezas[fila + 1][columna + 1] != null && ubicacionesPiezas[fila + 1][columna + 1].getColor().equals(color.NEGRO)) {
                    movimiento = utilidades.ubicacion(fila + 1, columna + 1);
                    lugaresProtegidos.add(movimiento);
                } else {
                    movimiento = (fila + 1) * 16 + columna + 1;
                    crucePeon.add(movimiento);
                }
            }
        }

        pieza.setCrucePeon(crucePeon);
        pieza.setLugaresProtegidos(lugaresProtegidos);
        pieza.setMovDisponible(movDisponible);
        pieza.setComidaDisponible(comidaDisponible);
        return pieza;
    }

    public static Pieza infoTorre(Pieza pieza, Pieza[][] ubicacionesPiezas) {
        Integer fila = utilidades.fila(pieza.getUbicacion());
        Integer columna = utilidades.columna(pieza.getUbicacion());
        ArrayList<Integer> movDisponible = new ArrayList<Integer>();
        ArrayList<Integer> comidaDisponible = new ArrayList<Integer>();
        List<Integer> lugaresProtegidos = new ArrayList<Integer>();
        Integer movimiento = 0;

        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila - i, columna)) {
                if (ubicacionesPiezas[fila - i][columna] == null) {
                    movimiento = utilidades.ubicacion(fila - i, columna);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila - i][columna].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila - i, columna);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila - i, columna);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila + i, columna)) {
                if (ubicacionesPiezas[fila + i][columna] == null) {
                    movimiento = utilidades.ubicacion(fila + i, columna);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila + i][columna].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila + i, columna);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila + i, columna);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila, columna - i)) {
                if (ubicacionesPiezas[fila][columna - i] == null) {
                    movimiento = utilidades.ubicacion(fila, columna - i);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila][columna - i].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila, columna - i);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila, columna - i);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }
        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila, columna + i)) {
                if (ubicacionesPiezas[fila][columna + i] == null) {
                    movimiento = utilidades.ubicacion(fila, columna + i);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila][columna + i].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila, columna + i);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila, columna + i);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }

        pieza.setLugaresProtegidos(lugaresProtegidos);
        pieza.setMovDisponible(movDisponible);
        pieza.setComidaDisponible(comidaDisponible);
        return pieza;
    }

    public static Pieza infoAlfil(Pieza pieza, Pieza[][] ubicacionesPiezas) {
        Integer fila = utilidades.fila(pieza.getUbicacion());
        Integer columna = utilidades.columna(pieza.getUbicacion());
        ArrayList<Integer> movDisponible = new ArrayList<Integer>();
        ArrayList<Integer> comidaDisponible = new ArrayList<Integer>();
        List<Integer> lugaresProtegidos = new ArrayList<Integer>();
        Integer movimiento = 0;

        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila - i, columna - i)) {
                if (ubicacionesPiezas[fila - i][columna - i] == null) {
                    movimiento = utilidades.ubicacion(fila - i, columna - i);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila - i][columna - i].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila - i, columna - i);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila - i, columna - i);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila + i, columna + i)) {
                if (ubicacionesPiezas[fila + i][columna + i] == null) {
                    movimiento = utilidades.ubicacion(fila + i, columna + i);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila + i][columna + i].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila + i, columna + i);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila + i, columna + i);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila + i, columna - i)) {
                if (ubicacionesPiezas[fila + i][columna - i] == null) {
                    movimiento = (fila + i) * 16 + columna - i;
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila + i][columna - i].getColor().equals(pieza.getColor())) {
                    movimiento = (fila + i) * 16 + columna - i;
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = (fila + i) * 16 + columna - i;
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }
        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila - i, columna + i)) {
                if (ubicacionesPiezas[fila - i][columna + i] == null) {
                    movimiento = utilidades.ubicacion(fila - i, columna + i);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila - i][columna + i].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila - i, columna + i);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila - i, columna + i);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }
        pieza.setLugaresProtegidos(lugaresProtegidos);
        pieza.setMovDisponible(movDisponible);
        pieza.setComidaDisponible(comidaDisponible);
        return pieza;
    }

    public static Pieza infoReina(Pieza pieza, Pieza[][] ubicacionesPiezas) {
        Integer fila = utilidades.fila(pieza.getUbicacion());
        Integer columna = utilidades.columna(pieza.getUbicacion());
        ArrayList<Integer> movDisponible = new ArrayList<Integer>();
        ArrayList<Integer> comidaDisponible = new ArrayList<Integer>();
        List<Integer> lugaresProtegidos = new ArrayList<Integer>();
        Integer movimiento = 0;

        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila - i, columna)) {
                if (ubicacionesPiezas[fila - i][columna] == null) {
                    movimiento = utilidades.ubicacion(fila - i, columna);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila - i][columna].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila - i, columna);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila - i, columna);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila + i, columna)) {
                if (ubicacionesPiezas[fila + i][columna] == null) {
                    movimiento = utilidades.ubicacion(fila + i, columna);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila + i][columna].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila + i, columna);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila + i, columna);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila, columna - i)) {
                if (ubicacionesPiezas[fila][columna - i] == null) {
                    movimiento = utilidades.ubicacion(fila, columna - i);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila][columna - i].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila, columna - i);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila, columna - i);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }
        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila, columna + i)) {
                if (ubicacionesPiezas[fila][columna + i] == null) {
                    movimiento = utilidades.ubicacion(fila, columna + i);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila][columna + i].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila, columna + i);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila, columna + i);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }
        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila - i, columna - i)) {
                if (ubicacionesPiezas[fila - i][columna - i] == null) {
                    movimiento = utilidades.ubicacion(fila - i, columna - i);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila - i][columna - i].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila - i, columna - i);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila - i, columna - i);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila + i, columna + i)) {
                if (ubicacionesPiezas[fila + i][columna + i] == null) {
                    movimiento = utilidades.ubicacion(fila + i, columna + i);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila + i][columna + i].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila + i, columna + i);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila + i, columna + i);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila + i, columna - i)) {
                if (ubicacionesPiezas[fila + i][columna - i] == null) {
                    movimiento = utilidades.ubicacion(fila + i, columna - i);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila + i][columna - i].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila + i, columna - i);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila + i, columna - i);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }
        for (int i = 1; i < 16; i++) {
            if (utilidades.dentroDelTablero(fila - i, columna + i)) {
                if (ubicacionesPiezas[fila - i][columna + i] == null) {
                    movimiento = utilidades.ubicacion(fila - i, columna + i);
                    movDisponible.add(movimiento);
                } else if (!ubicacionesPiezas[fila - i][columna + i].getColor().equals(pieza.getColor())) {
                    movimiento = utilidades.ubicacion(fila - i, columna + i);
                    comidaDisponible.add(movimiento);
                    i = 16;
                } else {
                    movimiento = utilidades.ubicacion(fila - i, columna + i);
                    lugaresProtegidos.add(movimiento);
                    i = 16;
                }
            } else {
                break;
            }
        }

        pieza.setLugaresProtegidos(lugaresProtegidos);
        pieza.setMovDisponible(movDisponible);
        pieza.setComidaDisponible(comidaDisponible);
        return pieza;
    }

    public static Pieza infoRey(Pieza pieza, Pieza[][] ubicacionesPiezas) {
        Integer fila = utilidades.fila(pieza.getUbicacion());
        Integer columna = utilidades.columna(pieza.getUbicacion());
        ArrayList<Integer> movDisponible = new ArrayList<Integer>();
        ArrayList<Integer> comidaDisponible = new ArrayList<Integer>();
        List<Integer> lugaresProtegidos = new ArrayList<Integer>();
        Integer movimiento = 0;

        if (utilidades.dentroDelTablero(fila - 1, columna - 1)) {
            if (ubicacionesPiezas[fila - 1][columna - 1] == null) {
                movimiento = utilidades.ubicacion(fila - 1, columna - 1);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila - 1][columna - 1].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila - 1, columna - 1);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila - 1, columna - 1);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila - 1, columna)) {
            if (ubicacionesPiezas[fila - 1][columna] == null) {
                movimiento = utilidades.ubicacion(fila - 1, columna);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila - 1][columna].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila - 1, columna);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila - 1, columna);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila - 1, columna + 1)) {
            if (ubicacionesPiezas[fila - 1][columna + 1] == null) {
                movimiento = utilidades.ubicacion(fila - 1, columna + 1);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila - 1][columna + 1].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila - 1, columna + 1);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila - 1, columna + 1);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila, columna - 1)) {
            if (ubicacionesPiezas[fila][columna - 1] == null) {
                movimiento = utilidades.ubicacion(fila, columna - 1);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila][columna - 1].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila, columna - 1);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila, columna - 1);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila, columna + 1)) {
            if (ubicacionesPiezas[fila][columna + 1] == null) {
                movimiento = utilidades.ubicacion(fila, columna + 1);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila][columna + 1].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila, columna + 1);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila, columna + 1);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila + 1, columna - 1)) {
            if (ubicacionesPiezas[fila + 1][columna - 1] == null) {
                movimiento = utilidades.ubicacion(fila + 1, columna - 1);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila + 1][columna - 1].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila + 1, columna - 1);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila + 1, columna - 1);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila + 1, columna)) {
            if (ubicacionesPiezas[fila + 1][columna] == null) {
                movimiento = utilidades.ubicacion(fila + 1, columna);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila + 1][columna].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila + 1, columna);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila + 1, columna);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila + 1, columna + 1)) {
            if (ubicacionesPiezas[fila + 1][columna + 1] == null) {
                movimiento = utilidades.ubicacion(fila + 1, columna + 1);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila + 1][columna + 1].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila + 1, columna + 1);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila + 1, columna + 1);
                lugaresProtegidos.add(movimiento);
            }
        }

        pieza.setLugaresProtegidos(lugaresProtegidos);
        pieza.setMovDisponible(movDisponible);
        pieza.setComidaDisponible(comidaDisponible);
        return pieza;
    }

    public static Pieza infoCaballo(Pieza pieza, Pieza[][] ubicacionesPiezas) {
        Integer fila = utilidades.fila(pieza.getUbicacion());
        Integer columna = utilidades.columna(pieza.getUbicacion());
        ArrayList<Integer> movDisponible = new ArrayList<Integer>();
        ArrayList<Integer> comidaDisponible = new ArrayList<Integer>();
        List<Integer> lugaresProtegidos = new ArrayList<Integer>();
        Integer movimiento = 0;

        if (utilidades.dentroDelTablero(fila - 2, columna - 1)) {
            if (ubicacionesPiezas[fila - 2][columna - 1] == null) {
                movimiento = utilidades.ubicacion(fila-2, columna-1);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila - 2][columna - 1].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila-2, columna-1);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila-2, columna-1);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila - 1, columna - 2)) {
            if (ubicacionesPiezas[fila - 1][columna - 2] == null) {
                movimiento = utilidades.ubicacion(fila-1, columna-2);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila - 1][columna - 2].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila-1, columna-2);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila-1, columna-2);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila + 1, columna - 2)) {
            if (ubicacionesPiezas[fila + 1][columna - 2] == null) {
                movimiento = utilidades.ubicacion(fila+1, columna-2);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila + 1][columna - 2].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila+1, columna-2);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila+1, columna-2);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila + 2, columna - 1)) {
            if (ubicacionesPiezas[fila + 2][columna - 1] == null) {
                movimiento = utilidades.ubicacion(fila+2, columna-1);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila + 2][columna - 1].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila+2, columna-1);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila+2, columna-1);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila + 2, columna + 1)) {
            if (ubicacionesPiezas[fila + 2][columna + 1] == null) {
                movimiento = utilidades.ubicacion(fila+2, columna+1);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila + 2][columna + 1].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila+2, columna+1);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila+2, columna+1);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila + 1, columna + 2)) {
            if (ubicacionesPiezas[fila + 1][columna + 2] == null) {
                movimiento = utilidades.ubicacion(fila+1, columna+2);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila + 1][columna + 2].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila+1, columna+2);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila+1, columna+2);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila - 1, columna + 2)) {
            if (ubicacionesPiezas[fila - 1][columna + 2] == null) {
                movimiento = utilidades.ubicacion(fila-1, columna+2);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila - 1][columna + 2].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila-1, columna+2);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila-1, columna+2);
                lugaresProtegidos.add(movimiento);
            }
        }
        if (utilidades.dentroDelTablero(fila - 2, columna + 1)) {
            if (ubicacionesPiezas[fila - 2][columna + 1] == null) {
                movimiento = utilidades.ubicacion(fila-2, columna+1);
                movDisponible.add(movimiento);
            } else if (!ubicacionesPiezas[fila - 2][columna + 1].getColor().equals(pieza.getColor())) {
                movimiento = utilidades.ubicacion(fila-2, columna+1);
                comidaDisponible.add(movimiento);
            } else {
                movimiento = utilidades.ubicacion(fila-2, columna+1);
                lugaresProtegidos.add(movimiento);
            }
        }

        pieza.setLugaresProtegidos(lugaresProtegidos);
        pieza.setMovDisponible(movDisponible);
        pieza.setComidaDisponible(comidaDisponible);
        return pieza;
    }

}

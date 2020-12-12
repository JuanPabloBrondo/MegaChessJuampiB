package utilidades;

import objetos.Pieza;

public class utilidades {

    //Funciones auxiliares para orientar 
    public static Integer fila(Integer ubicacion) {
        Integer fi = (ubicacion / 16);
        return fi;
    }

    public static Integer columna(Integer ubicacion) {
        Integer fi = ubicacion / 16;
        Integer col = ubicacion - fi * 16;
        return col;
    }

    public static Integer ubicacion(Integer fila, Integer columna) {
        Integer ubicacion = 16 * fila + columna;
        return ubicacion;
    }

    public static boolean dentroDelTablero(Integer fila, Integer columna) {
        if (fila < 0 || fila > 15 || columna < 0 || columna > 15) {
            return false;
        }
        return true;
    }

    public static Integer ordenDeValor(Pieza pieza) {
        Integer salida = 0;
        switch (pieza.getTipo()) {
            case REY:
                salida = 6;
                break;
            case REINA:
                salida = 5;
                break;
            case TORRE:
                salida = 4;
                break;
            case ALFIL:
                salida = 3;
                break;
            case CABALLO:
                salida = 2;
                break;
            case PEON:
                salida = 1;
                break;
        }
        return salida;
    }
}

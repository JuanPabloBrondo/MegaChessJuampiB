package service;

import enumeracion.categoria;
import enumeracion.color;
import java.util.ArrayList;
import objetos.Pieza;
import objetos.Tablero;
import utilidades.utilidades;

public class tableroService {

    //Recibimos la info codificada y armamos las ubicaciones de las piezas en el tablero
    public static String[][] recibir(String codigoTablero) {
        String ubicacion[][] = new String[16][16];
        int cont = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                ubicacion[i][j] = codigoTablero.substring(cont, cont + 1);
                cont++;
            }
        }
        return ubicacion;
    }

    //Con la matriz de Strings armamos la matriz de piezas
    public static Pieza[][] definirUbicacionPiezas(String[][] ubicaciones) {
        Pieza[][] ubicacionespieza = new Pieza[16][16];
        Integer cont = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                String letra = ubicaciones[i][j];
                if (!letra.equals(" ")) {
                    Pieza pieza = piezaService.mapearPieza(letra, cont);
                    ubicacionespieza[i][j] = pieza;
                }
                cont++;
            }
        }
        return ubicacionespieza;
    }

    //El SIGUIENTE MÉTODO ACTUALIZA LAS PIEZAS TENIENDO AHORA CARGADA LOS MOVIMIENTOS
    public static Pieza[][] cargarMovimientos(Pieza[][] ubicacionPiezas, Tablero tablero) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (ubicacionPiezas[i][j] != null) {
                    Pieza pieza = ubicacionPiezas[i][j];
                    pieza = piezaService.movimientosDisponibles(pieza, tablero.getUbicacionesPieza());
                    ubicacionPiezas[i][j] = pieza;
                }
            }
        }
        return ubicacionPiezas;
    }

    //Ahora definimos situaciones en función de nuestro color 
    public static Tablero analizarTablero(Tablero tablero, color colorMio) {
        //Primero marcamos que lugares son peligrosos para mover
        tablero.setRivalProtege(definirLugaresPeligrosos(tablero.getUbicacionesPieza(), colorMio));
        //Marcamos los lugares en los que tenemos una pieza y si esta es comida tenemos otra pieza que la come en venganza
        tablero.setYoProtejo(definirLugaresYoProtejo(tablero.getUbicacionesPieza(), colorMio));
        //También los lugares que protegen 2 o más de nuestras piezas
        tablero.setDobleProteccion(definirLugaresProtegidos(tablero.getUbicacionesPieza(), colorMio));
        //Luego los lugares que corremos riesgo y esta matriz tiene quién puede ser quien come nuestra pieza
        tablero.setRivalPuedeComer(definirLugaresApuntadosPorRival(tablero.getUbicacionesPieza(), colorMio));
        //Marcamos que filas tienen una o más reinas nuestras
        tablero.setFilasConReinaPropia(filaConReinaPropia(tablero.getUbicacionesPieza(), colorMio));
        return tablero;
    }

    //Marcamos en una matriz todas las zonas a las que las piezas del rival tienen llegada
    //Incluímos aca el crucePeon por ser practico y un caso particular pero relevante ya que usamos esta información para 
    // saber si ubicar nuestra pieza en esa posición o no
    public static boolean[][] definirLugaresPeligrosos(Pieza[][] ubicacionPiezas, color colorMio) {
        boolean[][] rivalProtege = new boolean[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                rivalProtege[i][j] = false;
            }
        }
        Integer fila = 0;
        Integer columna = 0;

        for (Pieza[] ubicacionPieza : ubicacionPiezas) {
            for (Pieza pieza : ubicacionPieza) {
                if (pieza != null && !pieza.getColor().equals(colorMio)) {
                    for (Integer ubicacion : pieza.getLugaresProtegidos()) {
                        fila = utilidades.fila(ubicacion);
                        columna = utilidades.columna(ubicacion);
                        rivalProtege[fila][columna] = true;
                    }
                    if (!pieza.getTipo().equals(categoria.PEON)) {
                        for (Integer ubicacion : pieza.getMovDisponible()) {
                            fila = utilidades.fila(ubicacion);
                            columna = utilidades.columna(ubicacion);
                            rivalProtege[fila][columna] = true;
                        }
                    }
                    if (pieza.getCrucePeon() != null) {
                        for (Integer ubicacion : pieza.getCrucePeon()) {
                            fila = utilidades.fila(ubicacion);
                            columna = utilidades.columna(ubicacion);
                            rivalProtege[fila][columna] = true;
                        }
                    }
                }
            }
        }
        return rivalProtege;
    }

    //Marcamos en una matriz todas las zonas a las que nuestras piezas defienden en caso de que coman a la pieza que está en dicho lugar
    public static boolean[][] definirLugaresYoProtejo(Pieza[][] ubicacionPiezas, color colorMio) {
        boolean[][] rivalProtege = new boolean[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                rivalProtege[i][j] = false;
            }
        }
        Integer fila = 0;
        Integer columna = 0;

        for (Pieza[] ubicacionPieza : ubicacionPiezas) {
            for (Pieza pieza : ubicacionPieza) {
                if (pieza != null && pieza.getColor().equals(colorMio)) {
                    for (Integer ubicacion : pieza.getLugaresProtegidos()) {
                        fila = utilidades.fila(ubicacion);
                        columna = utilidades.columna(ubicacion);
                        rivalProtege[fila][columna] = true;
                    }
                }
            }
        }
        return rivalProtege;
    }

    //Marcamos en una matriz todas las zonas a las 2 o más de nuestras piezas tienen llegada
    public static boolean[][] definirLugaresProtegidos(Pieza[][] ubicacionPiezas, color colorMio) {
        boolean[][] Protegido = new boolean[16][16];
        boolean[][] dobleProteccion = new boolean[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Protegido[i][j] = false;
                dobleProteccion[i][j] = false;
            }
        }
        Integer fila = 0;
        Integer columna = 0;

        for (Pieza[] ubicacionPieza : ubicacionPiezas) {
            for (Pieza pieza : ubicacionPieza) {
                if (pieza != null && pieza.getColor().equals(colorMio)) {
                    for (Integer ubicacion : pieza.getComidaDisponible()) {
                        fila = utilidades.fila(ubicacion);
                        columna = utilidades.columna(ubicacion);
                        if (Protegido[fila][columna]) {
                            dobleProteccion[fila][columna] = true;
                        } else {
                            Protegido[fila][columna] = true;
                        }
                    }
                    for (Integer ubicacion : pieza.getMovDisponible()) {
                        fila = utilidades.fila(ubicacion);
                        columna = utilidades.columna(ubicacion);
                        if (Protegido[fila][columna]) {
                            dobleProteccion[fila][columna] = true;
                        } else {
                            Protegido[fila][columna] = true;
                        }
                    }
                }
            }
        }
        return dobleProteccion;
    }

    //Marcamos una matriz que tiene quién puede ser quien come nuestra pieza en la ubicación de nuestra pieza
    //Para el caso de doble apuntado suponemos que la pieza menos importante es quien come 
    //Estaría bueno hacer una matriz de listas de posibles atacantes para considerar todas las situaciones
    public static Pieza[][] definirLugaresApuntadosPorRival(Pieza[][] ubicacionPiezas, color colorMio) {
        Pieza[][] rivalAtaca = new Pieza[16][16];
        Integer fila = 0;
        Integer columna = 0;
        for (Pieza[] ubicacionPieza : ubicacionPiezas) {
            for (Pieza pieza : ubicacionPieza) {
                if (pieza != null && !pieza.getColor().equals(colorMio) && !pieza.getComidaDisponible().isEmpty()) {
                    for (Integer ubicacion : pieza.getComidaDisponible()) {
                        fila = utilidades.fila(ubicacion);
                        columna = utilidades.columna(ubicacion);
                        //Suponemos que el rival come siempre con la pieza más valiosa (eso se varía con el signo < de abajo)
                        if (rivalAtaca[fila][columna] == null || utilidades.ordenDeValor(rivalAtaca[fila][columna]) < utilidades.ordenDeValor(pieza)) {
                            rivalAtaca[fila][columna] = pieza;
                        }
                    }
                }
            }
        }
        return rivalAtaca;
    }

    //Marcamos que filas tienen una o más reinas nuestras
    public static ArrayList<Integer> filaConReinaPropia(Pieza[][] ubicacionPieza, color colorMio) {
        ArrayList<Integer> filas = new ArrayList<Integer>();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (ubicacionPieza[i][j] != null && ubicacionPieza[i][j].getTipo().equals(categoria.REINA) && ubicacionPieza[i][j].getColor().equals(colorMio)) {
                    filas.add(i);
                }
            }
        }
        return filas;
    }

}

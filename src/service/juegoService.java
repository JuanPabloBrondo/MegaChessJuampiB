package service;

import enumeracion.categoria;
import enumeracion.color;
import java.util.ArrayList;
import java.util.List;
import objetos.Juego;
import objetos.Pieza;
import objetos.Situacion;
import objetos.Tablero;
import utilidades.utilidades;

public class juegoService {

    ///Armamos el juego con la info recivida, mapeamos el tablero  y las piezas del juego
    //El tablero tiene matriz con letras (por fichas) y matriz por objetos pieza 
    public static Juego leerJuego(String codigoTablero, String colorMio) {
        Juego juego = new Juego();
        String[][] ubicaciones = tableroService.recibir(codigoTablero);
        Pieza[][] ubicacionespieza = tableroService.definirUbicacionPiezas(ubicaciones);
        Tablero tablero = juego.getTablero();
        tablero.setUbicaciones(ubicaciones);
        tablero.setUbicacionesPieza(ubicacionespieza);
        juego.setTablero(tablero);
        juego.setColorMio(definirColor(colorMio));
        return juego;
    }

    public static color definirColor(String colorMio) {
        if (colorMio.equals("NEGRO")) {
            return color.NEGRO;
        } else {
            return color.BLANCO;
        }
    }

    //Comienza la búsqueda de info para armar estrategias
    //Primero cargamos todos los movimientos posibles por pieza
    public static Juego armarInformacion(Juego juego) {
        Tablero tablero = juego.getTablero();
        Pieza[][] ubicacionPiezas = tablero.getUbicacionesPieza();
        ubicacionPiezas = tableroService.cargarMovimientos(ubicacionPiezas, tablero);
        tablero.setUbicacionesPieza(ubicacionPiezas);
        juego.setTablero(tablero);
        return juego;
    }

    //Ahora con la información del tablero ordenamos la información que ocurre en cada cuadrado del tablero ya con el enfoque 
    //de que color es nuestro y cual del rival
    //Lo siguiente es ponderar cada situación y esto se logra calculando el beneficio de cada situacion 
    public static Juego enfocarInformacion(Juego juego) {
        juego.setTablero(tableroService.analizarTablero(juego.getTablero(), juego.getColorMio()));
        juego.setSituacion(definirSituaciones(juego.getTablero(), juego.getColorMio()));
        return juego;
    }

    //Con el tablero analizado cargamos las situaciones con las que contamos
    public static List<Situacion> definirSituaciones(Tablero tablero, color colorMio) {
        List<Situacion> situaciones = new ArrayList<Situacion>();
        Pieza[][] ubicacionPiezas = tablero.getUbicacionesPieza();
        Integer ubicacionInicial = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (ubicacionPiezas[i][j] != null && ubicacionPiezas[i][j].getColor().equals(colorMio)) {
                    ubicacionInicial = utilidades.ubicacion(i, j);
                    situaciones = situacionService.definirSituacionesDePieza(tablero, ubicacionInicial, colorMio, situaciones);
                }
            }
        }
        return situaciones;
    }

    public static Situacion jugar(String codigoTablero, String colorMio, String turnosPendientes) {
        String myString = turnosPendientes;
        Integer turnosPen = Integer.parseInt(myString);
        Juego juego = leerJuego(codigoTablero, colorMio);  //Paso 1
        juego = armarInformacion(juego);                   //Paso 2
        juego = enfocarInformacion(juego);                 //Paso 3
        Situacion respuesta = estrategia.mejorBeneficio(juego);//Paso 4 
        printTablero(juego);
        printRespuesta(respuesta);
        return respuesta;
    }

    public static void printTablero(Juego juego) {
        Tablero tablero = juego.getTablero();
        String[][] ubicaciones = tablero.getUbicaciones();
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                String letra = ubicaciones[i][j];
                System.out.print(letra);
            }
            System.out.println("");
        }
    }

    public static void printRespuesta(Situacion respuesta) {
        System.out.println("");
        if (respuesta.getAtacante() != null) {
            System.out.println("Atacante: " + respuesta.getAtacante().getTipo() + "  " + respuesta.getAtacante().getColor());
            System.out.println("En riesgo: " + respuesta.getEnRiesgo() + "  Se mueve y queda expuesto: " + respuesta.getMuereDespues());
            System.out.println("Si no se mueve y lo matan hay quien lo vengue: " + respuesta.getCustodiado());
            System.out.println("Si avanza hay otro que lo protege: " + respuesta.getDobleProteccion());
            System.out.println("Ubicacion inicial: " + respuesta.getUbicacionInicial() + " Ubicacion Final: " + respuesta.getUbicacionFinal());
        }
        if (respuesta.getVictima() != null) {
            System.out.println("Victima: " + respuesta.getVictima().getColor() + " " + respuesta.getVictima().getTipo());
        }
        if (respuesta.getTapado() != null && respuesta.getAtacante().getTipo().equals(categoria.REINA)) {

            System.out.println("Tapado: " + respuesta.getTapado().getTipo() + " " + respuesta.getTapado().getColor());

        }
        System.out.println("El beneficio es: " + respuesta.getBeneficio());
    }

}

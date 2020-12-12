package service;

import enumeracion.color;
import objetos.Juego;
import objetos.Pieza;
import objetos.Tablero;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class juegoServiceTest {

    @Test
    public void testLeerJuego() {
        System.out.println("Leer Juego 01");
        String codigoTablero = "rrhhbbqqkkbbhhrrrrhhbbqqkkbbhhrr p  p pp   p    p pppp p           p p  ppp  pppppp   p pppppppp    Q                             Q                             P  P PPPP P   PP           P P           P  P   PPPPPPPPPPPPPPPPRRHHBBQQKKBBHHRRRRHHBBQQKKBBHHRR";
        String colorMio = "NEGRO";

        Juego expResult = new Juego();
        Tablero tablero = expResult.getTablero();
        tablero.setUbicaciones(tableroService.recibir(codigoTablero));
        tablero.setUbicacionesPieza(tableroService.definirUbicacionPiezas(tablero.getUbicaciones()));
        expResult.setTablero(tablero);
        expResult.setColorMio(juegoService.definirColor(colorMio));

        Juego result = juegoService.leerJuego(codigoTablero, colorMio);
        assertEquals(expResult.getTablero().getUbicaciones(), result.getTablero().getUbicaciones());
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (result.getTablero().getUbicacionesPieza()[i][j] != null) {
                    assertEquals(expResult.getTablero().getUbicacionesPieza()[i][j].toString(), result.getTablero().getUbicacionesPieza()[i][j].toString());
                }
            }
        }
        assertEquals(expResult.getColorMio(), result.getColorMio());

    }
//Muchos test se evaluan en base a comparar el toString de 2 objetos ya que comparar los objetos en sí da  
    //siempre distinto (creo que debido a que se guardan en espacios de memoria distintos entonces nunca coinciden al 100%)

    @Test
    public void testDefinirColor1() {
        System.out.println("Definir color (Negro)");
        color expResult = color.NEGRO;
        color result = juegoService.definirColor("NEGRO");
        assertEquals(expResult, result);
    }

    @Test
    public void testDefinirColor2() {
        System.out.println("Definir color (Blanco)");
        color expResult = color.BLANCO;
        color result = juegoService.definirColor("BLANCO");
        assertEquals(expResult, result);
    }

    @Test
    public void testArmarInformacion() {
        String codigoTablero = "rrhhbbqqkkbbhhrrrrhhbbqqkkbbhhrr p  p pp   p    p pppp p           p p  ppp  pppppp   p pppppppp    Q                             Q                             P  P PPPP P   PP           P P           P  P   PPPPPPPPPPPPPPPPRRHHBBQQKKBBHHRRRRHHBBQQKKBBHHRR";
        String colorMio = "NEGRO";

        Juego expResult = new Juego();
        Tablero tablero = expResult.getTablero();
        tablero.setUbicaciones(tableroService.recibir(codigoTablero));
        Pieza[][] piezas = tableroService.definirUbicacionPiezas(tablero.getUbicaciones());
        tablero.setUbicacionesPieza(piezas);
        expResult.setTablero(tablero);
        expResult.setColorMio(juegoService.definirColor(colorMio));

        Juego result = juegoService.armarInformacion(expResult);

        piezas = tableroService.cargarMovimientos(piezas, expResult.getTablero());

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (result.getTablero().getUbicacionesPieza()[i][j] != null) {
                    assertEquals(piezas[i][j].toString(), result.getTablero().getUbicacionesPieza()[i][j].toString());
                }
            }
        }
    }
}

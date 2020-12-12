/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import enumeracion.categoria;
import enumeracion.color;
import objetos.Juego;
import objetos.Pieza;
import objetos.Tablero;
import org.junit.Test;
import static org.junit.Assert.*;

public class tableroServiceTest {

    @Test
    public void testRecibir() {
        System.out.println("recibir");
        String codigoTablero = "rrhhbbqqkkbbhhrr"
                + "rrhh bqqkkbbhhrr"
                + "pppppppppppppppp"
                + "pppppppppppppppp"
                + "                "
                + "            R   "
                + "  b             "
                + "                "
                + "                "
                + "                "
                + "                "
                + "        P       "
                + "PPPPPPPP PPPPPPP"
                + "PPPPPPPPPPPPPPPP"
                + "R HHBBQQKKBBHHRR"
                + "RRHHBBQQKKBBHHRR";
        String[][] expResult = new String[16][16];
        int cont = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                expResult[i][j] = codigoTablero.substring(cont, cont + 1);
                cont++;
            }
        }
        String[][] result = tableroService.recibir(codigoTablero);
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testDefinirUbicacionPiezas() {
        System.out.println("DefinirUbicacionPiezas");
        String codigoTablero = "                "
                + "p               "
                + " b              "
                + "  h             "
                + "   r            "
                + "    q           "
                + "     k          "
                + "      P         "
                + "       B        "
                + "        H       "
                + "         R      "
                + "          Q     "
                + "           K    "
                + "                "
                + "                "
                + "                ";
        Pieza[][] expResult = new Pieza[16][16];
        expResult[1][0] = piezaService.mapearPieza("p", 16);
        expResult[2][1] = piezaService.mapearPieza("b", 16 * 2 + 1);
        expResult[3][2] = piezaService.mapearPieza("h", 16 * 3 + 2);
        expResult[4][3] = piezaService.mapearPieza("r", 16 * 4 + 3);
        expResult[5][4] = piezaService.mapearPieza("q", 16 * 5 + 4);
        expResult[6][5] = piezaService.mapearPieza("k", 16 * 6 + 5);
        expResult[7][6] = piezaService.mapearPieza("P", 16 * 7 + 6);
        expResult[8][7] = piezaService.mapearPieza("B", 16 * 8 + 7);
        expResult[9][8] = piezaService.mapearPieza("H", 16 * 9 + 8);
        expResult[10][9] = piezaService.mapearPieza("R", 16 * 10 + 9);
        expResult[11][10] = piezaService.mapearPieza("Q", 16 * 11 + 10);
        expResult[12][11] = piezaService.mapearPieza("K", 16 * 12 + 11);

        String[][] ubicacion = tableroService.recibir(codigoTablero);
        Pieza[][] result = tableroService.definirUbicacionPiezas(ubicacion);

        //Tuve que comparar resultados así porque con el assert me comparaba un número hash que se crea en cada pieza y nunca coincidía aunque todos los atributos fueran los mismos
        for (int i = 0; i < 12; i++) {
            if (expResult[i + 1][i].getColor().equals(result[i + 1][i].getColor()) && expResult[i + 1][i].getTipo().equals(result[i + 1][i].getTipo()) && expResult[i + 1][i].getUbicacion().equals(result[i + 1][i].getUbicacion())) {

            } else {
                fail("No coinciden las matrices");
            }
        }
        //assertArrayEquals(expResult, result);

    }

    @Test
    public void testCargarMovimientos() {
        System.out.println("Cargar Movimientos");
        String codigoTablero = "rrhhbbqqkkbbhhrrrrhhbbqqkkbbhhrr p  p pp   p    p pppp p           p p  ppp  pppppp   p pppppppp    Q                             Q                             P  P PPPP P   PP           P P           P  P   PPPPPPPPPPPPPPPPRRHHBBQQKKBBHHRRRRHHBBQQKKBBHHRR";
        String colorMio = "NEGRO";

        Juego expResult = new Juego();
        Tablero tablero = expResult.getTablero();
        tablero.setUbicaciones(tableroService.recibir(codigoTablero));
        tablero.setUbicacionesPieza(tableroService.definirUbicacionPiezas(tablero.getUbicaciones()));
        expResult.setTablero(tablero);
        expResult.setColorMio(juegoService.definirColor(colorMio));

        Pieza[][] result = tableroService.cargarMovimientos(expResult.getTablero().getUbicacionesPieza(), expResult.getTablero());

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (expResult.getTablero().getUbicacionesPieza()[i][j] != null) {
                    Pieza pieza = expResult.getTablero().getUbicacionesPieza()[i][j];
                    pieza = piezaService.movimientosDisponibles(pieza, tablero.getUbicacionesPieza());
                    assertEquals(pieza.toString(), result[i][j].toString());
                }
            }
        }
    }

    @Test
    public void testDefinirLugaresPeligrosos1() {
        System.out.println("Definir Lugares Peligrosos)");
        String codigoTablero = "                "
                + "                "
                + "                "
                + "                "
                + "      P         "//fila 4 y col 6
                + "     Q          "//fila 5 y col 5
                + "      P         "//fila 6 y col 6
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                ";
        String colorMio = "NEGRO";

        Juego juego = new Juego();
        juego = juegoService.leerJuego(codigoTablero, colorMio);
        juego = juegoService.armarInformacion(juego);
        boolean[][] result = tableroService.definirLugaresPeligrosos(juego.getTablero().getUbicacionesPieza(), juego.getColorMio());

        boolean[][] expResult = new boolean[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                expResult[i][j] = false;
            }
        }
        expResult[5][5] = true;
        expResult[4][6] = true;
        expResult[6][6] = true;

        expResult[3][5] = true;
        expResult[3][7] = true;
        expResult[5][5] = true;
        expResult[5][7] = true;

        expResult[4][5] = true;
        expResult[3][5] = true;
        expResult[2][5] = true;
        expResult[1][5] = true;
        expResult[0][5] = true;

        expResult[6][5] = true;
        expResult[7][5] = true;
        expResult[8][5] = true;
        expResult[9][5] = true;
        expResult[10][5] = true;
        expResult[11][5] = true;
        expResult[12][5] = true;
        expResult[13][5] = true;
        expResult[14][5] = true;
        expResult[15][5] = true;

        expResult[5][0] = true;
        expResult[5][1] = true;
        expResult[5][2] = true;
        expResult[5][3] = true;
        expResult[5][4] = true;
        expResult[5][6] = true;
        expResult[5][7] = true;
        expResult[5][8] = true;
        expResult[5][9] = true;
        expResult[5][10] = true;
        expResult[5][11] = true;
        expResult[5][12] = true;
        expResult[5][13] = true;
        expResult[5][14] = true;
        expResult[5][15] = true;

        expResult[4][4] = true;
        expResult[3][3] = true;
        expResult[2][2] = true;
        expResult[1][1] = true;
        expResult[0][0] = true;

        expResult[6][4] = true;
        expResult[7][3] = true;
        expResult[8][2] = true;
        expResult[9][1] = true;
        expResult[10][0] = true;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                assertEquals(expResult[i][j], result[i][j]);
            }
        }
    }

    @Test
    public void testDefinirLugaresYoProtejo() {
        System.out.println("Definir Lugares Que Yo Protejo)");
        String codigoTablero = "                "
                + "                "
                + "                "
                + "                "
                + "      p         "//fila 4 y col 6
                + "     q          "//fila 5 y col 5
                + "      p         "//fila 6 y col 6
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                ";
        String colorMio = "NEGRO";

        Juego juego = new Juego();
        juego = juegoService.leerJuego(codigoTablero, colorMio);
        juego = juegoService.armarInformacion(juego);
        boolean[][] result = tableroService.definirLugaresYoProtejo(juego.getTablero().getUbicacionesPieza(), juego.getColorMio());

        boolean[][] expResult = new boolean[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                expResult[i][j] = false;
            }
        }
        expResult[4][6] = true;
        expResult[5][5] = true;
        expResult[6][6] = true;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                assertEquals(expResult[i][j], result[i][j]);
            }
        }
    }

    @Test
    public void testDefinirLugaresProtegidos() {
        System.out.println("Definir Lugares Que Yo Apunto con 2 piezas)");
        String codigoTablero = "                "
                + "                "
                + "                "
                + "                "
                + "    k           "//fila 4 y col 4
                + "     q K        "//fila 5 y col 5 || col 7
                + "      k         "//fila 6 y col 6
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                ";
        String colorMio = "NEGRO";

        Juego juego = new Juego();
        juego = juegoService.leerJuego(codigoTablero, colorMio);
        juego = juegoService.armarInformacion(juego);
        boolean[][] result = tableroService.definirLugaresProtegidos(juego.getTablero().getUbicacionesPieza(), juego.getColorMio());

        boolean[][] expResult = new boolean[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                expResult[i][j] = false;
            }
        }
        expResult[3][5] = true;
        expResult[5][3] = true;
        expResult[4][5] = true;
        expResult[5][7] = true;
        expResult[5][4] = true;
        expResult[5][6] = true;
        expResult[6][5] = true;
        expResult[7][5] = true;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                assertEquals(expResult[i][j], result[i][j]);
            }
        }
    }

    @Test
    public void testDefinirLugaresApuntadosPorRival() {
        System.out.println("Definir Lugares Que Apunta el rival");
        String codigoTablero = "                "
                + "R               "//fila 1 col 0
                + "p               "//fila 2 col 0
                + "                "
                + "                "
                + "      qK        "//fila 5 y col 6 || col 7
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "      Q        p"//fila 11 col 6 || col 15
                + "                "
                + "                "
                + "                "
                + "                ";
        String colorMio = "NEGRO";

        Juego juego = new Juego();
        juego = juegoService.leerJuego(codigoTablero, colorMio);
        juego = juegoService.armarInformacion(juego);
        Pieza[][] result = tableroService.definirLugaresApuntadosPorRival(juego.getTablero().getUbicacionesPieza(), juego.getColorMio());
        Pieza[][] expResult = new Pieza[16][16];
        Pieza p1 = new Pieza();
        Pieza p2 = new Pieza();
        Pieza p3 = new Pieza();
        p1.setColor(color.BLANCO);
        p2.setColor(color.BLANCO);
        p3.setColor(color.BLANCO);
        p1.setTipo(categoria.TORRE);
        p2.setTipo(categoria.REY);
        p3.setTipo(categoria.REINA);
        expResult[2][0] = p1;
        expResult[5][6] = p2;
        expResult[11][15] = p3;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (result[i][j] != null) {
                    if (result[i][j].getColor().equals(expResult[i][j].getColor()) && result[i][j].getTipo().equals(expResult[i][j].getTipo())) {
                    } else {
                        fail("Falló el test");
                    }
                }
            }
        }

    }

    @Test
    public void testAnalizarTablero() {
        System.out.println("Analizar Tablero");
        String codigoTablero = "                "
                + "R               "//fila 1 col 0
                + "p               "//fila 2 col 0
                + "                "
                + "                "
                + "      qK        "//fila 5 y col 6 || col 7
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "      Q        p"//fila 11 col 6 || col 15
                + "                "
                + "                "
                + "                "
                + "                ";
        String colorMio = "NEGRO";

        Juego juego = new Juego();
        juego = juegoService.leerJuego(codigoTablero, colorMio);
        juego = juegoService.armarInformacion(juego);
        Tablero result = tableroService.analizarTablero(juego.getTablero(), juego.getColorMio());
        if (result.getRivalProtege() != null && result.getYoProtejo() != null && result.getDobleProteccion() != null && result.getRivalPuedeComer() != null) {
        } else {
            fail("Falló el test");
        }
    }
}

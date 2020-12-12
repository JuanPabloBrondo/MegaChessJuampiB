package service;

import enumeracion.categoria;
import enumeracion.color;
import java.util.ArrayList;
import java.util.List;
import objetos.Pieza;
import objetos.Tablero;
import org.junit.Test;
import static org.junit.Assert.*;
import utilidades.utilidades;

public class piezaServiceTest {

    @Test
    public void testMapearPieza1() {
        System.out.println("mapearPieza 1");
        String letra = "Q";
        Integer ubicacion = 43;
        Pieza expResult = new Pieza();
        expResult.setColor(color.BLANCO);
        expResult.setTipo(categoria.REINA);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza2() {
        System.out.println("mapearPieza 2");
        String letra = "K";
        Integer ubicacion = 254;
        Pieza expResult = new Pieza();
        expResult.setColor(color.BLANCO);
        expResult.setTipo(categoria.REY);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza3() {
        System.out.println("mapearPieza 3");
        String letra = "R";
        Integer ubicacion = 43;
        Pieza expResult = new Pieza();
        expResult.setColor(color.BLANCO);
        expResult.setTipo(categoria.TORRE);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza4() {
        System.out.println("mapearPieza 4");
        String letra = "H";
        Integer ubicacion = 3;
        Pieza expResult = new Pieza();
        expResult.setColor(color.BLANCO);
        expResult.setTipo(categoria.CABALLO);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza5() {
        System.out.println("mapearPieza 5");
        String letra = "B";
        Integer ubicacion = 100;
        Pieza expResult = new Pieza();
        expResult.setColor(color.BLANCO);
        expResult.setTipo(categoria.ALFIL);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza6() {
        System.out.println("mapearPieza 6");
        String letra = "P";
        Integer ubicacion = 143;
        Pieza expResult = new Pieza();
        expResult.setColor(color.BLANCO);
        expResult.setTipo(categoria.PEON);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza7() {
        System.out.println("mapearPieza 7");
        String letra = "q";
        Integer ubicacion = 23;
        Pieza expResult = new Pieza();
        expResult.setColor(color.NEGRO);
        expResult.setTipo(categoria.REINA);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza8() {
        System.out.println("mapearPieza 8");
        String letra = "k";
        Integer ubicacion = 24;
        Pieza expResult = new Pieza();
        expResult.setColor(color.NEGRO);
        expResult.setTipo(categoria.REY);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza9() {
        System.out.println("mapearPieza 9");
        String letra = "r";
        Integer ubicacion = 0;
        Pieza expResult = new Pieza();
        expResult.setColor(color.NEGRO);
        expResult.setTipo(categoria.TORRE);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza10() {
        System.out.println("mapearPieza 10");
        String letra = "h";
        Integer ubicacion = 38;
        Pieza expResult = new Pieza();
        expResult.setColor(color.NEGRO);
        expResult.setTipo(categoria.CABALLO);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza11() {
        System.out.println("mapearPieza 11");
        String letra = "b";
        Integer ubicacion = 140;
        Pieza expResult = new Pieza();
        expResult.setColor(color.NEGRO);
        expResult.setTipo(categoria.ALFIL);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza12() {
        System.out.println("mapearPieza 12");
        String letra = "p";
        Integer ubicacion = 43;
        Pieza expResult = new Pieza();
        expResult.setColor(color.NEGRO);
        expResult.setTipo(categoria.PEON);
        expResult.setUbicacion(ubicacion);
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor().equals(expResult.getColor()) && result.getUbicacion().equals(expResult.getUbicacion()) && result.getTipo().equals(expResult.getTipo())) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testMapearPieza13() {
        System.out.println("mapearPieza 13");
        String letra = "j";
        Integer ubicacion = 38;
        Pieza result = piezaService.mapearPieza(letra, ubicacion);
        if (result.getColor() == null && result.getTipo() == null) {

        } else {
            fail("Falló el test");

        }
        //  assertEquals(expResult, result);
    }

    @Test
    public void testInfoPeonBlanco1() {
        System.out.println("Info Peon Blanco");

        String codigoTablero = "rrhhbbqqkkbbhhrr"
                + "rrhhbbqqkkbbhhrr"
                + " ppppppppppppppp"
                + " pppppppppppp pp"
                + "                "
                + "                "
                + "p               "
                + "q            qQ "
                + "            P   " //fila 8 col 12  peon 1
                + "                "
                + "     k          "
                + "      P         " //fila 11 col 6   peon 2
                + " P              " // fila 12 col 1   peon 3
                + "PPPPPPPPPPPPPPPP"//fila 13 col 0   peon 4
                + "RRHHBBQQKKBBHHRR"
                + "RRHHBBQQKKBBHHRR";
        String[][] ubicacion = tableroService.recibir(codigoTablero);
        Pieza[][] ubicacionPieza = tableroService.definirUbicacionPiezas(ubicacion);
        Pieza peon1 = piezaService.mapearPieza("P", utilidades.ubicacion(8, 12));
        Pieza peon2 = piezaService.mapearPieza("P", utilidades.ubicacion(11, 6));
        Pieza peon3 = piezaService.mapearPieza("P", utilidades.ubicacion(12, 1));
        Pieza peon4 = piezaService.mapearPieza("P", utilidades.ubicacion(13, 0));
        Pieza expResult1 = piezaService.infoPeon(peon1, ubicacionPieza);
        Pieza expResult2 = piezaService.infoPeon(peon2, ubicacionPieza);
        Pieza expResult3 = piezaService.infoPeon(peon3, ubicacionPieza);
        Pieza expResult4 = piezaService.infoPeon(peon4, ubicacionPieza);

        ArrayList<Integer> movDisponiblePeon1 = new ArrayList<Integer>();
        movDisponiblePeon1.add(utilidades.ubicacion(7, 12));
        ArrayList<Integer> comidaDisponiblePeon1 = new ArrayList<Integer>();
        comidaDisponiblePeon1.add(utilidades.ubicacion(7, 13));
        List<Integer> lugaresProtegidosPeon1 = new ArrayList<Integer>();
        List<Integer> crucePeonPeon1 = new ArrayList<Integer>();
        crucePeonPeon1.add(utilidades.ubicacion(7, 11));
        if (movDisponiblePeon1.equals(expResult1.getMovDisponible()) && comidaDisponiblePeon1.equals(expResult1.getComidaDisponible()) && lugaresProtegidosPeon1.equals(expResult1.getLugaresProtegidos()) && crucePeonPeon1.equals(expResult1.getCrucePeon())) {
        } else {
            fail("Falló el peon 1");

        }

        ArrayList<Integer> movDisponiblePeon2 = new ArrayList<Integer>();
        movDisponiblePeon2.add(utilidades.ubicacion(10, 6));
        ArrayList<Integer> comidaDisponiblePeon2 = new ArrayList<Integer>();
        comidaDisponiblePeon2.add(utilidades.ubicacion(10, 5));
        List<Integer> lugaresProtegidosPeon2 = new ArrayList<Integer>();
        List<Integer> crucePeonPeon2 = new ArrayList<Integer>();
        crucePeonPeon2.add(utilidades.ubicacion(10, 7));
        if (movDisponiblePeon2.equals(expResult2.getMovDisponible()) && comidaDisponiblePeon2.equals(expResult2.getComidaDisponible()) && lugaresProtegidosPeon2.equals(expResult2.getLugaresProtegidos()) && crucePeonPeon2.equals(expResult2.getCrucePeon())) {
        } else {
            fail("Falló el peon 2");
        }

        ArrayList<Integer> movDisponiblePeon3 = new ArrayList<Integer>();
        movDisponiblePeon3.add(utilidades.ubicacion(11, 1));
        movDisponiblePeon3.add(utilidades.ubicacion(10, 1));
        ArrayList<Integer> comidaDisponiblePeon3 = new ArrayList<Integer>();
        List<Integer> lugaresProtegidosPeon3 = new ArrayList<Integer>();
        List<Integer> crucePeonPeon3 = new ArrayList<Integer>();
        crucePeonPeon3.add(utilidades.ubicacion(11, 0));
        crucePeonPeon3.add(utilidades.ubicacion(11, 2));
        if (movDisponiblePeon3.equals(expResult3.getMovDisponible()) && comidaDisponiblePeon3.equals(expResult3.getComidaDisponible()) && lugaresProtegidosPeon3.equals(expResult3.getLugaresProtegidos()) && crucePeonPeon3.equals(expResult3.getCrucePeon())) {
        } else {
            fail("Falló el peon 3");
        }

        ArrayList<Integer> movDisponiblePeon4 = new ArrayList<Integer>();
        movDisponiblePeon4.add(utilidades.ubicacion(12, 0));
        movDisponiblePeon4.add(utilidades.ubicacion(11, 0));
        ArrayList<Integer> comidaDisponiblePeon4 = new ArrayList<Integer>();
        List<Integer> lugaresProtegidosPeon4 = new ArrayList<Integer>();
        lugaresProtegidosPeon4.add(utilidades.ubicacion(12, 1));
        List<Integer> crucePeonPeon4 = new ArrayList<Integer>();
        if (movDisponiblePeon4.equals(expResult4.getMovDisponible()) && comidaDisponiblePeon4.equals(expResult4.getComidaDisponible()) && lugaresProtegidosPeon4.equals(expResult4.getLugaresProtegidos()) && crucePeonPeon4.equals(expResult4.getCrucePeon())) {
        } else {
            fail("Falló el peon 4");
        }
    }

    @Test
    public void testInfoPeonNegro1() {
        System.out.println("Info Peon Negro");
        String codigoTablero = "rrhhbbqqkkbbhhrr"
                + "rrhhbbqqkkbbhhrr"
                + " ppppppppppppppp"//fila 2 columna 13 peon 1
                + " pppppppppppp pp"// fila 3 columna 7 peon 2
                + "      P P       "
                + "                "
                + "p               "//fila 6 columna 0 peon 3
                + "q            qQ "
                + "            P   "
                + "                "
                + "     k          "
                + "      P         "
                + " P              "
                + "PPPPPPPPPPPPPPPP"
                + "RRHHBBQQKKBBHHRR"
                + "RRHHBBQQKKBBHHRR";
        String[][] ubicacion = tableroService.recibir(codigoTablero);
        Pieza[][] ubicacionPieza = tableroService.definirUbicacionPiezas(ubicacion);
        Pieza peon1 = piezaService.mapearPieza("p", utilidades.ubicacion(2, 13));
        Pieza peon2 = piezaService.mapearPieza("p", utilidades.ubicacion(3, 7));
        Pieza peon3 = piezaService.mapearPieza("p", utilidades.ubicacion(6, 0));
        Pieza expResult1 = piezaService.infoPeon(peon1, ubicacionPieza);
        Pieza expResult2 = piezaService.infoPeon(peon2, ubicacionPieza);
        Pieza expResult3 = piezaService.infoPeon(peon3, ubicacionPieza);

        ArrayList<Integer> movDisponiblePeon1 = new ArrayList<Integer>();
        movDisponiblePeon1.add(utilidades.ubicacion(3, 13));
        movDisponiblePeon1.add(utilidades.ubicacion(4, 13));
        ArrayList<Integer> comidaDisponiblePeon1 = new ArrayList<Integer>();
        List<Integer> lugaresProtegidosPeon1 = new ArrayList<Integer>();
        lugaresProtegidosPeon1.add(utilidades.ubicacion(3, 12));
        lugaresProtegidosPeon1.add(utilidades.ubicacion(3, 14));
        List<Integer> crucePeonPeon1 = new ArrayList<Integer>();
        if (movDisponiblePeon1.equals(expResult1.getMovDisponible()) && comidaDisponiblePeon1.equals(expResult1.getComidaDisponible()) && lugaresProtegidosPeon1.equals(expResult1.getLugaresProtegidos()) && crucePeonPeon1.equals(expResult1.getCrucePeon())) {
        } else {
            fail("Falló el peon 1");

        }

        ArrayList<Integer> movDisponiblePeon2 = new ArrayList<Integer>();
        movDisponiblePeon2.add(utilidades.ubicacion(4, 7));
        movDisponiblePeon2.add(utilidades.ubicacion(5, 7));
        ArrayList<Integer> comidaDisponiblePeon2 = new ArrayList<Integer>();
        comidaDisponiblePeon2.add(utilidades.ubicacion(4, 6));
        comidaDisponiblePeon2.add(utilidades.ubicacion(4, 8));
        List<Integer> lugaresProtegidosPeon2 = new ArrayList<Integer>();
        List<Integer> crucePeonPeon2 = new ArrayList<Integer>();
        if (movDisponiblePeon2.equals(expResult2.getMovDisponible()) && comidaDisponiblePeon2.equals(expResult2.getComidaDisponible()) && lugaresProtegidosPeon2.equals(expResult2.getLugaresProtegidos()) && crucePeonPeon2.equals(expResult2.getCrucePeon())) {
        } else {
            fail("Falló el peon 2");
        }

        ArrayList<Integer> movDisponiblePeon3 = new ArrayList<Integer>();
        ArrayList<Integer> comidaDisponiblePeon3 = new ArrayList<Integer>();
        List<Integer> lugaresProtegidosPeon3 = new ArrayList<Integer>();
        List<Integer> crucePeonPeon3 = new ArrayList<Integer>();
        crucePeonPeon3.add(utilidades.ubicacion(7, 1));
        if (movDisponiblePeon3.equals(expResult3.getMovDisponible()) && comidaDisponiblePeon3.equals(expResult3.getComidaDisponible()) && lugaresProtegidosPeon3.equals(expResult3.getLugaresProtegidos()) && crucePeonPeon3.equals(expResult3.getCrucePeon())) {
        } else {
            fail("Falló el peon 3");
        }

    }

    @Test
    public void testInfoTorre1() {
        System.out.println("Info Torre");
        String codigoTablero = "rrhhbbqqkkbbhhrr"
                + "rrhhbbqqkkbbhhrr"//fila 1 col 0
                + " ppppppppppppppp"
                + " pppppppppppp pp"
                + "      P P       "
                + "                "
                + "                "
                + "q     R      qQ "// fila 7 col 6
                + " P          P   "
                + "                "
                + "     k          "
                + "      P         "
                + "                "
                + "PPPPPPPPPPPPPPPP"
                + "RRHHBBQQKKBBHHRR"
                + "RRHHBBQQKKBBHHRR";
        String[][] ubicacion = tableroService.recibir(codigoTablero);
        Pieza[][] ubicacionPieza = tableroService.definirUbicacionPiezas(ubicacion);
        Pieza torreBlanca = piezaService.mapearPieza("R", utilidades.ubicacion(7, 6));
        Pieza torreNegra = piezaService.mapearPieza("r", utilidades.ubicacion(1, 0));
        Pieza expResult1 = piezaService.infoTorre(torreBlanca, ubicacionPieza);
        Pieza expResult2 = piezaService.infoTorre(torreNegra, ubicacionPieza);

        ArrayList<Integer> movDisponibleTorreB = new ArrayList<Integer>();
        movDisponibleTorreB.add(utilidades.ubicacion(6, 6));
        movDisponibleTorreB.add(utilidades.ubicacion(5, 6));
        movDisponibleTorreB.add(utilidades.ubicacion(8, 6));
        movDisponibleTorreB.add(utilidades.ubicacion(9, 6));
        movDisponibleTorreB.add(utilidades.ubicacion(10, 6));
        movDisponibleTorreB.add(utilidades.ubicacion(7, 5));
        movDisponibleTorreB.add(utilidades.ubicacion(7, 4));
        movDisponibleTorreB.add(utilidades.ubicacion(7, 3));
        movDisponibleTorreB.add(utilidades.ubicacion(7, 2));
        movDisponibleTorreB.add(utilidades.ubicacion(7, 1));
        movDisponibleTorreB.add(utilidades.ubicacion(7, 7));
        movDisponibleTorreB.add(utilidades.ubicacion(7, 8));
        movDisponibleTorreB.add(utilidades.ubicacion(7, 9));
        movDisponibleTorreB.add(utilidades.ubicacion(7, 10));
        movDisponibleTorreB.add(utilidades.ubicacion(7, 11));
        movDisponibleTorreB.add(utilidades.ubicacion(7, 12));
        ArrayList<Integer> comidaDisponibleTorreB = new ArrayList<Integer>();
        comidaDisponibleTorreB.add(utilidades.ubicacion(7, 0));
        comidaDisponibleTorreB.add(utilidades.ubicacion(7, 13));
        List<Integer> lugaresProtegidosTorreB = new ArrayList<Integer>();
        lugaresProtegidosTorreB.add(utilidades.ubicacion(4, 6));
        lugaresProtegidosTorreB.add(utilidades.ubicacion(11, 6));
        if (movDisponibleTorreB.equals(expResult1.getMovDisponible()) && comidaDisponibleTorreB.equals(expResult1.getComidaDisponible()) && lugaresProtegidosTorreB.equals(expResult1.getLugaresProtegidos())) {
        } else {
            fail("Falló la torre Blanca");

        }

        ArrayList<Integer> movDisponibleTorreN = new ArrayList<Integer>();
        movDisponibleTorreN.add(utilidades.ubicacion(2, 0));
        movDisponibleTorreN.add(utilidades.ubicacion(3, 0));
        movDisponibleTorreN.add(utilidades.ubicacion(4, 0));
        movDisponibleTorreN.add(utilidades.ubicacion(5, 0));
        movDisponibleTorreN.add(utilidades.ubicacion(6, 0));
        ArrayList<Integer> comidaDisponibleTorreN = new ArrayList<Integer>();
        List<Integer> lugaresProtegidosTorreN = new ArrayList<Integer>();
        lugaresProtegidosTorreN.add(utilidades.ubicacion(0, 0));
        lugaresProtegidosTorreN.add(utilidades.ubicacion(7, 0));
        lugaresProtegidosTorreN.add(utilidades.ubicacion(1, 1));

        if (movDisponibleTorreN.equals(expResult2.getMovDisponible()) && comidaDisponibleTorreN.equals(expResult2.getComidaDisponible()) && lugaresProtegidosTorreN.equals(expResult2.getLugaresProtegidos())) {
        } else {
            fail("Falló la torre Negra");
        }
    }

    @Test
    public void testInfoAlfil1() {
        System.out.println("Info Alfil");
        String codigoTablero = "rrhhbbqqkkbbhhrr"
                + "rrhhbbqqkkbbhhrr"
                + " ppppppppppppppp"
                + " pppppppppppp pp"
                + "      P P       "
                + "                "
                + "       B        "//fila 6 col 7
                + "q     R      qQ "
                + " P          P   "
                + "                "
                + "     k          "
                + "      P         "
                + "           b    "// fila 12 col 11
                + "PPPPPPPPPPPPPPPP"
                + "RRHHBBQQKKBBHHRR"
                + "RRHHBBQQKKBBHHRR";
        String[][] ubicacion = tableroService.recibir(codigoTablero);
        Pieza[][] ubicacionPieza = tableroService.definirUbicacionPiezas(ubicacion);
        Pieza alfilBlanco = piezaService.mapearPieza("B", utilidades.ubicacion(6, 7));
        Pieza alfilNegro = piezaService.mapearPieza("b", utilidades.ubicacion(12, 11));
        Pieza expResult1 = piezaService.infoAlfil(alfilBlanco, ubicacionPieza);
        Pieza expResult2 = piezaService.infoAlfil(alfilNegro, ubicacionPieza);

        ArrayList<Integer> movDisponibleAlfilB = new ArrayList<Integer>();
        movDisponibleAlfilB.add(utilidades.ubicacion(5, 6));
        movDisponibleAlfilB.add(utilidades.ubicacion(4, 5));
        movDisponibleAlfilB.add(utilidades.ubicacion(7, 8));
        movDisponibleAlfilB.add(utilidades.ubicacion(8, 9));
        movDisponibleAlfilB.add(utilidades.ubicacion(9, 10));
        movDisponibleAlfilB.add(utilidades.ubicacion(10, 11));
        movDisponibleAlfilB.add(utilidades.ubicacion(11, 12));
        movDisponibleAlfilB.add(utilidades.ubicacion(12, 13));
        movDisponibleAlfilB.add(utilidades.ubicacion(5, 8));
        movDisponibleAlfilB.add(utilidades.ubicacion(4, 9));
        ArrayList<Integer> comidaDisponibleAlfilB = new ArrayList<Integer>();
        comidaDisponibleAlfilB.add(utilidades.ubicacion(3, 4));
        comidaDisponibleAlfilB.add(utilidades.ubicacion(3, 10));
        List<Integer> lugaresProtegidosAlfilB = new ArrayList<Integer>();
        lugaresProtegidosAlfilB.add(utilidades.ubicacion(13, 14));
        lugaresProtegidosAlfilB.add(utilidades.ubicacion(7, 6));
        if (movDisponibleAlfilB.equals(expResult1.getMovDisponible()) && comidaDisponibleAlfilB.equals(expResult1.getComidaDisponible()) && lugaresProtegidosAlfilB.equals(expResult1.getLugaresProtegidos())) {
        } else {
            fail("Falló el alfil Blanco");

        }
        ArrayList<Integer> movDisponibleAlfilN = new ArrayList<Integer>();
        movDisponibleAlfilN.add(utilidades.ubicacion(11, 10));
        movDisponibleAlfilN.add(utilidades.ubicacion(10, 9));
        movDisponibleAlfilN.add(utilidades.ubicacion(9, 8));
        movDisponibleAlfilN.add(utilidades.ubicacion(8, 7));
        movDisponibleAlfilN.add(utilidades.ubicacion(11, 12));
        movDisponibleAlfilN.add(utilidades.ubicacion(10, 13));
        movDisponibleAlfilN.add(utilidades.ubicacion(9, 14));
        movDisponibleAlfilN.add(utilidades.ubicacion(8, 15));
        ArrayList<Integer> comidaDisponibleAlfilN = new ArrayList<Integer>();
        comidaDisponibleAlfilN.add(utilidades.ubicacion(7, 6));
        comidaDisponibleAlfilN.add(utilidades.ubicacion(13, 12));
        comidaDisponibleAlfilN.add(utilidades.ubicacion(13, 10));
        List<Integer> lugaresProtegidosAlfilN = new ArrayList<Integer>();
        if (movDisponibleAlfilN.equals(expResult2.getMovDisponible()) && comidaDisponibleAlfilN.equals(expResult2.getComidaDisponible()) && lugaresProtegidosAlfilN.equals(expResult2.getLugaresProtegidos())) {
        } else {
            fail("Falló el alfil Negro");

        }
    }

    @Test
    public void testInfoReina1() {
        System.out.println("Info Reina");
        String codigoTablero = "rrhhbbqqkkbbhhrr"
                + "rrhhbbqqkkbbhhrr"
                + " ppppppppppppppp"
                + " pppppppppppp pp"
                + "      P P       "
                + "                "
                + "       B        "
                + "      R         "
                + " P          P   "
                + "                "
                + "     kQ         "//fila 10 col 6
                + "      P         "
                + "           b    "
                + "PPPPPPPPPPPPPPPP"
                + "RRHHBB qKKBBHHRR"//fila 14 col 7
                + "RRHHBBQ KKBBHHRR";
        String[][] ubicacion = tableroService.recibir(codigoTablero);
        Pieza[][] ubicacionPieza = tableroService.definirUbicacionPiezas(ubicacion);
        Pieza reinaBlanco = piezaService.mapearPieza("Q", utilidades.ubicacion(10, 6));
        Pieza reinaNegro = piezaService.mapearPieza("q", utilidades.ubicacion(14, 7));
        Pieza expResult1 = piezaService.infoReina(reinaBlanco, ubicacionPieza);
        Pieza expResult2 = piezaService.infoReina(reinaNegro, ubicacionPieza);

        ArrayList<Integer> movDisponibleReinaB = new ArrayList<Integer>();
        movDisponibleReinaB.add(utilidades.ubicacion(9, 6));
        movDisponibleReinaB.add(utilidades.ubicacion(8, 6));
        movDisponibleReinaB.add(utilidades.ubicacion(10, 7));
        movDisponibleReinaB.add(utilidades.ubicacion(10, 8));
        movDisponibleReinaB.add(utilidades.ubicacion(10, 9));
        movDisponibleReinaB.add(utilidades.ubicacion(10, 10));
        movDisponibleReinaB.add(utilidades.ubicacion(10, 11));
        movDisponibleReinaB.add(utilidades.ubicacion(10, 12));
        movDisponibleReinaB.add(utilidades.ubicacion(10, 13));
        movDisponibleReinaB.add(utilidades.ubicacion(10, 14));
        movDisponibleReinaB.add(utilidades.ubicacion(10, 15));
        movDisponibleReinaB.add(utilidades.ubicacion(9, 5));
        movDisponibleReinaB.add(utilidades.ubicacion(8, 4));
        movDisponibleReinaB.add(utilidades.ubicacion(7, 3));
        movDisponibleReinaB.add(utilidades.ubicacion(6, 2));
        movDisponibleReinaB.add(utilidades.ubicacion(5, 1));
        movDisponibleReinaB.add(utilidades.ubicacion(4, 0));
        movDisponibleReinaB.add(utilidades.ubicacion(11, 7));
        movDisponibleReinaB.add(utilidades.ubicacion(12, 8));
        movDisponibleReinaB.add(utilidades.ubicacion(11, 5));
        movDisponibleReinaB.add(utilidades.ubicacion(12, 4));
        movDisponibleReinaB.add(utilidades.ubicacion(9, 7));
        movDisponibleReinaB.add(utilidades.ubicacion(8, 8));
        movDisponibleReinaB.add(utilidades.ubicacion(7, 9));
        movDisponibleReinaB.add(utilidades.ubicacion(6, 10));
        movDisponibleReinaB.add(utilidades.ubicacion(5, 11));
        movDisponibleReinaB.add(utilidades.ubicacion(4, 12));
        movDisponibleReinaB.add(utilidades.ubicacion(3, 13));
        ArrayList<Integer> comidaDisponibleReinaB = new ArrayList<Integer>();
        comidaDisponibleReinaB.add(utilidades.ubicacion(10, 5));
        comidaDisponibleReinaB.add(utilidades.ubicacion(2, 14));
        List<Integer> lugaresProtegidosReinaB = new ArrayList<Integer>();
        lugaresProtegidosReinaB.add(utilidades.ubicacion(7, 6));
        lugaresProtegidosReinaB.add(utilidades.ubicacion(11, 6));
        lugaresProtegidosReinaB.add(utilidades.ubicacion(13, 9));
        lugaresProtegidosReinaB.add(utilidades.ubicacion(13, 3));

        if (movDisponibleReinaB.equals(expResult1.getMovDisponible()) && comidaDisponibleReinaB.equals(expResult1.getComidaDisponible()) && lugaresProtegidosReinaB.equals(expResult1.getLugaresProtegidos())) {
        } else {
            fail("Falló la reina Blanca");

        }

        ArrayList<Integer> movDisponibleReinaN = new ArrayList<Integer>();
        movDisponibleReinaN.add(utilidades.ubicacion(15, 7));
        movDisponibleReinaN.add(utilidades.ubicacion(14, 6));
        ArrayList<Integer> comidaDisponibleReinaN = new ArrayList<Integer>();
        comidaDisponibleReinaN.add(utilidades.ubicacion(13, 7));
        comidaDisponibleReinaN.add(utilidades.ubicacion(14, 5));
        comidaDisponibleReinaN.add(utilidades.ubicacion(14, 8));
        comidaDisponibleReinaN.add(utilidades.ubicacion(13, 6));
        comidaDisponibleReinaN.add(utilidades.ubicacion(15, 8));
        comidaDisponibleReinaN.add(utilidades.ubicacion(15, 6));
        comidaDisponibleReinaN.add(utilidades.ubicacion(13, 8));
        List<Integer> lugaresProtegidosReinaN = new ArrayList<Integer>();

        if (movDisponibleReinaN.equals(expResult2.getMovDisponible()) && comidaDisponibleReinaN.equals(expResult2.getComidaDisponible()) && lugaresProtegidosReinaN.equals(expResult2.getLugaresProtegidos())) {
        } else {
            fail("Falló la reina Negra");

        }
    }

    @Test
    public void testInfoRey1() {
        System.out.println("Info Rey");
        String codigoTablero = "rrhhbbqqkkbbhhrr"
                + "rrhhbbqqkkbbhhrr"
                + " ppppppppppppppp"
                + " pppKpppppppp pp"//fila 3 col 4
                + "      P P       "
                + "                "
                + "       B        "
                + "      R         "
                + " P          P   "
                + "    ppp   PPP   "
                + "    pkp   PkP   "//fila 10 col 5    //fila 10 col 11
                + "    ppp   PPP   "
                + "           b    "
                + "PPPPPPPPPPPPPPPP"
                + "RRHHBB qKKBBHHRR"
                + "RRHHBBQ KKBBHHRR";
        String[][] ubicacion = tableroService.recibir(codigoTablero);
        Pieza[][] ubicacionPieza = tableroService.definirUbicacionPiezas(ubicacion);
        Pieza reyBlanco = piezaService.mapearPieza("K", utilidades.ubicacion(3, 4));
        Pieza reyNegro = piezaService.mapearPieza("k", utilidades.ubicacion(10, 5));
        Pieza reyNegro2 = piezaService.mapearPieza("k", utilidades.ubicacion(10, 11));
        Pieza expResult1 = piezaService.infoRey(reyBlanco, ubicacionPieza);
        Pieza expResult2 = piezaService.infoRey(reyNegro, ubicacionPieza);
        Pieza expResult3 = piezaService.infoRey(reyNegro2, ubicacionPieza);

        ArrayList<Integer> movDisponibleReyB = new ArrayList<Integer>();
        movDisponibleReyB.add(utilidades.ubicacion(4, 3));
        movDisponibleReyB.add(utilidades.ubicacion(4, 4));
        movDisponibleReyB.add(utilidades.ubicacion(4, 5));
        ArrayList<Integer> comidaDisponibleReyB = new ArrayList<Integer>();
        comidaDisponibleReyB.add(utilidades.ubicacion(2, 3));
        comidaDisponibleReyB.add(utilidades.ubicacion(2, 4));
        comidaDisponibleReyB.add(utilidades.ubicacion(2, 5));
        comidaDisponibleReyB.add(utilidades.ubicacion(3, 3));
        comidaDisponibleReyB.add(utilidades.ubicacion(3, 5));
        List<Integer> lugaresProtegidosReyB = new ArrayList<Integer>();

        if (movDisponibleReyB.equals(expResult1.getMovDisponible()) && comidaDisponibleReyB.equals(expResult1.getComidaDisponible()) && lugaresProtegidosReyB.equals(expResult1.getLugaresProtegidos())) {
        } else {
            fail("Falló el rey Blanco");
        }

        ArrayList<Integer> movDisponibleReyN = new ArrayList<Integer>();
        ArrayList<Integer> comidaDisponibleReyN = new ArrayList<Integer>();
        List<Integer> lugaresProtegidosReyN = new ArrayList<Integer>();
        lugaresProtegidosReyN.add(utilidades.ubicacion(9, 4));
        lugaresProtegidosReyN.add(utilidades.ubicacion(9, 5));
        lugaresProtegidosReyN.add(utilidades.ubicacion(9, 6));
        lugaresProtegidosReyN.add(utilidades.ubicacion(10, 4));
        lugaresProtegidosReyN.add(utilidades.ubicacion(10, 6));
        lugaresProtegidosReyN.add(utilidades.ubicacion(11, 4));
        lugaresProtegidosReyN.add(utilidades.ubicacion(11, 5));
        lugaresProtegidosReyN.add(utilidades.ubicacion(11, 6));
        if (movDisponibleReyN.equals(expResult2.getMovDisponible()) && comidaDisponibleReyN.equals(expResult2.getComidaDisponible()) && lugaresProtegidosReyN.equals(expResult2.getLugaresProtegidos())) {
        } else {
            fail("Falló el rey Negro 1");
        }

        ArrayList<Integer> movDisponibleReyN2 = new ArrayList<Integer>();
        ArrayList<Integer> comidaDisponibleReyN2 = new ArrayList<Integer>();
        comidaDisponibleReyN2.add(utilidades.ubicacion(9, 10));
        comidaDisponibleReyN2.add(utilidades.ubicacion(9, 11));
        comidaDisponibleReyN2.add(utilidades.ubicacion(9, 12));
        comidaDisponibleReyN2.add(utilidades.ubicacion(10, 10));
        comidaDisponibleReyN2.add(utilidades.ubicacion(10, 12));
        comidaDisponibleReyN2.add(utilidades.ubicacion(11, 10));
        comidaDisponibleReyN2.add(utilidades.ubicacion(11, 11));
        comidaDisponibleReyN2.add(utilidades.ubicacion(11, 12));
        List<Integer> lugaresProtegidosReyN2 = new ArrayList<Integer>();
        if (movDisponibleReyN2.equals(expResult3.getMovDisponible()) && comidaDisponibleReyN2.equals(expResult3.getComidaDisponible()) && lugaresProtegidosReyN2.equals(expResult3.getLugaresProtegidos())) {
        } else {
            fail("Falló el rey Negro 2");
        }
    }

    @Test
    public void testInfoCaballMueve() {
        System.out.println("Info Caballo mueve");
        String codigoTablero = "rrhhbbqqkkbbhhrr"
                + "rrhhbbqqkkbbhhrr"
                + " ppppppppppppppp"
                + " pppKpppppppp pp"
                + "      P P       "
                + "                "
                + "       B        "
                + "      R         "
                + " P          P   "
                + "    ppp   PPP   "
                + "          PkP   "
                + "          PPP   "
                + "           b    "
                + "   h            "//fila 13 col 3
                + "                "
                + "                ";
        String[][] ubicacion = tableroService.recibir(codigoTablero);
        Pieza[][] ubicacionPieza = tableroService.definirUbicacionPiezas(ubicacion);
        Pieza caballoNegro = piezaService.mapearPieza("h", utilidades.ubicacion(13, 3));
        Pieza expResult1 = piezaService.infoCaballo(caballoNegro, ubicacionPieza);

        ArrayList<Integer> movDisponibleCaballo = new ArrayList<Integer>();
        movDisponibleCaballo.add(utilidades.ubicacion(11, 2));
        movDisponibleCaballo.add(utilidades.ubicacion(12, 1));
        movDisponibleCaballo.add(utilidades.ubicacion(14, 1));
        movDisponibleCaballo.add(utilidades.ubicacion(15, 2));
        movDisponibleCaballo.add(utilidades.ubicacion(15, 4));
        movDisponibleCaballo.add(utilidades.ubicacion(14, 5));
        movDisponibleCaballo.add(utilidades.ubicacion(12, 5));
        movDisponibleCaballo.add(utilidades.ubicacion(11, 4));

        if (movDisponibleCaballo.equals(expResult1.getMovDisponible())) {
        } else {
            fail("Falló el movimiento del caballo");
        }
    }

    @Test
    public void testInfoCaballoCome() {
        System.out.println("Info Caballo mueve");
        String codigoTablero = "rrhhbbqqkkbbhhrr"
                + "rrhhbbqqkkbbhhrr"
                + " ppppppppppppppp"
                + " pppKpppppppp pp"
                + "      P P       "
                + "                "
                + "       B        "
                + "      R         "
                + " P          P   "
                + "    ppp   PPP   "
                + "          PkP   "
                + "  P P     PPP   "
                + " P   P     b    "
                + "   h            "//fila 13 col 3
                + " P   P          "
                + "  P P           ";
        String[][] ubicacion = tableroService.recibir(codigoTablero);
        Pieza[][] ubicacionPieza = tableroService.definirUbicacionPiezas(ubicacion);
        Pieza caballoNegro = piezaService.mapearPieza("h", utilidades.ubicacion(13, 3));
        Pieza expResult1 = piezaService.infoCaballo(caballoNegro, ubicacionPieza);

        ArrayList<Integer> comidaDisponibleCaballo = new ArrayList<Integer>();
        comidaDisponibleCaballo.add(utilidades.ubicacion(11, 2));
        comidaDisponibleCaballo.add(utilidades.ubicacion(12, 1));
        comidaDisponibleCaballo.add(utilidades.ubicacion(14, 1));
        comidaDisponibleCaballo.add(utilidades.ubicacion(15, 2));
        comidaDisponibleCaballo.add(utilidades.ubicacion(15, 4));
        comidaDisponibleCaballo.add(utilidades.ubicacion(14, 5));
        comidaDisponibleCaballo.add(utilidades.ubicacion(12, 5));
        comidaDisponibleCaballo.add(utilidades.ubicacion(11, 4));

        if (comidaDisponibleCaballo.equals(expResult1.getComidaDisponible())) {
        } else {
            fail("Falló la comida del caballo");
        }
    }

    @Test
    public void testInfoCaballoProtege() {
        System.out.println("Info Caballo mueve");
        String codigoTablero = "rrhhbbqqkkbbhhrr"
                + "rrhhbbqqkkbbhhrr"
                + " ppppppppppppppp"
                + " pppKpppppppp pp"
                + "      P P       "
                + "                "
                + "       B        "
                + "      R         "
                + " P          P   "
                + "    ppp   PPP   "
                + "          PkP   "
                + "  P P     PPP   "
                + " P   P     b    "
                + "   H            "//fila 13 col 3
                + " P   P          "
                + "  P P           ";
        String[][] ubicacion = tableroService.recibir(codigoTablero);
        Pieza[][] ubicacionPieza = tableroService.definirUbicacionPiezas(ubicacion);
        Pieza caballoBlanco = piezaService.mapearPieza("H", utilidades.ubicacion(13, 3));
        Pieza expResult1 = piezaService.infoCaballo(caballoBlanco, ubicacionPieza);

        ArrayList<Integer> lugaresProtegidosCaballo = new ArrayList<Integer>();
        lugaresProtegidosCaballo.add(utilidades.ubicacion(11, 2));
        lugaresProtegidosCaballo.add(utilidades.ubicacion(12, 1));
        lugaresProtegidosCaballo.add(utilidades.ubicacion(14, 1));
        lugaresProtegidosCaballo.add(utilidades.ubicacion(15, 2));
        lugaresProtegidosCaballo.add(utilidades.ubicacion(15, 4));
        lugaresProtegidosCaballo.add(utilidades.ubicacion(14, 5));
        lugaresProtegidosCaballo.add(utilidades.ubicacion(12, 5));
        lugaresProtegidosCaballo.add(utilidades.ubicacion(11, 4));

        if (lugaresProtegidosCaballo.equals(expResult1.getLugaresProtegidos())) {
        } else {
            fail("Falló la protección del caballo");
        }
    }

    @Test
    public void testMovimientosDisponibles1() {
        System.out.println("Movimientos Disponibles (Peon)");
        String codigoTablero = "                "
                + "p               "
                + " p              "
                + "                "
                + "                "
                + "                "
                + "            P   "
                + "             P  "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                ";
        Tablero tablero = new Tablero();
        tablero.setUbicaciones(tableroService.recibir(codigoTablero));
        tablero.setUbicacionesPieza(tableroService.definirUbicacionPiezas(tablero.getUbicaciones()));
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (tablero.getUbicacionesPieza()[i][j] != null) {
                    Pieza expResult = piezaService.infoPeon(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    Pieza result = piezaService.movimientosDisponibles(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    assertEquals(expResult.toString(), result.toString());
                }
            }
        }
    }

    @Test
    public void testMovimientosDisponibles2() {
        System.out.println("Movimientos Disponibles (Torre)");
        String codigoTablero = "                "
                + "r               "
                + " r              "
                + "                "
                + "                "
                + "                "
                + "            R   "
                + "             R  "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                ";
        Tablero tablero = new Tablero();
        tablero.setUbicaciones(tableroService.recibir(codigoTablero));
        tablero.setUbicacionesPieza(tableroService.definirUbicacionPiezas(tablero.getUbicaciones()));
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (tablero.getUbicacionesPieza()[i][j] != null) {
                    Pieza expResult = piezaService.infoTorre(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    Pieza result = piezaService.movimientosDisponibles(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    assertEquals(expResult.toString(), result.toString());
                }
            }
        }
    }

    @Test
    public void testMovimientosDisponibles3() {
        System.out.println("Movimientos Disponibles (Alfil)");
        String codigoTablero = "                "
                + "b               "
                + " b              "
                + "                "
                + "                "
                + "                "
                + "            B   "
                + "             B  "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                ";
        Tablero tablero = new Tablero();
        tablero.setUbicaciones(tableroService.recibir(codigoTablero));
        tablero.setUbicacionesPieza(tableroService.definirUbicacionPiezas(tablero.getUbicaciones()));
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (tablero.getUbicacionesPieza()[i][j] != null) {
                    Pieza expResult = piezaService.infoAlfil(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    Pieza result = piezaService.movimientosDisponibles(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    assertEquals(expResult.toString(), result.toString());
                }
            }
        }
    }

    @Test
    public void testMovimientosDisponibles4() {
        System.out.println("Movimientos Disponibles (Reina)");
        String codigoTablero = "                "
                + "q               "
                + " q              "
                + "                "
                + "                "
                + "                "
                + "            Q   "
                + "             Q  "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                ";
        Tablero tablero = new Tablero();
        tablero.setUbicaciones(tableroService.recibir(codigoTablero));
        tablero.setUbicacionesPieza(tableroService.definirUbicacionPiezas(tablero.getUbicaciones()));
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (tablero.getUbicacionesPieza()[i][j] != null) {
                    Pieza expResult = piezaService.infoReina(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    Pieza result = piezaService.movimientosDisponibles(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    assertEquals(expResult.toString(), result.toString());
                }
            }
        }
    }

    @Test
    public void testMovimientosDisponibles5() {
        System.out.println("Movimientos Disponibles (Rey)");
        String codigoTablero = "                "
                + "k               "
                + " k              "
                + "                "
                + "                "
                + "                "
                + "            K   "
                + "             K  "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                ";
        Tablero tablero = new Tablero();
        tablero.setUbicaciones(tableroService.recibir(codigoTablero));
        tablero.setUbicacionesPieza(tableroService.definirUbicacionPiezas(tablero.getUbicaciones()));
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (tablero.getUbicacionesPieza()[i][j] != null) {
                    Pieza expResult = piezaService.infoRey(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    Pieza result = piezaService.movimientosDisponibles(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    assertEquals(expResult.toString(), result.toString());
                }
            }
        }
    }

    @Test
    public void testMovimientosDisponibles6() {
        System.out.println("Movimientos Disponibles (Caballo)");
        String codigoTablero = "                "
                + "h               "
                + " h              "
                + "                "
                + "                "
                + "                "
                + "            H   "
                + "             H  "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                "
                + "                ";
        Tablero tablero = new Tablero();
        tablero.setUbicaciones(tableroService.recibir(codigoTablero));
        tablero.setUbicacionesPieza(tableroService.definirUbicacionPiezas(tablero.getUbicaciones()));
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (tablero.getUbicacionesPieza()[i][j] != null) {
                    Pieza expResult = piezaService.infoCaballo(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    Pieza result = piezaService.movimientosDisponibles(tablero.getUbicacionesPieza()[i][j], tablero.getUbicacionesPieza());
                    assertEquals(expResult.toString(), result.toString());
                }
            }
        }
    }
}

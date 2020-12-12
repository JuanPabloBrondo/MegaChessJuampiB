package service;

import enumeracion.categoria;
import enumeracion.color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import objetos.Pieza;
import objetos.Situacion;
import objetos.Tablero;
import utilidades.utilidades;

public class situacionService {

    //Con esta función definimos todas los posibles movimientos con los que contamos por pieza (situaciones) y sus distintas 
    //características llegando al final a cuantificar el valor (beneficio) de cada movimiento posbile
    public static List<Situacion> definirSituacionesDePieza(Tablero tablero, Integer ubicacionInicial, color colorMio, List<Situacion> situaciones) {
        Pieza[][] ubicacionPiezas = tablero.getUbicacionesPieza();
        boolean[][] rivalProtege = tablero.getRivalProtege();
        boolean[][] yoProtejo = tablero.getYoProtejo();
        boolean[][] dobleProteccion = tablero.getDobleProteccion();
        Pieza[][] rivalPuedeComer = tablero.getRivalPuedeComer();
        Pieza rivalComeCon = null;

        Integer columnaI = utilidades.columna(ubicacionInicial);
        Integer filaI = utilidades.fila(ubicacionInicial);
        Integer columnaF = 0;
        Integer filaF = 0;
        Integer columnaComeCon = 0;
        Integer filaComeCon = 0;
        Integer columnaTapado = 0;
        Integer filaTapado = 0;

        Pieza piezaMover = ubicacionPiezas[filaI][columnaI];
        ArrayList<Integer> largoListaComida = piezaMover.getComidaDisponible();
        ArrayList<Integer> largoListaMover = piezaMover.getMovDisponible();
        ArrayList<Integer> filasConReinaPropia = tablero.getFilasConReinaPropia();

        situaciones = situacionesConComida(largoListaComida, ubicacionInicial, piezaMover, filaF, columnaF, ubicacionPiezas, rivalProtege, filaI, columnaI, yoProtejo, dobleProteccion, rivalPuedeComer, rivalComeCon, filaComeCon, columnaComeCon, filaTapado, columnaTapado, colorMio, situaciones, filasConReinaPropia);
        //////////////Hasta acá llega el for con las situaciones de comer, ahora comienza el for con situaciones de mover sin comer///////////////////////
        //Definimos los atributos de cada situacion, la cargamos en el array y pasamos el array de situaciones por cada pieza de nuestro color
        situaciones = situacionesConMovimiento(largoListaMover, ubicacionInicial, piezaMover, filaF, columnaF, rivalProtege, filaI, columnaI, yoProtejo, dobleProteccion, rivalPuedeComer, rivalComeCon, filaComeCon, columnaComeCon, filaTapado, columnaTapado, ubicacionPiezas, colorMio, situaciones, filasConReinaPropia);
        //Finalmente tenemos una lista de situaciones para la ubicación actual de nuestra pieza//
        return situaciones;
    }

    public static List<Situacion> situacionesConComida(ArrayList<Integer> largoListaComida, Integer ubicacionInicial, Pieza piezaMover, Integer filaF, Integer columnaF,
            Pieza[][] ubicacionPiezas, boolean[][] rivalProtege, Integer filaI, Integer columnaI, boolean[][] yoProtejo, boolean[][] dobleProteccion,
            Pieza[][] rivalPuedeComer, Pieza rivalComeCon, Integer filaComeCon, Integer columnaComeCon, Integer filaTapado, Integer columnaTapado,
            color colorMio, List<Situacion> situaciones, ArrayList filasConReinaPropia) {
        //Definimos los atributos de cada situacion, la cargamos en el array y pasamos el array de situaciones por cada pieza de nuestro color
        for (Integer ubicacionComida : largoListaComida) {
            Situacion situacion = new Situacion();
            situacion.setUbicacionInicial(ubicacionInicial);

            situacion.setAtacante(piezaMover);
            situacion.setUbicacionFinal(ubicacionComida);

            filaF = utilidades.fila(ubicacionComida);
            columnaF = utilidades.columna(ubicacionComida);
            situacion.setVictima(ubicacionPiezas[filaF][columnaF]);

            situacion.setMuereDespues(rivalProtege[filaF][columnaF]);
            situacion.setCustodiado(yoProtejo[filaI][columnaI]);
            situacion.setDobleProteccion(dobleProteccion[filaF][columnaF]);

            if (rivalPuedeComer[filaI][columnaI] != null) {
                situacion.setEnRiesgo(true);

                //Con esta informacion vemos si moviendo nuestra pieza queda al descubierto otra pieza más importante
                //Todo lo que queda hasta que termine el switch es pura y exclusivamente para determinar el atributo tapado
                //Para mejorar esto habría que considerar todas las piezas que pueden comer en una ubicación (en este caso solo consideramos uno que 
                // es el rivalComeCon)
                //Todo esto es activado si y solo si la pieza que se va a mover está en riesgo
                rivalComeCon = rivalPuedeComer[filaI][columnaI];
                filaComeCon = utilidades.fila(rivalComeCon.getUbicacion());
                columnaComeCon = utilidades.columna(rivalComeCon.getUbicacion());
                switch (rivalComeCon.getTipo()) {
                    case REINA:
                        //Movimiento horizontal 
                        if (filaComeCon.equals(filaI)) {
                            situacion = tapadoHorizontal(situacion, columnaComeCon, columnaI, filaComeCon, filaF, filaTapado, ubicacionPiezas, colorMio, piezaMover);
                        }
                        //Movimiento vertical 
                        if (columnaComeCon.equals(columnaI)) {
                            situacion = tapadoVertical(situacion, filaComeCon, filaI, columnaComeCon, columnaF, columnaTapado, ubicacionPiezas, colorMio, piezaMover);
                        }

                        //Movimiento diagonal cumple con el siguiente requisito
                        if (Math.abs(filaComeCon - filaI) == Math.abs(columnaComeCon - columnaI)) {
                            situacion = tapadoDiagonal(situacion, columnaComeCon, columnaI, filaComeCon, filaI, filaF, columnaF, ubicacionPiezas, colorMio, piezaMover);
                        }
                        break;
                    case TORRE:
                        //Movimiento horizontal 
                        if (filaComeCon.equals(filaI)) {
                            situacion = tapadoHorizontal(situacion, columnaComeCon, columnaI, filaComeCon, filaF, filaTapado, ubicacionPiezas, colorMio, piezaMover);
                        }
                        //Movimiento vertical 
                        if (columnaComeCon.equals(columnaI)) {
                            situacion = tapadoVertical(situacion, filaComeCon, filaI, columnaComeCon, columnaF, columnaTapado, ubicacionPiezas, colorMio, piezaMover);
                        }
                        break;
                    case ALFIL:
                        //Movimiento diagonal cumple con el siguiente requisito
                        if (Math.abs(filaComeCon - filaI) == Math.abs(columnaComeCon - columnaI)) {
                            situacion = tapadoDiagonal(situacion, columnaComeCon, columnaI, filaComeCon, filaI, filaF, columnaF, ubicacionPiezas, colorMio, piezaMover);
                            break;
                        }
                }
            } else {
                situacion.setEnRiesgo(false);
                situacion.setTapado(null);
            }

            //Excepción cuando nuestra pieza come a la que nos apuntaba tapado queda nulo
            //Todo esto funciona ideal si la pieza solo era apuntada por una pieza porque en este caso tomamos como
            //que nos atacan con la pieza más valiosa
            if (rivalComeCon != null && Objects.equals(ubicacionComida, rivalComeCon.getUbicacion())) {
                situacion.setTapado(null);
            }
            //Ahora ponderamos cada situación
            situacion.setBeneficio(valuarBeneficio(situacion, filasConReinaPropia, colorMio));
            situaciones.add(situacion);
        }
        return situaciones;
    }

    public static List<Situacion> situacionesConMovimiento(ArrayList<Integer> largoListaMover, Integer ubicacionInicial, Pieza piezaMover, Integer filaF,
            Integer columnaF, boolean[][] rivalProtege, Integer filaI, Integer columnaI, boolean[][] yoProtejo, boolean[][] dobleProteccion, Pieza[][] rivalPuedeComer,
            Pieza rivalComeCon, Integer filaComeCon, Integer columnaComeCon, Integer filaTapado, Integer columnaTapado, Pieza[][] ubicacionPiezas,
            color colorMio, List<Situacion> situaciones, ArrayList<Integer> filasConReinaPropia) {
        for (Integer ubicacionMover : largoListaMover) {
            Situacion situacion = new Situacion();
            situacion.setUbicacionInicial(ubicacionInicial);
            situacion.setAtacante(piezaMover);
            situacion.setUbicacionFinal(ubicacionMover);

            filaF = utilidades.fila(ubicacionMover);
            columnaF = utilidades.columna(ubicacionMover);
            situacion.setVictima(null);
            situacion.setMuereDespues(rivalProtege[filaF][columnaF]);
            situacion.setCustodiado(yoProtejo[filaI][columnaI]);
            situacion.setDobleProteccion(dobleProteccion[filaF][columnaF]);

            if (rivalPuedeComer[filaI][columnaI] != null) {
                situacion.setEnRiesgo(true);

                //Con esta informacion vemos si moviendo nuestra pieza queda al descubierto otra pieza más importante
                //Todo lo que queda hasta que termine el switch es pura y exclusivamente para determinar el atributo tapado
                //Para mejorar esto habría que considerar todas las piezas que pueden comer en una ubicación (en este caso solo consideramos uno que 
                // es el rivalComeCon)
                //Todo esto es activado si y solo si la pieza que se va a mover está en riesgo
                rivalComeCon = rivalPuedeComer[filaI][columnaI];
                filaComeCon = utilidades.fila(rivalComeCon.getUbicacion());
                columnaComeCon = utilidades.columna(rivalComeCon.getUbicacion());
                switch (rivalComeCon.getTipo()) {
                    case REINA:
                        //Movimiento horizontal 
                        if (filaComeCon.equals(filaI)) {
                            situacion = tapadoHorizontal(situacion, columnaComeCon, columnaI, filaComeCon, filaF, filaTapado, ubicacionPiezas, colorMio, piezaMover);
                        }
                        //Movimiento vertical 
                        if (columnaComeCon.equals(columnaI)) {
                            situacion = tapadoVertical(situacion, filaComeCon, filaI, columnaComeCon, columnaF, columnaTapado, ubicacionPiezas, colorMio, piezaMover);
                        }

                        //Movimiento diagonal cumple con el siguiente requisito
                        if (Math.abs(filaComeCon - filaI) == Math.abs(columnaComeCon - columnaI)) {
                            situacion = tapadoVertical(situacion, filaComeCon, filaI, columnaComeCon, columnaF, columnaTapado, ubicacionPiezas, colorMio, piezaMover);
                        }
                        break;
                    case TORRE:
                        //Movimiento horizontal 
                        if (filaComeCon.equals(filaI)) {
                            situacion = tapadoHorizontal(situacion, columnaComeCon, columnaI, filaComeCon, filaF, filaTapado, ubicacionPiezas, colorMio, piezaMover);
                        }
                        //Movimiento vertical 
                        if (columnaComeCon.equals(columnaI)) {
                            situacion = tapadoVertical(situacion, filaComeCon, filaI, columnaComeCon, columnaF, columnaTapado, ubicacionPiezas, colorMio, piezaMover);
                        }
                        break;
                    case ALFIL:
                        //Movimiento diagonal cumple con el siguiente requisito
                        if (Math.abs(filaComeCon - filaI) == Math.abs(columnaComeCon - columnaI)) {
                            situacion = tapadoVertical(situacion, filaComeCon, filaI, columnaComeCon, columnaF, columnaTapado, ubicacionPiezas, colorMio, piezaMover);
                        }
                        break;
                }
            } else {
                situacion.setEnRiesgo(false);
                situacion.setTapado(null);
            }
            //Ahora ponderamos cada situación
            situacion.setBeneficio(valuarBeneficio(situacion, filasConReinaPropia, colorMio));
            situaciones.add(situacion);
        }
        return situaciones;
    }

    public static Situacion tapadoHorizontal(Situacion situacion, Integer columnaComeCon, Integer columnaI, Integer filaComeCon,
            Integer filaF, Integer filaTapado, Pieza[][] ubicacionPiezas, color colorMio, Pieza piezaMover) {
        if (columnaComeCon > columnaI) {
            if (!filaComeCon.equals(filaF)) {
                filaTapado = filaComeCon;
                for (int i = 1; i < 16; i++) {
                    if ((columnaI - i) >= 0 && ubicacionPiezas[filaTapado][columnaI - i] != null) {
                        if (ubicacionPiezas[filaTapado][columnaI - i].getColor().equals(colorMio)) {
                            situacion.setTapado(ubicacionPiezas[filaTapado][columnaI - i]);
                        }
                        i = 16;
                    }
                    if ((columnaI - i - 1) < 0) {
                        i = 16;
                    }
                }
            } else {
                situacion.setTapado(piezaMover);
            }
        } else {
            if (!filaComeCon.equals(filaF)) {
                filaTapado = filaComeCon;
                for (int i = 1; i < 16; i++) {
                    if ((columnaI + i) < 16 && ubicacionPiezas[filaTapado][columnaI + i] != null) {
                        if (ubicacionPiezas[filaTapado][columnaI + i].getColor().equals(colorMio)) {
                            situacion.setTapado(ubicacionPiezas[filaTapado][columnaI + i]);
                        }
                        i = 16;
                    }
                    if ((columnaI + i + 1) > 15) {
                        i = 16;
                    }
                }
            } else {
                situacion.setTapado(piezaMover);
            }
        }
        return situacion;
    }

    public static Situacion tapadoVertical(Situacion situacion, Integer filaComeCon, Integer filaI, Integer columnaComeCon, Integer columnaF, Integer columnaTapado,
            Pieza[][] ubicacionPiezas, color colorMio, Pieza piezaMover) {
        //Está abajo el comedor
        if (filaComeCon > filaI) {
            if (!columnaComeCon.equals(columnaF)) {
                columnaTapado = columnaComeCon;
                for (int i = 1; i < 16; i++) {
                    if ((filaI - i) >= 0 && ubicacionPiezas[filaI - i][columnaTapado] != null) {
                        if (ubicacionPiezas[filaI - i][columnaTapado].getColor().equals(colorMio)) {
                            situacion.setTapado(ubicacionPiezas[filaI - i][columnaTapado]);
                        }
                        i = 16;
                    }
                    if ((filaI - i - 1) < 0) {
                        i = 16;
                    }
                }
            } else {
                situacion.setTapado(piezaMover);
            }
        } else {
            //Está arriba el comedor    
            if (!columnaComeCon.equals(columnaF)) {
                columnaTapado = columnaComeCon;
                for (int i = 1; i < 16; i++) {
                    if ((filaI + i) < 16 && ubicacionPiezas[filaI + i][columnaTapado] != null) {
                        if (ubicacionPiezas[filaI + i][columnaTapado].getColor().equals(colorMio)) {
                            situacion.setTapado(ubicacionPiezas[filaI + i][columnaTapado]);
                        }
                        i = 16;
                    }
                    if ((filaI + i + 1) > 15) {
                        i = 16;
                    }
                }
            } else {
                situacion.setTapado(piezaMover);
            }
        }
        return situacion;
    }

    public static Situacion tapadoDiagonal(Situacion situacion, Integer columnaComeCon, Integer columnaI, Integer filaComeCon, Integer filaI, Integer filaF,
            Integer columnaF, Pieza[][] ubicacionPiezas, color colorMio, Pieza piezaMover) {
//Dentro de este if entran si la pieza está  arriba a la izquierda nuestra pieza respecto a la del ComeCon
        if (columnaComeCon > columnaI && filaComeCon > filaI) {
            //esta condicion implica que al mover la pieza, esta no queda expuesta en la misma diagonal
            if ((filaF - filaI) != (columnaF - columnaI)) {
                for (int i = 1; i < 16; i++) {
                    if ((columnaI - i) >= 0 && (filaI - i) >= 0 && ubicacionPiezas[filaI - i][columnaI - i] != null) {
                        if (ubicacionPiezas[filaI - i][columnaI - i].getColor().equals(colorMio)) {
                            situacion.setTapado(ubicacionPiezas[filaI - i][columnaI - i]);
                        }
                        i = 16;
                    }
                    if ((columnaI - i - 1) < 0 || (filaI - i - 1) < 0) {
                        i = 16;
                    }
                }
            } else {
                situacion.setTapado(piezaMover);
            }
        }
        //Dentro de este if entran si la pieza está a abajo a la derecha nuestra pieza respecto a la del ComeCon
        if (columnaComeCon < columnaI && filaComeCon < filaI) {
            //esta condicion implica que al mover la pieza, esta no queda expuesta en la misma diagonal
            if ((filaF - filaI) != (columnaF - columnaI)) {
                for (int i = 1; i < 16; i++) {
                    if ((columnaI + i) < 16 && (filaI + i) < 16 && ubicacionPiezas[filaI + i][columnaI + i] != null) {
                        if (ubicacionPiezas[filaI + i][columnaI + i].getColor().equals(colorMio)) {
                            situacion.setTapado(ubicacionPiezas[filaI + i][columnaI + i]);
                        }
                        i = 16;
                    }
                    if ((columnaI + i + 1) > 15 || (filaI + i + 1) > 15) {
                        i = 16;
                    }
                }
            } else {
                situacion.setTapado(piezaMover);
            }
        }

        //Dentro de este if entran si la pieza está arriba a la derecha nuestra pieza respecto a la del ComeCon
        if (columnaComeCon < columnaI && filaComeCon > filaI) {
            //esta condicion implica que al mover la pieza, esta no queda expuesta en la misma diagonal
            if ((filaF - filaI) != -(columnaF - columnaI)) {
                for (int i = 1; i < 16; i++) {
                    if ((columnaI + i) < 16 && (filaI - i) >= 0 && ubicacionPiezas[filaI - i][columnaI + i] != null) {
                        if (ubicacionPiezas[filaI - i][columnaI + i].getColor().equals(colorMio)) {
                            situacion.setTapado(ubicacionPiezas[filaI - i][columnaI + i]);
                        }
                        i = 16;
                    }
                    if ((columnaI + i + 1) > 15 || (filaI - i - 1) < 0) {
                        i = 16;
                    }
                }
            } else {
                situacion.setTapado(piezaMover);
            }
        }
        //Dentro de este if entran si la pieza está abajo a la izquierda nuestra pieza respecto a la del ComeCon
        if (columnaComeCon > columnaI && filaComeCon < filaI) {
            //esta condicion implica que al mover la pieza, esta no queda expuesta en la misma diagonal
            if ((filaF - filaI) != -(columnaF - columnaI)) {
                for (int i = 1; i < 16; i++) {
                    if ((columnaI - i) >= 0 && (filaI + i) < 16 && ubicacionPiezas[filaI + i][columnaI - i] != null) {
                        if (ubicacionPiezas[filaI + i][columnaI - i].getColor().equals(colorMio)) {
                            situacion.setTapado(ubicacionPiezas[filaI + i][columnaI - i]);
                        }
                        i = 16;
                    }
                    if ((filaI + i + 1) > 15 || (columnaI - i - 1) < 0) {
                        i = 16;
                    }
                }
            } else {
                situacion.setTapado(piezaMover);
            }
        }
        return situacion;
    }

//Con esta función calculamos el beneficio de hacer o no este movimiento (situación) 
    public static Integer valuarBeneficio(Situacion situacion, ArrayList<Integer> filasConReinaPropia, color colorMio) {
        Integer beneficio = 0;
        //Ganancia de mover una pieza
        ///Término 1
        if (situacion.getVictima() == null) {
            beneficio += beneficioMover(situacion);
        }
        //Ganancia por comer una pieza
        ///Término 2
        if (situacion.getVictima() != null) {
            beneficio += beneficioComer(situacion);
        }

        //El siguiente término del la ecuación beneficio representa el beneficio de escapar de una zona expuesta aunque valuando la posibilidad de estar respaldado
        //y considerando también el riesgo dejar al descubierto otra pieza por mover esta 
        //Este término suma porque representa qeu se está escapando aunque resta si la pieza que queda expuesta es más valiosa que la primera
        ///Término 3
        if (situacion.getEnRiesgo()) {
            Double Protegido = 1.0;//Si protegido vale 1 no está protegido, si vale 0,5 está protegido (en la posición inical)  Los valores son empíricos
            if (situacion.getCustodiado() && !situacion.getAtacante().getTipo().equals(categoria.REY)) {
                Protegido = 0.5;
            }
            Integer posiblePerdida = 0;
            posiblePerdida = valorPiezaPropia(situacion.getAtacante().getTipo());
            Double sum = posiblePerdida * Protegido;
            Integer suma = sum.intValue();

            Integer quedaExpuesto = 0;
            if (situacion.getTapado() != null) {
                quedaExpuesto = valorPiezaPropia(situacion.getTapado().getTipo());
            }
            beneficio = beneficio + suma - quedaExpuesto; ///Termino 3
        }

        //Este término representa el riesgo de moverse a una ubicación apuntada por el rival
        //Este término resta en la ecuación ya que por mover queda expuesto
        ///Término 4
        if (situacion.getMuereDespues()) {
            Double ProtegidoDespues = 1.0;//Si protegido vale 1 no está protegido, si vale 0,5 está protegido (en la posición final)  Los valores son empíricos
            if (situacion.getCustodiado()) {
                ProtegidoDespues = 0.5;
            }

            Integer posiblePerdida = 0;
            posiblePerdida = valorPiezaPropia(situacion.getAtacante().getTipo());
            Double resta = posiblePerdida * ProtegidoDespues;
            Integer res = resta.intValue();
            beneficio -= res;  ///Termino 4
        }

        //Este término incentiva a que si hay reinas blancas en la fila 8 y se pueden mover a la fila 6 (si no hay reina B en la fila 6) le de mayor beneficio
        //que mover un peon hacia adelante. Es el mismo caso para reinas negras desde la fila 7 a la 9
        //Termino 5
        if (situacion.getAtacante().getTipo().equals(categoria.REINA)) {
            beneficio += reinasTomanMedio(situacion.getUbicacionFinal(), filasConReinaPropia, colorMio);
        }

        //Este término pretende desmotivar el ataque de reinas que están en el medio hacia afuera del medio para poder conservar al menos 4 reinas en el medio
        //El valor es -600 para que en caso de poder atacar al rey lo haga
        //Termino 6
        Integer filaFinal = utilidades.fila(situacion.getUbicacionFinal());
        if (situacion.getAtacante().getTipo().equals(categoria.REINA) && (filaFinal > 11 || filaFinal < 4) && reinasEnElMedio(filasConReinaPropia) < 5) {
            beneficio -= 600;
        }

        return beneficio;
    }

    public static Integer beneficioMover(Situacion situacion) {
        Integer beneficio = 0;
        switch (situacion.getAtacante().getTipo()) {
            case REY:
                beneficio += 5;
                break;
            case REINA:
                beneficio += 7;
                break;
            case TORRE:
                beneficio += 6;
                break;
            case ALFIL:
                beneficio += 4;
                break;
            case CABALLO:
                beneficio += 3;
                break;
            case PEON:
                /* if(utilidades.fila(situacion.getUbicacionFinal())>3 && utilidades.fila(situacion.getUbicacionFinal())<12 &&utilidades.fila(situacion.getUbicacionFinal())!=8 &&utilidades.fila(situacion.getUbicacionFinal())!=9){
                    beneficio++;
                }*/
                switch (utilidades.fila(situacion.getUbicacionFinal())) {
                    case 8:
                        beneficio += 15;
                        break;
                    case 9:
                        beneficio += 14;
                        break;
                    case 10:
                        beneficio += 13;
                        break;
                    case 11:
                        beneficio += 12;
                        if (utilidades.columna(situacion.getUbicacionFinal()) == 8 || utilidades.columna(situacion.getUbicacionFinal()) == 9) {
                            beneficio = 10;
                        }//De esta forma los peones que cubren al rey no se mueven primeros
                        break;
                    case 12:
                        beneficio += 9;
                        break;
                    case 7:
                        beneficio += 15;
                        break;
                    case 6:
                        beneficio += 14;
                        break;
                    case 5:
                        beneficio += 13;
                        break;
                    case 4:
                        beneficio += 12;
                        if (utilidades.columna(situacion.getUbicacionFinal()) == 8 || utilidades.columna(situacion.getUbicacionFinal()) == 9) {
                            beneficio = 10;
                        }//De esta forma los peones que cubren al rey no se mueven primeros
                        break;
                    case 3:
                        beneficio += 9;
                        break;
                }
                if (peonCorona(situacion)) {
                    beneficio += 50;
                }
                break;
        }
        return beneficio;
    }

    public static Integer beneficioComer(Situacion situacion) {
        Integer beneficio = 0;
        switch (situacion.getVictima().getTipo()) {
            case REY:
                beneficio += 1000;
                break;
            case REINA:
                beneficio += 500;
                break;
            case TORRE:
                beneficio += 600;
                break;
            case ALFIL:
                beneficio += 400;
                break;
            case CABALLO:
                beneficio += 300;
                break;
            case PEON:
                beneficio += 100;
                break;
        }
        return beneficio;
    }

    //En este switch está el valor de nuestras piezas ya sea por salvarse o por quedar expuestas
    public static Integer valorPiezaPropia(categoria tipo) {
        Integer valor = 0;
        switch (tipo) {
            case REY:
                valor = 1000;
                break;
            case REINA:
                valor = 500;
                break;
            case TORRE:
                valor = 600;
                break;
            case ALFIL:
                valor = 400;
                break;
            case CABALLO:
                valor = 300;
                break;
            case PEON:
                valor = 100;
                break;
        }
        return valor;
    }

    public static boolean peonCorona(Situacion situacion) {
        Integer ubicacionFinal = situacion.getUbicacionFinal();
        if (ubicacionFinal >= 112 && ubicacionFinal <= 143) {
            return true;
        } else {
            return false;
        }
    }

    public static Integer reinasTomanMedio(Integer ubicacionFinal, List<Integer> ubicacionReinasPropias, color colorMio) {
        Integer fila = utilidades.fila(ubicacionFinal);
        Integer en9 = 0;
        Integer en8 = 0;
        Integer en7 = 0;
        Integer en6 = 0;
        for (Integer ubicacionReinasPropia : ubicacionReinasPropias) {
            if (ubicacionReinasPropia == 6) {
                en6++;
            }
            if (ubicacionReinasPropia == 7) {
                en7++;
            }
            if (ubicacionReinasPropia == 8) {
                en8++;
            }
            if (ubicacionReinasPropia == 9) {
                en9++;
            }
        }
        if (colorMio.equals(color.BLANCO) && fila == 6 && en8 > 1) {
            return 10;
        }
        if (colorMio.equals(color.BLANCO) && fila == 7 && en6 > 0 && en8 > 1) {
            return 10;
        }
        if (colorMio.equals(color.NEGRO) && fila == 9 && en7 > 1) {
            return 10;
        }
        if (colorMio.equals(color.NEGRO) && fila == 8 && en9 > 0 && en7 > 1) {
            return 10;
        }
        return 0;
    }

    public static Integer reinasEnElMedio(List<Integer> ubicacionReinasPropias) {
        Integer total = 0;
        for (Integer ubicacionReinasPropia : ubicacionReinasPropias) {
            if (ubicacionReinasPropia == 5) {
                total++;
            }
            if (ubicacionReinasPropia == 6) {
                total++;
            }
            if (ubicacionReinasPropia == 7) {
                total++;
            }
            if (ubicacionReinasPropia == 8) {
                total++;
            }
            if (ubicacionReinasPropia == 9) {
                total++;
            }
            if (ubicacionReinasPropia == 10) {
                total++;
            }
        }
        return total;
    }
}

package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import objetos.Juego;
import objetos.Situacion;
/*Esta clase va a recivir juegos y determinar que estrategia usar para darle más razonamiento a los movimientos
 */
public class estrategia {

    public static Situacion mejorBeneficio(Juego juego) {

        Situacion respuesta = null;
        List<Situacion> situacionesOrdenadas = new ArrayList<>();
        situacionesOrdenadas = juego.getSituacion();
        situacionesOrdenadas.sort(situacionComparadaPorBeneficio);
        juego.setSituacion(situacionesOrdenadas);

        Integer compare = situacionesOrdenadas.get(0).getBeneficio();
        List<Situacion> opcionesFinales = new ArrayList<>();
        for (Situacion situacionesOrdenada : situacionesOrdenadas) {
            if (Objects.equals(compare, situacionesOrdenada.getBeneficio())) {
                opcionesFinales.add(situacionesOrdenada);
            } else {
                break;
            }
        }
        Double azar = Math.random() * opcionesFinales.size();
        Integer azaroso = azar.intValue();
        respuesta = opcionesFinales.get(azaroso);

        return respuesta;
    }

    public static Comparator<Situacion> situacionComparadaPorBeneficio = new Comparator<Situacion>() {
        public int compare(Situacion s1, Situacion s2) {
            Integer sit1 = s1.getBeneficio();
            Integer sit2 = s2.getBeneficio();
            return sit2.compareTo(sit1);
        }
    };
}

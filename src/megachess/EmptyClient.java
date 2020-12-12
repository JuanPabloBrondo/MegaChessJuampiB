package megachess;


import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import objetos.Situacion;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import service.juegoService;

public class EmptyClient extends WebSocketClient {

    public static void main(String[] args) throws URISyntaxException {
        WebSocketClient client = new EmptyClient(new URI("ws://megachess.herokuapp.com/service?authtoken=c1264282-7e9e-4a35-af2d-d738f50c674f"));
        client.connect();
    }

    public EmptyClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message);
        Recive_challenge(message);
        play(message);
        playT(message);

    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");

    }

    //////////METODOS DE RECIVIR CHALLENGE Y ACEPTAR///////////////
    public void Recive_challenge(String message) {
        if (message.contains("ask_challenge")) {
            String id = boardIdAsk(message);
            Accept_challenge(id);

        }
    }

    public String boardIdAsk(String message) {
        if (message.contains("ask_challenge") && message.contains("board_id")) {
            String[] partes = message.split("\\:");

            String id = partes[4];

            String id2 = id.substring(1, id.length() - 3);
            return id2;
        }
        return null;
    }

    public void Accept_challenge(String id) {
        send("{" + '"' + "action" + '"' + ":" + '"' + "accept_challenge" + '"' + ", " + '"' + "data"
                + '"' + ": " + "{"
                + '"' + "board_id" + '"' + ": " + '"' + id + '"' + "}" + "}");

    }

    ///////////////////////////////////////////////////
    /////////METODOS DE LEER MENSAJE DE JUEGO PARA LUEGO MOVER PIEZA///////////
    public void play(String message) {
        if (message.contains("your_turn") && !message.contains("tournament")) {
            String boardId = boardIdAskPlay(message);
            String turnToken = turnTokenPlay(message);
            String color = colorPlay(message);
            String tablero = tableroPlay(message);
            String movimientosParaTerminar = turnosParaFinPlay(message);
            System.out.println(color);
            Situacion eleccion = juegoService.jugar(tablero, color, movimientosParaTerminar);

            Integer fromRow = fila(eleccion.getUbicacionInicial());
            Integer fromCol = columna(eleccion.getUbicacionInicial());
            Integer toRow = fila(eleccion.getUbicacionFinal());
            Integer toCol = columna(eleccion.getUbicacionFinal());

            send("{" + '"' + "action" + '"' + ":" + '"' + "move" + '"' + "," + '"' + "data" + '"' + ": {" + '"' + "board_id" + '"' + ": " + '"' + boardId + '"' + "," + '"' + "turn_token" + '"' + ": " + '"' + turnToken
                    + "," + '"' + "from_row" + '"' + ": " + fromRow + "," + '"' + "from_col" + '"' + ": " + fromCol + "," + '"' + "to_row" + '"' + ": " + toRow + "," + '"' + "to_col" + '"' + ": " + toCol + "}}");
            System.out.println("{" + '"' + "action" + '"' + ":" + '"' + "move" + '"' + "," + '"' + "data" + '"' + ": {" + '"' + "board_id" + '"' + ": " + '"' + boardId + '"' + "," + '"' + "turn_token" + '"' + ": " + '"' + turnToken
                    + "," + '"' + "from_row" + '"' + ": " + fromRow + "," + '"' + "from_col" + '"' + ": " + fromCol + "," + '"' + "to_row" + '"' + ": " + toRow + "," + '"' + "to_col" + '"' + ": " + toCol + "}}");
        }
    }

    public String boardIdAskPlay(String message) {
        if (message.contains("your_turn") && message.contains("board_id")) {
            String[] partes = message.split("\\:");

            String id = partes[3];
            String id2 = id.substring(1, id.length() - 14);

            return id2;
        }
        return null;
    }

    public String turnTokenPlay(String message) {
        if (message.contains("your_turn") && message.contains("turn_token")) {
            String[] partes = message.split("\\:");
            String id = partes[4];

            String id2 = id.substring(1, id.length() - 11);
            return id2;
        }
        return null;
    }

    public String colorPlay(String message) {
        if (message.contains("your_turn") && message.contains("turn_token")) {
            String[] partes = message.split("\\:");
            String id = partes[6];
            String id2 = id.substring(1, 8);
            if (id2.contains("black")) {
                return "NEGRO";
            } else if (id2.contains("white")) {
                return "BLANCO";
            }
            System.out.println("NOLOTOMO");
            return null;
        }
        return null;
    }

    public String tableroPlay(String message) {
        if (message.contains("your_turn") && message.contains("turn_token")) {
            String[] partes = message.split("\\:");
            String id = partes[7];

            String id2 = id.substring(1, 257);
            return id2;
        }
        return null;
    }

    public String turnosParaFinPlay(String message) {
        if (message.contains("your_turn") && message.contains("move_left")) {
            String[] partes = message.split("\\:");

            String id = partes[8];

            String id2 = id.substring(0, 3);
            if (id2.contains(",")) {
                id2 = id2.substring(0, 2);
                if (id2.contains(",")) {
                    id2 = id2.substring(0, 1);
                }
            }
            System.out.println(id2);
            return id2;
        }
        return null;
    }

    ////////////////////////////////////
    @Override
    public void onOpen(ServerHandshake handshakedata) {

        System.out.println("new connection opened");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason
                + remote);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }

    public static Integer fila(Integer ubicacion) {
        Integer fi = (ubicacion / 16);
        return fi;
    }

    public static Integer columna(Integer ubicacion) {
        Integer fi = ubicacion / 16;
        Integer col = ubicacion - fi * 16;
        return col;
    }

    public static void recibir(String codigoTablero) {
        String ubicacion[][] = new String[16][16];
        int cont = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                ubicacion[i][j] = codigoTablero.substring(cont, cont + 1);
                cont++;
            }
        }
        for (int i = 0; i < 16; i++) {
            System.out.print("                  ");
            for (int j = 0; j < 16; j++) {
                System.out.print(ubicacion[i][j]);
            }
            System.out.println("");
        }
    }

    public String boardIdTorneoPlay(String message) {
        if (message.contains("your_turn") && message.contains("board_id")) {
            String[] partes = message.split("\\:");
            String id = partes[4];

            return id;
        }
        return null;
    }

    public void playT(String message) {
        if (message.contains("your_turn") && message.contains("tournament")) {
            String boardId = boardIdPlayT(message);
            String turnToken = turnTokenPlayT(message);
            String color = colorPlayT(message);
            String tablero = tableroPlayT(message);
            String movimientosParaTerminar = turnosParaFinPlayT(message);
            Situacion eleccion = juegoService.jugar(tablero, color, movimientosParaTerminar);

            Integer fromRow = fila(eleccion.getUbicacionInicial());
            Integer fromCol = columna(eleccion.getUbicacionInicial());
            Integer toRow = fila(eleccion.getUbicacionFinal());
            Integer toCol = columna(eleccion.getUbicacionFinal());

            send("{" + '"' + "action" + '"' + ":" + '"' + "move" + '"' + "," + '"' + "data" + '"' + ": {" + '"' + "board_id" + '"' + ": " + '"' + boardId + '"' + "," + '"' + "turn_token" + '"' + ": " + '"' + turnToken
                    + '"' + "," + '"' + "from_row" + '"' + ": " + fromRow + "," + '"' + "from_col" + '"' + ": " + fromCol + "," + '"' + "to_row" + '"' + ": " + toRow + "," + '"' + "to_col" + '"' + ": " + toCol + "}}");
            System.out.println("{" + '"' + "action" + '"' + ":" + '"' + "move" + '"' + "," + '"' + "data" + '"' + ": {" + '"' + "board_id" + '"' + ": " + '"' + boardId + '"' + "," + '"' + "turn_token" + '"' + ": " + '"' + turnToken
                    + '"' + "," + '"' + "from_row" + '"' + ": " + fromRow + "," + '"' + "from_col" + '"' + ": " + fromCol + "," + '"' + "to_row" + '"' + ": " + toRow + "," + '"' + "to_col" + '"' + ": " + toCol + "}}");
        }
    }

    public String boardIdPlayT(String message) {
        if (message.contains("your_turn") && message.contains("board_id")) {
            String[] partes = message.split("\\:");
            String id1 = partes[3];
            id1 = id1.substring(1);
            String id2 = partes[4];
            String id3 = partes[6];
            id3 = id3.substring(0, 36);
            String id = id1 + ":" + id2 + "::" + id3;
            return id;
        }
        return null;
    }

    public String turnTokenPlayT(String message) {
        if (message.contains("your_turn") && message.contains("turn_token")) {
            String[] partes = message.split("\\:");
            String id = partes[7];
            String id2 = id.substring(1, 37);
            return id2;
        }
        return null;
    }

    public String colorPlayT(String message) {
        if (message.contains("your_turn") && message.contains("turn_token")) {
            String[] partes = message.split("\\:");
            String id = partes[9];
            String id2 = id.substring(1, 8);
            if (id2.contains("black")) {
                return "NEGRO";
            } else if (id2.contains("white")) {
                return "BLANCO";
            }
            System.out.println("NOLOTOMO");
            return null;
        }
        return null;
    }

    public String tableroPlayT(String message) {
        if (message.contains("your_turn") && message.contains("turn_token")) {
            String[] partes = message.split("\\:");
            String id = partes[10];
            String id2 = id.substring(1, 257);
            return id2;
        }
        return null;
    }

    public String turnosParaFinPlayT(String message) {
        if (message.contains("your_turn") && message.contains("move_left")) {
            String[] partes = message.split("\\:");

            String id = partes[11];
            String id2 = id.substring(0, 3);
            if (id2.contains(",")) {
                id2 = id2.substring(0, 2);
                if (id2.contains(",")) {
                    id2 = id2.substring(0, 1);
                }
            }
            return id2;
        }
        return null;
    }
}

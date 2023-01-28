package Model;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author b44rto
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int PORT = 4160;

        String claveTecladoUsuario = "";

        HashMap<String, String> dictionary = new HashMap<>();
        dictionary.put("A", "Azuay");
        dictionary.put("B", "Bolivar");
        dictionary.put("U", "Caniar");
        dictionary.put("X", "Cotopaxi");
        dictionary.put("H", "Chimborazo");
        dictionary.put("O", "El Oro");
        dictionary.put("E", "Esmeraldas");
        dictionary.put("Q", "Franciso de Orellana");
        dictionary.put("W", "Galapagos");
        dictionary.put("G", "Guayas");
        dictionary.put("I", "Imbabura");
        dictionary.put("L", "Loja");
        dictionary.put("R", "Los Rios");
        dictionary.put("M", "Manabi");
        dictionary.put("V", "Morona Santiago");
        dictionary.put("N", "Napo");
        dictionary.put("S", "Pastaza");
        dictionary.put("P", "Pichincha");
        dictionary.put("Y", "Santa Elena");
        dictionary.put("J", "Santo Domingo de los Tsachilas");
        dictionary.put("", "Sucumbios");
        dictionary.put("", "Tungurahua");
        dictionary.put("", "Zamora Chimpchipe");

        System.out.printf("\n\n\t***    Placa - Lugar     ***\n");
        while (!claveTecladoUsuario.equals("0")) {
            try {
                DatagramSocket server = new DatagramSocket(PORT);

                byte[] bufferPeticion = new byte[1024];
                byte[] bufferRespuesta = new byte[1024];

                DatagramPacket paketePeticion = new DatagramPacket(bufferPeticion, bufferPeticion.length);
                server.receive(paketePeticion);
                claveTecladoUsuario = new String(paketePeticion.getData());

                for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    key = key.toUpperCase();
                    claveTecladoUsuario = claveTecladoUsuario.toUpperCase();
                    if (key.equals(claveTecladoUsuario.trim())) {
                        System.out.println("Placa: " + key);
                        System.out.println("Lugar: " + value + "\n");

                        InetAddress HOST = InetAddress.getByName("localhost");
                        int puertoCliente = paketePeticion.getPort();
                        bufferRespuesta = value.getBytes();
                        DatagramPacket paketeRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, HOST, puertoCliente);
                        server.send(paketeRespuesta);
                    }
                }
                server.close();
            } catch (Exception e) {
            }
        }
        System.out.printf("\n\n\t***    Placa - Lugar     ***\n");
    }

}

package Model;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author b44rto
 */
public class Client {

    static public void main(String... zhunIOS) {

        int PORT = 4160;
        String claveTecladoUsuario = "";
        String valorHashMap = "";

        Scanner reader = new Scanner(System.in);

        while (!claveTecladoUsuario.equals("0")) {
            try {
                DatagramSocket client = new DatagramSocket();
                InetAddress HOST = InetAddress.getByName("localhost");

                System.out.println("Placa: ");
                claveTecladoUsuario = reader.nextLine();
                claveTecladoUsuario = claveTecladoUsuario.toUpperCase();
                byte[] bufferPeticion = claveTecladoUsuario.getBytes();
                DatagramPacket paketePeticion = new DatagramPacket(bufferPeticion, bufferPeticion.length, HOST, PORT);
                client.send(paketePeticion);

                byte[] bufferRespuesta = new byte[1024];
                DatagramPacket paketeRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
                client.receive(paketeRespuesta);
                valorHashMap = new String(paketeRespuesta.getData());
                System.out.println("Lugar: " + valorHashMap + "\n");

                client.close();
            } catch (Exception e) {
            }
        }

    }

}

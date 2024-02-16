package ExPersonne;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        final String SERVER_HOST = "localhost";
        final int SERVER_PORT = 12345;

        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

            // Envoi de données au serveur
            Personne personne = new Personne(22, "Mohamed");
            output.writeObject(personne);


            // Lecture de l'identifiant renvoyé par le serveur
            int clientId = input.readInt();
            System.out.println("Identifiant du client côté serveur : " + clientId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

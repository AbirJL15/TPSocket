package ExVoiture;


import java.io.*;
import java.net.*;

public class SocketServeur {
    public static void main(String[] args) {
        int port = 12345;  // Port d'écoute

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur en attente de connexions...");

            // Attente d'une connexion client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connexion etablie avec le client.");

            // Obtention des flux d'entrée/sortie
            ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());

            // Lecture de l'objet Voiture depuis le client
            Voiture voitureFromClient = (Voiture) input.readObject();
            System.out.println("Voiture reçue du client : " + voitureFromClient);

            // Modification de la quantité de carburant
            voitureFromClient.setCarburant(50);

            // Envoi de l'objet modifié au client
            output.writeObject(voitureFromClient);

            // Fermeture des flux et sockets
            input.close();
            output.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

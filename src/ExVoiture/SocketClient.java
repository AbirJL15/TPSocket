package ExVoiture;
import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        String serverHost = "localhost";  // Adresse IP du serveur
        int serverPort = 12345;           // Port du serveur

        try {
            // Création de la voiture côté client
            Voiture voitureToSend = new Voiture("SportsCar", "Ferrari");

            // Affichage de l'état initial
            System.out.println("Voiture avant envoi : " + voitureToSend);

            // Connexion au serveur
            Socket socket = new Socket(serverHost, serverPort);
            System.out.println("Connecté au serveur.");

            // Obtention des flux d'entrée/sortie
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Envoi de l'objet Voiture au serveur
            output.writeObject(voitureToSend);

            // Lecture de l'objet modifié renvoyé par le serveur
            Voiture voitureFromServer = (Voiture) input.readObject();
            System.out.println("Voiture après traitement côté serveur : " + voitureFromServer);

            // Fermeture des flux et socket
            output.close();
            input.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

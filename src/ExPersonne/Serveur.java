package ExPersonne;

import ExPersonne.Personne;

import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur en attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouvelle connexion : " + clientSocket);

                // Gestion de la connexion dans un thread séparé
                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            while (true) {
                // Lecture des données du client
                Personne personne = (Personne) input.readObject();

                // Traitement des données (exemple : affichage côté serveur)
                System.out.println("Données reçues du client : " + personne);

                // Retourne un identifiant au client
                int clientId = generateClientId();
                output.writeInt(clientId);
                output.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client déconnecté : " + clientSocket);
        }
    }

    private static int generateClientId() {
        // À remplacer par une logique d'attribution d'identifiants unique
        return (int) (Math.random() * 1000);
    }
}

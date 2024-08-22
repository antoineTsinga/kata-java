package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Erreur : Veuillez spécifier le chemin du fichier d'entrée en argument.");
            System.err.println("Usage : java -jar kata-test.jar chemin/vers/fichier_entree.txt");
            return;
        }
        String filePath = args[0];

        // Initialisation des strategies
        Map<Character, MovementStrategy> strategies = new HashMap<>();
        strategies.put('G', Position::rotateLeft);
        strategies.put('D', Position::rotateRight);
        strategies.put('A', Position::moveForward);


        try {
            File file = new File(filePath);
            processFile(file, strategies);
        } catch (FileNotFoundException e) {
            System.err.println("Erreur : Le fichier spécifié n'a pas été trouvé.");
            System.err.println("Détails : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Une erreur inattendue s'est produite lors du traitement du fichier.");
            System.err.println("Détails : " + e.getMessage());
        }
    }

    public static void processFile(File file, Map<Character, MovementStrategy> strategies) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            // Lire la taille de la pelouse
            int pelouseX = scanner.nextInt();
            int pelouseY = scanner.nextInt();
            Pelouse pelouse = new Pelouse(pelouseX, pelouseY);

            while (scanner.hasNext()) {
                try {
                    // Lire la position initiale et l'orientation de la tondeuse
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    char orientation = scanner.next().charAt(0);
                    Position position = new Position(x, y, orientation);

                    Tondeuse tondeuse = new Tondeuse(position, pelouse, strategies);

                    // Lire et exécuter les instructions
                    String instructions = scanner.next();
                    tondeuse.executeInstructions(instructions);

                    // Afficher la position finale
                    Position finalPosition = tondeuse.getPosition();
                    System.out.println(finalPosition.getX() + " " + finalPosition.getY() + " " + finalPosition.getOrientation());

                } catch (InputMismatchException e) {
                    System.err.println("Erreur de format dans le fichier d'entrée. Passage à la ligne suivante.");
                    scanner.nextLine(); // Passer à la ligne suivante
                } catch (Exception e) {
                    System.err.println("Erreur lors du traitement d'une tondeuse : " + e.getMessage());
                    scanner.nextLine(); // Passer à la ligne suivante
                }
            }
        }
    }
}

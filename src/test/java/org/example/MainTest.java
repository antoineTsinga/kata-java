package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testProcessFile() throws Exception {
        // Préparation du fichier temporaire d'entrée
        File inputFile = File.createTempFile("test-input", ".txt");
        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write("5 5\n");            // Taille de la pelouse
            writer.write("1 2 N\n");          // Position initiale de la tondeuse
            writer.write("GAGAGAGAA\n");      // Instructions
            writer.write("3 3 E\n");          // Autre tondeuse
            writer.write("AADAADADDA\n");     // Instructions
        }

        // Initialisation des stratégies
        Map<Character, MovementStrategy> strategies = new HashMap<>();
        strategies.put('G', Position::rotateLeft);
        strategies.put('D', Position::rotateRight);
        strategies.put('A', Position::moveForward);

        // Exécution de la méthode processFile
        Main.processFile(inputFile, strategies);

        // Récupération et normalisation de la sortie
        String output = outputStream.toString().replace("\r\n", "\n").trim();
        String expectedOutput = "1 3 N\n" + "5 1 E";

        // Comparaison
        assertEquals(expectedOutput, output);

        // Supprimer le fichier temporaire
        inputFile.delete();
    }

    @Test
    public void testMainWithMissingFileArgument() {
        // Sauvegarder le flux d'erreur original
        ByteArrayOutputStream errStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errStream));

        // Exécution sans argument pour vérifier le message d'erreur
        Main.main(new String[]{});
        System.out.println();
        String expectedError = "Erreur : Veuillez spécifier le chemin du fichier d'entrée en argument.";
        assertTrue(errStream.toString().trim().contains(expectedError));
    }

    @Test
    public void testMainWithFileNotFound() {
        // Sauvegarder le flux d'erreur original
        ByteArrayOutputStream errStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errStream));

        // Exécution avec un chemin de fichier invalide
        Main.main(new String[]{"fichier_inexistant.txt"});

        String expectedErrorStart = "Erreur : Le fichier spécifié n'a pas été trouvé.";
        assertTrue(errStream.toString().trim().startsWith(expectedErrorStart));
    }
}

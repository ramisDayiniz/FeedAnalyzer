import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Die Klasse Utility enthält Methoden zur Analyse von Texten basierend auf einem
 * vordefinierten Sentiment-Lexikon.
 * @author Ramis Ekici
 * @version 01.04.2025
 */
public class Utility {

    /**
     * Analysiert den gegebenen Text und berechnet einen Sentiment-Score basierend auf einem Lexikon.
     * @param text Der zu analysierende Text.
     * @return Der berechnete Sentiment-Score. Falls Fehler auftreten oder kein relevantes Wort gefunden wird, wird 0.0 zurückgegeben.
     * @throws IOException Falls eine Datei nicht gelesen werden kann.
     */
    public static double analyzeText(String text) throws IOException {
        Set<String> stopWords = new HashSet<>();
        Map<String, Double> vaderLexicon = new HashMap<>();
        double sentiment = 0.0;
        int gezaehlteWoerter = 0;

        // Stop-Wörter laden
        File stopWordsFile = new File("C:\\Users\\ekici\\IdeaProjects\\FeedAnalyzer\\rescource\\SmartStoplist.txt");
        if (!stopWordsFile.exists()) {
            System.out.println("Die Datei 'SmartStoplist.txt' wurde nicht gefunden.");
            return -1;
        }

        try (Scanner s = new Scanner(new FileReader(stopWordsFile))) {
            while (s.hasNextLine()) {
                stopWords.add(s.nextLine().trim().toLowerCase());
            }
        }

        // vader-Lexikon laden
        File vaderLexiconFile = new File("C:\\Users\\ekici\\IdeaProjects\\FeedAnalyzer\\rescource\\vader_lexicon.txt");
        if (!vaderLexiconFile.exists()) {
            System.out.println("Die Datei 'vader_lexicon.txt' wurde nicht gefunden.");
            return -1;
        }

        try (Scanner s = new Scanner(new FileReader(vaderLexiconFile))) {
            while (s.hasNextLine()) {
                String[] parts = s.nextLine().split("\t");
                if (parts.length >= 2) {
                    vaderLexicon.put(parts[0].toLowerCase(), Double.parseDouble(parts[1]));
                }
            }
        }

        // Textanalyse
        if (text == null || text.trim().isEmpty()) {
            return 0.0;
        }

        System.out.println("\nAnalyse von: " + text);
        String[] words = text.split("\\s+");

        for (String word : words) {
            word = word.trim().toLowerCase();

            if (word.isEmpty()) {
                continue;
            }

            if (stopWords.contains(word)) {
                System.out.println("Übersprungen (Stop-Wort): " + word);
                continue;
            }

            Double score = vaderLexicon.get(word);
            if (score != null) {
                sentiment += score;
                gezaehlteWoerter++;
                System.out.println("Bewertet: " + word + " = " + score);
            } else {
                System.out.println("Nicht im Lexikon: " + word);
            }
        }

        // Ergebnisberechnung
        double result = gezaehlteWoerter > 0 ? sentiment / gezaehlteWoerter : 0.0;
        System.out.println("Ergebnis: " + result);
        return result;
    }
}
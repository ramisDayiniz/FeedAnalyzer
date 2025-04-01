import java.io.*;
import java.util.*;
/**
 * Die Klasse FeedAnalyzer analysiert die Stimmung von Textnachrichten aus einer CSV-Datei.
 * Die Sentiment-Scores werden mit Hilfe der Klasse Utility berechnet und ausgegeben.
 * @author Ramis Ekici
 * @version 01.04.2025
 */
public class FeedAnalyzer {
    /**
     * Die volle Funktion von dieser Klasse wird in der main Methode bearbeitet
     * @param args Commandline parameter
     */
    public static void main(String[] args) {
        Map<String, AnalyzedText> map = new HashMap<>();
        double totalSentiment = 0;
        int messageCount = 0;

        File datei = new File("C:\\Users\\ekici\\IdeaProjects\\FeedAnalyzer\\rescource\\potus_tweets_2017_webarchive_publicaccess.csv");

        if (!datei.exists()) {
            System.out.println("Die Datei wurde nicht gefunden. Bitte überprüfe den Pfad.");
            return;
        }

        try (Scanner s = new Scanner(new FileReader(datei))) {
            while (s.hasNextLine()) {
                String nachricht = s.nextLine().trim();


                if (nachricht.isEmpty()) continue;

                double sentiment = Utility.analyzeText(nachricht);
                AnalyzedText at = new AnalyzedText(nachricht);

                map.put(nachricht, at);
                totalSentiment += sentiment;
                messageCount++;
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei: " + e.getMessage());
            return;
        }

        if (messageCount > 0) {
            double averageSentiment = totalSentiment / messageCount;
            System.out.println("Anzahl analysierter Nachrichten: " + messageCount);
            System.out.println("Gesamt Sentiment Score: " + totalSentiment);
            System.out.println("Durchschnittlicher Sentiment Score: " + averageSentiment);
        } else {
            System.out.println("Keine Nachrichten zum Verarbeiten gefunden.");
        }
    }
}
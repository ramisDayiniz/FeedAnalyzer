import java.io.IOException;
import java.util.Objects;
/**
 * Die Klasse AnalyzedText analysiert einen gegebenen Text und berechnet dessen Sentiment-Score.
 * Sie implementiert das Interface Comparable, um eine Vergleichbarkeit basierend auf dem Sentiment-Wert zu ermöglichen.
 * @author Ramis Ekici
 * @version 01.04.2025
 */
public class AnalyzedText implements Comparable<AnalyzedText>{
    String text;
    double sentiment;

    /**
     * Konstruktor der {@code AnalyzedText}-Klasse.
     * Erstellt ein neues Objekt und analysiert den Sentiment-Score des übergebenen Textes.
     *
     * @param text Der zu analysierende Text.
     * @throws IOException Falls ein Fehler beim Laden der Sentiment-Daten auftritt.
     */
    public AnalyzedText(String text) throws IOException {
        this.text = text;
    }

    /**
     * Gibt den ursprünglichen Text zurück.
     *
     * @return Der analysierte Text als String.
     */
    public String getText() {
        return text;
    }

    /**
     * Gibt den berechneten Sentiment-Score des Textes zurück.
     *
     * @return Der Sentiment-Score als {@code double}.
     */
    public double getSentiment() {
        return sentiment;
    }

    /**
     * Vergleicht dieses {@code AnalyzedText}-Objekt mit einem anderen basierend auf dem Sentiment-Score.
     *
     * @param o Das zu vergleichende {@code AnalyzedText}-Objekt.
     * @return Eine negative Zahl, wenn dieses Objekt kleiner ist, 0 wenn sie gleich sind, eine positive Zahl, wenn es größer ist.
     */
    @Override
    public int compareTo(AnalyzedText o) {
        // Vergleiche nach dem Sentiment-Score
        if (this.sentiment > o.sentiment) {
            return 1;
        } else if (this.sentiment < o.sentiment) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Überprüft, ob dieses {@code AnalyzedText}-Objekt gleich einem anderen Objekt ist.
     *
     * @param o Das zu vergleichende Objekt.
     * @return {@code true}, wenn die Objekte gleich sind, sonst {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AnalyzedText that)) return false;
        return Double.compare(sentiment, that.sentiment) == 0 && Objects.equals(text, that.text);
    }

    /**
     * Berechnet den Hash-Code dieses Objekts basierend auf dem Text und dem Sentiment-Wert.
     *
     * @return Der berechnete Hash-Code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(text, sentiment);
    }

    /**
     * Gibt eine String-Repräsentation des {@code AnalyzedText}-Objekts zurück.
     *
     * @return Eine String-Darstellung des Objekts.
     */
    @Override
    public String toString() {
        return "AnalyzedText{" +
                "message='" + text + '\'' +
                ", accountName='Potus" + '\'' +
                ", sentimentScore=" + sentiment +
                '}';
    }
}

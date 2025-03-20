package analysis;

import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class NGramStats {
    private final Map<String, Integer> unigrams;
    private final Map<String, Integer> bigrams;
    private final Map<String, Integer> trigrams;
    private int totalChars; 

    public NGramStats() {
        this.unigrams = new HashMap<>();
        this.bigrams = new HashMap<>();
        this.trigrams = new HashMap<>();
        this.totalChars = 0;
    }


    public void exportCsv(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("type,ngram,count\n");

            // Unigrams
            for (Map.Entry<String, Integer> entry : unigrams.entrySet()) {
                writer.write("1," + entry.getKey() + "," + entry.getValue() + "\n");
            }

            // Bigrams
            for (Map.Entry<String, Integer> entry : bigrams.entrySet()) {
                writer.write("2," + entry.getKey() + "," + entry.getValue() + "\n");
            }

            // Trigrams
            for (Map.Entry<String, Integer> entry : trigrams.entrySet()) {
                writer.write("3," + entry.getKey() + "," + entry.getValue() + "\n");
            }

            writer.flush();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier CSV : " + filePath);
            e.printStackTrace();
        }
    }

    public void addUnigram(String c) {
        unigrams.merge(c, 1, Integer::sum);
        totalChars++;
    }

    public void addBigram(String c1, String c2) {
        String key = c1 + c2;
        bigrams.merge(key, 1, Integer::sum);
    }

    public void addTrigram(String c1, String c2, String c3) {
        String key = c1 + c2 + c3;
        trigrams.merge(key, 1, Integer::sum);
    }

    public Map<String, Integer> getUnigrams() {
        return unigrams;
    }

    public Map<String, Integer> getBigrams() {
        return bigrams;
    }

    public Map<String, Integer> getTrigrams() {
        return trigrams;
    }

    public int getTotalChars() {
        return totalChars;
    }
}

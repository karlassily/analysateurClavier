package analysis;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CorpusAnalyzer {


    public void analyzeFile(String filePath, NGramStats stats) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line, stats);
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture fichier : " + filePath);
            e.printStackTrace();
        }
    }


    private void processLine(String line, NGramStats stats) {
        line = line.trim();

        // On va transformer la ligne en séquence de touches physiques
        List<Character> physSeq = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            List<Character> mapped = CaractersBizarres.mapChar(c);
            physSeq.addAll(mapped);
        }

        // Ajout des unigrams
        for (int i = 0; i < physSeq.size(); i++) {
            stats.addUnigram(String.valueOf(physSeq.get(i)));
        }

        // Ajout des bigrams
        for (int i = 0; i < physSeq.size() - 1; i++) {
            String c1 = String.valueOf(physSeq.get(i));
            String c2 = String.valueOf(physSeq.get(i + 1));
            stats.addBigram(c1, c2);
        }

        // Ajout des trigrammes
        for (int i = 0; i < physSeq.size() - 2; i++) {
            String c1 = String.valueOf(physSeq.get(i));
            String c2 = String.valueOf(physSeq.get(i + 1));
            String c3 = String.valueOf(physSeq.get(i + 2));
            stats.addTrigram(c1, c2, c3);
        }
    }
}

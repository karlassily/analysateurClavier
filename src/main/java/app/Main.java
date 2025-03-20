package app;

import analysis.CorpusAnalyzer;
import analysis.NGramStats;
import evaluation.KeyboardEvaluator;
import evaluation.Weights;
import evaluation.WeightsLoader;
import model.KeyboardGeometry;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        //Analyser plusieurs fichiers texte (corpus)
        NGramStats stats = new NGramStats();
        CorpusAnalyzer analyzer = new CorpusAnalyzer();

        String[] corpusFiles = { "exemple2.txt" };

        int nbrThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(nbrThreads);

        for (String file : corpusFiles) {
            executor.submit(() -> {
                analyzer.analyzeFile(file, stats);
            });
        }

        // Arrête l'acceptation de nouvelles tâches et attend la fin de l'exécution
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                System.err.println("Le traitement a pris trop de temps !");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Affichage des stats de base
        System.out.println("Unigrams : " + stats.getUnigrams().size());
        System.out.println("Bigrams : " + stats.getBigrams().size());
        System.out.println("Trigrams : " + stats.getTrigrams().size());
        System.out.println("Total chars : " + stats.getTotalChars());

        // Export des stats dans un fichier CSV
        String csvOutput = "stats.csv";
        stats.exportCsv(csvOutput);
        System.out.println("Fichier CSV généré : " + csvOutput);

        try {
            // Charger la disposition QWERTY OU AZERTY (via JSON)
            // Par exemple QWERTY :
            KeyboardGeometry geometry = KeyboardGeometry.loadFromJson("keyboard_layouts/qwerty.json");
            // Azerty
            // KeyboardGeometry geometry = KeyboardGeometry.loadFromJson("keyboard_layouts/azerty.json");

            // 4) Charger les poids (fichier weights.json) 
            Weights weights = WeightsLoader.loadWeights("keyboard_layouts/weights.json");

            // 5) Évaluer la disposition
            KeyboardEvaluator evaluator = new KeyboardEvaluator();
            double score = evaluator.evaluate(stats, geometry, weights);
            System.out.println("Score final de la disposition : " + score);

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement JSON : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package test.java.app;

import analysis.NGramStats;
import evaluation.KeyboardEvaluator;
import evaluation.Weights;
import evaluation.WeightsLoader;
import model.KeyboardGeometry;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegrationTest {

    @Test
    void testFullEvaluation() throws IOException {
        KeyboardGeometry geometry = KeyboardGeometry.loadFromJson("keyboard_layouts/qwerty.json");
        Weights weights = WeightsLoader.loadWeights("keyboard_layouts/weights.json");

        NGramStats stats = new NGramStats();
        stats.addUnigram("f");
        stats.addUnigram("j");
        stats.addUnigram("a");

        stats.addBigram("f","j"); 
        stats.addBigram("f","j");
        stats.addBigram("a","a");

        KeyboardEvaluator evaluator = new KeyboardEvaluator();
        double score = evaluator.evaluate(stats, geometry, weights);

        System.out.println("Score obtenu = " + score);

        assertTrue(score >= 0, "Le score doit être positif.");
    }
}

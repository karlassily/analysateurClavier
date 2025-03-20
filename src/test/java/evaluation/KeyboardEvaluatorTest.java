package evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Key;
import org.junit.jupiter.api.Test;

import evaluation.KeyboardEvaluator;
import evaluation.MovementType;

public class KeyboardEvaluatorTest {

    @Test
    void testClassifyBigram_SFB() {
        Key k1 = new Key("a", "LEFT_PINKY", 1, 0);
        Key k2 = new Key("q", "LEFT_PINKY", 1, 1);

        KeyboardEvaluator evaluator = new KeyboardEvaluator();
        MovementType type = evaluator.bigramTestClassifier(k1, k2);

        assertEquals(MovementType.SFB, type);
    }

    @Test
    void testClassifyBigram_ALTERNANCE() {
        Key k1 = new Key("f", "LEFT_INDEX", 1, 3);
        Key k2 = new Key("j", "RIGHT_INDEX", 1, 6);

        KeyboardEvaluator evaluator = new KeyboardEvaluator();
        MovementType type = evaluator.bigramTestClassifier(k1, k2);

        assertEquals(MovementType.ALTERNANCE, type);
    }

    @Test
    void testClassifyTrigram_REDIRECTION() {
        Key k1 = new Key("j", "RIGHT_INDEX", 1, 6);
        Key k2 = new Key("k", "RIGHT_MIDDLE", 1, 7);
        Key k3 = new Key("j", "RIGHT_INDEX", 1, 6);

        KeyboardEvaluator evaluator = new KeyboardEvaluator();
        MovementType type = evaluator.trigramTestClassifier(k1, k2, k3);

        assertEquals(MovementType.REDIRECTION, type);
    }

    @Test
void testAlternanceBigram() {
    Key k1 = new Key("f", "LEFT_INDEX", 1, 3);
    Key k2 = new Key("j", "RIGHT_INDEX", 1, 6);

    KeyboardEvaluator evaluator = new KeyboardEvaluator();
    MovementType result1 = evaluator.classifyBigram(k1, k2);
    MovementType result2 = evaluator.classifyBigram(k2, k1);

    // Assert
    assertEquals(MovementType.ALTERNANCE, result1, "Le mouvement f -> j doit être une alternance.");
    assertEquals(MovementType.ALTERNANCE, result2, "Le mouvement j -> f doit être une alternance.");
}

}

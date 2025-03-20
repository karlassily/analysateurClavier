package evaluation;

import analysis.NGramStats;
import model.Key;
import model.KeyboardGeometry;

public class KeyboardEvaluator {

    public double evaluate(NGramStats stats, KeyboardGeometry geometry, Weights weights) {
        double totalScore = 0.0;

        for (var entry : stats.getBigrams().entrySet()) {
            String bigram = entry.getKey();
            int freq = entry.getValue();

            if (bigram.length() < 2) continue;

            char c1 = bigram.charAt(0);
            char c2 = bigram.charAt(1);

            Key k1 = geometry.findKeyByChar(c1);
            Key k2 = geometry.findKeyByChar(c2);

            if (k1 == null || k2 == null) {
                continue;
            }

            MovementType mt = classifyBigram(k1, k2);

            double cost = getCost(mt, weights) * freq;
            for (int i = 0; i < freq; i++) {
                System.out.println("Mouvement détecté : " + mt + " | Coût : " + getCost(mt, weights));
            }
            totalScore += cost;
        }

        for (var entry : stats.getTrigrams().entrySet()) {
            String trigram = entry.getKey();
            int freq = entry.getValue();

            if (trigram.length() < 3) continue;

            char c1 = trigram.charAt(0);
            char c2 = trigram.charAt(1);
            char c3 = trigram.charAt(2);

            Key k1 = geometry.findKeyByChar(c1);
            Key k2 = geometry.findKeyByChar(c2);
            Key k3 = geometry.findKeyByChar(c3);

            if (k1 == null || k2 == null || k3 == null) {
                continue;
            }

            MovementType mt = classifyTrigram(k1, k2, k3);
            double cost = getCost(mt, weights) * freq;
            for (int i = 0; i < freq; i++) {
                System.out.println("Mouvement détecté : " + mt + " | Coût : " + getCost(mt, weights));
            }
            totalScore += cost;
        }

        double normalization = (stats.getTotalChars() > 0) ? stats.getTotalChars() : 1;
        return totalScore / normalization;
    }

    public MovementType classifyBigram(Key k1, Key k2) {
        boolean sameHand = (k1.isLeftHand() == k2.isLeftHand());
        int rowDiff = k2.row() - k1.row();
        int absRowDiff = Math.abs(rowDiff);
        
        if (sameHand && absRowDiff == 2) {
            return MovementType.CIS;
        }
    
        if (k1.finger().equals(k2.finger())) {
            int rd = Math.abs(k1.row() - k2.row());
            int cd = Math.abs(k1.col() - k2.col());
            if (rd >= 1 || cd >= 2) {
                return MovementType.LSB;
            } else {
                return MovementType.SFB;
            }
        }
    
        if (sameHand && !k1.finger().equals(k2.finger())) {
            return MovementType.ROLLING;
        }
    
        if (!sameHand) {
            return MovementType.ALTERNANCE;
        }
    
        return MovementType.OTHER;
    }
    
    public MovementType classifyTrigram(Key k1, Key k2, Key k3) {
        int colDir12 = Integer.compare(k2.col(), k1.col()); // -1, 0 ou +1
        int colDir23 = Integer.compare(k3.col(), k2.col());
        
        boolean sameHand = (k1.isLeftHand() == k2.isLeftHand() && k2.isLeftHand() == k3.isLeftHand());
        if (sameHand && colDir12 != 0 && colDir23 != 0 && colDir12 != colDir23) {
            return MovementType.REDIRECTION;
        }
 
        if (k1.finger().equals(k3.finger()) && (k1.isLeftHand() != k2.isLeftHand())) {
            return MovementType.SKIPGRAM;
        }
        
        return MovementType.OTHER;
    }
    

    public MovementType bigramTestClassifier(Key k1, Key k2) {
        return classifyBigram(k1, k2);
    }

    public MovementType trigramTestClassifier(Key k1, Key k2, Key k3) {
        return classifyTrigram(k1, k2, k3);
    }

    private double getCost(MovementType type, Weights w) {
        double cost;
    
        switch (type) {
            case SFB:
                cost = w.SFB;
                break;
            case LSB:
                cost = w.LSB;
                break;
            case CIS:
                cost = w.CIS;
                break;
            case REDIRECTION:
                cost = w.REDIRECTION;
                break;
            case SKIPGRAM:
                cost = w.SKIPGRAM;
                break;
            case ALTERNANCE:
                cost = w.ALTERNANCE;
                break;
            case ROLLING:
                cost = w.ROLLING;
                break;
            default:
                cost = w.OTHER;
        }
    
        return cost;
    }
}

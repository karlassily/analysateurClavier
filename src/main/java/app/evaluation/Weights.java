package evaluation;

public class Weights {
    public double SFB;         // Same-Finger Bigram
    public double LSB;         // Lateral Stretch Bigram
    public double CIS;         // "Ciseaux"
    public double REDIRECTION; // Redirection dans les trigrammes
    public double SKIPGRAM;    // Same-Finger Skipgram
    public double ALTERNANCE;  // Alternance main gauche/main droite
    public double ROLLING;     // Roulement
    public double OTHER;       // Coût par défaut

    @Override
    public String toString() {
        return "Weights{" +
               "SFB=" + SFB +
               ", LSB=" + LSB +
               ", CIS=" + CIS +
               ", REDIRECTION=" + REDIRECTION +
               ", SKIPGRAM=" + SKIPGRAM +
               ", ALTERNANCE=" + ALTERNANCE +
               ", ROLLING=" + ROLLING +
               ", OTHER=" + OTHER +
               '}';
    }
}

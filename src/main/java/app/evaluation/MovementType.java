package evaluation;

public enum MovementType {
    SFB,        // Same-Finger Bigram
    LSB,        // Lateral Stretch Bigram
    CIS,        // Ciseaux
    REDIRECTION,// Redirection (dans un trigram)
    SKIPGRAM,   // Same-Finger Skipgram (trigram)
    ALTERNANCE, // Alternance main G/D
    ROLLING,    // Roulement
    OTHER       // Mouvement par défaut
}

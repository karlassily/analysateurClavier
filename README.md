ASSILY Karl 22101183
GEBRAYEL Maroun 22218173

# Projet CPOO5 - Analyseur de texte et Évaluateur de disposition de clavier

## Description

Ce projet implémente :
- Un **analyseur de corpus** (fréquences de 1-grammes, 2-grammes, 3-grammes)
- Un **évaluateur de disposition** (via la classe `KeyboardEvaluator`) permettant de calculer un score pour un clavier donné

## Structure du projet

- `src/main/java/...`
  - `analysis/` : contient la logique de comptage des N-grammes (`CorpusAnalyzer`, `NGramStats`).
  - `evaluation/` : contient la logique d’évaluation (`KeyboardEvaluator`, `MovementType`, `Weights`, `WeightsLoader`).
  - `model/` : contient la gestion des claviers, touches, layout (`KeyboardGeometry`, `Key`, etc.) (qui n'est pas terminée)
  - `app/` : contient la classe principale `Main`

- `src/test/java/...`
  - Tests unitaires et d’intégration (exemples `KeyboardEvaluatorTest`, etc.).

- `keyboard_layouts/` : exemples de configuration JSON de clavier (`qwerty.json`, `azerty.json`), et un fichier `weights.json` pour configurer les pénalités/poids.

## Prérequis

Pour compiler, exécuter et tester le projet, utilisez les commandes suivantes à partir du répertoire racine du projet :

Compiler et exécuter le projet :
./gradlew run

Lancer les tests unitaires :
./gradlew test

## Utilisation des fichiers JSON dans Main

Dans la classe principale Main, nous utilisons des fichiers JSON pour charger les configurations du clavier et les poids d’évaluation. Voici un résumé de la logique :

Corpus : Le programme analyse un ou plusieurs fichiers texte, précisés dans le tableau corpusFiles. Par défaut, le fichier utilisé est exemple.txt.

Disposition de clavier : Le fichier JSON correspondant à la disposition de clavier est chargé via la méthode KeyboardGeometry.loadFromJson. Deux exemples de dispositions sont fournis :

keyboard_layouts/qwerty.json

keyboard_layouts/azerty.json

Poids d’évaluation : Les poids utilisés pour calculer le score final sont extraits du fichier weights.json, via la classe WeightsLoader.

Exemple de logique dans Main

try {
    // Charger la disposition QWERTY OU AZERTY (via JSON)
    KeyboardGeometry geometry = KeyboardGeometry.loadFromJson("keyboard_layouts/qwerty.json");
    // Charger les poids (fichier weights.json)
    Weights weights = WeightsLoader.loadWeights("keyboard_layouts/weights.json");

    // Évaluer la disposition
    KeyboardEvaluator evaluator = new KeyboardEvaluator();
    double score = evaluator.evaluate(stats, geometry, weights);
    System.out.println("Score final de la disposition : " + score);

} catch (IOException e) {
    System.err.println("Erreur lors du chargement JSON : " + e.getMessage());
    e.printStackTrace();
}

Après l'exécution du programme, un fichier stats.csv est généré dans le répertoire racine. Ce fichier contient la liste des N-grammes extraits du corpus, suivie de leur fréquence d'apparition respective, au format : type,ngram,count.


## Arborescence

src
├── main
│   └── java
│       └── app
│           ├── Main.java
│           ├── analysis
│           │   ├── CaractersBizarres.java
│           │   ├── CorpusAnalyzer.java
│           │   └── NGramStats.java
│           ├── evaluation
│           │   ├── KeyboardEvaluator.java
│           │   ├── MovementType.java
│           │   ├── WeightsLoader.java
│           │   ├── Movement.java
│           │   └── Weights.java
│           └── model
│               ├── Key.java
│               ├── KeyboardGeometry.java
│               └── Layout.java
└── test
    └── java
        ├── app
        │   └── IntegrationTest.java
        └── evaluation
            └── KeyboardEvaluatorTest.java

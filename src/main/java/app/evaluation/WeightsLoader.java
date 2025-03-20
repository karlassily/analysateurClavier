package evaluation;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe utilitaire pour charger un fichier weights.json
 */
public class WeightsLoader {

    public static Weights loadWeights(String filePath) throws IOException {
        Gson gson = new Gson();

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            // Transforme le JSON en objet Weights
            Weights w = gson.fromJson(reader, Weights.class);
            return w;
        }
    }
}

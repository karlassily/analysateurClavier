package model;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class KeyboardGeometry {
    private List<Key> keys = new ArrayList<>();

    public static KeyboardGeometry loadFromJson(String filePath) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            KeyboardGeometryData data = gson.fromJson(reader, KeyboardGeometryData.class);

            KeyboardGeometry geometry = new KeyboardGeometry();
            for (KeyData kd : data.keys) {
                // On crée la Key
                Key k = new Key(
                    kd.ch,
                    kd.finger,
                    kd.row,
                    kd.col
                );
                geometry.keys.add(k);
            }
            return geometry;
        }
    }

    public Key findKeyByChar(char c) {
        for (Key k : keys) {
            if (k.keyId().equals(String.valueOf(c))) {
                return k;
            }
        }
        return null;
    }

    public List<Key> getKeys() {
        return keys;
    }
}

class KeyboardGeometryData {
    String name;
    List<KeyData> keys;
}

class KeyData {
    String ch;       // "q", "a", etc.
    String finger;   // "LEFT_PINKY", ...
    int row;
    int col;
}

package evaluation;

import model.Key;
import java.util.List;

public final class Movement {
    private final List<Key> keys;
    private final MovementType type;

    public Movement(List<Key> keys, MovementType type) {
        this.keys = List.copyOf(keys);
        this.type = type;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public MovementType getType() {
        return type;
    }
}

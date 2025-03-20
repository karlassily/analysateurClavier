package evaluation;

import model.Key;
import java.util.List;

public final class SimpleMovement implements MovementInterface {
    private final List<Key> keys;
    private final MovementType movementType;

    public SimpleMovement(List<Key> keys, MovementType movementType) {
        this.keys = List.copyOf(keys);
        this.movementType = movementType;
    }

    @Override
    public MovementType getMovementType() {
        return movementType;
    }

    @Override
    public List<Key> getKeys() {
        return keys;
    }
}

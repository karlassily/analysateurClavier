package evaluation;

import model.Key;
import java.util.List;

public sealed interface MovementInterface permits SimpleMovement {
    MovementType getMovementType();
    List<Key> getKeys();
}

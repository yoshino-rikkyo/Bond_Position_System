package simplex.bondpositionsystem.model;

import java.util.List;
import java.util.Map;


public interface PositionRepository {

    void save(Position position);

    Position getPosition(String code);

    List<Position> getPositions();

    Map<String, Position> getPositionsMap();

    void remove(String code);

}

package simplex.bondpositionsystem.model;

import java.util.List;
import java.util.Map;

public interface BondRepository {

    Bond getBond(String code);

    List<Bond> getBonds();

    Map<String, Bond> getBondsMap();
}

package simplex.bondpositionsystem.adapter;

import simplex.bondpositionsystem.model.Bond;
import simplex.bondpositionsystem.model.BondRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BondRepositoryImpl implements BondRepository {
    private final Map<String, Bond> bonds;

    public BondRepositoryImpl() {

        BondLoader loader = new BondLoader();
        bonds = loader.load("src/main/resources/BondData.csv");
    }

    @Override
    public Bond getBond(String code) {
        return bonds.get(code);
    }

    @Override
    public List<Bond> getBonds() {
        List<Bond> bondsList = new ArrayList<>(bonds.values());
        return bondsList;
    }

    @Override
    public Map<String, Bond> getBondsMap(){
        return bonds;
    }

}

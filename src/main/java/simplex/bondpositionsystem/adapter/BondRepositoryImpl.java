package simplex.bondpositionsystem.adapter;

import simplex.bondpositionsystem.model.Bond;
import simplex.bondpositionsystem.model.BondRepository;

import java.util.ArrayList;
import java.util.Comparator;
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

        BondComparator bondComparator = new BondComparator();
        bondsList.sort(bondComparator);

        /*
        ラムダ式。以下２つは同じ意味
        利点：comparatorクラスを作らずに、sortできる。つまり、見た目が綺麗でわかりやすい！
        bondsList.sort(Comparator.comparing(bond -> bond.getCode()));

        bondsList.sort(Comparator.comparing(Bond::getCode));

         */
        return bondsList;
    }

    public class BondComparator implements Comparator<Bond> {

        @Override
        public int compare(Bond o1, Bond o2) {
            String code1 = o1.getCode();
            String code2 = o2.getCode();
            return code1.compareTo(code2);
        }
    }
}

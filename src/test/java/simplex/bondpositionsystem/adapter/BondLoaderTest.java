package simplex.bondpositionsystem.adapter;

import org.junit.Test;
import simplex.bondpositionsystem.model.Bond;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class BondLoaderTest {

    @Test
    public void testLoad() {
        Map<String, Bond> bonds = new HashMap<>();
        BigDecimal bd1 = new BigDecimal("2.0");
        bonds.put("001",new Bond("001","toyota",bd1,20251212,2));

        BigDecimal bd2 = new BigDecimal("1.0");
        bonds.put("002", new Bond("002","nissan",bd2,20301212,2));

        BigDecimal bd3 = new BigDecimal("1.5");
        bonds.put("003", new Bond("003","suzuki",bd3,20291031,2));

        BigDecimal bd4 = new BigDecimal("1.2");
        bonds.put("aaa", new Bond("aaa","subal",bd4,20451212,2));

        BigDecimal bd5 = new BigDecimal("1.3");
        bonds.put("0aa", new Bond("0aa","daihatu",bd5,20291231,2));

        BigDecimal bd6 = new BigDecimal("1.5");
        bonds.put("あ", new Bond("あ","bentu",bd6,20501111,2));

        BigDecimal bd7 = new BigDecimal("1.4");
        bonds.put("か", new Bond("か","bmw",bd7,20251010,2));

        BigDecimal bd8 = new BigDecimal("1.2");
        bonds.put("さ", new Bond("さ","wargen",bd8,20220303,2));



        BondLoader bondLoader1 = new BondLoader();

        assertThat(bondLoader1.load("src/main/resources/BondData.csv")).isEqualTo(bonds);
        //import static org.junit.Assert.*;のときは
        //assertEquals(bonds, bondLoader1.load("src/main/resources/BondData.csv"));でテストする。
    }
}


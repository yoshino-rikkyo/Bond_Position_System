package simplex.bondpositionsystem.adapter;

import org.junit.Test;
import simplex.bondpositionsystem.model.MarketPrice;
import simplex.bondpositionsystem.model.MarketPriceRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class MarketPriceRepositoryImplTest {

    @Test
    public void TestLoad()throws IOException {
        Path resource = Paths.get("src/main/resources/MarketPriceData.csv");
        Path out = Paths.get("work/test/MarketPriceDataLoad.csv");
        Files.copy(resource, out, StandardCopyOption.REPLACE_EXISTING);
        MarketPriceRepository marketPriceRepository = new MarketPriceRepositoryImpl("work/test/MarketPriceDataLoad.csv");
        Map<String, MarketPrice> marketPrices = new HashMap<>();
        marketPrices.put("aaa", new MarketPrice("aaa", new BigDecimal("1000")));
        marketPrices.put("0aa", new MarketPrice("0aa", new BigDecimal("900")));
        marketPrices.put("001", new MarketPrice("001", new BigDecimal("1000")));
        marketPrices.put("002", new MarketPrice("002", new BigDecimal("900")));
        marketPrices.put("003", new MarketPrice("003", new BigDecimal("800")));
        marketPrices.put("さん", new MarketPrice("さん", new BigDecimal("1000")));
        marketPrices.put("いち", new MarketPrice("いち", new BigDecimal("900")));
        marketPrices.put("に", new MarketPrice("に", new BigDecimal("800")));
        assertThat(marketPriceRepository.getMarketPriceMap()).isEqualTo(marketPrices);

    }

    @Test
    public void save()throws IOException {
        Path resource = Paths.get("src/main/resources/MarketPriceData.csv");
        Path out = Paths.get("work/test/MarketPriceDataSave.csv");
        Files.copy(resource, out, StandardCopyOption.REPLACE_EXISTING);
        //テストしたいメソッドが、テスト用にコピーしたフォルダを読みにいって、mapができている。
        MarketPriceRepository marketPriceRepository = new MarketPriceRepositoryImpl("work/test/MarketPriceDataSave.csv");
        //saveの引数に001コードのMarketPriceを追加する。1000 → 100になり、それ以外のコードは変わっていないはず。
        MarketPrice marketPrice = new MarketPrice("001", new BigDecimal("100"));
        marketPriceRepository.save(marketPrice);
        //コード001のみ、1000 → 100になり、それ以外のコードは変わらない。
        Map<String, MarketPrice> marketPrices = new HashMap<>();
        marketPrices.put("aaa", new MarketPrice("aaa", new BigDecimal("1000")));
        marketPrices.put("0aa", new MarketPrice("0aa", new BigDecimal("900")));
        marketPrices.put("001", new MarketPrice("001", new BigDecimal("100")));
        marketPrices.put("002", new MarketPrice("002", new BigDecimal("900")));
        marketPrices.put("003", new MarketPrice("003", new BigDecimal("800")));
        marketPrices.put("さん", new MarketPrice("さん", new BigDecimal("1000")));
        marketPrices.put("いち", new MarketPrice("いち", new BigDecimal("900")));
        marketPrices.put("に", new MarketPrice("に", new BigDecimal("800")));

        //test
        assertThat(marketPriceRepository.getMarketPriceMap()).isEqualTo(marketPrices);
    }

}
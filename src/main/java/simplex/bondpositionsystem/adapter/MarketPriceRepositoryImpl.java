package simplex.bondpositionsystem.adapter;

import simplex.bondpositionsystem.model.MarketPrice;
import simplex.bondpositionsystem.model.MarketPriceRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MarketPriceRepositoryImpl implements MarketPriceRepository {

    private final Map<String, MarketPrice> marketPrices;

    public MarketPriceRepositoryImpl() {

        marketPrices = load();
    }


    public Map<String, MarketPrice> load() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/MarketPriceData.csv"))) {
            String text;
            Map<String, MarketPrice> marketPrices = new HashMap<>();
            while ((text = reader.readLine()) != null) {

                MarketPrice marketPrice = parse(text);
                marketPrices.put(marketPrice.getCode(), marketPrice);

            }

            return marketPrices;

        } catch (IOException e) {
            System.out.println("ファイルの読み込み時にエラーが発生しました。");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(MarketPrice marketPrice) {
        marketPrices.put(marketPrice.getCode(), marketPrice);
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get("src/main/resources/MarketPriceData.csv")))) {
            for (MarketPrice m : marketPrices.values()) {
                //ファイルの更新
                writer.println(format(m));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //文字列解釈（load)
    private MarketPrice parse(String text) {
        String[] arrayText = text.split(",");
        String code = arrayText[0];
        BigDecimal marketPrice = new BigDecimal(arrayText[1]);

        return new MarketPrice(code, marketPrice);
    }

    //文字列解釈（save)
    private String format(MarketPrice marketPrice) {
        return String.format("%s,%s", marketPrice.getCode(), marketPrice.getMarketPrice());
    }

    @Override
    public MarketPrice getMarketPrice(String code) {
        return marketPrices.get(code);
    }
}

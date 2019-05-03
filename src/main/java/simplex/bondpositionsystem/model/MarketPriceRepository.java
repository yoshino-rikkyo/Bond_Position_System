package simplex.bondpositionsystem.model;


import java.util.Map;

public interface MarketPriceRepository {

    void save(MarketPrice marketPrice);

    MarketPrice getMarketPrice(String code);

    Map<String, MarketPrice> getMarketPriceMap();



}

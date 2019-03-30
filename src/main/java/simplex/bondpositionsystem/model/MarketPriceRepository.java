package simplex.bondpositionsystem.model;


public interface MarketPriceRepository {

    void save(MarketPrice marketPrice);

    MarketPrice getMarketPrice(String code);



}

package simplex.bondpositionsystem.model;

import java.math.BigDecimal;

public class MarketPrice {
    private final String code;
    private final BigDecimal marketPrice;


    public MarketPrice(String code, BigDecimal marketPrice) {
        if (code == null) {
            throw new IllegalArgumentException("codeがnullです");
        } else if (marketPrice == null) {
            throw new IllegalArgumentException("marketPriceがnullです");
        }

        this.code = code;
        this.marketPrice = marketPrice;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    @Override
    public String toString() {
        return String.format("MarketPrice(code=%s,marketPrice=%s)", code, marketPrice);
    }
}

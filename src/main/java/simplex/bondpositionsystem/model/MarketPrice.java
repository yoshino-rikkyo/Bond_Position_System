package simplex.bondpositionsystem.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.MatchResult;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MarketPrice)) {
            return false;
        }
        MarketPrice objMarketPrice = (MarketPrice) obj;
        if (this.code.equals(objMarketPrice.code) && this.marketPrice.compareTo(objMarketPrice.marketPrice) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, marketPrice);
    }


}

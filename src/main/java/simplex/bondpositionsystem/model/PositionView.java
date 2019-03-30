package simplex.bondpositionsystem.model;

import java.math.BigDecimal;

public class PositionView {
    private final String code;
    private final String name;
    private final BigDecimal rate;
    private final int maturity;
    private final int couponCount;
    private final BigDecimal amount;
    private final BigDecimal bookValue;
    private final BigDecimal marketPrice;
    private final BigDecimal profitAndLoss;

    public PositionView(String code, String name, BigDecimal rate, int maturity, int couponCount,
                        BigDecimal amount, BigDecimal bookValue, BigDecimal marketPrice, BigDecimal profitAndLoss) {
        this.code = code;
        this.name = name;
        this.rate = rate;
        this.maturity = maturity;
        this.couponCount = couponCount;
        this.amount = amount;
        this.bookValue = bookValue;
        this.marketPrice = marketPrice;
        this.profitAndLoss = profitAndLoss;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public int getMaturity() {
        return maturity;
    }

    public int getCouponCount() {
        return couponCount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBookValue() {
        return bookValue;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public BigDecimal getProfitAndLoss() {
        return profitAndLoss;
    }
}

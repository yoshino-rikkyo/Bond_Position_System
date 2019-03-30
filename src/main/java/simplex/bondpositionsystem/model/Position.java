package simplex.bondpositionsystem.model;

import java.math.BigDecimal;

public class Position {
    private final String code;
    private final BigDecimal amount;
    private final BigDecimal bookValue;


    public Position(String code, BigDecimal amount, BigDecimal bookValue) {
        if (code == null) {
            throw new IllegalArgumentException("codeがnullです。");
        } else if (amount == null) {
            throw new IllegalArgumentException("amountがnullです。");
        } else if (bookValue == null) {
            throw new IllegalArgumentException("bookValueがnullです。");
        }

        this.code = code;
        this.amount = amount;
        this.bookValue = bookValue;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBookValue() {
        return bookValue;
    }

    @Override
    public String toString() {
        return String.format("Position(code=%s,amount=%s,bookValue=%s)", code, amount, bookValue);
    }
}

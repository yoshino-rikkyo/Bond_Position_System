package simplex.bondpositionsystem.model;

import jdk.jfr.Percentage;

import java.math.BigDecimal;
import java.util.Objects;

public class Bond {
    private final String code; //Not null
    private final String name; //Not null
    private final BigDecimal rate; //Not null
    private final int maturity;
    private final int couponCount;


    public Bond(String code, String name, BigDecimal rate, int maturity, int couponCount) {
        if (code == null) {
            throw new IllegalArgumentException("codeがnullです。");
        } else if (name == null) {
            throw new IllegalArgumentException("nameがnullです。");
        } else if (rate == null) {
            throw new IllegalArgumentException("rateがnullです。");
        } else if ((maturity < 20000101 || maturity > 29991231)) {
            throw new IllegalArgumentException("maturityは200010101以上、29991231以下にしてください。");
        }

        this.code = code;
        this.name = name;
        this.rate = rate;
        this.maturity = maturity;
        this.couponCount = couponCount;
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

    @Override
    public String toString() {
        return String.format("Bond(code=%s,name=%s,rate=%s,maturity=%d,couponCount=%d)", code, name, rate, maturity, couponCount);
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Bond)){
            return false;
        }
        Bond objBond = (Bond)obj;
        if(this.code.equals(objBond.code) && this.name.equals(objBond.name) && this.rate.compareTo(objBond.rate)==0 &&
                this.maturity == objBond.maturity && this.couponCount == objBond.couponCount){
            return true;
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(code,name, rate, maturity, couponCount);
    }
}

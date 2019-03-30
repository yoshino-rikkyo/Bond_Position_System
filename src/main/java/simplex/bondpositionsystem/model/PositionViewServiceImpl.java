package simplex.bondpositionsystem.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//評価損益も計算して、一覧表示するために必要な情報を実装する
public class PositionViewServiceImpl implements PositionViewService {

    private final BondRepository bondRepository;
    private final PositionRepository positionRepository;
    private final MarketPriceRepository marketPriceRepository;

    public PositionViewServiceImpl(BondRepository bondRepository, PositionRepository positionRepository, MarketPriceRepository marketPriceRepository) {
        this.bondRepository = bondRepository;
        this.positionRepository = positionRepository;
        this.marketPriceRepository = marketPriceRepository;
    }


    @Override
    public List<PositionView> positionView() {
        List<PositionView> positions = new ArrayList<>();
        for (Bond bond : bondRepository.getBonds()) {
            String code = bond.getCode();
            String name = bond.getName();
            BigDecimal rate = bond.getRate();
            int maturity = bond.getMaturity();
            int couponCount = bond.getCouponCount();
            //コードを頼りにPositionをget(Listで帰ってくるやつは使わない。)
            Position position = positionRepository.getPosition(code);
            //同様に、コードを頼りにMarketPriceをget
            MarketPrice marketPriceClass = marketPriceRepository.getMarketPrice(code);
            BigDecimal amount;
            BigDecimal bookValue;
            if (position == null) {
                amount = BigDecimal.ZERO;
                bookValue = BigDecimal.ZERO;
            } else {
                amount = position.getAmount();
                bookValue = position.getBookValue();
            }

            BigDecimal marketPrice;
            BigDecimal profitAndLoss;
            if (marketPriceClass == null) {
                marketPrice = null;
                profitAndLoss = null;
            } else {
                marketPrice = marketPriceClass.getMarketPrice();
                profitAndLoss = marketPrice.subtract(bookValue).multiply(amount);
            }

            PositionView positionView = new PositionView(code, name, rate, maturity, couponCount,
                    amount, bookValue, marketPrice, profitAndLoss);
            positions.add(positionView);

        }
        return positions;
    }
}

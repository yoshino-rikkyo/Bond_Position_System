package simplex.bondpositionsystem.model;


import java.math.BigDecimal;

public class MarketPriceManagementServiceImpl implements MarketPriceManagementService {
    private final BondRepository bondRepository;
    private final MarketPriceRepository marketPriceRepository;

    public MarketPriceManagementServiceImpl(BondRepository bondRepository, MarketPriceRepository marketPriceRepository) {
        this.bondRepository = bondRepository;
        this.marketPriceRepository = marketPriceRepository;
    }


    @Override
    public void registerMarketPrice(String code, String inputMarketPrice) throws UserInputException {
        if (!isNumber(inputMarketPrice)) {
            throw new UserInputException("数字を入力してください。");
        }
        BigDecimal marketPrice = new BigDecimal(inputMarketPrice);
        if (marketPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new UserInputException("時価にマイナスの値は不適切です。");
        }
        MarketPrice marketPriceObject = new MarketPrice(code, marketPrice);
        marketPriceRepository.save(marketPriceObject);
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

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
        if (isNumber(inputMarketPrice)) {
            BigDecimal marketPrice = new BigDecimal(inputMarketPrice);
            MarketPrice marketPriceObject = new MarketPrice(code, marketPrice);
            marketPriceRepository.save(marketPriceObject);
        } else {
            String message = "数字を入力してください。";
            throw new UserInputException(message);
        }
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

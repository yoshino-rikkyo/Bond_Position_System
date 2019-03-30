package simplex.bondpositionsystem.model;

import java.math.BigDecimal;

public interface MarketPriceManagementService {
    void registerMarketPrice(String code, String inputMarketPrice) throws UserInputException;
}

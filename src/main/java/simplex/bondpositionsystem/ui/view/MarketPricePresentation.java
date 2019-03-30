package simplex.bondpositionsystem.ui.view;

import simplex.bondpositionsystem.model.Bond;
import simplex.bondpositionsystem.model.BondRepository;
import simplex.bondpositionsystem.model.MarketPriceManagementService;
import simplex.bondpositionsystem.model.UserInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MarketPricePresentation {

    private final BondRepository bondRepository;
    private final MarketPriceManagementService marketPriceManagementService;

    public MarketPricePresentation(BondRepository bondRepository, MarketPriceManagementService marketPriceManagementService) {
        this.bondRepository = bondRepository;
        this.marketPriceManagementService = marketPriceManagementService;
    }


    public void managemeMarketPrice() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (Bond bond : bondRepository.getBonds()) {
                System.out.println("銘柄コード : " + bond.getCode() + "名称 : " + bond.getName());
                System.out.println("銘柄に対応した時価を入力してください。");
                String inputMarketPrice = br.readLine();
                marketPriceManagementService.registerMarketPrice(bond.getCode(), inputMarketPrice);

            }
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (UserInputException e) {
            System.out.println(e.getMessage());
            System.out.println("現在の入力以外は在庫データに反映しました。");
        }

    }

}

package simplex.bondpositionsystem.ui.view;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    //Mainにて、PositionProducerをPositionProducerImplに実装させている前提
    private final PositionPresentation positionPresentation;
    private final PositionViewPresentation positionViewPresentation;
    private final MarketPricePresentation marketPricePresentation;

    public Menu(PositionPresentation positionPresentation, MarketPricePresentation marketPricePresentation, PositionViewPresentation positionViewPresentation) {
        this.positionPresentation = positionPresentation;
        this.positionViewPresentation = positionViewPresentation;
        this.marketPricePresentation = marketPricePresentation;
    }


    public void unitedMenu() {
        boolean isContinue = true;
        while(isContinue){
            System.out.println("「1」在庫データの入力\n「2」値洗い\n「3」保有銘柄残高一覧表示\n「0」終了");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                String menuSelect = br.readLine();

                if (menuSelect.equals("1")) {
                    positionPresentation.managePosition();
                }
                else if (menuSelect.equals("2")) {
                    marketPricePresentation.managemeMarketPrice();
                } else if (menuSelect.equals("3")) {
                    positionViewPresentation.doPresentation();
                } else if (menuSelect.equals("0")) {
                    isContinue = false;
                }

            } catch (IOException e) {

            }
        }

    }
}

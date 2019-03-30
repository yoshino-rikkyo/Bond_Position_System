package simplex.bondpositionsystem.ui.view;


import simplex.bondpositionsystem.model.PositionView;
import simplex.bondpositionsystem.model.PositionViewService;

import java.math.RoundingMode;
import java.util.List;

public class PositionViewPresentation {
    private final PositionViewService positionViewService;

    public PositionViewPresentation(PositionViewService positionViewService) {
        this.positionViewService = positionViewService;
    }


    public void doPresentation() {
        String[] table = {"code", "name", "rate", "maturity", "couponCount", "amount", "bookValue", "marketPrice", "profitAndLoss"};
        for (int i = 0; i < table.length; i++) {
            System.out.print("|" + String.format("%1$-12s", table[i]));
        }
        System.out.println("|");

        List<PositionView> positions = positionViewService.positionView();
        for (PositionView position : positions) {
            if (position.getMarketPrice() == null && position.getProfitAndLoss() == null) {
                System.out.println(String.format("|%-12s|%-12s|%12s|%12d|%12d|%12s|%12s|%-12s|%-12s|",
                        position.getCode(), position.getName(), position.getRate().setScale(3, RoundingMode.DOWN), position.getMaturity(), position.getCouponCount(),
                        position.getAmount().setScale(3, RoundingMode.DOWN), position.getBookValue().setScale(3, RoundingMode.DOWN),
                        "NA", "NA"));
            } else {
                System.out.println(String.format("|%-12s|%-12s|%12s|%12d|%12d|%12s|%12s|%12s|%12s|",
                        position.getCode(), position.getName(), position.getRate().setScale(3, RoundingMode.DOWN), position.getMaturity(), position.getCouponCount(),
                        position.getAmount().setScale(3, RoundingMode.DOWN), position.getBookValue().setScale(3, RoundingMode.DOWN),
                        position.getMarketPrice().setScale(3, RoundingMode.DOWN), position.getProfitAndLoss().setScale(3, RoundingMode.DOWN)));

            }

        }
    }
}

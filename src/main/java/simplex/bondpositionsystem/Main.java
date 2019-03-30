package simplex.bondpositionsystem;

import simplex.bondpositionsystem.adapter.*;
import simplex.bondpositionsystem.model.*;
import simplex.bondpositionsystem.ui.view.MarketPricePresentation;
import simplex.bondpositionsystem.ui.view.Menu;
import simplex.bondpositionsystem.ui.view.PositionPresentation;
import simplex.bondpositionsystem.ui.view.PositionViewPresentation;




public class Main {
    public static void main(String[] args) {
        BondRepository bondRepository = new BondRepositoryImpl();
        PositionRepository positionRepository = new PositionRepositoryImpl();
        MarketPriceRepository marketPriceRepository = new MarketPriceRepositoryImpl();

        //ここでPositionのオブジェクトが作られ、Repositoryが機能するようになる。
        PositionManagementService positionManagementService = new PositionManagementServiceImpl(bondRepository, positionRepository);

        //MarketPriceのRepositoryが機能するようになったあと、
        MarketPriceManagementService marketPriceManagementService = new MarketPriceManagementServiceImpl(bondRepository, marketPriceRepository);


        PositionViewService positionViewService = new PositionViewServiceImpl(bondRepository, positionRepository, marketPriceRepository);
        PositionViewPresentation positionViewPresentation = new PositionViewPresentation(positionViewService);

        PositionPresentation positionPresentation = new PositionPresentation(positionManagementService);
        MarketPricePresentation marketPricePresentation = new MarketPricePresentation(bondRepository, marketPriceManagementService);

        Menu menu = new Menu(positionPresentation, marketPricePresentation, positionViewPresentation);


        menu.unitedMenu();
    }

}

package simplex.bondpositionsystem;

import simplex.bondpositionsystem.adapter.*;
import simplex.bondpositionsystem.model.*;
import simplex.bondpositionsystem.ui.view.MarketPricePresentation;
import simplex.bondpositionsystem.ui.view.Menu;
import simplex.bondpositionsystem.ui.view.PositionPresentation;
import simplex.bondpositionsystem.ui.view.PositionViewPresentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//src/main/resources内のファイルをコピーしてつかう。
public class Main {
    public static void main(String[] args) {
        //BondData.csvは書き変わらないから、コピーなし。
        BondRepository bondRepository = new BondRepositoryImpl();
        Path resouce = Paths.get("src/main/resources/PositionData.csv");
        Path out = Paths.get("work/main/PositionData.csv");
        try {
            Files.copy(resouce, out);
        }catch(IOException e){
            //エラー時に終了。
            //RunTimeExceptionをthrowしているってことはお手上げってこと。
            //だから、RunTimeExceptionをcatchしちゃだめ
            throw new RuntimeException(e);
        }
        //main用のcsvファイルをload()する
        PositionRepository positionRepository = new PositionRepositoryImpl("work/main/PositionData.csv");
        MarketPriceRepository marketPriceRepository = new MarketPriceRepositoryImpl();

        //ここでPositionのオブジェクトが作られ、Repositoryが機能するようになる。
        PositionManagementService positionManagementService = new PositionManagementServiceImpl(bondRepository, positionRepository);

        //MarketPriceのRepositoryが機能するようになったあと、
        MarketPriceManagementService marketPriceManagementService = new MarketPriceManagementServiceImpl(marketPriceRepository);

        //メニュー①:在庫データ入力
        PositionPresentation positionPresentation = new PositionPresentation(positionManagementService);
        //メニュー②:値洗い
        MarketPricePresentation marketPricePresentation = new MarketPricePresentation(bondRepository, marketPriceManagementService);

        PositionViewService positionViewService = new PositionViewServiceImpl(bondRepository, positionRepository, marketPriceRepository);
        //メニュー③:一覧表示
        PositionViewPresentation positionViewPresentation = new PositionViewPresentation(positionViewService);

        //メニュー全体
        Menu menu = new Menu(positionPresentation, marketPricePresentation, positionViewPresentation);
        //始動
        menu.unitedMenu();
    }

}

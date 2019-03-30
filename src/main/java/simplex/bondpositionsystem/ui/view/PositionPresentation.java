package simplex.bondpositionsystem.ui.view;

import simplex.bondpositionsystem.model.PositionManagementService;
import simplex.bondpositionsystem.model.UserInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PositionPresentation {

    private final PositionManagementService positionManagementService;

    public PositionPresentation(PositionManagementService positionManagementService) {
        this.positionManagementService = positionManagementService;
    }

    public void managePosition() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("「1」在庫データの上書き\n「2」削除\n※「0」メニューに戻る");
            String positionMenu = br.readLine();

            if (positionMenu.equals("0")) {
                //Menuのメソッドを呼ぶのではなくて、このメソッドを抜ければ自然。
                return;
            } else if (positionMenu.equals("1")) {
                System.out.println("銘柄コード, 保有数量, 簿価を入力してください。（入力例）001,100,1000");
                //入力.
                String text = br.readLine();
                //Positionの作成はPositionProducerImplのInterfaceに。
                //UserInputExceptionをthrowsしてくるからcatchする。
                positionManagementService.registerPosition(text);
            } else if (positionMenu.equals("2")) {
                System.out.println("削除したい銘柄コードを入力してください。");

                String code = br.readLine();
                positionManagementService.deletePosition(code);
            } else {
                System.out.println("「1」または「2」を入力してください。※「0」メニューに戻る");
                managePosition();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UserInputException ue) {
            System.out.println(ue.getMessage());
        }
    }
}

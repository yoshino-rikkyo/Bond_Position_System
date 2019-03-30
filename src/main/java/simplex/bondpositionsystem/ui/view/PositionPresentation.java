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
            System.out.println("「１」在庫データの上書き\n「２」削除");
            String positionMenu = br.readLine();
            if (positionMenu.equals("1")) {
                System.out.println("銘柄コード, 保有数量, 簿価を入力してください。（入力例）100,100,1000");


                //入力.
                String text = br.readLine();
                //Positionの作成はPositionProducerImplのInterfaceに。
                //throwsしてくるからcatchする。
                positionManagementService.registerPosition(text);
            } else if (positionMenu.equals("2"))
                System.out.println("削除したい銘柄コードを入力してください。");
            String code = br.readLine();
            positionManagementService.deletePosition(code);

        } catch (IOException e) {
            throw new RuntimeException();
        } catch (UserInputException ue) {
            System.out.println(ue.getMessage());
        }
    }
}

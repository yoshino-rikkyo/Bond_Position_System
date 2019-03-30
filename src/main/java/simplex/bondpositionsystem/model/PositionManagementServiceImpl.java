package simplex.bondpositionsystem.model;

import java.math.BigDecimal;

public class PositionManagementServiceImpl implements PositionManagementService {

    private final BondRepository bondRepository;
    private final PositionRepository positionRepository;

    public PositionManagementServiceImpl(BondRepository bondRepository, PositionRepository positionRepository) {
        this.bondRepository = bondRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public void registerPosition(String text) throws UserInputException {

        //ただし、コンマ2こないと配列エラー※RuntimeExceptionがくる
        String[] arrayText = text.split(",");
        String code = arrayText[0];

        if (bondRepository.getBond(code) == null) {
            String message = "銘柄codeがみつかりませんでした。";
            throw new UserInputException(message);
        }

        //配列チェック
        if (arrayText.length != 3) {
            String message = "コンマ区切りで、正しく入力してください。";
            throw new UserInputException(message);
        }
        if (isNumber(arrayText[1]) && isNumber(arrayText[2])) {
            BigDecimal amount = new BigDecimal(arrayText[1]);
            BigDecimal bookValue = new BigDecimal(arrayText[2]);
            //amountやPriceにマイナスの数は適さないので、チェック
            if (amount.compareTo(BigDecimal.ZERO) < 0 && bookValue.compareTo(BigDecimal.ZERO) < 0) {
                String message = "保有数量と簿価がマイナスなのは不適切です。";
                throw new UserInputException(message);
            }
            else if (amount.compareTo(BigDecimal.ZERO) < 0) {
                String message = "保有数量がマイナスなのは不適切です。";
                throw new UserInputException(message);
            } else if (bookValue.compareTo(BigDecimal.ZERO) < 0) {
                String message = "簿価がマイナスなのは不適切です。";
                throw new UserInputException(message);
            }
            Position position = new Position(code, amount, bookValue);
            positionRepository.save(position);
            System.out.println("銘柄code："+ code + "の在庫データの入力が完了しました。");
        } else {
            String message = "スペースや数字以外は使用せずに、保有数量と簿価を入力してください。";
            throw new UserInputException(message);
        }

    }

    @Override
    public void deletePosition(String code) throws UserInputException {

        if (positionRepository.getPositionsMap().containsKey(code)) {
            positionRepository.remove(code);
        } else {
            String message = "銘柄コードが見つからないため削除できませんでした。";
            throw new UserInputException(message);
        }
    }


    //ナンバーチェック
    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

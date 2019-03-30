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
        //また、3こめ以降は無視されるがエラーはでない。
        String[] arrayText = text.split(",");
        String code = arrayText[0];

        if (bondRepository.getBondsMap().containsKey(code)) {

        } else {
            String message = "銘柄codeがみつかりませんでした。";
            throw new UserInputException(message);
        }

        if (isNumber(arrayText[1]) && isNumber(arrayText[2])) {
            BigDecimal amount = new BigDecimal(arrayText[1]);
            BigDecimal bookValue = new BigDecimal(arrayText[2]);
            Position position = new Position(code, amount, bookValue);
            positionRepository.save(position);
        } else {
            String message = "保有数量と時価を数字で入力してください。";
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

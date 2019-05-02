package simplex.bondpositionsystem.adapter;

import simplex.bondpositionsystem.model.Position;
import simplex.bondpositionsystem.model.PositionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionRepositoryImpl implements PositionRepository {

    private final Map<String, Position> positions;
    private final String pathFild;

    public PositionRepositoryImpl(String path) {
        if(path == null){
            throw new IllegalArgumentException("pathを指定してください。");
        }
        pathFild = path;
        positions = load(pathFild);

    }

    public Map<String, Position> load(String path) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String text;
            Map<String, Position> positions = new HashMap<>();
            while ((text = reader.readLine()) != null) {

                Position position = parse(text);
                positions.put(position.getCode(), position);

            }

            return positions;

        } catch (IOException e) {
            System.out.println("ファイルの読み込み時にエラーが発生しました。");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Position position) {
        //引数のcodeがmapに入っているcodeと被っていないとき
        if (!positions.containsKey(position.getCode())) {
            //フィールドの更新
            positions.put(position.getCode(), position);
        }
        //引数のcodeがmapに入っていたら、amountと移動平均簿価を再計算し、Positionのオブジェクトを作成する
        else {
            //mapのPosition　+ 引数のPosition
            //少数点計算は未処理
            BigDecimal amount = positions.get(position.getCode()).getAmount().add(position.getAmount());
            BigDecimal amountMultiplyMarketPrice1 = positions.get(position.getCode()).getAmount().
                    multiply(positions.get(position.getCode()).getBookValue());
            BigDecimal amountMultiplyMarketPrice2 = position.getAmount().multiply(position.getBookValue());
            BigDecimal bookValue = (amountMultiplyMarketPrice1.add(amountMultiplyMarketPrice2)).divide(amount, 6, RoundingMode.HALF_UP);

            Position p = new Position(position.getCode(), amount, bookValue);
            positions.put(position.getCode(), p);
        }

        //mapの情報をファイルに書き込む
        saveToFile(pathFild);

    }

    //文字列解釈（load)
    private Position parse(String text) {
        String[] arrayText = text.split(",");
        String code = arrayText[0];
        BigDecimal amount = new BigDecimal(arrayText[1]);
        BigDecimal bookValue = new BigDecimal(arrayText[2]);

        Position position = new Position(code, amount, bookValue);
        return position;
    }

    //文字列解釈（save)
    private String format(Position position) {
        return String.format("%s,%s,%s", position.getCode(), position.getAmount(), position.getBookValue());
    }

    @Override
    public Position getPosition(String code) {
        return positions.get(code);
    }

    @Override
    public List<Position> getPositions() {
        List<Position> positionsList = new ArrayList<>(positions.values());
        return positionsList;
    }

    @Override
    public Map<String, Position> getPositionsMap(){
        return positions;
    }

    @Override
    public void remove(String code){

        positions.remove(code);
        saveToFile(pathFild);
    }

    private void saveToFile(String path){
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(path)))) {
            for (Position p : positions.values()) {
                //ファイルの更新
                writer.println(format(p));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}

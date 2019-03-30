package simplex.bondpositionsystem.adapter;

import simplex.bondpositionsystem.model.Bond;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class BondLoader {

    //throw new RuntimeExceptionをしているから、load(String filename)利用時に気をつけてください。
    public Map<String, Bond> load(String filename) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String text;
            Map<String, Bond> bonds = new HashMap<>();
            while ((text = reader.readLine()) != null) {

                String[] arrayText = text.split(",");
                String code = arrayText[0];
                String name = arrayText[1];
                BigDecimal rate = new BigDecimal(arrayText[2]);
                int maturity = Integer.parseInt(arrayText[3]);
                int couponCount = Integer.parseInt(arrayText[4]);
                Bond bond = new Bond(code, name, rate, maturity, couponCount);
                bonds.put(code, bond);

            }

            return bonds;

        } catch (IOException e) {
            System.out.println("ファイルの読み込み時にエラーが発生しました。");
            throw new RuntimeException(e);
        }

    }
}

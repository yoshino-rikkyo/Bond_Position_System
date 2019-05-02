package simplex.bondpositionsystem.adapter;

import org.junit.Test;
import simplex.bondpositionsystem.model.Bond;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BondRepositoryImplTest {


    @Test
    public void testGetBonds() {

        List<Bond> list = new ArrayList<Bond>();
        list.add(new Bond("001","toyota",new BigDecimal("2.0"),20251212,2));
        list.add(new Bond("002","nissan",new BigDecimal("1.0"),20301212,2));
        list.add(new Bond("003","suzuki",new BigDecimal("1.5"),20291031,2));
        list.add(new Bond("0aa","daihatu",new BigDecimal("1.3"),20291231,2));
        list.add(new Bond("aaa","subal",new BigDecimal("1.2"),20451212,2));
        list.add(new Bond("あ","bentu",new BigDecimal("1.5"),20501111,2));
        list.add(new Bond("か","bmw",new BigDecimal("1.4"),20251010,2));
        list.add(new Bond("さ","wargen",new BigDecimal("1.2"),20220303,2));

        BondRepositoryImpl bonds = new BondRepositoryImpl();

        /*
        >import static org.junit.Assert.*;の中にあるassertThatではなく、
        >import static org.assertj.core.api.Assertions.assertThat;のassertThatを使うと便利。
        >とりあえず、assertJでテストすること
        >assertThat(テスト対象).メソッド名(期待値)
        >を覚えておけばいい。なお、テスト対象の型に合わせて、メソッド名の検索候補が変わるから身構えなくておっけー。
        >※assertJを使うために、gradleにtestCompile group: 'org.assertj', name: 'assertj-core', version: '3.12.2'を書いた。
         */
        /*
        >assertThat(bonds.getBonds()).containsExactly(array);のarrayの部分が要素をたくさん入れていくやつ(...)
        >だから、listはいれられない。
        >でも、配列なら入れられる。
         */
        Bond[] array = list.toArray(new Bond[list.size()]);
        assertThat(bonds.getBonds()).containsExactly(array);

    }
}
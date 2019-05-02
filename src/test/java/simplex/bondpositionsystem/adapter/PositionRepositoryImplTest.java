package simplex.bondpositionsystem.adapter;

import org.junit.Test;
import simplex.bondpositionsystem.model.Position;
import simplex.bondpositionsystem.model.PositionRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class PositionRepositoryImplTest {

    @Test
    public void TestLoad()throws IOException {
        Path resource = Paths.get("src/main/resources/PositionData.csv");
        Path out = Paths.get("work/test/PositionDataLoad.csv");
        Files.copy(resource, out, StandardCopyOption.REPLACE_EXISTING);

        PositionRepository positionRepository = new PositionRepositoryImpl("work/test/PositionDataLoad.csv");
        Map<String, Position> positions = new HashMap<>();
        positions.put("002", new Position("002", new BigDecimal("11300"), new BigDecimal("1002.654867")));
        positions.put("003", new Position("003", new BigDecimal("100"), new BigDecimal("800")));
        assertThat(positionRepository.getPositionsMap()).isEqualTo(positions);
    }

    @Test
    //すでに存在するコードがきたら、amountとbookValueが変わって存在しているはず。
    //Mapに存在しないコードがきたら、positionが一つ増える。
    public void testSave() throws IOException {
        Path resource = Paths.get("src/main/resources/PositionData.csv");
        Path out = Paths.get("work/test/PositionDataSave.csv");
        Files.copy(resource, out, StandardCopyOption.REPLACE_EXISTING);
        PositionRepository positionRepository = new PositionRepositoryImpl("work/test/PositionDataSave.csv");
        Position position1 = new Position("003", new BigDecimal("50"), new BigDecimal("800"));
        positionRepository.save(position1);
        Path path = Paths.get("work/test/PositionDataSave.csv");
        List<String> fileStr = Files.readAllLines(path);
        String[] array = {"002,11300,1002.654867", "003,150,800.000000"};
        assertThat(fileStr).containsExactly(array);
    }


    @Test
    public void testRemove()throws IOException {
        Path resource = Paths.get("src/main/resources/PositionData.csv");
        Path out = Paths.get("work/test/PositionDataRemove.csv");
        Files.copy(resource, out, StandardCopyOption.REPLACE_EXISTING);
        PositionRepository positionRepository = new PositionRepositoryImpl("work/test/PositionDataRemove.csv");
        positionRepository.remove("002");
        Path path = Paths.get("work/test/PositionDataRemove.csv");
        List<String> fileStr = Files.readAllLines(path);
        String[] array = {"003,100,800"};
        assertThat(fileStr).containsExactly(array);
    }

}
package by.afanasyeu.avtoxam.dao.mappersTest;

import by.afanasyeu.avtoxam.dao.entities.DTO.RegionDTO;
import by.afanasyeu.avtoxam.dao.mappers.RegionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

/**
 * Created by Afanasyeu Alexei on 06.07.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@Sql(
        scripts = "classpath:sqlScripts/deleteData-InsertData-test.sql",
        config = @SqlConfig(transactionMode = ISOLATED),
        executionPhase = BEFORE_TEST_METHOD
)
public class RegionMapperTest {

    @Autowired
    private RegionMapper regionMapper;

    @Test
    public void getRegionsByCountryIdTest() {
        List<RegionDTO> list = regionMapper.getRegionsByCountryId(1);
        assertEquals(3,list.size());
        assertTrue(list.contains(new RegionDTO(1, "Минск")));
        assertTrue(list.contains(new RegionDTO(2, "Минская обл.")));
        assertTrue(list.contains(new RegionDTO(3, "Витебск")));
    }
}

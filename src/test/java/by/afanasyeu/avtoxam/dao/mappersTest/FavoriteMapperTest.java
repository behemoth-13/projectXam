package by.afanasyeu.avtoxam.dao.mappersTest;

import by.afanasyeu.avtoxam.dao.entities.Favorite;
import by.afanasyeu.avtoxam.dao.mappers.FavoriteMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

/**
 * @author Afanasyeu Alexei
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@Sql(
        scripts = "classpath:sqlScripts/deleteData-InsertData-test.sql",
        config = @SqlConfig(transactionMode = ISOLATED),
        executionPhase = BEFORE_TEST_METHOD
)
public class FavoriteMapperTest {

    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void insertFavoriteTest() {
        Favorite f1 = new Favorite();
        f1.setUserId(1L);
        f1.setMessageId(6L);
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM favorite", null, Integer.class);
        favoriteMapper.insertFavorite(f1);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM favorite", null, Integer.class);
        assertTrue((after - before) == 1);
    }

    @Test
    public void deleteTest() {
        Favorite f1 = new Favorite();
        f1.setUserId(1L);
        f1.setMessageId(1L);
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM favorite", null, Integer.class);
        favoriteMapper.delete(f1);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM favorite", null, Integer.class);
        assertTrue((before - after) == 1);
    }
}
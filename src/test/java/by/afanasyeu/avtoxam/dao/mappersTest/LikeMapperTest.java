package by.afanasyeu.avtoxam.dao.mappersTest;

import by.afanasyeu.avtoxam.dao.entities.Like;
import by.afanasyeu.avtoxam.dao.mappers.LikeMapper;
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
public class LikeMapperTest {

    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void insertLikeTest() {
        Like l1 = new Like();
        l1.setUserId(6L);
        l1.setMessageId(6L);
        Like l2 = new Like();
        l2.setUserId(6L);
        l2.setCommentId(6L);
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `like`", null, Integer.class);
        likeMapper.insertLike(l1);
        likeMapper.insertLike(l2);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `like`", null, Integer.class);
        assertTrue((after - before) == 2);
    }

    @Test
    public void deleteFromMessageTest() {
        Like l1 = new Like();
        l1.setUserId(2L);
        l1.setMessageId(2L);
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `like`", null, Integer.class);
        likeMapper.deleteFromMessage(l1);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `like`", null, Integer.class);
        assertTrue((before - after) == 1);
    }

    @Test
    public void deleteFromMCommentTest() {
        Like l1 = new Like();
        l1.setUserId(1L);
        l1.setCommentId(1L);
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `like`", null, Integer.class);
        likeMapper.deleteFromComment(l1);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `like`", null, Integer.class);
        assertTrue((before - after) == 1);
    }
}

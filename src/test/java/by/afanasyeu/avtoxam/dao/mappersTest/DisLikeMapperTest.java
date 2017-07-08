package by.afanasyeu.avtoxam.dao.mappersTest;

import by.afanasyeu.avtoxam.dao.entities.DisLike;
import by.afanasyeu.avtoxam.dao.mappers.DisLikeMapper;
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
 * Created by Afanasyeu Alexei on 08.07.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@Sql(
        scripts = "classpath:sqlScripts/deleteData-InsertData-test.sql",
        config = @SqlConfig(transactionMode = ISOLATED),
        executionPhase = BEFORE_TEST_METHOD
)
public class DisLikeMapperTest {

    @Autowired
    private DisLikeMapper disLikeMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void insertDisLikeTest() {
        DisLike d1 = new DisLike();
        d1.setUserId(6L);
        d1.setMessageId(6L);
        DisLike d2 = new DisLike();
        d2.setUserId(6L);
        d2.setCommentId(6L);
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `dis_like`", null, Integer.class);
        disLikeMapper.insertDisLike(d1);
        disLikeMapper.insertDisLike(d2);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `dis_like`", null, Integer.class);
        assertTrue((after - before) == 2);
    }

    @Test
    public void deleteFromMessageTest() {
        DisLike d1 = new DisLike();
        d1.setUserId(3L);
        d1.setMessageId(2L);
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `dis_like`", null, Integer.class);
        disLikeMapper.deleteFromMessage(d1);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `dis_like`", null, Integer.class);
        assertTrue((before - after) == 1);
    }

    @Test
    public void deleteFromMCommentTest() {
        DisLike d1 = new DisLike();
        d1.setUserId(1L);
        d1.setCommentId(4L);
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `dis_like`", null, Integer.class);
        disLikeMapper.deleteFromComment(d1);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM `dis_like`", null, Integer.class);
        assertTrue((before - after) == 1);
    }
}

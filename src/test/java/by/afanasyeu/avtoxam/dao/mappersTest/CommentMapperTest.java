package by.afanasyeu.avtoxam.dao.mappersTest;

import by.afanasyeu.avtoxam.dao.entities.Comment;
import by.afanasyeu.avtoxam.dao.entities.DTO.CommentDTO;
import by.afanasyeu.avtoxam.dao.entities.DTO.MessageDTO;
import by.afanasyeu.avtoxam.dao.mappers.CommentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

/**
 * Created by Afanasyeu Alexei on 09.07.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@Sql(
        scripts = "classpath:sqlScripts/deleteData-InsertData-test.sql",
        config = @SqlConfig(transactionMode = ISOLATED),
        executionPhase = BEFORE_TEST_METHOD
)
public class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void insertMessageTest() {
        Comment c = new Comment();
        c.setUserId(1L);
        c.setMessageId(6L);
        c.setComment("Этот коммент из class CommentMapperTest");
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM comment", null, Integer.class);
        commentMapper.insertComment(c);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM comment", null, Integer.class);
        assertTrue((after - before) == 1);
    }

    @Test
    public void getFirstsTest() {
        List<CommentDTO> list = commentMapper.getFirsts(1L, 1L, 10);
        CommentDTO commentDTO = list.get(0);
        assertTrue(commentDTO.getId() == 1);
        //assertTrue(commentDTO.getDateComment().toString().equals("Sun Jul 09 18:00:55 MSK 2017"));
        assertTrue(commentDTO.getComment().equals("Пример комментария 1"));
        assertTrue(commentDTO.getUserLogin().equals("Логин1"));
        assertTrue(commentDTO.getCountLike() == 4);
        assertTrue(commentDTO.getCountDisLike() == 0);
        assertTrue(commentDTO.getStatus().equals('b'));
    }

    @Test
    public void getFromIntervalTest() {
        List<CommentDTO> list = commentMapper.getFromInterval(1L, 2L, 2, 1L);
        CommentDTO commentDTO = list.get(0);
//        for (CommentDTO c : list) {
//            System.out.println(c);
//        }
        assertTrue(commentDTO.getId() == 3);
        //assertTrue(commentDTO.getDateComment().toString().equals("Sun Jul 09 18:00:55 MSK 2017"));
        assertTrue(commentDTO.getComment().equals("Пример комментария 3"));
        assertTrue(commentDTO.getUserLogin().equals("Логин2"));
        assertTrue(commentDTO.getCountLike() == 1);
        assertTrue(commentDTO.getCountDisLike() == 1);
        assertTrue(commentDTO.getStatus().equals('b'));
    }

    @Test
    public void deleteByCommentIdUserIdTest() {
        commentMapper.deleteByCommentIdUserId(1L, 1L);
        int countMessage = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM comment", null, Integer.class);
        assertTrue(countMessage == 11);
    }
}
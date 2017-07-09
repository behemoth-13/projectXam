package by.afanasyeu.avtoxam.dao.mappersTest;

import by.afanasyeu.avtoxam.dao.entities.DTO.MessageDTO;
import by.afanasyeu.avtoxam.dao.entities.Message;
import by.afanasyeu.avtoxam.dao.mappers.MessageMapper;
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
 * Created by Afanasyeu Alexei on 06.07.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
@Sql(
        scripts = "classpath:sqlScripts/deleteData-InsertData-test.sql",
        config = @SqlConfig(transactionMode = ISOLATED),
        executionPhase = BEFORE_TEST_METHOD
)
public class MessageMapperTest {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void insertMessageTest() {
        Message m = new Message();
        m.setUserId(1L);
        m.setMessage("Hello World!");
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM message", null, Integer.class);
        messageMapper.insertMessage(m);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM message", null, Integer.class);
        assertTrue((after - before) == 1);
    }

    @Test
    public void getLastsTest() {
        List<MessageDTO> list = messageMapper.getLasts(13, 1L);
        MessageDTO messageDTO = list.get(12);
        assertTrue(messageDTO.getId() == 1);
        //assertTrue(messageDTO.getDateMessage().toString().equals("Sat Jun 24 20:00:02 MSK 2017"));
        assertTrue(messageDTO.getMessage().equals("пример сообщения 1"));
        assertTrue(messageDTO.getUserLogin().equals("Логин1"));
        assertTrue(messageDTO.getCountLike() == 3);
        assertTrue(messageDTO.getCountDisLike() == 0);
        assertTrue(messageDTO.getCountComment() == 4);
        assertTrue(messageDTO.getStatus().equals('e'));
    }

    @Test
    public void deleteByMessageIdUserIdTest() {
        messageMapper.deleteByMessageIdUserId(1L, 1L);
        int countMessage = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM message", null, Integer.class);
        assertTrue(countMessage == 12);
        int countComment = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM comment", null, Integer.class);
        assertTrue(countComment == 8);
    }

    @Test
    public void getFromIntervalTest() {
        List<MessageDTO> list = messageMapper.getFromInterval(2L, 5, 1L);
        MessageDTO messageDTO = list.get(0);
        assertTrue(messageDTO.getId() == 3);
        //assertTrue(messageDTO.getDateMessage().toString().equals("Sat Jun 24 20:00:02 MSK 2017"));
        assertTrue(messageDTO.getMessage().equals("пример сообщения 3"));
        assertTrue(messageDTO.getUserLogin().equals("Логин1"));
        assertTrue(messageDTO.getCountLike() == 1);
        assertTrue(messageDTO.getCountDisLike() == 1);
        assertTrue(messageDTO.getCountComment() == 1);
        assertTrue(messageDTO.getStatus().equals('e'));
    }

    @Test
    public void getCountSinceByIdTest() {
        Integer count = messageMapper.getCountSinceById(11L);
        assertTrue(count == 2);
    }

    @Test
    public void getLastLikedMessageTest() {
        List<MessageDTO> list = messageMapper.getLastLikedMessage(2, 1L);
        MessageDTO messageDTO = list.get(0);
        assertTrue(messageDTO.getId() == 3);
        //assertTrue(messageDTO.getDateMessage().toString().equals("Sat Jun 24 20:00:02 MSK 2017"));
        assertTrue(messageDTO.getMessage().equals("пример сообщения 3"));
        assertTrue(messageDTO.getUserLogin().equals("Логин1"));
        assertTrue(messageDTO.getCountLike() == 1);
        assertTrue(messageDTO.getCountDisLike() == 1);
        assertTrue(messageDTO.getCountComment() == 1);
        assertTrue(messageDTO.getStatus().equals('e'));
    }

    @Test
    public void getLastLikedMessageFromIntervalTest() {
        List<MessageDTO> list = messageMapper.getLikedMessageFromInterval(2L, 1L, 1L);
        MessageDTO messageDTO = list.get(0);
        assertTrue(messageDTO.getId() == 3);
        //assertTrue(messageDTO.getDateMessage().toString().equals("Sat Jun 24 20:00:02 MSK 2017"));
        assertTrue(messageDTO.getMessage().equals("пример сообщения 3"));
        assertTrue(messageDTO.getUserLogin().equals("Логин1"));
        assertTrue(messageDTO.getCountLike() == 1);
        assertTrue(messageDTO.getCountDisLike() == 1);
        assertTrue(messageDTO.getCountComment() == 1);
        assertTrue(messageDTO.getStatus().equals('e'));
    }

    @Test
    public void getLastFavotiteMessageTest() {
        List<MessageDTO> list = messageMapper.getLastFavotiteMessage(3, 1L);
        MessageDTO messageDTO = list.get(2);
        assertTrue(messageDTO.getId() == 3);
        //assertTrue(messageDTO.getDateMessage().toString().equals("Sat Jun 24 20:00:02 MSK 2017"));
        assertTrue(messageDTO.getMessage().equals("пример сообщения 3"));
        assertTrue(messageDTO.getUserLogin().equals("Логин1"));
        assertTrue(messageDTO.getCountLike() == 1);
        assertTrue(messageDTO.getCountDisLike() == 1);
        assertTrue(messageDTO.getCountComment() == 1);
        assertTrue(messageDTO.getStatus().equals('e'));
    }

    @Test
    public void getFavotiteMessageFromIntervalTest() {
        List<MessageDTO> list = messageMapper.getFavotiteMessageFromInterval(2L, 2, 1L);
        MessageDTO messageDTO = list.get(0);
        assertTrue(messageDTO.getId() == 5);
        //assertTrue(messageDTO.getDateMessage().toString().equals("Sat Jun 24 20:00:02 MSK 2017"));
        assertTrue(messageDTO.getMessage().equals("пример сообщения 5"));
        assertTrue(messageDTO.getUserLogin().equals("Логин2"));
        assertTrue(messageDTO.getCountLike() == 0);
        assertTrue(messageDTO.getCountDisLike() == 1);
        assertTrue(messageDTO.getCountComment() == 2);
        assertTrue(messageDTO.getStatus().equals('c'));
    }
}

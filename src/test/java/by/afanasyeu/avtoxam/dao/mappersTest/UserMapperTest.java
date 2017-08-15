package by.afanasyeu.avtoxam.dao.mappersTest;

import by.afanasyeu.avtoxam.dao.entities.DTO.UserDTO;
import by.afanasyeu.avtoxam.dao.entities.User;
import by.afanasyeu.avtoxam.dao.mappers.UserMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

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
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void insertUserTest() {
        User u = new User();
        u.setId(24L);
        u.setLogin("testLogin");
        u.setRegDate(new Date());
        u.setPassword("testPassword");
        u.setRegion(1);
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user", null, Integer.class);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user", null, Integer.class);
        assertTrue((after - before) == 1);
    }

    @Test
    public void isExistLoginTest() {
        assertTrue(userMapper.isExistLogin("Логин1"));
    }

    @Test
    public void updateLoginTest() {
        userMapper.updateLogin("Логин1", "newLogin");
        Boolean isUpdate = jdbcTemplate.queryForObject("SELECT IF(COUNT(*) > 0,'true','false') FROM user WHERE login = 'newLogin'", null, Boolean.class);
        assertTrue(isUpdate);
    }

    @Test
    public void updateRegionTest() {
        userMapper.updateRegion(1L, 9);
        Boolean isUpdate = jdbcTemplate.queryForObject("SELECT IF(COUNT(*) > 0,'true','false') FROM user WHERE region = 9", null, Boolean.class);
        assertTrue(isUpdate);
    }

    @Test
    public void updatePasswordTest() {
        userMapper.updatePassword(1L, "newPassword");
        Boolean isUpdate = jdbcTemplate.queryForObject("SELECT IF(COUNT(*) > 0,'true','false') FROM user WHERE password = 'newPassword'", null, Boolean.class);
        assertTrue(isUpdate);
    }

    @Test
    public void deleteByLoginTest() {
        int before = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user", null, Integer.class);
        userMapper.deleteById(1L);
        int after = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user", null, Integer.class);
        assertTrue((before - after) == 1);
    }

    @Test
    public void isExistUserTest() {
        assertTrue(userMapper.isExistUser("Логин1", "пароль1"));
    }

    @Test
    public void getUserDTOTest() {
        UserDTO userDTO = userMapper.getUserDTO("Логин1");
        assertTrue(userDTO.getCountry().equals("Беларусь"));
        assertTrue(userDTO.getRegion().equals("Минск"));
        assertTrue(userDTO.getLogin().equals("Логин1"));
        assertTrue(userDTO.getRegDate().equals("Sat Jun 24 20:00:02 MSK 2017"));
    }

    @Test
    public void getByLoginTest() {
        User user = userMapper.getByLogin("Логин3242");
        System.out.println(user==null);
    }
}
package by.afanasyeu.avtoxam.daoTest.mappersTest;

import by.afanasyeu.avtoxam.dao.entities.DTO.UserDTO;
import by.afanasyeu.avtoxam.dao.entities.User;
import by.afanasyeu.avtoxam.dao.mappers.UserMapper;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;


/**
 * Created by Afanasyeu Alexei on 28.06.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
//@Sql(
//        scripts = "classpath:sqlScripts/deleteData-InsertData-testUserMapper.sql",
//        config = @SqlConfig(transactionMode = ISOLATED),
//        executionPhase = BEFORE_TEST_METHOD
//)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    //@Ignore
    public void insertUserTest() {
        User u = new User();
        u.setLogin("testLogin");
        u.setRegDate(new Date());
        u.setPassword("testPassword");
        u.setRegion(1);
        if (userMapper == null) {
            System.out.println("hey");
        }
        userMapper.insertUser(u);
        assertEquals(2, 1 + 1);
    }

    @Test
    public void isExistLoginTest() {
        assertTrue(userMapper.isExistLogin("testLogin"));
    }

    @Test
    public void updateLoginTest() {
        userMapper.updateLogin("testLogin", "newLogin");
    }

    @Test
    public void getUserDTOTest() {
        UserDTO userDTO = userMapper.getUserDTO("testLogin");
        System.out.println(userDTO);
    }
}

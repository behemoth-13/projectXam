package by.afanasyeu.avtoxam.daoTest.mappersTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Afanasyeu Alexei on 28.06.2017.
 */

@DisplayName("A special test case")
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
class UserMapperTest {

    /*@BeforeEach
    void insertDataInDb() {

    }*/

    @Test
    @DisplayName("A sa test case")
    void test(){
        assertEquals(2, 1 + 1);
    }

}

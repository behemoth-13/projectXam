package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.DTO.UserDTO;
import by.afanasyeu.avtoxam.dao.entities.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Используется для обращения к БД
 * @author Afanasyeu Alexei
 */

@Repository
public interface UserMapper {
        @Insert("INSERT INTO user(region, login, password, reg_date) VALUES"
                + "(#{region}, #{login}, #{password}, #{regDate})")
        @Options(useGeneratedKeys=true,
                flushCache=Options.FlushCachePolicy.DEFAULT,
                keyColumn="id")
        void insertUser(User user);

        @Select("SELECT IF(COUNT(*) > 0,'true','false') FROM user WHERE login = #{login}")
        Boolean isExistLogin(String login);

        @Update("UPDATE user SET login = #{newLogin} WHERE login = #{oldLogin}")
        void updateLogin(@Param("oldLogin") String oldLogin, @Param("newLogin") String newLogin);

        @Update("UPDATE user SET region = #{region} WHERE id = #{id}")
        void updateRegion(@Param("id") Long id, @Param("region") Integer region);

        @Update("UPDATE user SET password = #{newPassword} WHERE id = #{id}")
        void updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

        @Delete("DELETE FROM user WHERE id = #{id}")
        void deleteById(Long id);

        @Select("SELECT if(COUNT(*)>0,'true','false') FROM user WHERE login = #{login} AND password = #{password}")
        Boolean isExistUser(@Param("login") String login, @Param("password") String password);

        @Select("SELECT c.country AS country, r.region AS region, login AS login, reg_date AS regDate " +
                "FROM user AS u JOIN region AS r ON u.region = r.id " +
                "JOIN country c ON r.country_id = c.id " +
                "WHERE login = #{login}")
        UserDTO getUserDTO(String login);

        @Select("SELECT id AS id, region AS region, login AS login, password AS password, reg_date AS regDate " +
                "FROM user WHERE login = #{login}")
        User getByLogin(String login);
}
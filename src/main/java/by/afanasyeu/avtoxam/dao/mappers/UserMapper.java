package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 28.06.2017.
 */
@Repository
public interface UserMapper {
        @Insert("INSERT INTO user(region, login, password, reg_date) VALUES"
                + "(#{region}, #{login}, #{password}, #{regDate})")
        @Options(useGeneratedKeys=true, keyProperty="id", flushCache=Options.FlushCachePolicy.DEFAULT, keyColumn="id")
        void insertUser(User user);

        @Select("SELECT * FROM student WHERE teacher_id = #{id}")
        @Results(value={
                @Result(property="teacherId", column ="teacher_id" )
        })
        List<User> getStudentsByTeacherId(Long id);

        @Delete("DELETE FROM student WHERE id =#{studentId} AND teacher_id = #{teacherId}")
        void deleteStudentById(@Param("studentId")Long studentId, @Param("teacherId") Long teacherId);

        @Select("SELECT FIRSTNAME as firstName, LASTNAME as lastName, "
                + "DATEOFBIRTH as dateOfBirth, EMAILADDRESS as emailAddress "
                + "FROM student WHERE firstName = #{firstName} AND lastName = #{lastName} " +
                "AND dateOfBirth = #{dateOfBirth}")
        @Results(value={
                @Result(property="teacherId", column ="teacher_id" )
        })
        User getUserByLoginPassword(User checkUser);
}

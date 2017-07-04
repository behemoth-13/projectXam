package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.Message;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 04.07.2017.
 */
@Repository
public interface MessageMapper {
    @Select("SELECT * FROM `tab1` order by `today` desc LIMIT 10")
    @Results(value={
            @Result(property="teacherId", column ="teacher_id"),
            @Result(property="teacherId", column ="teacher_id")
    })
    List<Message> getLasts (Integer countLast);

    void deleteByUserId (Long userId);
    List<Message> getFromInterval(@Param("first") Long first, @Param("last") Long last);
    Integer getcountSinceById (Long messageIdSince);
}
/*    @Select("SELECT * FROM student WHERE teacher_id = #{id}")
    @Results(value={
            @Result(property="teacherId", column ="teacher_id" )
    })*/
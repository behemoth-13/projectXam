package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Используется для обращения к БД
 * @author Afanasyeu Alexei
 */
@Repository
public interface DisLikeMapper {
    @Insert("INSERT INTO `dis_like`(user_id, message_id, comment_id) " +
            "VALUES (#{userId}, #{messageId}, #{commentId})")
    @Options(useGeneratedKeys=true,
            flushCache=Options.FlushCachePolicy.DEFAULT,
            keyColumn="id")
    void insertDisLike(Like disLike);

    @Delete("DELETE FROM `dis_like` WHERE user_id = #{userId} AND message_id = #{messageId}")
    void deleteFromMessage(Like disLike);

    @Delete("DELETE FROM `dis_like` WHERE user_id = #{userId} AND comment_id = #{commentId}")
    void deleteFromComment(Like disLike);

    @Select("SELECT if(COUNT(*)>0,'true','false') FROM `like` WHERE user_id = #{userId} AND message_id = #{messageId}")
    Boolean isExistFromMessage(Like like);

    @Select("SELECT if(COUNT(*)>0,'true','false') FROM `like` WHERE user_id = #{userId} AND comment_id = #{commentId}")
    Boolean isExistFromComment(Like like);
}

package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * Created by Afanasyeu Alexei on 08.07.2017.
 */
@Repository
public interface LikeMapper {
    @Insert("INSERT INTO `like`(user_id, message_id, comment_id) " +
            "VALUES (#{userId}, #{messageId}, #{commentId})")
    @Options(useGeneratedKeys=true,
            keyProperty="id",
            flushCache=Options.FlushCachePolicy.DEFAULT,
            keyColumn="id")
    void insertLike(Like like);

    @Delete("DELETE FROM `like` WHERE user_id = #{userId} AND message_id = #{messageId}")
    void deleteFromMessage(Like like);

    @Delete("DELETE FROM `like` WHERE user_id = #{userId} AND comment_id = #{commentId}")
    void deleteFromComment(Like like);
}
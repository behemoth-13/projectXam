package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.DisLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * Created by Afanasyeu Alexei on 08.07.2017.
 */
@Repository
public interface DisLikeMapper {
    @Insert("INSERT INTO `dis_like`(user_id, message_id, comment_id) " +
            "VALUES (#{userId}, #{messageId}, #{commentId})")
    @Options(useGeneratedKeys=true,
            keyProperty="id",
            flushCache=Options.FlushCachePolicy.DEFAULT,
            keyColumn="id")
    void insertDisLike(DisLike disLike);

    @Delete("DELETE FROM `dis_like` WHERE user_id = #{userId} AND message_id = #{messageId}")
    void deleteFromMessage(DisLike disLike);

    @Delete("DELETE FROM `dis_like` WHERE user_id = #{userId} AND comment_id = #{commentId}")
    void deleteFromComment(DisLike disLike);
}

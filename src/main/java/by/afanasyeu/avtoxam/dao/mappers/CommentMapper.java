package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.Comment;
import by.afanasyeu.avtoxam.dao.entities.DTO.CommentDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Используется для обращения к БД
 * @author Afanasyeu Alexei
 */

@Repository
public interface CommentMapper {
    @Insert("INSERT INTO comment(user_id, message_id, comment) VALUES "
            + "(#{userId}, #{messageId}, #{comment})")
    @Options(useGeneratedKeys=true,
            flushCache= Options.FlushCachePolicy.DEFAULT,
            keyColumn="id")
    void insertComment(Comment comment);

    @Select(
            "SELECT c.id AS id, c.date_comment AS dateComment, c.comment AS comment, u.login AS userLogin, " +
                    "COUNT(DISTINCT l.id) AS countLike, COUNT(DISTINCT d.id) AS countDisLike, " +
                    "CASE" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))>0 THEN 'a' " +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))>0 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 THEN 'b' " +
                    "END AS status " +
                    "FROM comment AS c " +
                    "JOIN user AS u ON c.user_id = u.id " +
                    "LEFT JOIN `like` AS l ON c.id = l.comment_id " +
                    "LEFT JOIN dis_like AS d ON c.id = d.comment_id " +
                    "WHERE c.message_id=#{messageId} GROUP BY c.id " +
                    "ORDER BY c.id LIMIT #{countFirst}"
    )
    @Results()
    List<CommentDTO> getFirsts(@Param("messageId") Long messageId, @Param("userId") Long userId,
                               @Param("countFirst")Integer countFirst);

    /**
     * Прямой порядок, если first = 2, то list начинается с 3
     */

    @Select(
            "SELECT c.id AS id, c.date_comment AS dateComment, c.comment AS comment, u.login AS userLogin, " +
                    "COUNT(DISTINCT l.id) AS countLike, COUNT(DISTINCT d.id) AS countDisLike, " +
                    "CASE" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))>0 THEN 'a' " +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))>0 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 THEN 'b' " +
                    "END AS status " +
                    "FROM comment AS c " +
                    "JOIN user AS u ON c.user_id = u.id " +
                    "LEFT JOIN `like` AS l ON c.id = l.comment_id " +
                    "LEFT JOIN dis_like AS d ON c.id = d.comment_id " +
                    "WHERE c.message_id=#{messageId} GROUP BY c.id " +
                    "ORDER BY c.id LIMIT #{first}, #{count}"
    )
    @Results()
    List<CommentDTO> getFromInterval(@Param("messageId") Long messageId, @Param("first") Long first,
                                     @Param("count") Integer count, @Param("userId") Long userId);

    @Delete("DELETE FROM comment WHERE id = #{commentId} AND user_id = #{userId}")
    void deleteByCommentIdUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);

    @Select("SELECT if(COUNT(*)>0,'true','false') FROM comment WHERE id = #{id}")
    Boolean isExistComment(Long id);
}
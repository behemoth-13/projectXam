package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.DTO.MessageDTO;
import by.afanasyeu.avtoxam.dao.entities.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Используется для обращения к БД
 * @author Afanasyeu Alexei
 */
@Repository
public interface MessageMapper {

    @Insert("INSERT INTO message(user_id, message) VALUES "
            + "(#{userId}, #{message})")
    @Options(useGeneratedKeys=true,
            flushCache= Options.FlushCachePolicy.DEFAULT,
            keyColumn="id")
    void insertMessage(Message message);

    /**
     * Обратный порядок
     */
    @Select(
            "SELECT m.id AS id, m.date_message AS dateMessage, m.message AS message, u.login AS userLogin, " +
            "COUNT(DISTINCT l.id) AS countLike, COUNT(DISTINCT d.id) AS countDisLike, COUNT(DISTINCT c.id) AS countComment, " +
            "CASE" +
            "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 " +
            "        AND SUM(IF(f.user_id = #{userId}, 1, 0))>0 THEN 'a'" +
            "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))>0 " +
            "        AND SUM(IF(f.user_id = #{userId}, 1, 0))<1 THEN 'b'" +
            "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))>0 " +
            "        AND SUM(IF(f.user_id = #{userId}, 1, 0))>0 THEN 'c'" +
            "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))>0 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 " +
            "        AND SUM(IF(f.user_id = #{userId}, 1, 0))<1 THEN 'd'" +
            "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))>0 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 " +
            "        AND SUM(IF(f.user_id = #{userId}, 1, 0))>0 THEN 'e'" +
            "END AS status  " +
            "FROM message AS m " +
            "JOIN user AS u ON m.user_id = u.id " +
            "LEFT JOIN `like` AS l ON m.id = l.message_id " +
            "LEFT JOIN dis_like AS d ON m.id = d.message_id " +
            "LEFT JOIN comment AS c ON m.id = c.message_id " +
            "LEFT JOIN favorite as f ON m.id = f.message_id GROUP BY m.id " +
            "ORDER BY m.id DESC LIMIT #{countLast}"
    )
    @Results()
    List<MessageDTO> getLasts (@Param("countLast") Integer countLast, @Param("userId") Long userId);

    /**
     * Удаляет сообщения и комментарии к нему
     */
    @Delete("DELETE FROM message WHERE id = #{messageId} AND user_id = #{userId}")
    void deleteByMessageIdUserId (@Param("messageId") Long messageId, @Param("userId") Long userId);

    /**
     * Прямой порядок. Начинается с first и на количество count
     * @param first первое сообщение
     * @param count количество сообщений
     * @param userId id пользователя, который выполняет запрос
     * @return List{@link MessageDTO}
     */
    @Select(
            "SELECT m.id AS id, m.date_message AS dateMessage, m.message AS message, u.login AS userLogin, " +
                    "COUNT(DISTINCT l.id) AS countLike, COUNT(DISTINCT d.id) AS countDisLike, COUNT(DISTINCT c.id) AS countComment, " +
                    "CASE" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 " +
                    "        AND SUM(IF(f.user_id = #{userId}, 1, 0))>0 THEN 'a'" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))>0 " +
                    "        AND SUM(IF(f.user_id = #{userId}, 1, 0))<1 THEN 'b'" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))>0 " +
                    "        AND SUM(IF(f.user_id = #{userId}, 1, 0))>0 THEN 'c'" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))>0 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 " +
                    "        AND SUM(IF(f.user_id = #{userId}, 1, 0))<1 THEN 'd'" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))>0 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 " +
                    "        AND SUM(IF(f.user_id = #{userId}, 1, 0))>0 THEN 'e'" +
                    "END AS status  " +
                    "FROM message AS m " +
                    "JOIN user AS u ON m.user_id = u.id " +
                    "LEFT JOIN `like` AS l ON m.id = l.message_id " +
                    "LEFT JOIN dis_like AS d ON m.id = d.message_id " +
                    "LEFT JOIN comment AS c ON m.id = c.message_id " +
                    "LEFT JOIN favorite as f ON m.id = f.message_id GROUP BY m.id " +
                    "ORDER BY m.id LIMIT #{first}, #{count}"
    )
    @Results()
    List<MessageDTO> getFromInterval(@Param("first") Long first, @Param("count") Integer count,
                                     @Param("userId") Long userId);

    /**
     * Сколько сообщений начиная с указанного id
     */
    @Select("SELECT COUNT(id) from message WHERE id > #{messageIdSince}")
    Integer getCountSinceById (Long messageIdSince);

    /**
     * обратный порядок
     */
    @Select(
            "SELECT m.id AS id, m.date_message AS dateMessage, m.message AS message, u.login AS userLogin, " +
                    "COUNT(DISTINCT l.id) AS countLike, COUNT(DISTINCT d.id) AS countDisLike, COUNT(DISTINCT c.id) AS countComment, " +
                    "CASE" +
                    "    WHEN SUM(IF(f.user_id = #{userId}, 1, 0))<1 THEN 'd'" +
                    "    WHEN SUM(IF(f.user_id = #{userId}, 1, 0))>0 THEN 'e'" +
                    "END AS status " +
                    "FROM message AS m " +
                    "JOIN user AS u ON m.user_id = u.id " +
                    "LEFT JOIN `like` AS l ON m.id = l.message_id " +
                    "LEFT JOIN dis_like AS d ON m.id = d.message_id " +
                    "LEFT JOIN comment AS c ON m.id = c.message_id " +
                    "LEFT JOIN favorite as f ON m.id = f.message_id " +
                    "WHERE l.user_id = #{userId} " +
                    "GROUP BY m.id " +
                    "ORDER BY m.id DESC LIMIT #{countLast}"
    )
    List<MessageDTO> getLastLikedMessage(@Param("countLast") Integer countLast, @Param("userId") Long userId);

    /**
     * прямой порядок. Начинается с first и на количество count
     */
    @Select(
            "SELECT m.id AS id, m.date_message AS dateMessage, m.message AS message, u.login AS userLogin, " +
                    "COUNT(DISTINCT l.id) AS countLike, COUNT(DISTINCT d.id) AS countDisLike, COUNT(DISTINCT c.id) AS countComment, " +
                    "CASE" +
                    "    WHEN SUM(IF(f.user_id = #{userId}, 1, 0))<1 THEN 'd'" +
                    "    WHEN SUM(IF(f.user_id = #{userId}, 1, 0))>0 THEN 'e'" +
                    "END AS status  " +
                    "FROM message AS m " +
                    "JOIN user AS u ON m.user_id = u.id " +
                    "LEFT JOIN `like` AS l ON m.id = l.message_id " +
                    "LEFT JOIN dis_like AS d ON m.id = d.message_id " +
                    "LEFT JOIN comment AS c ON m.id = c.message_id " +
                    "LEFT JOIN favorite as f ON m.id = f.message_id " +
                    "WHERE l.user_id = #{userId} " +
                    "GROUP BY m.id " +
                    "ORDER BY m.id LIMIT #{first}, #{count}"
    )
    @Results()
    List<MessageDTO> getLikedMessageFromInterval(@Param("first") Long first, @Param("count") Integer count,
                                                 @Param("userId") Long userId);

    /**
     * обратный порядок
     */
    @Select(
            "SELECT m.id AS id, m.date_message AS dateMessage, m.message AS message, u.login AS userLogin, " +
                    "COUNT(DISTINCT l.id) AS countLike, COUNT(DISTINCT d.id) AS countDisLike, COUNT(DISTINCT c.id) AS countComment, " +
                    "CASE" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 THEN 'a'" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))>0 THEN 'c'" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))>0 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 THEN 'e'" +
                    "END AS status " +
                    "FROM message AS m " +
                    "JOIN user AS u ON m.user_id = u.id " +
                    "LEFT JOIN `like` AS l ON m.id = l.message_id " +
                    "LEFT JOIN dis_like AS d ON m.id = d.message_id " +
                    "LEFT JOIN comment AS c ON m.id = c.message_id " +
                    "LEFT JOIN favorite as f ON m.id = f.message_id " +
                    "WHERE f.user_id = #{userId} " +
                    "GROUP BY m.id " +
                    "ORDER BY m.id DESC LIMIT #{countLast}"
    )
    List<MessageDTO> getLastFavoriteMessage(@Param("countLast") Integer countLast, @Param("userId") Long userId);

    /**
     * прямой порядок. Начинается с first и на количество count
     */
    @Select(
            "SELECT m.id AS id, m.date_message AS dateMessage, m.message AS message, u.login AS userLogin, " +
                    "COUNT(DISTINCT l.id) AS countLike, COUNT(DISTINCT d.id) AS countDisLike, COUNT(DISTINCT c.id) AS countComment, " +
                    "CASE" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 THEN 'a'" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<1 AND SUM(IF(d.user_id = #{userId}, 1, 0))>0 THEN 'c'" +
                    "    WHEN SUM(IF(l.user_id = #{userId}, 1, 0))>0 AND SUM(IF(d.user_id = #{userId}, 1, 0))<1 THEN 'e'" +
                    "END AS status " +
                    "FROM message AS m " +
                    "JOIN user AS u ON m.user_id = u.id " +
                    "LEFT JOIN `like` AS l ON m.id = l.message_id " +
                    "LEFT JOIN dis_like AS d ON m.id = d.message_id " +
                    "LEFT JOIN comment AS c ON m.id = c.message_id " +
                    "LEFT JOIN favorite as f ON m.id = f.message_id " +
                    "WHERE f.user_id = #{userId} " +
                    "GROUP BY m.id " +
                    "ORDER BY m.id LIMIT  #{first}, #{count}"
    )
    List<MessageDTO> getFavoriteMessageFromInterval(@Param("first") Long first,
                                                    @Param("count") Integer count, @Param("userId") Long userId);

    @Select("SELECT if(COUNT(*)>0,'true','false') FROM message WHERE id = #{id}")
    Boolean isExistMessage(Long id);
}
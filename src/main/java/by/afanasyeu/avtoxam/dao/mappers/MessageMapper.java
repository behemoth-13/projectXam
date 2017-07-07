package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.DTO.MessageDTO;
import by.afanasyeu.avtoxam.dao.entities.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 04.07.2017.
 */
@Repository
public interface MessageMapper {
    @Insert("INSERT INTO message(user_id, message) VALUES "
            + "(#{userId}, #{message})")
    @Options(useGeneratedKeys=true,
            keyProperty="id",
            flushCache= Options.FlushCachePolicy.DEFAULT,
            keyColumn="id")
    void insertMessage(Message message);

    @Select("SELECT m.id AS id, m.date_message AS dateMessage, m.message AS message, u.login AS userLogin, " +
            "COUNT(DISTINCT l.id) AS countLike, COUNT(DISTINCT d.id) AS countDisLike, COUNT(DISTINCT c.id) AS countComment, " +
            "CASE " +
            " WHEN SUM(IF(l.user_id = #{userId}, 1, 0))<4 AND SUM(IF(d.user_id = #{userId}, 1, 0))<3 THEN 'a' " +
            " END AS status " +
            "FROM message AS m JOIN user AS u ON m.user_id = u.id " +
            "left JOIN `like` AS l ON m.id = l.message_id " +
            "left JOIN dis_like AS d ON m.id = d.message_id " +
            "left JOIN comment AS c ON m.id = c.message_id GROUP BY m.id " +
            "ORDER BY m.id DESC LIMIT #{countLast}"
    )
    /*SELECT ccc_news . * ,
SUM(if(ccc_news_comments.id = 'approved', 1, 0)) AS comments
FROM ccc_news
LEFT JOIN ccc_news_comments ON ccc_news_comments.news_id = ccc_news.news_id
WHERE `ccc_news`.`category` = 'news_layer2'
AND `ccc_news`.`status` = 'Active'
GROUP BY ccc_news.news_id
ORDER BY ccc_news.set_order ASC
LIMIT 20 */
    @Results(value={
            //@Result(property="dateMessage", column ="date_message" )
    })
    List<MessageDTO> getLasts (@Param("countLast") Integer countLast, @Param("userId") Long userId);

    void deleteByUserId (Long userId);
    List<Message> getFromInterval(@Param("first") Long first, @Param("last") Long last);
    Integer getcountSinceById (Long messageIdSince);
}
/*select distributor_id,
    count(*) total,
    sum(case when level = 'exec' then 1 else 0 end) ExecCount,
    sum(case when level = 'personal' then 1 else 0 end) PersonalCount
from yourtable
group by distributor_id

SELECT d.*,
COUNT(distinct d2.id) AS department_count,
COUNT(distinct e.id) AS employee_count
FROM department AS d
LEFT JOIN department AS d2 ON (d.id = d2.parent_id)
LEFT JOIN employees AS e ON (e.department_id = d.id)
GROUP BY d.id
ORDER BY d.id*/
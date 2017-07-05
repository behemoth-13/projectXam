package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.DTO.MessageDTO;
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
    @Select("SELECT m.id AS id, m.date_message AS dateMessage, m.message AS message, u.login AS userLogin, " +
            "COUNT(l.id) AS countLike, COUNT(d.id) AS countDisLike, COUNT(c.id) AS countComment " +
            "FROM message  WHERE country_id = #{countryId}")
    @Results(value={
            @Result(property="countryId", column ="country_id" )
    })
    List<MessageDTO> getLasts (Integer countLast);

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
package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.Favorite;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Afanasyeu Alexei on 08.07.2017.
 */
@Repository
public interface FavoriteMapper {
    @Insert("INSERT INTO favorite (user_id, message_id) " +
            "VALUES (#{userId}, #{messageId})")
    @Options(useGeneratedKeys=true,
            keyProperty="id",
            flushCache=Options.FlushCachePolicy.DEFAULT,
            keyColumn="id")
    void insertFavorite(Favorite favorite);

    @Delete("DELETE FROM favorite WHERE user_id = #{userId} AND message_id = #{messageId}")
    void delete(Favorite favorite);

    @Select("SELECT if(COUNT(*)>0,'true','false') FROM `favorite` WHERE user_id = #{userId} AND message_id = #{messageId}")
    Boolean isExist (Favorite favorite);
}
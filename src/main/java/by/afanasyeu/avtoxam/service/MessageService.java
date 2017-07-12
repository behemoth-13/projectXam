package by.afanasyeu.avtoxam.service;

import by.afanasyeu.avtoxam.dao.entities.DTO.MessageDTO;
import by.afanasyeu.avtoxam.dao.entities.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */
public interface MessageService {
    void insertMessage(Message message);
    List<MessageDTO> getLasts (@Param("countLast") Integer countLast, @Param("userId") Long userId);
    void deleteByMessageIdUserId (@Param("messageId") Long messageId, @Param("userId") Long userId);
    List<MessageDTO> getFromInterval(@Param("first") Long first, @Param("count") Integer count, @Param("userId") Long userId);
    Integer getCountSinceById (Long messageIdSince);
    List<MessageDTO> getLastLikedMessage(@Param("countLast") Integer countLast, @Param("userId") Long userId);
    List<MessageDTO> getLikedMessageFromInterval(@Param("first") Long first, @Param("count") Long count, @Param("userId") Long userId);
    List<MessageDTO> getLastFavoriteMessage(@Param("countLast") Integer countLast, @Param("userId") Long userId);
    List<MessageDTO> getFavoriteMessageFromInterval(@Param("first") Long first, @Param("count") Integer count, @Param("userId") Long userId);
}

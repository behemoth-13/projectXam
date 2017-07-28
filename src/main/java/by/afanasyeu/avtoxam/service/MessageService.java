package by.afanasyeu.avtoxam.service;

import by.afanasyeu.avtoxam.dao.entities.DTO.MessageDTO;
import by.afanasyeu.avtoxam.dao.entities.Message;
import by.afanasyeu.avtoxam.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */
public interface MessageService {
    void insertMessage(Message message);
    List<MessageDTO> getLasts (Integer countLast, Long userId) throws ServiceException;
    void deleteByMessageIdUserId (Long messageId, Long userId);
    List<MessageDTO> getFromInterval(Long first, Integer count, Long userId) throws ServiceException;
    Integer getCountSinceById (Long messageIdSince);
    List<MessageDTO> getLastLikedMessage(Integer countLast, Long userId) throws ServiceException;
    List<MessageDTO> getLikedMessageFromInterval(Long first, Integer count, Long userId) throws ServiceException;
    List<MessageDTO> getLastFavoriteMessage(Integer countLast, Long userId) throws ServiceException;
    List<MessageDTO> getFavoriteMessageFromInterval(Long first, Integer count, Long userId) throws ServiceException;
}

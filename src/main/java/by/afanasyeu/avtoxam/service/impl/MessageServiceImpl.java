package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.DTO.MessageDTO;
import by.afanasyeu.avtoxam.dao.entities.Message;
import by.afanasyeu.avtoxam.dao.mappers.MessageMapper;
import by.afanasyeu.avtoxam.service.MessageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageMapper messageMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertMessage(Message message) {
        messageMapper.insertMessage(message);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getLasts(Integer countLast, Long userId) {
        return messageMapper.getLasts(countLast, userId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteByMessageIdUserId(@Param("messageId") Long messageId, @Param("userId") Long userId) {
        messageMapper.deleteByMessageIdUserId(messageId, userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getFromInterval(@Param("first") Long first, @Param("count") Integer count,
                                            @Param("userId") Long userId) {
        return messageMapper.getFromInterval(first, count, userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Integer getCountSinceById(Long messageIdSince) {
        return messageMapper.getCountSinceById(messageIdSince);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getLastLikedMessage(@Param("countLast") Integer countLast, @Param("userId") Long userId) {
        return messageMapper.getLastLikedMessage(countLast,userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getLikedMessageFromInterval(@Param("first") Long first, @Param("count") Long count,
                                                        @Param("userId") Long userId) {
        return messageMapper.getLikedMessageFromInterval(first, count,userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getLastFavoriteMessage(@Param("countLast") Integer countLast, @Param("userId") Long userId) {
        return messageMapper.getLastFavoriteMessage(countLast,userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getFavoriteMessageFromInterval(@Param("first") Long first, @Param("count") Integer count,
                                                           @Param("userId") Long userId) {
        return messageMapper.getFavoriteMessageFromInterval(first, count, userId);
    }
}

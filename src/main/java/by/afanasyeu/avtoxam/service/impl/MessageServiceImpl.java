package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.DTO.MessageDTO;
import by.afanasyeu.avtoxam.dao.entities.Message;
import by.afanasyeu.avtoxam.dao.mappers.MessageMapper;
import by.afanasyeu.avtoxam.service.MessageService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Afanasyeu Alexei
 */

@Service
public class MessageServiceImpl implements MessageService{

    private static final Integer COUNT_LIMIT = 100;

    private final MessageMapper messageMapper;

    @Autowired
    public MessageServiceImpl(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertMessage(Message message) {
        messageMapper.insertMessage(message);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getLasts(Integer countLast, Long userId) throws ServiceException {
        if (countLast > COUNT_LIMIT) {
            throw new ServiceException("CountLast message cannot be greater than " + COUNT_LIMIT + ". CountLast is " + countLast + ".getLasts");
        }
        return messageMapper.getLasts(countLast, userId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteByMessageIdUserId(Long messageId, Long userId) {
        messageMapper.deleteByMessageIdUserId(messageId, userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getFromInterval(Long first, Integer count, Long userId) throws ServiceException {
        if (count > COUNT_LIMIT) {
            throw new ServiceException("Count messages cannot be greater than " + COUNT_LIMIT + ". CountLast is " + count + ".getFromInterval");
        }
        return messageMapper.getFromInterval(first, count, userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Integer getCountSinceById(Long messageIdSince) {
        return messageMapper.getCountSinceById(messageIdSince);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getLastLikedMessage(Integer countLast, Long userId) throws ServiceException {
        if (countLast > COUNT_LIMIT) {
            throw new ServiceException("CountLast messages cannot be greater than " + COUNT_LIMIT + ". CountLast is " + countLast + ".getLastLikedMessage");
        }
        return messageMapper.getLastLikedMessage(countLast,userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getLikedMessageFromInterval(Long first, Integer count, Long userId) throws ServiceException {
        if (count > COUNT_LIMIT) {
            throw new ServiceException("Count messages cannot be greater than " + COUNT_LIMIT + ". CountLast is " + count + ".getLikedMessageFromInterval");
        }
        return messageMapper.getLikedMessageFromInterval(first, count, userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getLastFavoriteMessage(Integer countLast, Long userId) throws ServiceException {
        if (countLast > COUNT_LIMIT) {
            throw new ServiceException("CountLast messages cannot be greater than " + COUNT_LIMIT + ". CountLast is " + countLast + ".getLastFavoriteMessage");
        }
        return messageMapper.getLastFavoriteMessage(countLast,userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<MessageDTO> getFavoriteMessageFromInterval(Long first, Integer count, Long userId) throws ServiceException {
        if (count > COUNT_LIMIT) {
            throw new ServiceException("Count messages cannot be greater than " + COUNT_LIMIT + ". CountLast is " + count + ".getFavoriteMessageFromInterval");
        }
        return messageMapper.getFavoriteMessageFromInterval(first, count, userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Boolean isExistMessage(Long id) {
        return messageMapper.isExistMessage(id);
    }
}

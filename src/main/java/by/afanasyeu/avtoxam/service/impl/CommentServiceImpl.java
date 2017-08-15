package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.Comment;
import by.afanasyeu.avtoxam.dao.entities.DTO.CommentDTO;
import by.afanasyeu.avtoxam.dao.mappers.CommentMapper;
import by.afanasyeu.avtoxam.service.CommentService;
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
public class CommentServiceImpl implements CommentService{

    private static final Integer COUNT_LIMIT = 100;

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<CommentDTO> getFirsts(Long messageId, Long userId, Integer countFirst) throws ServiceException {
        if (countFirst > COUNT_LIMIT) {
            throw new ServiceException("CountFirst comment cannot be greater than " + COUNT_LIMIT + ". CountFirst is " + countFirst + ".getFirsts");
        }
        return commentMapper.getFirsts(messageId, userId, countFirst);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<CommentDTO> getFromInterval(Long messageId, Long first, Integer count, Long userId) throws ServiceException {
        if (count > COUNT_LIMIT) {
            throw new ServiceException("Count comment cannot be greater than " + COUNT_LIMIT + ". Count is " + count + ".getFromInterval");
        }
        return commentMapper.getFromInterval(messageId, first, count, userId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteByCommentIdUserId(Long commentId, Long userId) {
        commentMapper.deleteByCommentIdUserId(commentId, userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Boolean isExistComment(Long id) {
        return commentMapper.isExistComment(id);
    }
}
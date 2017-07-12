package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.Comment;
import by.afanasyeu.avtoxam.dao.entities.DTO.CommentDTO;
import by.afanasyeu.avtoxam.dao.mappers.CommentMapper;
import by.afanasyeu.avtoxam.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Transactional
    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public List<CommentDTO> getFirsts(Long messageId, Long userId, Integer countFirst) {
        return commentMapper.getFirsts(messageId, userId, countFirst);
    }

    @Override
    public List<CommentDTO> getFromInterval(Long messageId, Long first, Integer count, Long userId) {
        return commentMapper.getFromInterval(messageId, first, count, userId);
    }

    @Transactional
    @Override
    public void deleteByCommentIdUserId(Long commentId, Long userId) {
        commentMapper.deleteByCommentIdUserId(commentId, userId);
    }
}

package by.afanasyeu.avtoxam.service;

import by.afanasyeu.avtoxam.dao.entities.Comment;
import by.afanasyeu.avtoxam.dao.entities.DTO.CommentDTO;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Afanasyeu Alexei
 */
public interface CommentService {
    void insertComment(Comment comment);
    List<CommentDTO> getFirsts(Long messageId, Long userId, Integer countFirst) throws ServiceException;
    List<CommentDTO> getFromInterval(Long messageId, Long first, Integer count, Long userId) throws ServiceException;
    void deleteByCommentIdUserId(Long commentId, Long userId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Boolean isExistComment(Long id);
}

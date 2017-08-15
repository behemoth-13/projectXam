package by.afanasyeu.avtoxam.dao.entities;

import org.springframework.validation.BindingResult;

/**
 * Используется для передачи в обе стороны(сервер и клиент)
 * @author Afanasyeu Alexei
 */

public class Like {
    private Long id;

    /**
     * Присваивается сервером.
     * @see by.afanasyeu.avtoxam.controller.rest.LikeRestController#insertLike(Like, BindingResult)
     * @see by.afanasyeu.avtoxam.controller.rest.DisLikeRestController#insertDisLike(Like, BindingResult)
     */
    private Long userId;
    private Long messageId;
    private Long commentId;

    public Like() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getMessageId() {
        return messageId;
    }
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
    public Long getCommentId() {
        return commentId;
    }
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}

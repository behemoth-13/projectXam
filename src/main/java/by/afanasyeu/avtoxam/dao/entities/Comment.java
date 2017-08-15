package by.afanasyeu.avtoxam.dao.entities;

/**
 * Используется для передачи на сервер
 * @see by.afanasyeu.avtoxam.dao.entities.DTO.CommentDTO
 */
public class Comment {
    private Long id;

    /**
     * Присваивается сервером.
     * @see by.afanasyeu.avtoxam.controller.rest.CommentRestController
     */
    private Long userId;
    private Long messageId;
    /**
     * От 1 до 2048 символов
     */
    private String comment;

    public Comment() {
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
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
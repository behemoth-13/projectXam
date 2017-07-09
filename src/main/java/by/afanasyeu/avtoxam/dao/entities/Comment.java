package by.afanasyeu.avtoxam.dao.entities;

/**
 * Created by Afanasyeu Alexei on 28.06.2017.
 */
public class Comment {
    private Long id;
    private Long userId;
    private Long messageId;
    private String comment;         //от 1 до 2048 символов

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
package by.afanasyeu.avtoxam.dao.entities;

/**
 * Created by Afanasyeu Alexei on 28.06.2017.
 */
public class DisLike {
    private Long id;
    private Long userId;
    private Long messageId;
    private Long commentId;

    public DisLike() {
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

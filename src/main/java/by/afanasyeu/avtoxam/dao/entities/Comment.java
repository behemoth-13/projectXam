package by.afanasyeu.avtoxam.dao.entities;

import java.util.Date;

/**
 * Created by Afanasyeu Alexei on 28.06.2017.
 */
public class Comment {
    private Long id;
    private Long userId;
    private Long messageId;
    private Date dateComment;
    private String comment;         //от 1 до 2048 символов
    private Integer countlike;      //нет в бд
    private Integer countDislike;   //нет в бд
    private Character status;       //нет в бд

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

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCountlike() {
        return countlike;
    }

    public void setCountlike(Integer countlike) {
        this.countlike = countlike;
    }

    public Integer getCountDislike() {
        return countDislike;
    }

    public void setCountDislike(Integer countDislike) {
        this.countDislike = countDislike;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }
}
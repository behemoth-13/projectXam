package by.afanasyeu.avtoxam.dao.entities;

import java.util.Date;

/**
 * Created by Afanasyeu Alexei on 28.06.2017.
 */
public class Message {
    private Long id;
    private Long userId;
    private Date dateMessage;
    private String message;         //от 1 до 2048 символов
    private Integer countlike;      //нет в бд
    private Integer countDislike;   //нет в бд
    private Integer countComment;   //нет в бд
    private Character status;       //нет в бд
    /*status
    null - лайк нет, дизлайк нет, избранное нет;
    a - лайк нет, дизлайк нет, избранное да;
    b - лайк нет, дизлайк да, избранное нет;
    c - лайк нет, дизлайк да, избранное да;
    d - лайк да, дизлайк нет, избранное нет;
    e - лайк да, дизлайк нет, избранное да;*/

    public Message() {
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

    public Date getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Integer getCountComment() {
        return countComment;
    }

    public void setCountComment(Integer countComment) {
        this.countComment = countComment;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }
}

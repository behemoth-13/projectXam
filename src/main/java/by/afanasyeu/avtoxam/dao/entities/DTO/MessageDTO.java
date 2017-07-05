package by.afanasyeu.avtoxam.dao.entities.DTO;

import java.util.Date;

/**
 * Created by Afanasyeu Alexei on 04.07.2017.
 */
public class MessageDTO {
    private Long id;
    private Date dateMessage;
    private String message;         //от 1 до 2048 символов
    private String userLogin;       //нет в бд
    private Integer countLike;      //нет в бд
    private Integer countDisLike;   //нет в бд
    private Integer countComment;   //нет в бд
    private Character status;       //нет в бд

    /*status
    null - лайк нет, дизлайк нет, избранное нет;
    a - лайк нет, дизлайк нет, избранное да;
    b - лайк нет, дизлайк да, избранное нет;
    c - лайк нет, дизлайк да, избранное да;
    d - лайк да, дизлайк нет, избранное нет;
    e - лайк да, дизлайк нет, избранное да;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Integer getCountLike() {
        return countLike;
    }

    public void setCountLike(Integer countLike) {
        this.countLike = countLike;
    }

    public Integer getCountDisLike() {
        return countDisLike;
    }

    public void setCountDisLike(Integer countDisLike) {
        this.countDisLike = countDisLike;
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

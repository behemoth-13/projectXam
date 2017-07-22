package by.afanasyeu.avtoxam.dao.entities.DTO;

import java.util.Date;

/**
 * Created by Afanasyeu Alexei on 04.07.2017.
 */
public class CommentDTO {
    private Long id;
    private String dateComment;
    private String comment;
    private String userLogin;
    private Integer countLike;
    private Integer countDisLike;
    private Character status;

    /*status
    null - лайк нет, дизлайк нет;
    a - лайк нет, дизлайк да;
    b - лайк да, дизлайк нет;*/

    public CommentDTO() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDateComment() {
        return dateComment;
    }
    public void setDateComment(String dateComment) {
        this.dateComment = dateComment;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
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
    public Character getStatus() {
        return status;
    }
    public void setStatus(Character status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", dateComment=" + dateComment +
                ", comment='" + comment + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", countLike=" + countLike +
                ", countDisLike=" + countDisLike +
                ", status=" + status +
                '}';
    }
}

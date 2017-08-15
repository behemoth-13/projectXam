package by.afanasyeu.avtoxam.dao.entities.DTO;

import by.afanasyeu.avtoxam.dao.entities.Like;

/**
 * Используется для передачи на клиент
 * @see by.afanasyeu.avtoxam.dao.entities.Comment
 * @author Afanasyeu Alexei
 */

public class CommentDTO {
    private Long id;
    private String dateComment;
    private String comment;

    /** Берется из БД. Из таблицы `user.login` где comment.user_id = user.id
     * @see by.afanasyeu.avtoxam.dao.mappers.CommentMapper
     */
    private String userLogin;

    /** Берется из БД. Количество {@link Like} из таблицы `like` где like.comment_id = comment.id
     * @see by.afanasyeu.avtoxam.dao.mappers.CommentMapper
     */
    private Integer countLike;

    /** Берется из БД. Количество disLike({@link Like}) из таблицы `dis_like` где dis_like.comment_id = comment.id
     * @see by.afanasyeu.avtoxam.dao.mappers.CommentMapper
     */
    private Integer countDisLike;

    /**
     * status <br/>
     * null - лайк нет, дизлайк нет; <br/>
     * a - лайк нет, дизлайк да; <br/>
     * b - лайк да, дизлайк нет; <br/>
     *
     * @see by.afanasyeu.avtoxam.dao.mappers.CommentMapper
     */
    private Character status;

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

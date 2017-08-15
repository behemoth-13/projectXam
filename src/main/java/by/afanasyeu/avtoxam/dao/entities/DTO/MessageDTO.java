package by.afanasyeu.avtoxam.dao.entities.DTO;

import by.afanasyeu.avtoxam.dao.entities.Comment;
import by.afanasyeu.avtoxam.dao.entities.Like;

/**
 * Используется для передачи на клиента
 * @see by.afanasyeu.avtoxam.dao.entities.Message
 * @author Afanasyeu Alexei
 */
public class MessageDTO {
    private Long id;
    private String dateMessage;

    /** От 1 до 2048 символов*/
    private String message;
    private String userLogin;

    /** Берется из БД. Количество {@link Like} из таблицы `like` где like.message_id = message.id
     * @see by.afanasyeu.avtoxam.dao.mappers.MessageMapper
     */
    private Integer countLike;

    /** Берется из БД. Количество disLike({@link Like}) из таблицы `dis_like` где dis_like.messageId = message.id
     * @see by.afanasyeu.avtoxam.dao.mappers.MessageMapper
     */
    private Integer countDisLike;

    /** Берется из БД. Количество {@link Comment} из таблицы `comment` где comment.message_id = message.id
     * @see by.afanasyeu.avtoxam.dao.mappers.MessageMapper
     */
    private Integer countComment;

    /**
     * status <br/>
     * null - лайк нет, дизлайк нет, избранное нет; <br/>
     * a - лайк нет, дизлайк нет, избранное да; <br/>
     * b - лайк нет, дизлайк да, избранное нет; <br/>
     * c - лайк нет, дизлайк да, избранное да; <br/>
     * d - лайк да, дизлайк нет, избранное нет; <br/>
     * e - лайк да, дизлайк нет, избранное да; <br/>
     *
     * @see by.afanasyeu.avtoxam.dao.mappers.MessageMapper
     */
    private Character status;



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDateMessage() {
        return dateMessage;
    }
    public void setDateMessage(String dateMessage) {
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

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", dateMessage=" + dateMessage +
                ", message='" + message + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", countLike=" + countLike +
                ", countDisLike=" + countDisLike +
                ", countComment=" + countComment +
                ", status=" + status +
                '}';
    }
}

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
}

package by.afanasyeu.avtoxam.dao.entities;

import org.springframework.validation.BindingResult;

import java.util.Date;

/**
 * Используется для передачи на сервер
 * @see by.afanasyeu.avtoxam.dao.entities.Message
 * @author Afanasyeu Alexei
 */
public class Message {
    private Long id;

    /**
     * Присваивается сервером.
     * @see by.afanasyeu.avtoxam.controller.rest.MessageRestController#insertMessage(Message, BindingResult)
     */
    private Long userId;

    /**
     * Присваивается базой данных(message.date_message (TIMESTAMP))
     */
    private Date dateMessage;

    /**
     * От 1 до 2048 символов
     */
    private String message;

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

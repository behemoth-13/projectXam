package by.afanasyeu.avtoxam.dao.entities;

/**
 * Используется для передачи в обе стороны(сервер и клиент)
 * @author Afanasyeu Alexei
 */
public class Favorite {
    private Long id;

    /**
     * Присваивается сервером.
     * @see by.afanasyeu.avtoxam.controller.rest.FavoriteRestController
     */
    private Long userId;
    private Long messageId;

    public Favorite() {
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
}

package by.afanasyeu.avtoxam.dao.entities;

import java.util.Date;

/**
 * Используется для передачи на сервер
 * @see by.afanasyeu.avtoxam.dao.entities.DTO.UserDTO
 * @author Afanasyeu Alexei
 */
public class User {
    private Long id;
    private Integer region;

    /**
     * От 2 до 15 символов
     */
    private String login;

    /**
     * От 6 до 10 символов, но зашифрованными с клиента приходят 64 символов
     */
    private String password;

    /**
     * Присваивается сервером
     * @see by.afanasyeu.avtoxam.service.impl.UserServiceImpl#insertUser(User)
     */
    private Date regDate;

    public User() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getRegion() {
        return region;
    }
    public void setRegion(Integer region) {
        this.region = region;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", region=" + region +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}

package by.afanasyeu.avtoxam.dao.entities;

import java.util.Date;

/**
 * Created by Afanasyeu Alexei on 28.06.2017.
 */
public class User {
    private Long id;
    private Integer region;
    private String login;       //от 2 до 15 символов
    private String password;    //от 6 до 10 символов, но зашифрованными с клиента приходят 64 символов
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


}

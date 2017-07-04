package by.afanasyeu.avtoxam.dao.entities.DTO;

import java.util.Date;

/**
 * Created by Afanasyeu Alexei on 04.07.2017.
 */
public class UserDTO {
    private String country;
    private String region;
    private String login;       //от 2 до 15 символов
    private Date regDate;

    public UserDTO() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", login='" + login + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}


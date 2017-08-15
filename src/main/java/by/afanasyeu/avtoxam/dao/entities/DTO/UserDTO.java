package by.afanasyeu.avtoxam.dao.entities.DTO;

import by.afanasyeu.avtoxam.dao.entities.User;

/**
 * Используется для передачи на клиента
 * @see by.afanasyeu.avtoxam.dao.entities.User
 * @author Afanasyeu Alexei
 */
public class UserDTO {

    /**
     * Берется из БД. Из таблицы `country.country` где region.country_id = country.id
     */
    private String country;

    /**
     * Берется из БД. Из таблицы `region.region` где user.region = region.id
     */
    private String region;

    /**
     * Берется из БД. Из таблицы `user.login`
     * от 2 до 15 символов
     */
    private String login;

    /**
     * Присваивается сервером
     * @see by.afanasyeu.avtoxam.service.impl.UserServiceImpl#insertUser(User)
     */
    private String regDate;

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
    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(String regDate) {
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
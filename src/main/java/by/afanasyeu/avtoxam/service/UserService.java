package by.afanasyeu.avtoxam.service;

import by.afanasyeu.avtoxam.dao.entities.DTO.UserDTO;
import by.afanasyeu.avtoxam.dao.entities.User;
import by.afanasyeu.avtoxam.service.exception.ServiceException;

/**
 * @author Afanasyeu Alexei
 */

public interface UserService {
    void insertUser(User user);
    Boolean isExistLogin(String login);
    void updateLogin(String oldLogin, String newLogin) throws ServiceException;
    void updateRegion(Long id, Integer region) throws ServiceException;
    void updatePassword(Long id, String newPassword) throws ServiceException;
    void deleteById(Long id);
    Boolean isExistUser(String login, String password);
    UserDTO getUserDTO(String login) throws ServiceException;
}

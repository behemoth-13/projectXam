package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.DTO.UserDTO;
import by.afanasyeu.avtoxam.dao.entities.User;
import by.afanasyeu.avtoxam.dao.mappers.RegionMapper;
import by.afanasyeu.avtoxam.dao.mappers.UserMapper;
import by.afanasyeu.avtoxam.service.UserService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Afanasyeu Alexei
 */

@Service
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final RegionMapper regionMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RegionMapper regionMapper) {
        this.userMapper = userMapper;
        this.regionMapper = regionMapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertUser(User user) {
        System.out.println(user);
        user.setRegDate(new Date());
        userMapper.insertUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Boolean isExistLogin(String login) {
        return userMapper.isExistLogin(login);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void updateLogin(String oldLogin, String newLogin) throws ServiceException {
        newLogin = newLogin.trim();
        if (newLogin.length() >= 2 && newLogin.length() <= 15) {
            userMapper.updateLogin(oldLogin, newLogin);
        } else {
            throw new ServiceException("newLogin is not valid. newLogin is: " + newLogin +
            ". oldLogin is: " + oldLogin);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void updateRegion(Long userId, Integer newRegion) throws ServiceException {
        if (regionMapper.isExistRegion(newRegion)) {
            userMapper.updateRegion(userId, newRegion);
        } else {
            throw new ServiceException("Region not exist. userId: " + userId + ". regionId: " + newRegion);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void updatePassword(Long id, String newPassword) throws ServiceException {
        if (newPassword.length() == 64) {
            userMapper.updatePassword(id, newPassword);
        } else {
            throw new ServiceException("newPassword is not valid. newLogin is: " + newPassword +
                    ". userId is: " + id);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Boolean isExistUser(String login, String password) {
        return userMapper.isExistUser(login, password);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public UserDTO getUserDTO(String login) throws ServiceException {
        UserDTO user = userMapper.getUserDTO(login);
        if (user != null) {
            return user;
        } else {
            throw new ServiceException("User with login: " + login + " not founded");
        }
    }
}

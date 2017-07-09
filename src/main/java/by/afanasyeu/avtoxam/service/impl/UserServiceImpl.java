package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.DTO.UserDTO;
import by.afanasyeu.avtoxam.dao.entities.User;
import by.afanasyeu.avtoxam.dao.mappers.RegionMapper;
import by.afanasyeu.avtoxam.dao.mappers.UserMapper;
import by.afanasyeu.avtoxam.service.UserService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Afanasyeu Alexei on 09.07.2017.
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RegionMapper regionMapper;

    @Override
    @Transactional
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public Boolean isExistLogin(String login) {
        return userMapper.isExistLogin(login);
    }

    @Override
    @Transactional
    public void updateLogin(String oldLogin, String newLogin) throws ServiceException {
        newLogin = newLogin.trim();
        if (newLogin.length() >= 2 && newLogin.length() <= 15) {
            userMapper.updateLogin(oldLogin, newLogin);
        } else {
            throw new ServiceException("newLogin is not valid. newLogin is: " + newLogin +
            ". oldLogin is: " + oldLogin);
        }
    }

    @Override
    @Transactional
    public void updateRegion(Long id, Integer region) throws ServiceException {
        if (regionMapper.isExistRegion(region)) {
            userMapper.updateRegion(id, region);
        } else {
            throw new ServiceException("Region not exist. userId: " + id + ". regionId: " + region);
        }
    }

    @Override
    @Transactional
    public void updatePassword(Long id, String newPassword) throws ServiceException {
        if (newPassword.length() == 64) {
            userMapper.updatePassword(id, newPassword);
        } else {
            throw new ServiceException("newPassword is not valid. newLogin is: " + newPassword +
                    ". userId is: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public Boolean isExistUser(String login, String password) {
        return userMapper.isExistUser(login, password);
    }

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

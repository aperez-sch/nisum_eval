package com.nisum.evaluation.service;

import com.nisum.evaluation.dao.IUserDAO;
import com.nisum.evaluation.domain.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {
    
    @Autowired
    private IUserDAO userDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserListAll() {
        return (List<User>) userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(User user) {
        return userDao.findById(user.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(User user) {
        return userDao.findByEmail(user.getEmail()).orElse(null);
    }
    
    @Transactional
    @Override
    public void insertUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        //TODO VALIDAR QUE TENGA ID
        userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public void disableUser(User user) {
        user.setActive(false);
        userDao.save(user);
    }
    
}

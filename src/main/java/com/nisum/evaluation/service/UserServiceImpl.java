package com.nisum.evaluation.service;

import com.nisum.evaluation.dao.IUserDAO;
import com.nisum.evaluation.domain.User;
import java.util.List;
import java.util.UUID;
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
        return userDao.findUserListWithoutActive();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(UUID id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
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
    public void deleteUser(UUID id) {
        User user = getUserById(id);
        userDao.delete(user);
    }

    @Override
    @Transactional
    public void disableUser(User user) {
        user.setActive(false);
        userDao.save(user);
    }
    
}

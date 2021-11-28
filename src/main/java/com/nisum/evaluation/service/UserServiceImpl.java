package com.nisum.evaluation.service;

import com.nisum.evaluation.dao.IUserDAO;
import com.nisum.evaluation.domain.Phone;
import com.nisum.evaluation.domain.User;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {
    
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private IUserDAO userDao;
    
    @Autowired
    private IPhoneService phoneService;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserListAll() {
        log.info("User service - getUserListAl");
        return (List<User>) userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserList() {
        log.info("User service - getUserList");
        return userDao.findUserListWithoutActive();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(UUID id) {
        log.info("User service - getUserById {}", id);
        return userDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        log.info("User service - getUserByEmail {}", email);
        return userDao.findByEmail(email).orElse(null);
    }
    
    @Transactional
    @Override
    public void insertUser(User user) {
        log.info("User service - insertUser {}", user);
        userDao.save(user);
        System.out.println("USAR AHORA VALE:" + user);
        for (Phone phone : user.getPhones()) {
            phone.setUser(user);
            this.phoneService.insertPhone(phone);
        }
        
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        log.info("User service - updateUser {}", user);
        userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        log.info("User service - deleteUser id {}", id);
        User user = getUserById(id);
        userDao.delete(user);
    }

    @Override
    @Transactional
    public void disableUser(User user) {
        log.info("User service - disableUser user {}", user);
        user.setActive(false);
        userDao.save(user);
    }
    
}

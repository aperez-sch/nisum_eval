package com.nisum.evaluation.service;

import com.nisum.evaluation.dao.IUserDAO;
import com.nisum.evaluation.domain.Phone;
import com.nisum.evaluation.domain.User;
import com.nisum.evaluation.exception.DBException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        if (!validateEmail(user.getEmail())) throw new DBException("El correo no posee el formato correcto. "
                                                            + "(Ejemplo:nombrecorreo@dominio.cl)");
        if (!validatePass(user.getPassword())) 
            throw new DBException("La clave no posee un formato valido, debe comenzar por una Mayuscula, el resto en minuscula "
                    + "y finalizar en 2 numeros, ejemplo: Aabcdefghi32");
        User found = getUserByEmail(user.getEmail());
        if (found!=null) throw new DBException("El correo ya se encuentra registrado");
        userDao.save(user);
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
    /**
     * validate email format. example: axyz@domail.cl
     * @param email
     * @return true if is valid, else false
     */
    private Boolean validateEmail(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);        
        return mather.find();
    }
    /**
     * validate password format. 1 Upper, many lower and end with 2 numbers
     * Example: Aabcdef12
     * @param pass
     * @return true if is valid, else false
     */
    private Boolean validatePass(String pass) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]+[0-9]{2}$");
        Matcher mather = pattern.matcher(pass);        
        return mather.find();
    }
}

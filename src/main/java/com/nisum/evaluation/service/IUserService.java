
package com.nisum.evaluation.service;

import com.nisum.evaluation.domain.User;
import java.util.List;

public interface IUserService {
    public List<User> getUserListAll();
    
    /**
     * get user list - only active
     * @return user's list
     */
    public List<User> getUserList();
    
    public User getUserById(User user);
    
    public User getUserByEmail(User user);
    
    public void insertUser(User user);
    
    public void updateUser(User user);
    
    public void deleteUser(User user);
    
    public void disableUser(User user);
}

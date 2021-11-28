package com.nisum.evaluation.service;

import com.nisum.evaluation.domain.User;
import java.util.List;
import java.util.UUID;

public interface IUserService {
    public List<User> getUserListAll();
    
    /**
     * get user list - only active
     * @return user's list
     */
    public List<User> getUserList();
    
    public User getUserById(UUID id);
    
    public User getUserByEmail(String email);
    
    public void insertUser(User user);
    
    public void updateUser(User user);
    
    public void deleteUser(UUID id);
    
    public void disableUser(User user);
}

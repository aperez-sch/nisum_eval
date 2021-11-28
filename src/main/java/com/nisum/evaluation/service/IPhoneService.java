package com.nisum.evaluation.service;

import com.nisum.evaluation.domain.Phone;
import java.util.List;
import java.util.UUID;

public interface IPhoneService {
    public List<Phone> getPhoneList();
    
    public Phone getPhoneById(UUID id);
    
    public List<Phone> getPhoneListByNumber(String phoneNumber);
    
    public List<Phone> getPhoneListByCity(String cityCode);
    
    public List<Phone> getPhoneListByCountry(String countryCode);
    
    public void insertPhone(Phone phone);
    
    public void updatePhone(Phone phone);
    
    public void deletePhone(UUID id);
    
}

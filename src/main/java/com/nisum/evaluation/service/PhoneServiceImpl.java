package com.nisum.evaluation.service;

import com.nisum.evaluation.dao.IPhoneDAO;
import com.nisum.evaluation.domain.Phone;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhoneServiceImpl implements IPhoneService{

    @Autowired
    private IPhoneDAO phoneDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Phone> getPhoneList() {
        return (List<Phone>) phoneDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Phone getPhoneById(UUID id) {
        return phoneDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phone> getPhoneListByNumber(String phoneNumber) {
        return phoneDao.getPhoneListByNumber(phoneNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phone> getPhoneListByCity(String cityCode) {
        return phoneDao.getPhoneListByCity(cityCode);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phone> getPhoneListByCountry(String countryCode) {
        return phoneDao.getPhoneListByCountry(countryCode);
    }

    @Override
    @Transactional
    public void insertPhone(Phone phone) {
        phoneDao.save(phone);
    }

    @Override
    @Transactional
    public void updatePhone(Phone phone) {
        //TODO VALIDAR que tenga id
        phoneDao.save(phone);
    }

    @Override
    @Transactional
    public void deletePhone(UUID id) {
        Phone phone = getPhoneById(id);
        phoneDao.delete(phone);
    }
}

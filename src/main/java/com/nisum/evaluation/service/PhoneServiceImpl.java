package com.nisum.evaluation.service;

import com.nisum.evaluation.dao.IPhoneDAO;
import com.nisum.evaluation.domain.Phone;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhoneServiceImpl implements IPhoneService{

    private final Logger log = LoggerFactory.getLogger(PhoneServiceImpl.class);
    
    @Autowired
    private IPhoneDAO phoneDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Phone> getPhoneList() {
        log.info("Phone service - getPhoneList");
        return (List<Phone>) phoneDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Phone getPhoneById(UUID id) {
        log.info("Phone service - getPhoneById id {}", id);
        return phoneDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phone> getPhoneListByNumber(String phoneNumber) {
        log.info("Phone service - getPhoneListByNumber ", phoneNumber);
        return phoneDao.getPhoneListByNumber(phoneNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phone> getPhoneListByCity(String cityCode) {
        log.info("Phone service - getPhoneListByCity cty ", cityCode);
        return phoneDao.getPhoneListByCity(cityCode);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phone> getPhoneListByCountry(String countryCode) {
        log.info("Phone service - getPhoneListByCountry code ", countryCode);
        return phoneDao.getPhoneListByCountry(countryCode);
    }

    @Override
    @Transactional
    public void insertPhone(Phone phone) {
        System.out.println("LLEGA ACA Y EL VALOR ES: " + phone);
        log.info("Phone service - insertPhone ", phone);
        phoneDao.save(phone);
    }

    @Override
    @Transactional
    public void updatePhone(Phone phone) {
        log.info("Phone service - updatePhone ", phone);
        phoneDao.save(phone);
    }

    @Override
    @Transactional
    public void deletePhone(UUID id) {
        log.info("Phone service - deletePhone id ", id);
        Phone phone = getPhoneById(id);
        phoneDao.delete(phone);
    }
}

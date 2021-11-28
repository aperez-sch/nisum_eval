package com.nisum.evaluation.dao;

import com.nisum.evaluation.domain.Phone;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IPhoneDAO extends CrudRepository<Phone, UUID> {
    @Query("SELECT p FROM Phone p WHERE p.number = '?1'")
    List<Phone> getPhoneListByNumber(String number);
    
    @Query("SELECT p FROM Phone p WHERE p.cityCode = '?1'")
    List<Phone> getPhoneListByCity(String cityCode);
    
    @Query("SELECT p FROM Phone p WHERE p.countryCode = '?1'")
    List<Phone> getPhoneListByCountry(String countryCode);
}

package com.nisum.evaluation.controller;

import com.nisum.evaluation.domain.Phone;
import com.nisum.evaluation.service.IPhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class PhoneController {
    
    @Autowired
    private IPhoneService phoneService;
    
    @GetMapping("/phones")
    public ResponseEntity<List<Phone>> getPhones() {
        log.info("REST request to get phones : {}");
        List<Phone> results = phoneService.getPhoneList();
        return ResponseEntity.ok(results);
    }
    
    @GetMapping("/phoneById/{id}")
    public ResponseEntity<Phone> getPhoneByid(@PathVariable UUID id) {
        log.info("REST request to get phone by id : {} ", id);
        Phone result = phoneService.getPhoneById(id);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/phonesBynumber/{number}")
    public ResponseEntity<List<Phone>> getPhonesByNumber(@PathVariable String number) {
        log.info("REST request to get phone by number : {} ", number);
        List<Phone> results = phoneService.getPhoneListByNumber(number);
        return ResponseEntity.ok(results);
    }
    
    @PostMapping("/insertPhone")
    public ResponseEntity<Phone> insertPhone(@RequestBody Phone phone) throws Exception {
        log.debug("REST request to save phone : {}", phone);
        if (phone.getId() != null) { throw new Exception("colocar excepcion"); }
        phoneService.insertPhone(phone);
        return ResponseEntity.ok(phone);
    }
    
    @PutMapping("/updatePhone")
    public ResponseEntity<Phone> updatePhone(@RequestBody Phone phone) throws Exception {
        log.debug("REST request to update phone : {}", phone);
        if (phone.getId() == null) { throw new Exception("colocar excepcion"); }
        phoneService.updatePhone(phone);
        return ResponseEntity.ok(phone);
    }
    
    @DeleteMapping("/phone/{id}")
    public ResponseEntity.BodyBuilder deleteUser(@PathVariable UUID id) {
        log.debug("REST request to delete phone with id : {}", id);
        phoneService.deletePhone(id);
        return ResponseEntity.ok();
    }
}

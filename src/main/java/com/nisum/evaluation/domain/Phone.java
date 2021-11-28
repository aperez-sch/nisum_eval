package com.nisum.evaluation.domain;

import java.util.UUID;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "phones")
public class Phone {
    
    @Id
    @Column(unique = true, updatable = false)
    private UUID id;
    
    private String number;
    
    @Column(name = "city_code")
    private String cityCode;
    
    @Column(name = "country_code")
    private String countryCode;
    
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private User user;
    
    @PrePersist
    public void onInsert() {
        this.id = UUID.randomUUID();
    }
    
}

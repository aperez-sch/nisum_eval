package com.nisum.evaluation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(value = "citycode")
    private String cityCode;
    
    @Column(name = "country_code")
    @JsonProperty(value = "countrycode")//en el PDF este campo esta como contrycode
    private String countryCode;         //se asumio que era un error y se cambio a country
    
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private User user;
    
    @PrePersist
    public void onInsert() {
        this.id = UUID.randomUUID();
    }
    
}

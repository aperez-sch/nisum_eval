
package com.nisum.evaluation.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
public class User implements Serializable {
    
    private static final Long SerialVersionUID = 1L;
    
    @Id
    @Column(unique = true, updatable = false)
    //@GeneratedValue(strategy = GenerationType.TABLE)
    private UUID id;
    
    private String name;
    
    private String email;
    
    private String password;
    
    private Instant created; 
    
    private Instant modified;
    
    @Column(name = "last_login")
    private Instant lastLogin; 
    
    private Boolean active;
    
    @OneToMany(mappedBy = "user")
    private List<Phone> phones;
    
    @PrePersist
    public void onInsert() {
        this.id = UUID.randomUUID();
        this.created = Instant.now();
        this.modified = Instant.now();
        this.lastLogin = Instant.now();
        this.active = true;
    }
    
    @PreUpdate
    public void onUpdate() {
        this.modified = Instant.now();
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password 
                + ", created=" + created + ", modified=" + modified + ", lastLogin=" + lastLogin + ", active=" + active +  '}';
    }
}

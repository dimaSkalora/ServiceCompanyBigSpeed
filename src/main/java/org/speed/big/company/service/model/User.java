package org.speed.big.company.service.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name, u.email"),
        @NamedQuery(name = User.BETWEEN_REGISTERED, query = "SELECT u FROM User u " +
                " where u.registered between :startDate and :endDate " +
                " ORDER BY u.registered")
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx"),
        @UniqueConstraint(columnNames = "phone", name = "users_unique_phone_idx")})
public class User extends AbstractBaseEntity{

    public static final String DELETE = "User.delete";
    public static final String ALL_SORTED = "User.getAllSorted";
    public static final String BETWEEN_REGISTERED = "User.betweenRegistered";

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(min = 5,max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 64)
    private String password;

    @Column(name = "phone", nullable = false, unique = true)
    @NotBlank
    @Size(min = 5, max = 20)
    private String phone;

    @Column(name = "registered", nullable = false)
    private LocalDate registered;        //Дата регистраиции пользователя

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled;        //true - активный, false - не активный

    public User() {
    }

    public User(Integer id, String name, String email, String password, String phone, LocalDate registered, boolean enabled) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.registered = registered;
        this.enabled=enabled;
    }

    public User(Integer id, String name, String email, String password, String phone) {
        this(id, name, email, password, phone, LocalDate.now(), true);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", registered=" + registered +
                ", enabled=" + enabled +
                ", id=" + id +
                '}';
    }
}
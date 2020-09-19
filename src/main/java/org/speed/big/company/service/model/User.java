package org.speed.big.company.service.model;

import org.speed.big.company.service.HasId;

import java.time.LocalDate;

public class User implements HasId {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private LocalDate registered;        //Дата регистраиции пользователя
    private boolean enabled;        //true - активный, false - не активный

    public User() {
    }

    public User(Integer id, String name, String email, String password, String phone, LocalDate registered, boolean enabled) {
        this.id=id;
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
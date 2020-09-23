package org.speed.big.company.service.model;

import java.time.LocalDateTime;

public class UserRole extends AbstractBaseEntity{
    private User userId;
    private Role roleId;
    private LocalDateTime dateTime;

    public UserRole() {
    }

    public UserRole(Integer id, User userId, Role roleId, LocalDateTime dateTime) {
        super(id);
        this.userId = userId;
        this.roleId = roleId;
        this.dateTime = dateTime;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", dateTime=" + dateTime +
                ", id=" + id +
                '}';
    }
}

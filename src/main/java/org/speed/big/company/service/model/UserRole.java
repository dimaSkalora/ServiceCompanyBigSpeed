package org.speed.big.company.service.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_role", uniqueConstraints = @UniqueConstraint(columnNames = "user_id",
        name = "ur_udid_rid_unique_idx"))
public class UserRole extends AbstractBaseEntity{
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roleId;
    @NotNull
    @Column(name = "dateTime", nullable = false)
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

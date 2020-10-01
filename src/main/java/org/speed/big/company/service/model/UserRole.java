package org.speed.big.company.service.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","role_id"},
        name = "ur_uid_rid_unique_idx"))
public class UserRole extends AbstractBaseEntity{
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roleId;
    @NotNull
    @Column(name = "dateTime", nullable = false)
    private LocalDateTime dateTime;
    @NotNull
    @Column(name = "comment", nullable = false)
    private String comment;

    public UserRole() {
    }

    public UserRole(User userId, Role roleId, @NotNull LocalDateTime dateTime, @NotNull String comment) {
        this.userId = userId;
        this.roleId = roleId;
        this.dateTime = dateTime;
        this.comment = comment;
    }

    public UserRole(Integer id, User userId, Role roleId, @NotNull LocalDateTime dateTime, @NotNull String comment) {
        super(id);
        this.userId = userId;
        this.roleId = roleId;
        this.dateTime = dateTime;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId.getId() +
                ", roleId=" + roleId+
                ", dateTime=" + dateTime +
                ", comment='" + comment + '\'' +
                ", id=" + id +
                '}';
    }
}

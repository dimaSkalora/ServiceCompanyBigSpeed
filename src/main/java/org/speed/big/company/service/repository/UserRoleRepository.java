package org.speed.big.company.service.repository;

import org.speed.big.company.service.model.UserRole;

import java.util.List;

public interface UserRoleRepository {
    UserRole save(UserRole userRole);
    UserRole get(int id);
    boolean delete(int id);
    List<UserRole> getAll();
    List<UserRole> filter(UserRole userRole);
    List<UserRole> getByUser(int userId);
    List<UserRole> getByRole(int roleId);
}

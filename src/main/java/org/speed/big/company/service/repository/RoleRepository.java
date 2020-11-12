package org.speed.big.company.service.repository;

import org.speed.big.company.service.model.Role;

import java.util.List;

public interface RoleRepository {
    Role save(Role role);
    Role get(int id);
    boolean delete(int id);
    List<Role> getAll();
    List<Role> filter(Role role);
    List<Role> getRoleFromUserRoleByUser(int userId);
    List<Role> getRoleFromUserRoleByUserRoleType(int userId,int roleTypeId);
}

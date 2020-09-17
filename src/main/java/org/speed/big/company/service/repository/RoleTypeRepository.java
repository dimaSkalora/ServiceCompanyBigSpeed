package org.speed.big.company.service.repository;

import org.speed.big.company.service.model.RoleType;

import java.util.List;

public interface RoleTypeRepository {
    RoleType save(RoleType roleType);
    RoleType get(int id);
    boolean delete(int id);
    List<RoleType> getAll();
    List<RoleType> filterRoleType(RoleType roleType);
}

package org.speed.big.company.service.service;

import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface RoleTypeService {
    RoleType create(RoleType roleType);
    void update(RoleType roleType)throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    RoleType get(int id)throws NotFoundException;
    boolean delete(int id)throws NotFoundException;
    List<RoleType> getAll();
    List<RoleType> filterRoleType(RoleType roleType);
}

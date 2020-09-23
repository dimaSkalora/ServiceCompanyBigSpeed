package org.speed.big.company.service.service;

import org.speed.big.company.service.model.UserRole;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface UserRoleService {
    UserRole create(UserRole userRole);
    UserRole update(UserRole userRole) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    UserRole get(int id) throws NotFoundException;
    boolean delete(int id) throws NotFoundException;
    List<UserRole> getAll();
    List<UserRole> filter(UserRole userRole);
}

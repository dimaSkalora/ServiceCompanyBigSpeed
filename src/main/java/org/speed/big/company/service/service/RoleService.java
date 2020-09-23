package org.speed.big.company.service.service;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.util.List;

public interface RoleService {
    Role create(Role role);
    Role update(Role role) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    Role get(int id) throws NotFoundException;
    boolean delete(int id) throws NotFoundException;
    List<Role> getAll();
    List<Role> filter(Role role);
}

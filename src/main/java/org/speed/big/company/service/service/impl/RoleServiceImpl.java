package org.speed.big.company.service.service.impl;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.repository.RoleRepository;
import org.speed.big.company.service.service.RoleService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFound;
import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {
   /* @Autowired
    @Qualifier("jdbcRoleRepositoryImpl")*/
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(@Qualifier("dataRoleRepositoryImpl") RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        Assert.notNull(role,"не должно быть null");
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) throws NotFoundException {
        Assert.notNull(role,"не должно быть null");
        return checkNotFoundWithId(roleRepository.save(role),role.getId());
    }

    @Override
    public Role get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(roleRepository.get(id),id);
    }

    @Override
    public Role getFromAllUsers(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(roleRepository.getFromAllUsers(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(roleRepository.delete(id)),id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.getAll();
    }

    @Override
    public List<Role> filter(Role role) {
        Assert.notNull(role,"не должно быть null");
        return roleRepository.filter(role);
    }

    @Override
    public List<Role> getRoleFromUserRoleByUser(int userId) throws NotFoundException {
        //Проверка - не найден
        return checkNotFound(roleRepository.getRoleFromUserRoleByUser(userId),"userId = "+userId);
    }

    @Override
    public List<Role> getRoleFromUserRoleByUserRoleType(int userId, int roleTypeId) throws NotFoundException {
        //Проверка - не найден
        return checkNotFound(roleRepository.getRoleFromUserRoleByUserRoleType(userId,
                roleTypeId),"userId = "+userId+", roleTypeId = "+roleTypeId);
    }
}

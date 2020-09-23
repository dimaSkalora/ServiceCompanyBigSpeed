package org.speed.big.company.service.service.impl;

import org.speed.big.company.service.model.UserRole;
import org.speed.big.company.service.service.UserRoleService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("userRoleServiceImpl")
public class UserRoleServiceImpl implements UserRoleService {

    //@Autowired
    //@Qualifier("jdbcUserRoleRepositoryImpl")
    private UserRoleService userRoleService;

    @Autowired
    public UserRoleServiceImpl(@Qualifier("jdbcUserRoleRepositoryImpl") UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Override
    public UserRole create(UserRole userRole) {
        Assert.notNull(userRole,"не должно быть null");
        return userRoleService.create(userRole);
    }

    @Override
    public UserRole update(UserRole userRole) throws NotFoundException {
        Assert.notNull(userRole,"не должно быть null");
        return checkNotFoundWithId(userRoleService.update(userRole),userRole.getId());
    }

    @Override
    public UserRole get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(userRoleService.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(userRoleService.delete(id)),id);
    }

    @Override
    public List<UserRole> getAll() {
        return userRoleService.getAll();
    }

    @Override
    public List<UserRole> filter(UserRole userRole) {
        Assert.notNull(userRole,"не должно быть null");
        return userRoleService.filter(userRole);
    }
}

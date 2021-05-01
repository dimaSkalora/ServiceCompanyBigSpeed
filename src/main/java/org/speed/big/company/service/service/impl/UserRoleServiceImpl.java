package org.speed.big.company.service.service.impl;

import org.speed.big.company.service.model.UserRole;
import org.speed.big.company.service.repository.UserRoleRepository;
import org.speed.big.company.service.service.UserRoleService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFound;
import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("userRoleServiceImpl")
public class UserRoleServiceImpl implements UserRoleService {

    //@Autowired
    //@Qualifier("jdbcUserRoleRepositoryImpl")
    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(@Qualifier("dataUserRoleRepositoryImpl") UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole create(UserRole userRole) {
        Assert.notNull(userRole,"не должно быть null");
        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole update(UserRole userRole) throws NotFoundException {
        Assert.notNull(userRole,"не должно быть null");
        return checkNotFoundWithId(userRoleRepository.save(userRole),userRole.getId());
    }

    @Override
    public UserRole get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(userRoleRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(userRoleRepository.delete(id)),id);
    }

    @Override
    public List<UserRole> getAll() {
        return userRoleRepository.getAll();
    }

    @Override
    public List<UserRole> filter(UserRole userRole) {
        Assert.notNull(userRole,"не должно быть null");
        return userRoleRepository.filter(userRole);
    }

    @Override
    public List<UserRole> getByUser(int userId) throws NotFoundException {
        //Проверка - не найден
        return checkNotFound(userRoleRepository.getByUser(userId),"userId = "+userId);
    }

    @Override
    public List<UserRole> getByRole(int roleId) throws NotFoundException {
        //Проверка - не найден
        return checkNotFound(userRoleRepository.getByRole(roleId),"roleId = "+roleId);
    }
}

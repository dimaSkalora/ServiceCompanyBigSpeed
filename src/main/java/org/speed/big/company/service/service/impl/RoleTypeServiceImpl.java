package org.speed.big.company.service.service.impl;

import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.repository.RoleTypeRepository;
import org.speed.big.company.service.service.RoleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("roleTypeServiceImpl")
public class RoleTypeServiceImpl implements RoleTypeService {

    @Autowired
    @Qualifier("jdbcRoleTypeRepositoryImpl")
    private RoleTypeRepository roleTypeRepository;

   /* @Autowired
    public RoleTypeServiceImpl(@Qualifier("jdbcRoleTypeRepositoryImpl")RoleTypeRepository roleTypeRepository) {
        this.roleTypeRepository = roleTypeRepository;
    }*/

    @Override
    public RoleType create(RoleType roleType) {
        Assert.notNull(roleType,"не должно быть null");
        return roleTypeRepository.save(roleType);
    }

    @Override
    public void update(RoleType roleType) {
        Assert.notNull(roleType,"не должно быть null");
        roleTypeRepository.save(roleType);
    }

    @Override
    public RoleType get(int id) {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(roleTypeRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(Boolean.valueOf(roleTypeRepository.delete(id)),id);
    }

    @Override
    public List<RoleType> getAll() {
        return roleTypeRepository.getAll();
    }

    @Override
    public List<RoleType> filterRoleType(RoleType roleType) {
        Assert.notNull(roleType,"не должно быть null");
        return roleTypeRepository.filterRoleType(roleType);
    }
}

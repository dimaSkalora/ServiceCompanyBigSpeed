package org.speed.big.company.service.web.role_type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.service.RoleTypeService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNew;
import static org.speed.big.company.service.util.ValidationUtil.checkNotNew;

public abstract class AbstractRoleTypeController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleTypeService roleTypeService;

    public RoleType create(RoleType roleType){
        checkNew(roleType);
        log.info("create {}",roleType);
        return roleTypeService.create(roleType);
    }

    public RoleType update(RoleType roleType){
        checkNotNew(roleType);
        log.info("update {}",roleType);
        return roleTypeService.update(roleType);
    }

    public RoleType get(int id) {
        log.info("get {}",id);
        return roleTypeService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return roleTypeService.delete(id);
    }

    public List<RoleType> getAll(){
        log.info("getAll");
        return roleTypeService.getAll();
    }

    public List<RoleType> filterRoleType(RoleType roleType){
        log.info("filterRoleType {}",roleType);
        return roleTypeService.filterRoleType(roleType);
    }
}

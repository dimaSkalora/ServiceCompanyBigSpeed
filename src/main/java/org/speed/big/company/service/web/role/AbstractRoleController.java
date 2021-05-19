package org.speed.big.company.service.web.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.service.RoleService;
import org.speed.big.company.service.service.RoleTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.*;

public abstract class AbstractRoleController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleTypeService roleTypeService;

    public Role create(Role role){
        checkNew(role);
        log.info("create {}",role);

        return roleService.create(role);
    }

    public Role update(Role role, int id){
        assureIdConsistent(role, id);
        log.info("update {}",role);

        return roleService.update(role);
    }

    public Role get(int id){
        log.info("get {}",id);
        return roleService.get(id);
    }

    public Role getFromAllUsers(int id){
        log.info("getFromAllUsers {}",id);
        return roleService.getFromAllUsers(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return roleService.delete(id);
    }

    public List<Role> getAll(){
        log.info("getAll");
        return roleService.getAll();
    }

    public List<Role> filterRole(Role role){
        log.info("filterRole {}",role);
        return roleService.filter(role);
    }

    public List<RoleType> getAllRoleType(){
        log.info("getAllRoleType");
        return roleTypeService.getAll();
    }

    public RoleType getRoleType(int roleTypeId){
        log.info("getRoleType {}", roleTypeId);
        return roleTypeService.get(roleTypeId);
    }

}

package org.speed.big.company.service.web.user_role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.model.UserRole;
import org.speed.big.company.service.service.RoleService;
import org.speed.big.company.service.service.UserRoleService;
import org.speed.big.company.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.*;

public abstract class AbstractUserRoleController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public UserRole create(UserRole userRole){
        checkNew(userRole);
        log.info("create {}",userRole);
        userRoleService.create(userRole);

        return userRole;
    }

    public void update(UserRole userRole, int id){
        assureIdConsistent(userRole, id);
        log.info("update {}",userRole);

        userRoleService.update(userRole);
    }

    public UserRole get(int id){
        log.info("get {}",id);
        return userRoleService.get(id);
    }

    public void delete(int id){
        log.info("delete {}",id);
        userRoleService.delete(id);
    }

    public List<UserRole> getAll(){
        log.info("getAll");
        return userRoleService.getAll();
    }

    List<UserRole> filter(UserRole userRole){
        log.info("filter {}", userRole);
        return userRoleService.filter(userRole);
    }

    public User getUser(int userId){
        log.info("getUser {}",userId);
        return userService.get(userId);
    }

    public List<User> getAllUser(){
        log.info("getAllUser");
        return userService.getAll();
    }

    public Role getRole(int roleId) {
        log.info("getRole {}", roleId);
        return roleService.get(roleId);
    }

    public List<Role> getAllRole(){
        log.info("getAllRole");
        return roleService.getAll();
    }

}

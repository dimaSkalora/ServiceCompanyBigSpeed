package org.speed.big.company.service.web.user;

import org.speed.big.company.service.model.User;
import org.speed.big.company.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNew;
import static org.speed.big.company.service.util.ValidationUtil.checkNotNew;

public abstract class AbstractUserController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public User create(User user){
        checkNew(user);
        log.info("create {}",user);
        return userService.create(user);
    }

    public void update(User user) {
        checkNotNew(user);
        log.info("update {}",user);
        userService.update(user);
    }

    public User get(int id){
        log.info("get {}",id);
        return userService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return userService.delete(id);
    }

    public List<User> getAll(){
        log.info("getAll");
        return userService.getAll();
    }

    public List<User> getBetweenRegistered(LocalDate startDate, LocalDate endDate){
        log.info("getBetweenRegistered startDate {}, endDate {}",startDate,endDate);
        return userService.getBetweenRegistered(startDate,endDate);
    }

    public List<User> filterUser(User user){
        log.info("filterUser {}", user);
        return userService.filterUser(user);
    }

    public List<User> filterConditionUser(User user, String sqlCondition){
        log.info("filterUserCondition user {}, filterUserCondition {}", user,sqlCondition);
        return userService.filterUser(user,sqlCondition);
    }
}

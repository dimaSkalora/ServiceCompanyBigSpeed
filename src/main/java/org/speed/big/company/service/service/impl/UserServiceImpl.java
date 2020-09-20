package org.speed.big.company.service.service.impl;

import org.speed.big.company.service.model.User;
import org.speed.big.company.service.repository.UserRepository;
import org.speed.big.company.service.service.UserService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("userServiceImpl")
public class UserServiceImpl  implements UserService {

    //@Autowired
    //@Qualifier("jdbcUserRepositoryImpl")
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("jdbcUserRepositoryImpl") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user,"не должно быть null");
        return userRepository.save(user);
    }

    @Override
    public void update(User user) throws NotFoundException {
        Assert.notNull(user,"не должно быть null");
        userRepository.save(user);
    }

    @Override
    public User get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(userRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(Boolean.valueOf(userRepository.delete(id)),id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public List<User> getBetweenRegistered(LocalDate startDate, LocalDate endDate) {
        Assert.notNull(startDate,"не должно быть null");
        Assert.notNull(endDate,"не должно быть null");

        return userRepository.getBetweenRegistered(startDate,endDate);
    }

    @Override
    public List<User> filterUser(User user) {
        Assert.notNull(user,"не должно быть null");
        return userRepository.filterUser(user);
    }

    @Override
    public List<User> filterUser(User user, String sqlCondition) {
        return userRepository.filterUser(user,sqlCondition);
    }
}

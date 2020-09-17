package org.speed.big.company.service.repository;

import org.speed.big.company.service.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository {
    User save(User user);
    User get(int id);
    boolean delete(int id);
    List<User> getAll();
    List<User> getBetweenRegistered(LocalDate startDate, LocalDate endDate);
    List<User> filterUser(User user);
    List<User> filterUser(User user, String sqlCondition);
}

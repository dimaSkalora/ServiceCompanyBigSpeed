package org.speed.big.company.service.service;

import org.speed.big.company.service.model.User;
import org.speed.big.company.service.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User create(User user);
    User update(User user) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    User get(int id) throws NotFoundException;
    boolean delete(int id) throws NotFoundException;
    List<User> getAll();
    List<User> getBetweenRegistered(LocalDate startDate, LocalDate endDate);
    List<User> filterUser(User user);
    List<User> filterUser(User user, String sqlCondition);
}

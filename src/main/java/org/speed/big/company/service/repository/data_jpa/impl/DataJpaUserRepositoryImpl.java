package org.speed.big.company.service.repository.data_jpa.impl;

import org.speed.big.company.service.model.User;
import org.speed.big.company.service.repository.UserRepository;
import org.speed.big.company.service.repository.data_jpa.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {

    @Autowired
    private CrudUserRepository crudUserRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    @Override
    public User getFromAllRoles(int id) {
        return crudUserRepository.getFromAllRoles(id);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.findByEmail(email);
    }

    @Override
    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll(Sort.by(Sort.Direction.ASC,"name", "email"));
    }

    @Override
    public List<User> getBetweenRegistered(LocalDate startDate, LocalDate endDate) {
        return crudUserRepository.getBetweenRegistered(startDate,endDate);
    }

    @Override
    public List<User> filterUser(User user) {
        return filterUser(user,null);
    }

    @Override
    public List<User> filterUser(User user, String sqlCondition) {
        String sqlFilterUser = "SELECT u FROM User u";
        int parameterCount = 0;

        if (user.getId() != null) {
            sqlFilterUser += " where u.id=:id";
            parameterCount++;
        }
        if (user.getName() != null){
            if (parameterCount > 0)
                sqlFilterUser += " and u.name=:name";
            else
                sqlFilterUser += " where u.name=:name";
            parameterCount++;
        }
        if (user.getEmail() != null){
            if (parameterCount > 0)
                sqlFilterUser += " and u.email=:email";
            else
                sqlFilterUser += " where u.email=:email";
            parameterCount++;
        }
        if (user.getPassword() != null){
            if (parameterCount > 0)
                sqlFilterUser += " and u.password=:password";
            else
                sqlFilterUser += " where u.password=:password";
            parameterCount++;
        }
        if (user.getPhone() != null){
            if (parameterCount > 0)
                sqlFilterUser += " and u.phone=:phone";
            else
                sqlFilterUser += " where u.phone=:phone";
            parameterCount++;
        }
        if (user.getRegistered() != null){
            if (parameterCount > 0)
                sqlFilterUser += " and u.registered=:registered";
            else
                sqlFilterUser += " where u.registered=:registered";
            parameterCount++;
        }
        if (parameterCount > 0)
            sqlFilterUser += " and u.enabled=:enabled";
        else
            sqlFilterUser += " where u.enabled=:enabled";

        if((sqlCondition != null) && (!sqlCondition.equals(""))){
            if (parameterCount > 0)
                sqlFilterUser += " and ( "+sqlCondition+" ) ";
            else
                sqlFilterUser = sqlFilterUser + " where ("+sqlCondition+" ) ";
        }

        Query query = entityManager.createQuery(sqlFilterUser);
        List<User> list;

        if (user.getId() != null)
            query.setParameter("id", user.getId());
        if (user.getName() != null)
            query.setParameter("name",user.getName());
        if (user.getEmail() != null)
            query.setParameter("email",user.getEmail());
        if (user.getPassword() != null)
            query.setParameter("password",user.getPassword());
        if (user.getPhone() != null)
            query.setParameter("phone",user.getPhone());
        if (user.getRegistered() != null)
            query.setParameter("registered",user.getRegistered());
        query.setParameter("enabled",user.isEnabled());

        list = query.getResultList();

        return list;
    }
}

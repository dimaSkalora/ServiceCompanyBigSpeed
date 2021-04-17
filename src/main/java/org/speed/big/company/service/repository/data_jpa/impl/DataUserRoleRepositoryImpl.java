package org.speed.big.company.service.repository.data_jpa.impl;

import org.speed.big.company.service.model.UserRole;
import org.speed.big.company.service.repository.UserRoleRepository;
import org.speed.big.company.service.repository.data_jpa.CrudUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DataUserRoleRepositoryImpl implements UserRoleRepository {

    @Autowired
    private CrudUserRoleRepository crudUserRoleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserRole save(UserRole userRole) {
        return crudUserRoleRepository.save(userRole);
    }

    @Override
    public UserRole get(int id) {
        return crudUserRoleRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudUserRoleRepository.delete(id) != 0;
    }

    @Override
    public List<UserRole> getAll() {
        return crudUserRoleRepository.findAll(Sort.by("dateTime"));
    }

    @Override
    public List<UserRole> filter(UserRole userRole) {
        String queryFilter = "select ur from UserRole ur join fetch ur.userId join fetch ur.roleId ";
        int paramCount = 0;

        if (userRole.getId() != null) {
            queryFilter = queryFilter + " where ur.id=:id ";
            paramCount++;
        }
        if (userRole.getUserId() != null){
            if (paramCount == 0)
                queryFilter += " where ur.userId=:userId ";
            else
                queryFilter += " and ur.userId=:userId ";
            paramCount++;
        }
        if (userRole.getRoleId() != null){
            if (paramCount == 0)
                queryFilter += " where ur.roleId=:roleId ";
            else
                queryFilter += " and ur.roleId=:roleId ";
            paramCount++;
        }
        if (userRole.getDateTime() != null){
            if (paramCount == 0)
                queryFilter += " where ur.dateTime=:dateTime ";
            else
                queryFilter += " and ur.dateTime=:dateTime ";
            paramCount++;
        }
        if (userRole.getComment() != null){
            if (paramCount == 0)
                queryFilter += " where ur.comment=:comment ";
            else
                queryFilter += " and ur.comment=:comment ";
            paramCount++;
        }

        queryFilter += " order by ur.dateTime "; //default ASC -  по возрастанию

        Query query = entityManager.createQuery(queryFilter);
        List<UserRole> list;

        if (userRole.getId() != null)
            query.setParameter("id", userRole.getId());
        if(userRole.getUserId() != null)
            query.setParameter("userId", userRole.getUserId());
        if (userRole.getRoleId() != null)
            query.setParameter("roleId", userRole.getRoleId());
        if(userRole.getDateTime() != null)
            query.setParameter("dateTime", userRole.getDateTime());
        if (userRole.getComment() != null)
            query.setParameter("comment", userRole.getComment());

        list = query.getResultList();

        return list;
    }

    @Override
    public List<UserRole> getByUser(int userId) {
        return crudUserRoleRepository.findUserRoleByUserId(userId);
    }

    @Override
    public List<UserRole> getByRole(int roleId) {
        return crudUserRoleRepository.findUserRoleByRoleId(roleId);
    }
}

package org.speed.big.company.service.repository.data_jpa.impl;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.repository.RoleRepository;
import org.speed.big.company.service.repository.data_jpa.CrudRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DataRoleRepositoryImpl implements RoleRepository {

    @Autowired
    private CrudRoleRepository crudRoleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role save(Role role) {
        return crudRoleRepository.save(role);
    }

    @Override
    public Role get(int id) {
        return crudRoleRepository.findById(id).orElse(null);
    }

    @Override
    public Role getFromAllUsers(int id) {
        return crudRoleRepository.findFromAllUsers(id);
    }

    @Override
    public boolean delete(int id) {
        return crudRoleRepository.delete(id) != 0;
    }

    @Override
    public List<Role> getAll() {
        return crudRoleRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<Role> filter(Role role) {
        String queryFilter = "select r from Role r join fetch r.roleTypeId ";
        int paramCount = 0;

        if (role.getId() != null){
            queryFilter = queryFilter + " where r.id=:id ";
            paramCount++;
        }
        if (role.getName() != null){
            if (paramCount == 0)
                queryFilter += " where r.name=:name ";
            else
                queryFilter += " and r.name=:name ";
            paramCount++;
        }
        if (role.getDescription() != null) {
            if (paramCount == 0)
                queryFilter += " where r.description=:description ";
            else
                queryFilter += " and r.description=:description ";
            paramCount++;
        }
        if (role.getRoleTypeId() != null)
            if (paramCount == 0)
                queryFilter += " where r.roleTypeId=:roleTypeId ";
            else
                queryFilter += " and r.roleTypeId=:roleTypeId ";

        queryFilter += " order by r.name "; //default ASC -  по возрастанию

        Query query = entityManager.createQuery(queryFilter);
        List<Role> list;

        if (role.getId() != null)
            query.setParameter("id", role.getId());
        if (role.getName() != null)
            query.setParameter("name", role.getName());
        if (role.getDescription() != null)
            query.setParameter("description", role.getDescription());
        if (role.getRoleTypeId() != null)
            query.setParameter("roleTypeId", role.getRoleTypeId());

        list = query.getResultList();

        return list;
    }

    @Override
    public List<Role> getRoleFromUserRoleByUser(int userId) {
        return getRoleFromUserRoleByUserRoleType(userId, Integer.MIN_VALUE);
    }

    @Override
    public List<Role> getRoleFromUserRoleByUserRoleType(int userId, int roleTypeId) {
        String queryGetRoleFromUserRoleByUserRoleType = "select r from Role r " +
                " join fetch r.roleTypeId " +
                " left join UserRole ur on r.id = ur.roleId.id " +
                " where ur.userId.id=:userId ";
        if(roleTypeId != Integer.MIN_VALUE)
            queryGetRoleFromUserRoleByUserRoleType += " and r.roleTypeId.id=:roleTypeId";

        Query query =  entityManager.createQuery(queryGetRoleFromUserRoleByUserRoleType,Role.class)
                .setParameter("userId",userId);

        if(roleTypeId != Integer.MIN_VALUE)
            query.setParameter("roleTypeId",roleTypeId);

        List<Role> list = query.getResultList();

        return list;
    }
}

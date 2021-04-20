package org.speed.big.company.service.repository.data_jpa.impl;

import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.repository.RoleTypeRepository;
import org.speed.big.company.service.repository.data_jpa.CrudRoleTypeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DataRoleTypeRepositoryImpl implements RoleTypeRepository {

    private final CrudRoleTypeRepository crudRoleTypeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DataRoleTypeRepositoryImpl(CrudRoleTypeRepository crudRoleTypeRepository) {
        this.crudRoleTypeRepository = crudRoleTypeRepository;
    }

    @Override
    public RoleType save(RoleType roleType) {
        return crudRoleTypeRepository.save(roleType);
    }

    @Override
    public RoleType get(int id) {
        return crudRoleTypeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudRoleTypeRepository.delete(id) != 0;
    }

    @Override
    public List<RoleType> getAll() {
        return crudRoleTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<RoleType> filterRoleType(RoleType roleType) {
        String sqlFilter = "select rt from RoleType rt";
        int parameterCount = 0;

        if (roleType.getId() != null){
            sqlFilter += " where id=:id";
            parameterCount++;
        }
        if (roleType.getName() != null){
            if (parameterCount == 0)
                sqlFilter += " where name=:name";
            else
                sqlFilter += " and name=:name";
        }

        Query query = entityManager.createQuery(sqlFilter);
        List<RoleType> list;

        if (roleType.getId() != null)
            query.setParameter("id",roleType.getId());
        if (roleType.getName() != null)
            query.setParameter("name",roleType.getName());

        list = query.getResultList();

        return list;
    }
}

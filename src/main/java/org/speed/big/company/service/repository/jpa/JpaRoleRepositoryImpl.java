package org.speed.big.company.service.repository.jpa;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.repository.RoleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaRoleRepositoryImpl")
@Transactional(readOnly = true)
public class JpaRoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Role save(Role role) {
        if (role.isNew())
            entityManager.persist(role);
        else
            entityManager.merge(role);
        return  role;
    }

    @Override
    public Role get(int id) {
     /*   Role role = entityManager.createNamedQuery(Role.GET, Role.class)
                .setParameter("id",id)
                .getSingleResult();*/
        Role role = entityManager.find(Role.class, id);
        return role;
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        // getReference - выбросить  EntityNotFoundException если не найдено (загружаеть часть полей)
        // find - будет null  если не найдено (загружает все поля)

       /* Role role = entityManager.getReference(Role.class, id);
        entityManager.remove(role);*/

      /*  boolean queryDelete = entityManager.createQuery("delete from Role where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/
  /*      boolean queryDelete =  entityManager.createNativeQuery("select from role where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;      */

        return  entityManager.createNamedQuery(Role.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<Role> getAll() {
        return entityManager.createNamedQuery(Role.ALL_SORTED).getResultList();
    }

    @Override
    public List<Role> filter(Role role) {
        String queryFilter = "select r from Role r ";
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
                queryFilter += " where r.role_type_id=:roleTypeId ";
            else
                queryFilter += " and r.role_type_id=:roleTypeId ";

        queryFilter += "order by r.name "; //default ASC -  по возрастанию

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
}
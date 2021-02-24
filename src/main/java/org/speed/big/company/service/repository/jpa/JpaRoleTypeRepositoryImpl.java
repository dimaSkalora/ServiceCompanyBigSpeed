package org.speed.big.company.service.repository.jpa;

import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.repository.RoleTypeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaRoleTypeRepository")
@Transactional(readOnly = true)
public class JpaRoleTypeRepositoryImpl implements RoleTypeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public RoleType save(RoleType roleType) {
        if (roleType.isNew()){
             entityManager.persist(roleType);
             return roleType;
        }
        else
            return entityManager.merge(roleType);
    }

    @Override
    public RoleType get(int id) {
        return entityManager.find(RoleType.class,id);
    }

    @Override
    public boolean delete(int id) {
/*      RoleType ref = em.getReference(RoleType.class, id);
        em.remove(ref);

        Query query = em.createQuery("DELETE FROM RoleType rt WHERE rt.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
*/

        return entityManager.createNamedQuery(RoleType.DELETE)
                .setParameter("id",id)
                .executeUpdate() !=0 ;
    }

    @Override
    public List<RoleType> getAll() {
        return entityManager.createNamedQuery(RoleType.ALL_SORTED, RoleType.class)
                .getResultList();
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

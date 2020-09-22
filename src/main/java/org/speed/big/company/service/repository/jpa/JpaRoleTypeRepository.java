package org.speed.big.company.service.repository.jpa;

import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.repository.RoleTypeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("jpaRoleTypeRepository")
@Transactional(readOnly = true)
public class JpaRoleTypeRepository implements RoleTypeRepository {

    @PersistenceContext
    EntityManager entityManager;

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
        String sqlFilter = "";




        return null;
    }
}

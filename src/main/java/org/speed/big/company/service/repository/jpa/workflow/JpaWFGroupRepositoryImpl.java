package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFGroup;
import org.speed.big.company.service.repository.workflow.WFGroupRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaWFGroupRepositoryImpl")
public class JpaWFGroupRepositoryImpl implements WFGroupRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WFGroup save(WFGroup wfGroup) {
        if (wfGroup.isNew())
            entityManager.persist(wfGroup);
        else
            entityManager.merge(wfGroup);
        return wfGroup;
    }

    @Override
    public WFGroup get(int id) {
      /*  WFGroup wfGroup = entityManager.createNamedQuery(WFGroup.GET, WFGroup.class)
                .setParameter("id",id)
                .getSingleResult();*/

        return entityManager.find(WFGroup.class,id);
    }

    @Override
    public boolean delete(int id) {
        /*WFGroup wfGroup = entityManager.getReference(WFGroup.class, id);
        entityManager.remove(wfGroup);*/
/*        boolean isDelete = entityManager.createNativeQuery("delete from wf_group where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/
   /*     boolean isDelete = entityManager.createQuery("delete from WFGroup where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/

        return entityManager.createNamedQuery(WFGroup.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<WFGroup> getAll() {
        return entityManager.createNamedQuery(WFGroup.ALL_SORTED, WFGroup.class).getResultList();
    }

    @Override
    public List<WFGroup> filter(WFGroup wfGroup) {
        StringBuilder queryFilter = new StringBuilder();
        queryFilter.append("select wfg from WFGroup wfg");
        int paramCount = 0;
        List<WFGroup> list = null;

        if (wfGroup.getId() != null) {
            queryFilter.append(" where wfg.id=:id\n");
            paramCount++;
        }
        if (wfGroup.getName() != null){
            if (paramCount == 0)
                queryFilter.append(" where wfg.name=:name\n");
            else
                queryFilter.append(" and wfg.name=:name\n");
            paramCount++;
        }
        if (wfGroup.getDescription() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfg.description=:description\n");
            else
                queryFilter.append(" and wfg.description=:description\n");
        }

        queryFilter.append("order by wfg.name");

        Query query = entityManager.createQuery(String.valueOf(queryFilter));

        if (wfGroup.getId() != null)
            query.setParameter("id",wfGroup.getId());
        if (wfGroup.getName() != null)
            query.setParameter("name",wfGroup.getName());
        if (wfGroup.getDescription() != null)
            query.setParameter("description",wfGroup.getDescription());

        list = query.getResultList();

        return list;
    }
}

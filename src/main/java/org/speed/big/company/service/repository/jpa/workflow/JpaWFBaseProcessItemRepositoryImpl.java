package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.speed.big.company.service.repository.workflow.WFBaseProcessItemRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaWFBaseProcessItemRepositoryImpl")
public class JpaWFBaseProcessItemRepositoryImpl implements WFBaseProcessItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WFBaseProcessItem save(WFBaseProcessItem wfBaseProcessItem) {
        if (wfBaseProcessItem.isNew())
            entityManager.persist(wfBaseProcessItem);
        else
            entityManager.merge(wfBaseProcessItem);

        return wfBaseProcessItem;
    }

    @Override
    public WFBaseProcessItem get(int id) {
        /*WFBaseProcessItem wfBaseProcessItem = (WFBaseProcessItem) entityManager
                .createNamedQuery(WFBaseProcessItem.GET)
                .setParameter("id",id)
                .getSingleResult();*/

        return entityManager.find(WFBaseProcessItem.class,id);
    }

    @Override
    public boolean delete(int id) {
      /*  WFBaseProcessItem wfBaseProcessItem = entityManager.getReference(WFBaseProcessItem.class, id);
        entityManager.remove(wfBaseProcessItem);*/

      /*  boolean isDelete = entityManager.createQuery("delete from WFBaseProcessItem where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0; */

     /*   boolean isDelete = entityManager.createNativeQuery("delete from wf_base_process_items where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/

        return entityManager.createNamedQuery(WFBaseProcessItem.DELETE, WFBaseProcessItem.class)
                .setParameter("id",id).executeUpdate() != 0;
    }

    @Override
    public List<WFBaseProcessItem> getAll() {
        return entityManager.createNamedQuery(WFBaseProcessItem.ALL_SORTED, WFBaseProcessItem.class).getResultList();
    }

    @Override
    public List<WFBaseProcessItem> filter(WFBaseProcessItem wfBaseProcessItem) {
        String queryFilter = "select wfbpi from WFBaseProcessItem wfbpi \n";
        int paramCount = 0;
        List<WFBaseProcessItem> list = null;
        if (wfBaseProcessItem.getId() != null){
            queryFilter = queryFilter +" where wfbpi.id=id\n";
            paramCount++;
        }
        if (wfBaseProcessItem.getStateFromId() != null){
            if (paramCount == 0)
                queryFilter += " where wfbpi.stateFromId=stateFromId\n";
            else
                queryFilter += " and wfbpi.stateFromId=stateFromId\n";
            paramCount++;
        }
        if (wfBaseProcessItem.getStateToId() != null){
            if (paramCount == 0)
                queryFilter += " where wfbpi.stateToId=stateToId\n";
            else
                queryFilter += " and wfbpi.stateToId=stateToId\n";
            paramCount++;
        }
        if (wfBaseProcessItem.getBaseProcessId() != null){
            if (paramCount == 0)
                queryFilter += " where wfbpi.baseProcessId=baseProcessId\n";
            else
                queryFilter += " and wfbpi.baseProcessId=baseProcessId\n";
            paramCount++;
        }

        queryFilter += " order wfbpi.id";

        Query query = entityManager.createNamedQuery(queryFilter,WFBaseProcessItem.class);
        if (wfBaseProcessItem.getId() != null)
            query.setParameter("id" ,wfBaseProcessItem.getId());
        if (wfBaseProcessItem.getStateFromId() != null)
            query.setParameter("stateFromId",wfBaseProcessItem.getStateFromId().getId());
        if (wfBaseProcessItem.getStateToId() != null)
            query.setParameter("stateToId",wfBaseProcessItem.getStateToId().getId());
        if (wfBaseProcessItem.getBaseProcessId() != null)
            query.setParameter("baseProcessId",wfBaseProcessItem.getBaseProcessId().getId());

        list = query.getResultList();

        return list;
    }
}

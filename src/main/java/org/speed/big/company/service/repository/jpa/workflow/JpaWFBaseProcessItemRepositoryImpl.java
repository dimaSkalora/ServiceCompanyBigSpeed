package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.repository.workflow.WFBaseProcessItemRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository("jpaWFBaseProcessItemRepositoryImpl")
@Transactional(readOnly = true)
public class JpaWFBaseProcessItemRepositoryImpl implements WFBaseProcessItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
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

    @Transactional
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

    @Override
    public List<WFProcessState> getListTransferWFProcessState(int processStateFromId, int baseProcessId) {
        String queryGetListTransferWFProcessState = "select wfbpi from WFBaseProcessItem wfbpi \n" +
                " where wfbpi.stateFromId=:processStateFromId\n" +
                " and wfbpi.baseProcessId=:baseProcessId";

        List<WFBaseProcessItem> list = entityManager.createQuery(queryGetListTransferWFProcessState, WFBaseProcessItem.class)
                .setParameter("processStateFromId",processStateFromId)
                .setParameter("baseProcessId",baseProcessId)
                .getResultList();

        List<WFProcessState> listTransferWFProcessStates = list.stream()
                .map(wbpi -> wbpi.getStateFromId())
                .collect(Collectors.toList());

        return listTransferWFProcessStates;
    }
}

package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.repository.workflow.WFProcessStateRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaWFProcessStateRepositoryImpl")
@Transactional(readOnly = true)
public class JpaWFProcessStateRepositoryImpl implements WFProcessStateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public WFProcessState save(WFProcessState wfProcessState) {
        if (wfProcessState.isNew())
            entityManager.persist(wfProcessState);
        else
            entityManager.merge(wfProcessState);
        return wfProcessState;
    }

    @Override
    public WFProcessState get(int id) {
        /*return entityManager.find(WFProcessState.class,id);*/

        return (WFProcessState) entityManager.createNamedQuery(WFProcessState.GET)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        /*WFProcessState wfProcessState = entityManager.getReference(WFProcessState.class,id);
        entityManager.remove(wfProcessState);*/

      /*  boolean isDelete = entityManager.createQuery("delete from WFProcessState where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/
      /*  boolean isDelete = entityManager
                .createNativeQuery("delete from wf_process_state where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/

        return entityManager.createNamedQuery(WFProcessState.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<WFProcessState> getAll() {
        return entityManager.createNamedQuery(WFProcessState.ALL_SORTED,
                WFProcessState.class).getResultList();
    }

    @Override
    public List<WFProcessState> filter(WFProcessState wfProcessState) {
        String queryFilter = "select wfpstate from WFProcessState wfpstate\n";
        int paramCount = 0;
        List<WFProcessState> list = null;

        if (wfProcessState.getId() != null) {
            queryFilter = queryFilter + " where wfpstate.id=:id\n";
            paramCount++;
        }
        if (wfProcessState.getName() != null){
            if (paramCount == 0)
                queryFilter += " where wfpstate.name=:name\n";
            else
                queryFilter += " and wfpstate.name=:name\n";
            paramCount++;
        }
        if (wfProcessState.getRoleId() != null){
            if (paramCount == 0)
                queryFilter += " where wfpstate.roleId=:roleId\n";
            else
                queryFilter += " and wfpstate.roleId=:roleId\n";
            paramCount++;
        }
        if (wfProcessState.getWfGroupId() != null){
            if (paramCount == 0)
                queryFilter += " where wfpstate.wfGroupId=:wfGroupId\n";
            else
                queryFilter += " and wfpstate.wfGroupId=:wfGroupId\n";
            paramCount++;
        }
        if (wfProcessState.getDescription() != null){
            if (paramCount == 0)
                queryFilter += " where wfpstate.description=:description\n";
            else
                queryFilter += " and wfpstate.description=:description\n";
        }

        queryFilter += " order by wfpstate.name";

        Query query = entityManager.createQuery(queryFilter, WFProcessState.class);

        if (wfProcessState.getId() != null)
            query.setParameter("id",wfProcessState.getId());
        if (wfProcessState.getName() != null)
            query.setParameter("name",wfProcessState.getName());
        if (wfProcessState.getRoleId() != null)
            query.setParameter("roleId",wfProcessState.getRoleId());
        if (wfProcessState.getWfGroupId() != null)
            query.setParameter("wfGroupId",wfProcessState.getWfGroupId());
        if (wfProcessState.getDescription() != null)
            query.setParameter("description",wfProcessState.getDescription());

        list = query.getResultList();

        return list;
    }
}

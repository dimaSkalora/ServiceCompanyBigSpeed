package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.speed.big.company.service.repository.workflow.WFProcessStatusRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaWFProcessStatusRepositoryImpl")
public class JpaWFProcessStatusRepositoryImpl implements WFProcessStatusRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WFProcessStatus save(WFProcessStatus wfProcessStatus) {
        if (wfProcessStatus.isNew())
            entityManager.persist(wfProcessStatus);
        else
            entityManager.merge(wfProcessStatus);
        return wfProcessStatus;
    }

    @Override
    public WFProcessStatus get(int id) {
    /*    WFProcessStatus wfProcessStatus = (WFProcessStatus) entityManager.createNamedQuery(WFProcessStatus.GET)
                    .setParameter("id",id)
                    .getSingleResult();*/

        return entityManager.find(WFProcessStatus.class,id);
    }

    @Override
    public boolean delete(int id) {
 /*       WFProcessStatus wfProcessStatus = entityManager.getReference(WFProcessStatus.class,id);
        entityManager.remove(wfProcessStatus);  */

        /*boolean isDelete =  entityManager
                .createQuery("delete from WFBaseProcess where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/

       /* boolean isDelete = entityManager
                .createNativeQuery("delete from wf_process_status where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0; */

        return entityManager.createNamedQuery(WFProcessStatus.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<WFProcessStatus> getAll() {
        return entityManager.createNamedQuery(WFProcessStatus.ALL_SORTED
                , WFProcessStatus.class).getResultList();
    }

    @Override
    public List<WFProcessStatus> filter(WFProcessStatus wfProcessStatus) {
        String queryFilter = "select wfps from WFBaseProcess wfps ";
        int paramCount=0;
        List<WFProcessStatus> list;

        if (wfProcessStatus.getId() != null) {
            queryFilter = queryFilter + " where wfps.id=:id\n";
            paramCount++;
        }
        if (wfProcessStatus.getName() != null)
            if (paramCount == 0)
                queryFilter += " where wfps.name=:name";
            else
                queryFilter += " and wfps.name=:name";

        queryFilter += " order by wfps.name";

        Query query = entityManager.createQuery(queryFilter);

        if (wfProcessStatus.getId() != null)
            query.setParameter("id", wfProcessStatus.getId());
        if (wfProcessStatus.getName() != null)
            query.setParameter("name",wfProcessStatus.getName());

        list = query.getResultList();

        return list;
    }
}

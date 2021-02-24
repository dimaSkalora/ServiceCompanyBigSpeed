package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.repository.workflow.WFBaseProcessRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaWFBaseProcessRepositoryImpl")
@Transactional(readOnly = true)
public class JpaWFBaseProcessRepositoryImpl implements WFBaseProcessRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public WFBaseProcess save(WFBaseProcess wfBaseProcess) {
        if (wfBaseProcess.isNew())
            entityManager.persist(wfBaseProcess);
        else
            entityManager.merge(wfBaseProcess);
        return wfBaseProcess;
    }

    @Override
    public WFBaseProcess get(int id) {
     /*   WFBaseProcess wfBaseProcess = (WFBaseProcess) entityManager
                .createNamedQuery(WFBaseProcess.GET)
                .setParameter("id",id)
                .getSingleResult();*/

        return entityManager.find(WFBaseProcess.class, id);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
  /*      WFBaseProcess wfBaseProcess = entityManager.getReference(WFBaseProcess.class, id);
        entityManager.remove(wfBaseProcess);*/

   /*     boolean isDelete = entityManager.createQuery("delete from WFBaseProcess where id=:id", WFBaseProcess.class)
                .setParameter("id",id)
                .executeUpdate() != 0;*/
      /*  boolean isDelete = entityManager.createNativeQuery("delete from wf_base_process where id=:id", WFBaseProcess.class)
                .setParameter("id",id)
                .executeUpdate() != 0;*/

        return entityManager.createNamedQuery(WFBaseProcess.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<WFBaseProcess> getAll() {
        return entityManager.createNamedQuery(WFBaseProcess.ALL_SORTED).getResultList();
    }

    @Override
    public List<WFBaseProcess> filter(WFBaseProcess wfBaseProcess) {
        String queryFilter = "select wfbp from WFBaseProcess wfbp\n";
        int paramCount = 0;
        List<WFBaseProcess> list;

        if (wfBaseProcess.getId() != null){
            queryFilter += " where wfbp.id=:id\n";
            paramCount++;
        }
        if (wfBaseProcess.getName() != null){
            if (paramCount == 0)
                queryFilter += " where wfbp.name=:name\n";
            else
                queryFilter += " and wfbp.name=:name\n";
            paramCount++;
        }
        if(wfBaseProcess.getDescription() != null){
            if (paramCount == 0)
                queryFilter += " where wfbp.description=:description\n";
            else
                queryFilter += " and wfbp.description=:description\n";
            paramCount++;
        }
        if(wfBaseProcess.getWfServiceId() != null){
            if (paramCount == 0)
                queryFilter += " where wfbp.wf_service_id=:wfServiceId\n";
            else
                queryFilter += " and wfbp.wf_service_id=:wfServiceId\n";
            paramCount++;
        }
        if(wfBaseProcess.getWfBaseProcessTypeId() != null){
            if (paramCount == 0)
                queryFilter += " where wfbp.wf_base_process_type_id=:wfBaseProcessTypeId\n";
            else
                queryFilter += " and wfbp.wf_base_process_type_id=:wfBaseProcessTypeId\n";
            paramCount++;
        }

        queryFilter += " order by wfbp.name";

        Query query = entityManager.createQuery(queryFilter, WFBaseProcess.class);

        if (wfBaseProcess.getId() != null)
            query.setParameter("id",wfBaseProcess.getId());
        if (wfBaseProcess.getName() != null)
            query.setParameter("name",wfBaseProcess.getName());
        if(wfBaseProcess.getDescription() != null)
            query.setParameter("description",wfBaseProcess.getDescription());
        if(wfBaseProcess.getWfServiceId() != null)
            query.setParameter("wfServiceId",wfBaseProcess.getWfServiceId());
        if(wfBaseProcess.getWfBaseProcessTypeId() != null)
            query.setParameter("wfBaseProcessTypeId",wfBaseProcess.getWfBaseProcessTypeId());

        list = query.getResultList();

        return list;
    }
}

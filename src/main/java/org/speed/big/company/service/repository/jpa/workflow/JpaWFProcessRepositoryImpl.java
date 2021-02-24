package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFProcess;
import org.speed.big.company.service.repository.workflow.WFProcessRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaWFProcessRepositoryImpl")
@Transactional(readOnly = true)
public class JpaWFProcessRepositoryImpl implements WFProcessRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String sqlQuery = "select wfp from WFProcess wfp\n";

    @Transactional
    @Override
    public WFProcess save(WFProcess wfProcess) {
        if (wfProcess.isNew())
            entityManager.persist(wfProcess);
        else
            entityManager.merge(wfProcess);
        return wfProcess;
    }

    @Override
    public WFProcess get(int id) {
      /*  WFProcess wfProcess = entityManager
                .createNamedQuery(WFProcess.GET, WFProcess.class)
                .setParameter("id",id)
                .getSingleResult();*/

        return entityManager.find(WFProcess.class,id);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
      /*  WFProcess wfProcess = entityManager.getReference(WFProcess.class, id);
        entityManager.remove(wfProcess);*/

      /*  boolean isDelete = entityManager
                .createQuery("select wfp from WFProcess wfp where wfp.id=:id"
                        ,WFProcess.class)
                .setParameter("id",id).executeUpdate() != 0;*/
  /*      boolean isDelete = entityManager
                .createNativeQuery("select * from wf_process where id=:=id")
                .setParameter("id",id).executeUpdate() != 0;*/

        return entityManager.createNamedQuery(WFProcess.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<WFProcess> getAll() {
        return entityManager.createNamedQuery(WFProcess.ALL_SORTED, WFProcess.class).getResultList();
    }

    @Override
    public List<WFProcess> filter(WFProcess wfProcess) {
        return filter(wfProcess,null);
    }

    @Override
    public List<WFProcess> filter(WFProcess wfProcess, String sqlCondition) {
        StringBuilder queryFilter = new StringBuilder();
        queryFilter.append("select wfp from WFProcess wfp \n");
        List<WFProcess> list;
        int paramCount = 0;

        if (wfProcess.getId() != null) {
            queryFilter.append("where wfp.id=:id\n");
            paramCount++;
        }
        if (wfProcess.getStartDate() != null){
            if (paramCount == 0)
                queryFilter.append("where wfp.start_date=:startDate\n");
            else
                queryFilter.append("and wfp.start_date=:startDate\n");
            paramCount++;
        }
        if (wfProcess.getFinalDate() != null){
            if (paramCount == 0)
                queryFilter.append("where wfp.final_date=:finalDate\n");
            else
                queryFilter.append("and wfp.final_date=:finalDate\n");
            paramCount++;
        }
        if (wfProcess.getDescription() != null){
            if (paramCount == 0)
                queryFilter.append("where wfp.description=:description\n");
            else
                queryFilter.append("and wfp.description=:description\n");
            paramCount++;
        }
        if (wfProcess.getDateEdit() != null){
            if (paramCount == 0)
                queryFilter.append("where wfp.date_edit=:dateEdit\n");
            else
                queryFilter.append("and wfp.date_edit=:dateEdit\n");
            paramCount++;
        }
        if (wfProcess.getUserEdit() != null){
            if (paramCount == 0)
                queryFilter.append("where wfp.user_edit=:userEdit\n");
            else
                queryFilter.append("and wfp.user_edit=:userEdit\n");
            paramCount++;
        }
        if (wfProcess.getWfPackageId() != null){
            if (paramCount == 0)
                queryFilter.append("where wfp.wf_package_id=:wfPackageId\n");
            else
                queryFilter.append("and wfp.wf_package_id=:wfPackageId\n");
            paramCount++;
        }
        if (wfProcess.getWfBaseProcessId() != null){
            if (paramCount == 0)
                queryFilter.append("where wfp.wf_base_process_id=:wfBaseProcessId\n");
            else
                queryFilter.append("and wfp.wf_base_process_id=:wfBaseProcessId\n");
            paramCount++;
        }
        if (wfProcess.getWfProcessStatusId() != null){
            if (paramCount == 0)
                queryFilter.append("where wfp.wf_process_status_id=:wfProcessStatusId\n");
            else
                queryFilter.append("and wfp.wf_process_status_id=:wfProcessStatusId\n");
            paramCount++;
        }

        if ((sqlCondition != null) && !("".equals(sqlCondition))){
           if(paramCount == 0)
               queryFilter.append(" where ( \n"+sqlCondition+"\n )");
           else
               queryFilter.append(" and ( \n"+sqlCondition+"\n )");
        }

        queryFilter.append("order by wfp.start_date DESC");// DESC - по убыванию.

        Query query = entityManager.createNamedQuery(String.valueOf(queryFilter), WFProcess.class);

        if (wfProcess.getId() != null)
            query.setParameter("id",wfProcess.getId());
        if (wfProcess.getStartDate() != null)
            query.setParameter("startDate",wfProcess.getStartDate());
        if (wfProcess.getFinalDate() != null)
            query.setParameter("finalDate",wfProcess.getFinalDate());
        if (wfProcess.getDescription() != null)
            query.setParameter("description",wfProcess.getDescription());
        if (wfProcess.getDateEdit() != null)
            query.setParameter("dateEdit",wfProcess.getDateEdit());
        if (wfProcess.getUserEdit() != null)
            query.setParameter("userEdit",wfProcess.getUserEdit());
        if (wfProcess.getWfPackageId() != null)
            query.setParameter("wfPackageId",wfProcess.getWfPackageId().getId());
        if (wfProcess.getWfBaseProcessId() != null)
            query.setParameter("wfBaseProcessId",wfProcess.getWfBaseProcessId().getId());
        if (wfProcess.getWfProcessStatusId() != null)
            query.setParameter("wfProcessStatusId",wfProcess.getWfProcessStatusId().getId());

        list = query.getResultList();

        return list;
    }

    @Override
    public List<WFProcess> getListWFProcess(int wfServiceId, int wfProcessStatusId) {
        String queryGetListWFProcess = sqlQuery+
                " where wfp.wfBaseProcessId.wfServiceId.id=:wfServiceId\n "+
                " and wfp.wfProcessStatusId.id=:wfProcessStatusId\n"+
                " order by wfp.startDate desc "; // по убиванию

        List<WFProcess> list = (List<WFProcess>) entityManager.createQuery(queryGetListWFProcess, WFProcess.class)
                .setParameter("wfServiceId",wfServiceId)
                .setParameter("wfProcessStatusId",wfProcessStatusId)
                .getSingleResult();

        return list;
    }

    @Override
    public List<WFProcess> getByWFPackageId(int wfPackageId) {
        String queryGetByWFPackageId = sqlQuery+
                " where wfp.wfPackageId.id=:wfPackageId\n "+
                " order by wfp.startDate desc "; // по убиванию

        List<WFProcess> list = (List<WFProcess>) entityManager.createQuery(queryGetByWFPackageId, WFProcess.class)
                .setParameter("wfPackageId",wfPackageId)
                .getSingleResult();

        return list;
    }
}

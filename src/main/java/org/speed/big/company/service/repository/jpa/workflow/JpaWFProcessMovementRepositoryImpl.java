package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFProcessMovement;
import org.speed.big.company.service.repository.workflow.WFProcessMovementRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaWFProcessMovementRepositoryImpl")
public class JpaWFProcessMovementRepositoryImpl implements WFProcessMovementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WFProcessMovement save(WFProcessMovement wfProcessMovement) {
        if (wfProcessMovement.isNew())
            entityManager.persist(wfProcessMovement);
        else
            entityManager.merge(wfProcessMovement);

        return wfProcessMovement;
    }

    @Override
    public WFProcessMovement get(int id) {
      /*  WFProcessMovement wfProcessMovement = (WFProcessMovement) entityManager.createNamedQuery(WFProcessMovement.GET)
                .setParameter("id",id)
                .getSingleResult();*/

        return entityManager.find(WFProcessMovement.class,id);
    }

    @Override
    public boolean delete(int id) {
        /*WFProcessMovement wfProcessMovement = entityManager.getReference(WFProcessMovement.class,id);
        entityManager.remove(wfProcessMovement);*/

   /*     boolean isDelete = entityManager.createQuery("delete from WFProcessMovement wfpm where wfpm.id=:id"
                ,WFProcessMovement.class)
                .setParameter("id",id)
                .executeUpdate() != 0;*/

     /*   boolean isDelete = entityManager.createNativeQuery("delete from wf_process_movement where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/

        return entityManager.createNamedQuery(WFProcessMovement.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<WFProcessMovement> getAll() {
        return entityManager.createNamedQuery(WFProcessMovement.ALL_SORTED, WFProcessMovement.class).getResultList();
    }

    @Override
    public List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement) {
        return this.filter(wfProcessMovement,null);
    }

    @Override
    public List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement, String sqlCondition) {
        String queryFilter = "select wfpm from WFProcessMovement wfpm";
        int paramCount = 0;
        List<WFProcessMovement> list = null;

        if (wfProcessMovement.getId() != null){
            queryFilter = queryFilter + " where wfpm.id=:id\n";
            paramCount++;
        }
        if (wfProcessMovement.getStartDateTime() != null){
            if (paramCount == 0)
                queryFilter += " where wfpm.startDateTime=:startDateTime";
            else
                queryFilter += " and wfpm.startDateTime=:startDateTime";
            paramCount++;
        }
        if (wfProcessMovement.getFinalDateTime() != null){
            if (paramCount == 0)
                queryFilter += " where wfpm.finalDateTime=:finalDateTime";
            else
                queryFilter += " and wfpm.finalDateTime=:finalDateTime";
            paramCount++;
        }
        if (Boolean.valueOf(wfProcessMovement.isCompleted()) != null){
            if (paramCount == 0)
                queryFilter += " where wfpm.isCompleted=:isCompleted";
            else
                queryFilter += " and wfpm.isCompleted=:isCompleted";
            paramCount++;
        }
        if (wfProcessMovement.getDescription() != null){
            if (paramCount == 0)
                queryFilter += " where wfpm.description=:description";
            else
                queryFilter += " and wfpm.description=:description";
            paramCount++;
        }
        if (wfProcessMovement.getDateEdit() != null){
            if (paramCount == 0)
                queryFilter += " where wfpm.dateEdit=:dateEdit";
            else
                queryFilter += " and wfpm.dateEdit=:dateEdit";
            paramCount++;
        }
        if (wfProcessMovement.getUserEdit() != null){
            if (paramCount == 0)
                queryFilter += " where wfpm.userEdit=:userEdit";
            else
                queryFilter += " and wfpm.userEdit=:userEdit";
            paramCount++;
        }
        if (wfProcessMovement.getUserId() != null){
            if (paramCount == 0)
                queryFilter += " where wfpm.userId=:userId";
            else
                queryFilter += " and wfpm.userId=:userId";
            paramCount++;
        }
        if (wfProcessMovement.getWfPackageId() != null){
            if (paramCount == 0)
                queryFilter += " where wfpm.wfPackageId=:wfPackageId";
            else
                queryFilter += " and wfpm.wfPackageId=:wfPackageId";
            paramCount++;
        }
        if (wfProcessMovement.getWfStateId() != null){
            if (paramCount == 0)
                queryFilter += " where wfpm.wfStateId=:wfStateId";
            else
                queryFilter += " and wfpm.wfStateId=:wfStateId";
            paramCount++;
        }
        if (wfProcessMovement.getWfProcessId() != null){
            if (paramCount == 0)
                queryFilter += " where wfpm.wfProcessId=:wfProcessId";
            else
                queryFilter += " and wfpm.wfProcessId=:wfProcessId";
            paramCount++;
        }
        if (wfProcessMovement.getWfBaseProcessId() != null){
            if (paramCount == 0)
                queryFilter += " where wfpm.wfBaseProcessId=:wfBaseProcessId";
            else
                queryFilter += " and wfpm.wfBaseProcessId=:wfBaseProcessId";
            paramCount++;
        }
        if (paramCount == 0)
            queryFilter += " where wfpm.isLast=:isLast";
        else
            queryFilter += " and wfpm.isLast=:isLast";


        if ((sqlCondition != null) && (!"".equals(sqlCondition))){
            if (paramCount == 0)
                queryFilter += " where ( "+sqlCondition+" )\n";
            else
                queryFilter += " and ( "+sqlCondition+" )\n";
        }

        queryFilter += " order by wfpm.startDateTime DESC";//default ASC -  по возрастанию; DSC - по убиванию

        Query query = entityManager.createQuery(queryFilter,WFProcessMovement.class);

        if (wfProcessMovement.getId() != null){
            query.setParameter("id",wfProcessMovement.getId());
            queryFilter = queryFilter + " where wfpm.id=:id\n";
            paramCount++;
        }
        if (wfProcessMovement.getStartDateTime() != null)
            query.setParameter("startDateTime",wfProcessMovement.getStartDateTime());
        if (wfProcessMovement.getFinalDateTime() != null)
            query.setParameter("finalDateTime",wfProcessMovement.getFinalDateTime());
        if (Boolean.valueOf(wfProcessMovement.isCompleted()) != null)
            query.setParameter("isCompleted",wfProcessMovement.isCompleted());
        if (wfProcessMovement.getDescription() != null)
            query.setParameter("description",wfProcessMovement.getDescription());
        if (wfProcessMovement.getDateEdit() != null)
            query.setParameter("dateEdit",wfProcessMovement.getDateEdit());
        if (wfProcessMovement.getUserEdit() != null)
            query.setParameter("userEdit",wfProcessMovement.getUserEdit());
        if (wfProcessMovement.getUserId() != null)
            query.setParameter("userId",wfProcessMovement.getUserId());
        if (wfProcessMovement.getWfPackageId() != null)
            query.setParameter("wfPackageId",wfProcessMovement.getWfPackageId());
        if (wfProcessMovement.getWfStateId() != null)
            query.setParameter("wfStateId",wfProcessMovement.getWfStateId());
        if (wfProcessMovement.getWfProcessId() != null)
            query.setParameter("wfProcessId",wfProcessMovement.getWfProcessId());
        if (wfProcessMovement.getWfBaseProcessId() != null)
            query.setParameter("wfBaseProcessId",wfProcessMovement.getWfBaseProcessId());
        query.setParameter("isLast",wfProcessMovement.isLast());

        list = query.getResultList();

        return list;
    }
}
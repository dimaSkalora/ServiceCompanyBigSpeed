package org.speed.big.company.service.repository.data_jpa.impl.workflow;

import org.speed.big.company.service.model.workflow.WFProcess;
import org.speed.big.company.service.repository.data_jpa.workflow.CrudWFProcessRepository;
import org.speed.big.company.service.repository.workflow.WFProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DataWFProcessRepositoryImpl implements WFProcessRepository {

    @Autowired
    private CrudWFProcessRepository crudWFProcessRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public WFProcess save(WFProcess wfProcess) {
        return crudWFProcessRepository.save(wfProcess);
    }

    @Override
    public WFProcess get(int id) {
        return crudWFProcessRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudWFProcessRepository.delete(id) != 0;
    }

    @Override
    public List<WFProcess> getAll() {
        return crudWFProcessRepository.findAll(Sort.by(Sort.Direction.DESC, "startDate"));
    }

    @Override
    public List<WFProcess> filter(WFProcess wfProcess) {
        return filter(wfProcess,null);
    }

    @Override
    public List<WFProcess> filter(WFProcess wfProcess, String sqlCondition) {
        StringBuilder queryFilter = new StringBuilder();
        queryFilter.append("select wfpro from WFProcess wfpro " +
                " join fetch wfpro.wfPackageId join fetch wfpro.wfBaseProcessId " +
                " join fetch wfpro.wfProcessStatusId ");
        List<WFProcess> list;
        int paramCount = 0;

        if (wfProcess.getId() != null) {
            queryFilter.append("where wfpro.id=:id\n");
            paramCount++;
        }
        if (wfProcess.getStartDate() != null){
            if (paramCount == 0)
                queryFilter.append("where wfpro.startDate=:startDate\n");
            else
                queryFilter.append("and wfpro.startDate=:startDate\n");
            paramCount++;
        }
        if (wfProcess.getFinalDate() != null){
            if (paramCount == 0)
                queryFilter.append("where wfpro.finalDate=:finalDate\n");
            else
                queryFilter.append("and wfpro.finalDate=:finalDate\n");
            paramCount++;
        }
        if (wfProcess.getDescription() != null){
            if (paramCount == 0)
                queryFilter.append("where wfpro.description=:description\n");
            else
                queryFilter.append("and wfpro.description=:description\n");
            paramCount++;
        }
        if (wfProcess.getDateEdit() != null){
            if (paramCount == 0)
                queryFilter.append("where wfpro.dateEdit=:dateEdit\n");
            else
                queryFilter.append("and wfpro.dateEdit=:dateEdit\n");
            paramCount++;
        }
        if (wfProcess.getUserEdit() != null){
            if (paramCount == 0)
                queryFilter.append("where wfpro.userEdit=:userEdit\n");
            else
                queryFilter.append("and wfpro.userEdit=:userEdit\n");
            paramCount++;
        }
        if (wfProcess.getWfPackageId() != null){
            if (paramCount == 0)
                queryFilter.append("where wfpro.wfPackageId=:wfPackageId\n");
            else
                queryFilter.append("and wfpro.wfPackageId=:wfPackageId\n");
            paramCount++;
        }
        if (wfProcess.getWfBaseProcessId() != null){
            if (paramCount == 0)
                queryFilter.append("where wfpro.wfBaseProcessId=:wfBaseProcessId\n");
            else
                queryFilter.append("and wfpro.wfBaseProcessId=:wfBaseProcessId\n");
            paramCount++;
        }
        if (wfProcess.getWfProcessStatusId() != null){
            if (paramCount == 0)
                queryFilter.append("where wfpro.wfProcessStatusId=:wfProcessStatusId\n");
            else
                queryFilter.append("and wfpro.wfProcessStatusId=:wfProcessStatusId\n");
            paramCount++;
        }

        if ((sqlCondition != null) && !("".equals(sqlCondition))){
            if(paramCount == 0)
                queryFilter.append(" where ( \n"+sqlCondition+"\n )");
            else
                queryFilter.append(" and ( \n"+sqlCondition+"\n )");
        }

        queryFilter.append("order by wfpro.startDate DESC");// DESC - по убыванию.

        Query query = entityManager.createQuery(String.valueOf(queryFilter), WFProcess.class);

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
            query.setParameter("wfPackageId",wfProcess.getWfPackageId());
        if (wfProcess.getWfBaseProcessId() != null)
            query.setParameter("wfBaseProcessId",wfProcess.getWfBaseProcessId());
        if (wfProcess.getWfProcessStatusId() != null)
            query.setParameter("wfProcessStatusId",wfProcess.getWfProcessStatusId());

        list = query.getResultList();

        return list;
    }

    @Override
    public List<WFProcess> getListWFProcess(int wfServiceId, int wfProcessStatusId) {
        return crudWFProcessRepository.findWFProcessesByWFSIdAndWFPSId(wfServiceId,
                wfProcessStatusId, Sort.by(Sort.Direction.DESC, "startDate"));
    }

    @Override
    public List<WFProcess> getByWFPackageId(int wfPackageId) {
        return crudWFProcessRepository.findWFProcessesByWFPackageId(wfPackageId, Sort.by(Sort.Direction.DESC, "startDate"));
    }
}

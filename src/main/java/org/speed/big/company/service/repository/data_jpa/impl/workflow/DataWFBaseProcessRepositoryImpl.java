package org.speed.big.company.service.repository.data_jpa.impl.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.repository.data_jpa.workflow.CrudWFBaseProcessRepository;
import org.speed.big.company.service.repository.workflow.WFBaseProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DataWFBaseProcessRepositoryImpl implements WFBaseProcessRepository {

    private final CrudWFBaseProcessRepository crudWFBaseProcessRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DataWFBaseProcessRepositoryImpl(CrudWFBaseProcessRepository crudWFBaseProcessRepository) {
        this.crudWFBaseProcessRepository = crudWFBaseProcessRepository;
    }

    @Override
    public WFBaseProcess save(WFBaseProcess wfBaseProcess) {
        return crudWFBaseProcessRepository.save(wfBaseProcess);
    }

    @Override
    public WFBaseProcess get(int id) {
        return crudWFBaseProcessRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudWFBaseProcessRepository.delete(id) != 0;
    }

    @Override
    public List<WFBaseProcess> getAll() {
        return crudWFBaseProcessRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<WFBaseProcess> filter(WFBaseProcess wfBaseProcess) {
        String queryFilter = "select wfbp from WFBaseProcess wfbp \n"+
                " join fetch wfbp.wfServiceId join fetch wfbp.wfBaseProcessTypeId \n" ;
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
                queryFilter += " where wfbp.wfServiceId=:wfServiceId\n";
            else
                queryFilter += " and wfbp.wfServiceId=:wfServiceId\n";
            paramCount++;
        }
        if(wfBaseProcess.getWfBaseProcessTypeId() != null){
            if (paramCount == 0)
                queryFilter += " where wfbp.wfBaseProcessTypeId=:wfBaseProcessTypeId\n";
            else
                queryFilter += " and wfbp.wfBaseProcessTypeId=:wfBaseProcessTypeId\n";
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

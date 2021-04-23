package org.speed.big.company.service.repository.data_jpa.impl.workflow;

import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.speed.big.company.service.repository.data_jpa.workflow.CrudWFProcessStatusRepository;
import org.speed.big.company.service.repository.workflow.WFProcessStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DataWFProcessStatusRepositoryImpl implements WFProcessStatusRepository {
    @Autowired
    private CrudWFProcessStatusRepository crudWFProcessStatusRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WFProcessStatus save(WFProcessStatus wfProcessStatus) {
        return crudWFProcessStatusRepository.save(wfProcessStatus);
    }

    @Override
    public WFProcessStatus get(int id) {
        return crudWFProcessStatusRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudWFProcessStatusRepository.delete(id) != 0;
    }

    @Override
    public List<WFProcessStatus> getAll() {
        return crudWFProcessStatusRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<WFProcessStatus> filter(WFProcessStatus wfProcessStatus) {
        String queryFilter = "select wfps from WFProcessStatus wfps ";
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

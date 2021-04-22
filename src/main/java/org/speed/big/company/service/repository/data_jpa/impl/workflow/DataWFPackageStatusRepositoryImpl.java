package org.speed.big.company.service.repository.data_jpa.impl.workflow;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.speed.big.company.service.repository.data_jpa.workflow.CrudWFPackageStatusRepository;
import org.speed.big.company.service.repository.workflow.WFPackageStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DataWFPackageStatusRepositoryImpl implements WFPackageStatusRepository {
    private final CrudWFPackageStatusRepository crudWFPackageStatusRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DataWFPackageStatusRepositoryImpl(CrudWFPackageStatusRepository crudWFPackageStatusRepository) {
        this.crudWFPackageStatusRepository = crudWFPackageStatusRepository;
    }

    @Override
    public WFPackageStatus save(WFPackageStatus wfPackageStatus) {
        return crudWFPackageStatusRepository.save(wfPackageStatus);
    }

    @Override
    public WFPackageStatus get(int id) {
        return crudWFPackageStatusRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudWFPackageStatusRepository.delete(id) != 0;
    }

    @Override
    public List<WFPackageStatus> getAll() {
        return crudWFPackageStatusRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<WFPackageStatus> filter(WFPackageStatus wfPackageStatus) {
        String queryFilter = "select wfps from WFPackageStatus wfps ";
        int paramCount = 0;
        List<WFPackageStatus> list;

        if (wfPackageStatus.getId() != null) {
            queryFilter = queryFilter + " where wfps.id=:id\n";
            paramCount++;
        }
        if (wfPackageStatus.getName() != null){
            if (paramCount == 0)
                queryFilter += " where wfps.name=:name";
            else
                queryFilter += " and wfps.name=:name";
        }

        queryFilter += " order by wfps.name "; //default ASC -  по возрастанию

        Query query = entityManager.createQuery(queryFilter);

        if (wfPackageStatus.getId() != null)
            query.setParameter("id",wfPackageStatus.getId());
        if (wfPackageStatus.getName() != null)
            query.setParameter("name", wfPackageStatus.getName());

        list = query.getResultList();

        return list;
    }
}

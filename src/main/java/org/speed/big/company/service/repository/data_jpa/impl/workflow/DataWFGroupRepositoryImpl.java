package org.speed.big.company.service.repository.data_jpa.impl.workflow;

import org.speed.big.company.service.model.workflow.WFGroup;
import org.speed.big.company.service.repository.data_jpa.workflow.CrudWFGroupRepository;
import org.speed.big.company.service.repository.workflow.WFGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DataWFGroupRepositoryImpl implements WFGroupRepository {

    private final CrudWFGroupRepository crudWFGroupRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DataWFGroupRepositoryImpl(CrudWFGroupRepository crudWFGroupRepository) {
        this.crudWFGroupRepository = crudWFGroupRepository;
    }

    @Override
    public WFGroup save(WFGroup wfGroup) {
        return crudWFGroupRepository.save(wfGroup);
    }

    @Override
    public WFGroup get(int id) {
        return crudWFGroupRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudWFGroupRepository.delete(id) != 0;
    }

    @Override
    public List<WFGroup> getAll() {
        return crudWFGroupRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<WFGroup> filter(WFGroup wfGroup) {
        StringBuilder queryFilter = new StringBuilder();
        queryFilter.append("select wfg from WFGroup wfg");
        int paramCount = 0;
        List<WFGroup> list = null;

        if (wfGroup.getId() != null) {
            queryFilter.append(" where wfg.id=:id\n");
            paramCount++;
        }
        if (wfGroup.getName() != null){
            if (paramCount == 0)
                queryFilter.append(" where wfg.name=:name\n");
            else
                queryFilter.append(" and wfg.name=:name\n");
            paramCount++;
        }
        if (wfGroup.getDescription() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfg.description=:description\n");
            else
                queryFilter.append(" and wfg.description=:description\n");
        }

        queryFilter.append("order by wfg.name");

        Query query = entityManager.createQuery(String.valueOf(queryFilter));

        if (wfGroup.getId() != null)
            query.setParameter("id",wfGroup.getId());
        if (wfGroup.getName() != null)
            query.setParameter("name",wfGroup.getName());
        if (wfGroup.getDescription() != null)
            query.setParameter("description",wfGroup.getDescription());

        list = query.getResultList();

        return list;
    }
}

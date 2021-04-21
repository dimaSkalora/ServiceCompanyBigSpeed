package org.speed.big.company.service.repository.data_jpa.impl.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.speed.big.company.service.repository.data_jpa.workflow.CrudWFBaseProcessTypeRepository;
import org.speed.big.company.service.repository.workflow.WFBaseProcessTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DataWFBaseProcessTypeRepositoryImpl implements WFBaseProcessTypeRepository {

    private final CrudWFBaseProcessTypeRepository crudWFBaseProcessTypeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DataWFBaseProcessTypeRepositoryImpl(CrudWFBaseProcessTypeRepository crudWFBaseProcessTypeRepository) {
        this.crudWFBaseProcessTypeRepository = crudWFBaseProcessTypeRepository;
    }

    @Override
    public WFBaseProcessType save(WFBaseProcessType wfBaseProcessType) {
        return crudWFBaseProcessTypeRepository.save(wfBaseProcessType);
    }

    @Override
    public WFBaseProcessType get(int id) {
        return crudWFBaseProcessTypeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudWFBaseProcessTypeRepository.delete(id) != 0;
    }

    @Override
    public List<WFBaseProcessType> getAll() {
        return crudWFBaseProcessTypeRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<WFBaseProcessType> filter(WFBaseProcessType wfBaseProcessType) {
        String queryFilter = " select wfbpt from WFBaseProcessType wfbpt \n";
        int paramCount = 0;
        List<WFBaseProcessType> list;

        if (wfBaseProcessType.getId() != null){
            queryFilter = queryFilter + " where wfbpt.id=:id";
            paramCount++;
        }
        if (wfBaseProcessType.getName() != null){
            if (paramCount == 0)
                queryFilter += " where wfbpt.name=:name";
            else
                queryFilter += " and wfbpt.name=:name";
        }

        queryFilter += " order by wfbpt.name";

        Query query = entityManager.createQuery(queryFilter);

        if (wfBaseProcessType.getId() != null)
            query.setParameter("id",wfBaseProcessType.getId());
        if (wfBaseProcessType.getName() != null)
            query.setParameter("namwe",wfBaseProcessType.getName());

        list = query.getResultList();

        return list;
    }
}

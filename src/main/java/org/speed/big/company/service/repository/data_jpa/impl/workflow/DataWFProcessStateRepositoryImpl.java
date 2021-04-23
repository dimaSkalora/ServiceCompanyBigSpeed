package org.speed.big.company.service.repository.data_jpa.impl.workflow;

import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.repository.data_jpa.workflow.CrudWFProcessStateRepository;
import org.speed.big.company.service.repository.workflow.WFProcessStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DataWFProcessStateRepositoryImpl implements WFProcessStateRepository {
    @Autowired
    private CrudWFProcessStateRepository crudWFProcessStateRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WFProcessState save(WFProcessState wfProcessState) {
        return crudWFProcessStateRepository.save(wfProcessState);
    }

    @Override
    public WFProcessState get(int id) {
        return crudWFProcessStateRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudWFProcessStateRepository.delete(id) != 0;
    }

    @Override
    public List<WFProcessState> getAll() {
        return crudWFProcessStateRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<WFProcessState> filter(WFProcessState wfProcessState) {
        String queryFilter = "select wfpstate from WFProcessState wfpstate\n" +
                " join fetch wfpstate.roleId join fetch wfpstate.wfGroupId \n";
        int paramCount = 0;
        List<WFProcessState> list = null;

        if (wfProcessState.getId() != null) {
            queryFilter = queryFilter + " where wfpstate.id=:id\n";
            paramCount++;
        }
        if (wfProcessState.getName() != null){
            if (paramCount == 0)
                queryFilter += " where wfpstate.name=:name\n";
            else
                queryFilter += " and wfpstate.name=:name\n";
            paramCount++;
        }
        if (wfProcessState.getRoleId() != null){
            if (paramCount == 0)
                queryFilter += " where wfpstate.roleId=:roleId\n";
            else
                queryFilter += " and wfpstate.roleId=:roleId\n";
            paramCount++;
        }
        if (wfProcessState.getWfGroupId() != null){
            if (paramCount == 0)
                queryFilter += " where wfpstate.wfGroupId=:wfGroupId\n";
            else
                queryFilter += " and wfpstate.wfGroupId=:wfGroupId\n";
            paramCount++;
        }
        if (wfProcessState.getDescription() != null){
            if (paramCount == 0)
                queryFilter += " where wfpstate.description=:description\n";
            else
                queryFilter += " and wfpstate.description=:description\n";
        }

        queryFilter += " order by wfpstate.name";

        Query query = entityManager.createQuery(queryFilter, WFProcessState.class);

        if (wfProcessState.getId() != null)
            query.setParameter("id",wfProcessState.getId());
        if (wfProcessState.getName() != null)
            query.setParameter("name",wfProcessState.getName());
        if (wfProcessState.getRoleId() != null)
            query.setParameter("roleId",wfProcessState.getRoleId());
        if (wfProcessState.getWfGroupId() != null)
            query.setParameter("wfGroupId",wfProcessState.getWfGroupId());
        if (wfProcessState.getDescription() != null)
            query.setParameter("description",wfProcessState.getDescription());

        list = query.getResultList();

        return list;
    }

    @Override
    public List<WFProcessState> getByRoleId(int roleId) {
        String queryGetByRoleId = "select wfpstate from WFProcessState wfpstate\n" +
                " join fetch wfpstate.roleId join fetch wfpstate.wfGroupId \n" +
                " where wfpstate.roleId=:roleId";

        List<WFProcessState> list = (List<WFProcessState>) entityManager.createQuery(queryGetByRoleId, WFProcessState.class)
                .setParameter("roleId",roleId)
                .getSingleResult();

        return list;
    }
}

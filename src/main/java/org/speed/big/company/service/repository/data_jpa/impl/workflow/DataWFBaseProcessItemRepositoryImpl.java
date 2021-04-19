package org.speed.big.company.service.repository.data_jpa.impl.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.repository.data_jpa.workflow.CrudWFBaseProcessItemRepository;
import org.speed.big.company.service.repository.workflow.WFBaseProcessItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataWFBaseProcessItemRepositoryImpl implements WFBaseProcessItemRepository {

    @Autowired
    private CrudWFBaseProcessItemRepository crudWFBaseProcessItemRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WFBaseProcessItem save(WFBaseProcessItem wfBaseProcessItem) {
        return crudWFBaseProcessItemRepository.save(wfBaseProcessItem);
    }

    @Override
    public WFBaseProcessItem get(int id) {
        return crudWFBaseProcessItemRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudWFBaseProcessItemRepository.delete(id) != 0;
    }

    @Override
    public List<WFBaseProcessItem> getAll() {
        return crudWFBaseProcessItemRepository.findAll(Sort.by("baseProcessId"));
    }

    @Override
    public List<WFBaseProcessItem> filter(WFBaseProcessItem wfBaseProcessItem) {
        String queryFilter = "select wfbpi from WFBaseProcessItem wfbpi \n"+
                " join fetch wfbpi.stateFromId join fetch wfbpi.stateToId " +
                " join fetch wfbpi.baseProcessId " ;
        int paramCount = 0;
        List<WFBaseProcessItem> list = null;
        if (wfBaseProcessItem.getId() != null){
            queryFilter = queryFilter +" where wfbpi.id=:id\n";
            paramCount++;
        }
        if (wfBaseProcessItem.getStateFromId() != null){
            if (paramCount == 0)
                queryFilter += " where wfbpi.stateFromId=:stateFromId\n";
            else
                queryFilter += " and wfbpi.stateFromId=:stateFromId\n";
            paramCount++;
        }
        if (wfBaseProcessItem.getStateToId() != null){
            if (paramCount == 0)
                queryFilter += " where wfbpi.stateToId=:stateToId\n";
            else
                queryFilter += " and wfbpi.stateToId=:stateToId\n";
            paramCount++;
        }
        if (wfBaseProcessItem.getBaseProcessId() != null){
            if (paramCount == 0)
                queryFilter += " where wfbpi.baseProcessId=:baseProcessId\n";
            else
                queryFilter += " and wfbpi.baseProcessId=:baseProcessId\n";
            paramCount++;
        }

        queryFilter += " order by wfbpi.id";

        Query query = entityManager.createQuery(queryFilter,WFBaseProcessItem.class);

        if (wfBaseProcessItem.getId() != null)
            query.setParameter("id" ,wfBaseProcessItem.getId());
        if (wfBaseProcessItem.getStateFromId() != null)
            query.setParameter("stateFromId",wfBaseProcessItem.getStateFromId());
        if (wfBaseProcessItem.getStateToId() != null)
            query.setParameter("stateToId",wfBaseProcessItem.getStateToId());
        if (wfBaseProcessItem.getBaseProcessId() != null)
            query.setParameter("baseProcessId",wfBaseProcessItem.getBaseProcessId());

        list = query.getResultList();

        return list;
    }

    @Override
    public List<WFProcessState> getListTransferWFProcessState(int processStateFromId, int baseProcessId) {
        String queryGetListTransferWFProcessState = "select wfbpi from WFBaseProcessItem wfbpi \n" +
                " where wfbpi.stateFromId=:processStateFromId\n" +
                " and wfbpi.baseProcessId=:baseProcessId";

        List<WFBaseProcessItem> list = entityManager.createQuery(queryGetListTransferWFProcessState, WFBaseProcessItem.class)
                .setParameter("processStateFromId",processStateFromId)
                .setParameter("baseProcessId",baseProcessId)
                .getResultList();

        List<WFProcessState> listTransferWFProcessStates = list.stream()
                .map(wbpi -> wbpi.getStateFromId())
                .collect(Collectors.toList());

        return listTransferWFProcessStates;
    }
}

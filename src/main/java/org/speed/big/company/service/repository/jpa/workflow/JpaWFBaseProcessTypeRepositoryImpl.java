package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.speed.big.company.service.repository.workflow.WFBaseProcessTypeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaWFBaseProcessTypeRepositoryImpl")
@Transactional(readOnly = true)
public class JpaWFBaseProcessTypeRepositoryImpl implements WFBaseProcessTypeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public WFBaseProcessType save(WFBaseProcessType wfBaseProcessType) {
        if (wfBaseProcessType.isNew())
            entityManager.persist(wfBaseProcessType);
        else
            entityManager.merge(wfBaseProcessType);

        return wfBaseProcessType;
    }

    @Override
    public WFBaseProcessType get(int id) {
/*        Query queryGet = entityManager.createNamedQuery(WFBaseProcessType.GET)
                .setParameter("id",id);
        WFBaseProcessType wfBaseProcessType = (WFBaseProcessType) queryGet.getSingleResult();*/

        return entityManager.find(WFBaseProcessType.class,id);
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        /*WFBaseProcessType wfBaseProcessType = entityManager.getReference(WFBaseProcessType.class, id);
        entityManager.remove(wfBaseProcessType);*/
       /* boolean isDelete = entityManager
                .createQuery("delete from WFBaseProcessType wfbpt where wfbpt.id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/
  /*      boolean isDelete = entityManager
                .createNativeQuery("delete from wf_base_process_type wfbpt where wfbpt.id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/

        return entityManager.createNamedQuery(WFBaseProcessType.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<WFBaseProcessType> getAll() {
        return entityManager.createNamedQuery(WFBaseProcessType.ALL_SORTED).getResultList();
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

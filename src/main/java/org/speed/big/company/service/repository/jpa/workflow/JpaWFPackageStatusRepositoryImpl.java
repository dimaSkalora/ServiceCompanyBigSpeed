package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.speed.big.company.service.repository.workflow.WFPackageStatusRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaWFPackageStatusRepositoryImpl")
@Transactional(readOnly = true)
public class JpaWFPackageStatusRepositoryImpl implements WFPackageStatusRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public WFPackageStatus save(WFPackageStatus wfPackageStatus) {
        if (wfPackageStatus.isNew())
            entityManager.persist(wfPackageStatus);
        else
            entityManager.merge(wfPackageStatus);

        return wfPackageStatus;
    }

    @Override
    public WFPackageStatus get(int id) {
       /* WFPackageStatus wfPackageStatus = entityManager
                .createNamedQuery(WFPackageStatus.GET, WFPackageStatus.class)
                .setParameter("id",id)
                .getSingleResult();*/

        WFPackageStatus wfPackageStatus = entityManager.find(WFPackageStatus.class, id);

        return wfPackageStatus;
    }

    @Transactional
    @Override
    public boolean delete(int id) {
       /* WFPackageStatus wfPackageStatus = entityManager
                .getReference(WFPackageStatus.class, id);
        entityManager.remove(wfPackageStatus);*/

  /*      boolean queryDelete = entityManager.createQuery("delete from WFPackageStatus where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/
       /* boolean queryDelete = entityManager.createNativeQuery("delete from wf_package_status where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/

        return entityManager.createNamedQuery(WFPackageStatus.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<WFPackageStatus> getAll() {
        return entityManager.createNamedQuery(WFPackageStatus.ALL_SORTED,
                WFPackageStatus.class).getResultList();
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

package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFService;
import org.speed.big.company.service.repository.workflow.WFServiceRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaWFServiceRepositoryImpl")
public class JpaWFServiceRepositoryImpl implements WFServiceRepository {

      /* @Autowired
     private SessionFactory sessionFactory;


     private Session openSession() {
         return sessionFactory.getCurrentSession();
     }*/

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WFService save(WFService wfService) {
        if (wfService.isNew())
            entityManager.persist(wfService);
        else
            entityManager.merge(wfService);
        return wfService;
    }

    @Override
    public WFService get(int id) {
       /* WFService wfService = entityManager
                .createNamedQuery(WFService.GET, WFService.class)
                .setParameter("id",id)
                .getSingleResult();*/

        WFService wfService = entityManager.find(WFService.class,id);

        return wfService;
    }

    @Override
    public boolean delete(int id) {
       /* WFService wfService = entityManager.getReference(WFService.class, id);
        entityManager.remove(wfService);*/

       /* boolean isDelete = entityManager.createNamedQuery(WFService.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;*/

   /*     boolean isDelete = entityManager
                .createNativeQuery("delete  from wf_service where id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/

        return entityManager.createQuery("delete from WFService wfs where wfs.id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<WFService> getAll() {
        return entityManager.createNamedQuery(WFService.ALL_SORTED).getResultList();
    }

    @Override
    public List<WFService> filter(WFService wfService) {
        //String queryFilter = "select wfs from WFService wfs";
        StringBuilder queryFilter = new StringBuilder("select wfs from WFService wfs \n");
        int paramCount = 0;

        if(wfService.getId() != null){
            queryFilter.append(" where wfp.id=:id \n");
            paramCount++;
        }
        if (wfService.getName() != null)
            if (paramCount == 0)
                queryFilter.append(" where wfp.name=:name \n");
            else
                queryFilter.append(" and wfp.name=:name \n");

        queryFilter.append("order by wfp.name "); //default ASC -  по возрастанию

        Query query = entityManager.createQuery(queryFilter.toString());

        if (wfService.getId() != null)
            query.setParameter("id", wfService.getId());
        if (wfService.getName() != null)
            query.setParameter("name", wfService.getName());

        List<WFService> list = query.getResultList();

        return list;
    }
}

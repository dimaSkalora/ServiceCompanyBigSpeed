package org.speed.big.company.service.repository.jpa.workflow;

import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.repository.workflow.WFPackageRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("jpaWFPackageRepositoryImpl")
@Transactional(readOnly = true)
public class JpaWFPackageRepositoryImpl implements WFPackageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public WFPackage save(WFPackage wfPackage) {
        if (wfPackage.isNew())
            entityManager.persist(wfPackage);
        else
            entityManager.merge(wfPackage);
        return wfPackage;
    }

    @Override
    public WFPackage get(int id) {

/*        WFPackage wfPackage = entityManager.createNamedQuery(WFPackage.GET, WFPackage.class)
                .setParameter("id", id)
                .getSingleResult();*/

        WFPackage wfPackage = entityManager.find(WFPackage.class, id);

        return wfPackage;
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        WFPackage wfPackage = entityManager.getReference(WFPackage.class, id);
        entityManager.remove(wfPackage);

     /*   boolean queryDelete = entityManager
                .createQuery("delete from WFPackage wfp where wfp.id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/
   /*     boolean queryDelete = entityManager
                .createNativeQuery("delete from wf_package wfp where wfp.id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/

        return entityManager.createNamedQuery(WFPackage.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<WFPackage> getAll() {
        return entityManager.createNamedQuery(WFPackage.ALL_SORTED).getResultList();
    }

    @Override
    public List<WFPackage> filter(WFPackage wfPackage) {
        return this.filter(wfPackage,null);
    }

    @Override
    public List<WFPackage> filter(WFPackage wfPackage, String sqlCondition) {
        StringBuilder queryFilter = new StringBuilder("select wfp from WFPackage\n");
        int paramCount = 0;
        List<WFPackage> list;

        if (wfPackage.getId() != null){
            queryFilter.append(" where wfp.id=:id\n");
            paramCount++;
        }
        if (wfPackage.getName() != null){
            if (paramCount == 0)
                queryFilter.append(" where wfp.name=:name\n");
            else
                queryFilter.append("and wfp.name=:name\n");
            paramCount++;
        }
        if (wfPackage.getDateRegistration() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.date_registration=:dateRegistration\n");
            else
                queryFilter.append("and wfp.date_registration=:dateRegistration\n");
            paramCount++;
        }
        if (wfPackage.getCustomerName() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.customer_name=:customerName\n");
            else
                queryFilter.append("and wfp.customer_name=:customerName\n");
            paramCount++;
        }
        if (wfPackage.getCustomerAddress() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.customer_address=:customerAddress\n");
            else
                queryFilter.append("and wfp.customer_address=:customerAddress\n");
            paramCount++;
        }
        if (wfPackage.getCustomerAddressJur() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.customer_address_jur=:customerAddressJur\n");
            else
                queryFilter.append("and wfp.customer_address_jur=:customerAddressJur\n");
            paramCount++;
        }
        if (wfPackage.getCustomerPhone() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.customer_phone=:customerPhone\n");
            else
                queryFilter.append("and wfp.customer_phone=:customerPhone\n");
            paramCount++;
        }
        if (wfPackage.getCustomerEmail() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.customer_email=:customerEmail\n");
            else
                queryFilter.append("and wfp.customer_email=:customerEmail\n");
            paramCount++;
        }
        if (wfPackage.getContractNumber() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.contract_number=:contractNumber\n");
            else
                queryFilter.append("and wfp.contract_number=:contractNumber\n");
            paramCount++;
        }
        if (wfPackage.getDescription() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.description=:description\n");
            else
                queryFilter.append("and wfp.description=:description\n");
            paramCount++;
        }
        if (wfPackage.getUserAdd() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.user_add=:userAdd\n");
            else
                queryFilter.append("and wfp.user_add=:userAdd\n");
            paramCount++;
        }
        if (wfPackage.getDateAdd() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.date_add=:dateAdd\n");
            else
                queryFilter.append("and wfp.date_add=:dateAdd\n");
            paramCount++;
        }
        if (wfPackage.getUserEdit() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.user_edit=:userEdit\n");
            else
                queryFilter.append("and wfp.user_edit=:userEdit\n");
            paramCount++;
        }
        if (wfPackage.getDateEdit() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.date_edit=:dateEdit\n");
            else
                queryFilter.append("and wfp.date_edit=:dateEdit\n");
            paramCount++;
        }
        if (wfPackage.getWfServiceId() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.wf_service_id=:wfServiceId\n");
            else
                queryFilter.append("and wfp.wf_service_id=:wfServiceId\n");
            paramCount++;
        }
        if (wfPackage.getWfPackageStatusId() != null) {
            if (paramCount == 0)
                queryFilter.append(" where wfp.wf_package_status_id=:wfPackageStatusId\n");
            else
                queryFilter.append("and wfp.wf_package_status_id=:wfPackageStatusId\n");
            paramCount++;
        }

        if ((sqlCondition != null) && ("".equals(sqlCondition))){
            if (paramCount == 0)
                queryFilter.append(" where ( "+ sqlCondition+" )");
            else
                queryFilter.append(" and ( "+sqlCondition+" )");
        }

        queryFilter.append("order by wfp.dateRegistration, wfp.name "); //default ASC -  по возрастанию

        Query query = entityManager.createQuery(queryFilter.toString());

        if (wfPackage.getId() != null)
            query.setParameter("id", wfPackage.getId());
        if (wfPackage.getName() != null)
            query.setParameter("name", wfPackage.getName());
        if (wfPackage.getDateRegistration() != null)
            query.setParameter("dateRegistration", wfPackage.getDateRegistration());
        if (wfPackage.getCustomerName() != null)
            query.setParameter("customerName", wfPackage.getCustomerName());
        if (wfPackage.getCustomerAddress() != null)
            query.setParameter("customerAddress", wfPackage.getCustomerAddress());
        if (wfPackage.getCustomerAddressJur() != null)
            query.setParameter("customerAddressJur", wfPackage.getCustomerAddressJur());
        if (wfPackage.getCustomerPhone() != null)
            query.setParameter("customerPhone", wfPackage.getCustomerPhone());
        if (wfPackage.getCustomerEmail() != null)
            query.setParameter("customerEmail", wfPackage.getCustomerEmail());
        if (wfPackage.getContractNumber() != null)
            query.setParameter("contractNumber", wfPackage.getContractNumber());
        if (wfPackage.getDescription() != null)
            query.setParameter("description", wfPackage.getDescription());
        if (wfPackage.getUserAdd() != null)
            query.setParameter("userAdd", wfPackage.getUserAdd());
        if (wfPackage.getDateAdd() != null)
            query.setParameter("dateAdd", wfPackage.getDateAdd());
        if (wfPackage.getUserEdit() != null)
            query.setParameter("userEdit", wfPackage.getUserEdit());
        if (wfPackage.getDateEdit() != null)
            query.setParameter("dateEdit", wfPackage.getDateEdit());
        if (wfPackage.getWfServiceId() != null)
            query.setParameter("wfServiceId", wfPackage.getWfServiceId());
        if (wfPackage.getWfPackageStatusId() != null)
            query.setParameter("wfPackageStatusId", wfPackage.getWfPackageStatusId());


        list = query.getResultList();

        return list;
    }
}

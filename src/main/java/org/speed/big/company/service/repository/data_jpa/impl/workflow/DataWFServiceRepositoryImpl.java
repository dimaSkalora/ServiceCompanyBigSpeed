package org.speed.big.company.service.repository.data_jpa.impl.workflow;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.workflow.WFService;
import org.speed.big.company.service.repository.data_jpa.workflow.CrudWFServiceRepository;
import org.speed.big.company.service.repository.workflow.WFServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataWFServiceRepositoryImpl implements WFServiceRepository {
    @Autowired
    private CrudWFServiceRepository crudWFServiceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WFService save(WFService wfService) {
        return crudWFServiceRepository.save(wfService);
    }

    @Override
    public WFService get(int id) {
        return crudWFServiceRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudWFServiceRepository.delete(id) != 0;
    }

    @Override
    public List<WFService> getAll() {
        return crudWFServiceRepository.findAll(Sort.by("name"));
    }

    @Override
    public List<WFService> filter(WFService wfService) {
        StringBuilder queryFilter = new StringBuilder("select wfs from WFService wfs \n");
        int paramCount = 0;

        if(wfService.getId() != null){
            queryFilter.append(" where wfs.id=:id \n");
            paramCount++;
        }
        if (wfService.getName() != null)
            if (paramCount == 0)
                queryFilter.append(" where wfs.name=:name \n");
            else
                queryFilter.append(" and wfs.name=:name \n");

        queryFilter.append(" order by wfs.name "); //default ASC -  по возрастанию

        Query query = entityManager.createQuery(queryFilter.toString());

        if (wfService.getId() != null)
            query.setParameter("id", wfService.getId());
        if (wfService.getName() != null)
            query.setParameter("name", wfService.getName());

        List<WFService> list = query.getResultList();

        return list;
    }

    @Override
    public List<WFService> getWFServiceFromRoles(List<Role> roles) {
        List<String> listNameRoles = roles.stream()
                .map(r -> r.getName())
                .collect(Collectors.toList());

        String queryGetWFServiceFromRoles = "select wfs from WFService wfs where wfs.name in :nameRoles";

        List<WFService> wfServices = entityManager
                .createQuery(queryGetWFServiceFromRoles,WFService.class)
                .setParameter("nameRoles",listNameRoles)
                .getResultList();

        return wfServices;
    }
}

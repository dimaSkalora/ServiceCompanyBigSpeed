package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.workflow.WFService;
import org.speed.big.company.service.repository.workflow.WFServiceRepository;
import org.speed.big.company.service.service.workflow.WFServiceService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("wfServiceServiceImpl")
public class WFServiceServiceImpl implements WFServiceService {

    private WFServiceRepository wfServiceRepository;

    public WFServiceServiceImpl(WFServiceRepository wfServiceRepository) {
        this.wfServiceRepository = wfServiceRepository;
    }

    @Override
    public WFService create(WFService wfService) {
        Assert.notNull(wfService,"не должно быть null");
        return wfServiceRepository.save(wfService);
    }

    @Override
    public WFService update(WFService wfService) throws NotFoundException {
        Assert.notNull(wfService,"не должно быть null");
        return checkNotFoundWithId(wfServiceRepository.save(wfService),wfService.getId());
    }

    @Override
    public WFService get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(wfServiceRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(wfServiceRepository.delete(id)),id);
    }

    @Override
    public List<WFService> getAll() {
        return wfServiceRepository.getAll();
    }

    @Override
    public List<WFService> filter(WFService wfService) {
        Assert.notNull(wfService,"не должно быть null");
        return wfServiceRepository.filter(wfService);
    }

    @Override
    public List<WFService> getWFServiceFromRoles(List<Role> roles) {
        Assert.notNull(roles,"не должно быть null");
        return wfServiceRepository.getWFServiceFromRoles(roles);
    }
}

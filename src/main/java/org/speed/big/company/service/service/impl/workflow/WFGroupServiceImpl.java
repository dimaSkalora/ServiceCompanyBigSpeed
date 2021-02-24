package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.workflow.WFGroup;
import org.speed.big.company.service.repository.workflow.WFGroupRepository;
import org.speed.big.company.service.service.workflow.WFGroupService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("wfGroupServiceImpl")
public class WFGroupServiceImpl implements WFGroupService {

    private WFGroupRepository wfGroupRepository;

    public WFGroupServiceImpl(WFGroupRepository wfGroupRepository) {
        this.wfGroupRepository = wfGroupRepository;
    }

    @Override
    public WFGroup create(WFGroup wfGroup) {
        Assert.notNull(wfGroup,"must not be null");
        return wfGroupRepository.save(wfGroup);
    }

    @Override
    public WFGroup update(WFGroup wfGroup) throws NotFoundException {
        Assert.notNull(wfGroup,"must not be null");
        return checkNotFoundWithId(wfGroupRepository.save(wfGroup),wfGroup.getId());
    }

    @Override
    public WFGroup get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(wfGroupRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(wfGroupRepository.delete(id)),id);
    }

    @Override
    public List<WFGroup> getAll() {
        return wfGroupRepository.getAll();
    }

    @Override
    public List<WFGroup> filter(WFGroup wfGroup) {
        Assert.notNull(wfGroup,"must not be null");
        return wfGroupRepository.filter(wfGroup);
    }
}

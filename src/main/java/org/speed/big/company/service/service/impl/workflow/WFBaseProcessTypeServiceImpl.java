package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.speed.big.company.service.repository.workflow.WFBaseProcessTypeRepository;
import org.speed.big.company.service.service.workflow.WFBaseProcessTypeService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("wfBaseProcessTypeServiceImpl")
public class WFBaseProcessTypeServiceImpl implements WFBaseProcessTypeService {

    private WFBaseProcessTypeRepository wfBaseProcessTypeRepository;

    public WFBaseProcessTypeServiceImpl(WFBaseProcessTypeRepository wfBaseProcessTypeRepository) {
        this.wfBaseProcessTypeRepository = wfBaseProcessTypeRepository;
    }

    @Override
    public WFBaseProcessType create(WFBaseProcessType wfBaseProcessType) {
        Assert.notNull(wfBaseProcessType,"не должно быть null");
        return wfBaseProcessTypeRepository.save(wfBaseProcessType);
    }

    @Override
    public WFBaseProcessType update(WFBaseProcessType wfBaseProcessType) throws NotFoundException {
        Assert.notNull(wfBaseProcessType,"не должно быть null");
        return checkNotFoundWithId(wfBaseProcessTypeRepository.save(wfBaseProcessType), wfBaseProcessType.getId());
    }

    @Override
    public WFBaseProcessType get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(wfBaseProcessTypeRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(wfBaseProcessTypeRepository.delete(id)),id);
    }

    @Override
    public List<WFBaseProcessType> getAll() {
        return wfBaseProcessTypeRepository.getAll();
    }

    @Override
    public List<WFBaseProcessType> filter(WFBaseProcessType wfBaseProcessType) {
        Assert.notNull(wfBaseProcessType,"не должно быть null");
        return wfBaseProcessTypeRepository.filter(wfBaseProcessType);
    }
}

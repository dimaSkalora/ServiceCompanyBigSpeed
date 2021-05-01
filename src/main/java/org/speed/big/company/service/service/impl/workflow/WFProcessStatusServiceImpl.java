package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.speed.big.company.service.repository.workflow.WFProcessStatusRepository;
import org.speed.big.company.service.service.workflow.WFProcessStatusService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("wfProcessStatusServiceImpl")
public class WFProcessStatusServiceImpl implements WFProcessStatusService {

    private WFProcessStatusRepository wfProcessStatusRepository;

    public WFProcessStatusServiceImpl(@Qualifier("dataWFProcessStatusRepositoryImpl") WFProcessStatusRepository wfProcessStatusRepository) {
        this.wfProcessStatusRepository = wfProcessStatusRepository;
    }

    @Override
    public WFProcessStatus create(WFProcessStatus wfProcessStatus) {
        Assert.notNull(wfProcessStatus, "must not be null");
        return wfProcessStatusRepository.save(wfProcessStatus);
    }

    @Override
    public WFProcessStatus update(WFProcessStatus wfProcessStatus) throws NotFoundException {
        Assert.notNull(wfProcessStatus,"must not be null");
        return checkNotFoundWithId(wfProcessStatusRepository.save(wfProcessStatus), wfProcessStatus.getId());
    }

    @Override
    public WFProcessStatus get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(wfProcessStatusRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(wfProcessStatusRepository.delete(id)),id);
    }

    @Override
    public List<WFProcessStatus> getAll() {
        return wfProcessStatusRepository.getAll();
    }

    @Override
    public List<WFProcessStatus> filter(WFProcessStatus wfProcessStatus) {
        Assert.notNull(wfProcessStatus,"must not be null");
        return wfProcessStatusRepository.filter(wfProcessStatus);
    }
}

package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.repository.workflow.WFProcessStateRepository;
import org.speed.big.company.service.service.workflow.WFProcessStateService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("wfProcessStateServiceImpl")
public class WFProcessStateServiceImpl  implements WFProcessStateService {

    private WFProcessStateRepository wfProcessStateRepository;

    @Autowired
    public WFProcessStateServiceImpl(@Qualifier("dataWFProcessStateRepositoryImpl") WFProcessStateRepository wfProcessStateRepository) {
        this.wfProcessStateRepository = wfProcessStateRepository;
    }

    @Override
    public WFProcessState create(WFProcessState wfProcessState) {
        Assert.notNull(wfProcessState,"не должно быть null");
        return wfProcessStateRepository.save(wfProcessState);
    }

    @Override
    public WFProcessState update(WFProcessState wfProcessState) throws NotFoundException {
        Assert.notNull(wfProcessState,"не должно быть null");
        return checkNotFoundWithId(wfProcessStateRepository.save(wfProcessState),wfProcessState.getId());
    }

    @Override
    public WFProcessState get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(wfProcessStateRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(wfProcessStateRepository.delete(id)),id);
    }

    @Override
    public List<WFProcessState> getAll() {
        return wfProcessStateRepository.getAll();
    }

    @Override
    public List<WFProcessState> filter(WFProcessState wfProcessState) {
        Assert.notNull(wfProcessState,"не должно быть null");
        return wfProcessStateRepository.filter(wfProcessState);
    }
}

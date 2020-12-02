package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.repository.workflow.WFBaseProcessItemRepository;
import org.speed.big.company.service.service.workflow.WFBaseProcessItemService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("wfBaseProcessItemServiceImpl")
public class WFBaseProcessItemServiceImpl implements WFBaseProcessItemService {

    private WFBaseProcessItemRepository wfBaseProcessItemRepository;

    public WFBaseProcessItemServiceImpl(@Qualifier("jdbcWFBaseProcessItemRepositoryImpl")WFBaseProcessItemRepository wfBaseProcessItemRepository) {
        this.wfBaseProcessItemRepository = wfBaseProcessItemRepository;
    }

    @Override
    public WFBaseProcessItem create(WFBaseProcessItem wfBaseProcessItem) {
        Assert.notNull(wfBaseProcessItem,"must not be null");
        return wfBaseProcessItemRepository.save(wfBaseProcessItem);
    }

    @Override
    public WFBaseProcessItem update(WFBaseProcessItem wfBaseProcessItem) throws NotFoundException {
        Assert.notNull(wfBaseProcessItem,"must not be null");
        return checkNotFoundWithId(wfBaseProcessItemRepository.save(wfBaseProcessItem),wfBaseProcessItem.getId());
    }

    @Override
    public WFBaseProcessItem get(int id) throws NotFoundException {
        //Проверка - не найден
        return checkNotFoundWithId(wfBaseProcessItemRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(wfBaseProcessItemRepository.delete(id)),id);
    }

    @Override
    public List<WFBaseProcessItem> getAll() {
        return wfBaseProcessItemRepository.getAll();
    }

    @Override
    public List<WFBaseProcessItem> filter(WFBaseProcessItem wfBaseProcessItem) {
        Assert.notNull(wfBaseProcessItem,"must not be null");
        return wfBaseProcessItemRepository.filter(wfBaseProcessItem);
    }

    @Override
    public List<WFProcessState> getListTransferWFProcessState(int processStateFromId, int baseProcessId) {
        return wfBaseProcessItemRepository.getListTransferWFProcessState(processStateFromId,baseProcessId);
    }
}

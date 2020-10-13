package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.repository.workflow.WFBaseProcessRepository;
import org.speed.big.company.service.service.workflow.WFBaseProcessService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("wfBaseProcessServiceImpl")
public class WFBaseProcessServiceImpl implements WFBaseProcessService {

    private WFBaseProcessRepository wfBaseProcessRepository;

    @Autowired
    public WFBaseProcessServiceImpl(@Qualifier("jdbcWFBaseProcessRepositoryImpl") WFBaseProcessRepository wfBaseProcessRepository) {
        this.wfBaseProcessRepository = wfBaseProcessRepository;
    }

    @Override
    public WFBaseProcess create(WFBaseProcess wfBaseProcess) {
        Assert.notNull(wfBaseProcess,"must not be null");
        return wfBaseProcessRepository.save(wfBaseProcess);
    }

    @Override
    public WFBaseProcess update(WFBaseProcess wfBaseProcess) throws NotFoundException {
        Assert.notNull(wfBaseProcess,"must not be null");
        return checkNotFoundWithId(wfBaseProcessRepository.save(wfBaseProcess),wfBaseProcess.getId());
    }

    @Override
    public WFBaseProcess get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(wfBaseProcessRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(wfBaseProcessRepository.delete(id)),id);
    }

    @Override
    public List<WFBaseProcess> getAll() {
        return wfBaseProcessRepository.getAll();
    }

    @Override
    public List<WFBaseProcess> filter(WFBaseProcess wfBaseProcess) {
        Assert.notNull(wfBaseProcess,"must not be null");
        return wfBaseProcessRepository.filter(wfBaseProcess);
    }
}

package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.workflow.WFProcess;
import org.speed.big.company.service.repository.workflow.WFProcessRepository;
import org.speed.big.company.service.service.workflow.WFProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("wfProcessServiceImpl")
public class WFProcessServiceImpl implements WFProcessService {

    private WFProcessRepository wfProcessRepository;

    //@Autowired
    public WFProcessServiceImpl(@Qualifier("jdbcWFProcessRepositoryImpl") WFProcessRepository wfProcessRepository) {
        this.wfProcessRepository = wfProcessRepository;
    }

    @Override
    public WFProcess create(WFProcess wfProcess) {
        Assert.notNull(wfProcess,"не должно быть null");
        return wfProcessRepository.save(wfProcess);
    }

    @Override
    public WFProcess update(WFProcess wfProcess) {
        Assert.notNull(wfProcess,"не должно быть null");
        return checkNotFoundWithId(wfProcessRepository.save(wfProcess), wfProcess.getId());
    }

    @Override
    public WFProcess get(int id) {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(wfProcessRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) {
        return checkNotFoundWithId(Boolean.valueOf(wfProcessRepository.delete(id)),id);
    }

    @Override
    public List<WFProcess> getAll() {
        return wfProcessRepository.getAll();
    }

    @Override
    public List<WFProcess> filter(WFProcess wfProcess) {
        Assert.notNull(wfProcess,"не должно быть null");
        return filter(wfProcess,null);
    }

    @Override
    public List<WFProcess> filter(WFProcess wfProcess, String sqlCondition) {
        return wfProcessRepository.filter(wfProcess,sqlCondition);
    }

    @Override
    public List<WFProcess> getListWFProcess(int wfServiceId, int wfProcessStatusId) {
        return wfProcessRepository.getListWFProcess(wfServiceId,wfProcessStatusId);
    }
}

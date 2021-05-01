package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.speed.big.company.service.repository.workflow.WFPackageStatusRepository;
import org.speed.big.company.service.service.workflow.WFPackageStatusService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("wfPackageStatusServiceImpl")
public class WFPackageStatusServiceImpl implements WFPackageStatusService {

    private WFPackageStatusRepository wfPackageStatusRepository;

    @Autowired
    public WFPackageStatusServiceImpl(@Qualifier("dataWFPackageStatusRepositoryImpl") WFPackageStatusRepository wfPackageStatusRepository) {
        this.wfPackageStatusRepository = wfPackageStatusRepository;
    }

    @Override
    public WFPackageStatus create(WFPackageStatus wfPackageStatus) {
        Assert.notNull(wfPackageStatus,"не должно быть null");
        return wfPackageStatusRepository.save(wfPackageStatus);
    }

    @Override
    public WFPackageStatus update(WFPackageStatus wfPackageStatus) throws NotFoundException {
        Assert.notNull(wfPackageStatus,"не должно быть null");
        return checkNotFoundWithId(wfPackageStatusRepository.save(wfPackageStatus),wfPackageStatus.getId());
    }

    @Override
    public WFPackageStatus get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(wfPackageStatusRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(wfPackageStatusRepository.delete(id)),id);
    }

    @Override
    public List<WFPackageStatus> getAll() {
        return wfPackageStatusRepository.getAll();
    }

    @Override
    public List<WFPackageStatus> filter(WFPackageStatus wfPackageStatus) {
        Assert.notNull(wfPackageStatus,"не должно быть null");
        return wfPackageStatusRepository.filter(wfPackageStatus);
    }
}

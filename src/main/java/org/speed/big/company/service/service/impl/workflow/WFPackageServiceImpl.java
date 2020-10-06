package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.repository.workflow.WFPackageRepository;
import org.speed.big.company.service.service.workflow.WFPackageService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("wfPackageServiceImpl")
public class WFPackageServiceImpl implements WFPackageService {

    private WFPackageRepository wfPackageRepository;

    @Autowired
    public WFPackageServiceImpl(@Qualifier("jdbcWFPackageRepositoryImpl") WFPackageRepository wfPackageRepository) {
        this.wfPackageRepository = wfPackageRepository;
    }

    @Override
    public WFPackage save(WFPackage wfPackage) {
        Assert.notNull(wfPackage,"не должно быть null");
        return wfPackageRepository.save(wfPackage);
    }

    @Override
    public WFPackage update(WFPackage wfPackage) throws NotFoundException {
        Assert.notNull(wfPackage,"не должно быть null");
        return checkNotFoundWithId(wfPackageRepository.save(wfPackage), wfPackage.getId());
    }

    @Override
    public WFPackage get(int id) throws NotFoundException {
        return checkNotFoundWithId(wfPackageRepository.get(id), id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(wfPackageRepository.delete(id)), id);
    }

    @Override
    public List<WFPackage> getAll() {
        return wfPackageRepository.getAll();
    }

    @Override
    public List<WFPackage> filter(WFPackage wfPackage) {
        Assert.notNull(wfPackage,"не должно быть null");
        return wfPackageRepository.filter(wfPackage);
    }
}

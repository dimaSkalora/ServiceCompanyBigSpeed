package org.speed.big.company.service.service.impl.workflow;

import org.speed.big.company.service.model.workflow.WFProcessMovement;
import org.speed.big.company.service.repository.workflow.WFProcessMovementRepository;
import org.speed.big.company.service.service.workflow.WFProcessMovementService;
import org.speed.big.company.service.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.speed.big.company.service.util.ValidationUtil.checkNotFoundWithId;

@Service("wfProcessMovementServiceImpl")
public class WFProcessMovementServiceImpl implements WFProcessMovementService {

    private WFProcessMovementRepository wfProcessMovementRepository;

    public WFProcessMovementServiceImpl(@Qualifier("jdbcWFProcessMovementRepositoryImpl") WFProcessMovementRepository wfProcessMovementRepository) {
        this.wfProcessMovementRepository = wfProcessMovementRepository;
    }

    @Override
    public WFProcessMovement create(WFProcessMovement wfProcessMovement) {
        Assert.notNull(wfProcessMovement,"must not be null");
        return wfProcessMovementRepository.save(wfProcessMovement);
    }

    @Override
    public WFProcessMovement update(WFProcessMovement wfProcessMovement) throws NotFoundException {
        Assert.notNull(wfProcessMovement,"must not be null");
        return checkNotFoundWithId(wfProcessMovementRepository.save(wfProcessMovement),wfProcessMovement.getId());
    }

    @Override
    public WFProcessMovement get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(wfProcessMovementRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(wfProcessMovementRepository.delete(id)),id);
    }

    @Override
    public List<WFProcessMovement> getAll() {
        return wfProcessMovementRepository.getAll();
    }

    @Override
    public List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement) {
        Assert.notNull(wfProcessMovement, "must not be null");
        return this.filter(wfProcessMovement,null);
    }

    @Override
    public List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement, String sqlCondition) {
        Assert.notNull(wfProcessMovement,"must not be null");
        return wfProcessMovementRepository.filter(wfProcessMovement,sqlCondition);
    }

    @Override
    public List<WFProcessMovement> getListWFProcessMovement(int roleId, int wfServiceId, int processStatus, boolean isCompleted, boolean isLast) {
        return wfProcessMovementRepository.getListWFProcessMovement(roleId,wfServiceId,processStatus,isCompleted,isLast);
    }

    @Override
    public List<WFProcessMovement> getListWFPMByProcessAndBaseProcess(int wfProcessId, int wfBaseProcessId) {
        return wfProcessMovementRepository.getListWFPMByProcessAndBaseProcess(wfProcessId,wfBaseProcessId);
    }

    @Override
    public int currentStateIdOfWFProcessMovementById(int id) {
        return wfProcessMovementRepository.currentStateIdOfWFProcessMovementById(id);
    }

    @Override
    public int currentStateIdOfWFProcessMovement(int wfPackageId, int wfProcessId, int wfBaseProcessId) {
        return currentStateIdOfWFProcessMovement(wfPackageId,wfProcessId,wfBaseProcessId);
    }
}

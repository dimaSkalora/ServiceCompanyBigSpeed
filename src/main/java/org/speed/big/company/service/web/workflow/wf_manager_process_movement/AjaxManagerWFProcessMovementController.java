package org.speed.big.company.service.web.workflow.wf_manager_process_movement;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.model.workflow.WFProcess;
import org.speed.big.company.service.model.workflow.WFProcessMovement;
import org.speed.big.company.service.model.workflow.WFService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AjaxManagerWFProcessMovementController.REST_URL)
public class AjaxManagerWFProcessMovementController extends AbstractManagerWFProcessMovementController{
    final static String REST_URL = "/ajax/workflow/managerWFProcessMovements";

    /**
     * Получить список ролей которые есть у юзера
     * produces - Какой формат отправляем клиенту
     *
     * @return
     */
    @GetMapping(value = "/rolesFromUserRoles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> rolesFromUserRoles(){
        return super.getRoleFromUserRoles();
    }

    /**
     * Получить список сервисов которые есть у юзера
     * produces - Какой формат отправляем клиенту
     *
     * @return
     */
    @GetMapping(value = "/wfServicesFromUserRoles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFService> wfServicesFromUserRoles(){
        return super.getWFServiceFromRoles();
    }

    /**
     * Получаем список движения по ролях, сервисах
     * и статуса процесса: (В работе, Ожидание)
     * produces - Какой формат отправляем клиенту
     *
     * @param roleId            - Id Роль
     * @param wfServiceId       - Id Послуи
     * @param indexProcessStatus - Индекс статуса процесса (В работе, Ожидание)
     */
    @GetMapping(value = "/processMovements/{roleId}/{wfServiceId}/{indexProcessStatus}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcessMovement> processMovements(@PathVariable int roleId,
                                                                    @PathVariable int wfServiceId,
                                                                    @PathVariable int indexProcessStatus){
        return super.getListWFProcessMovement(roleId,wfServiceId, indexProcessStatus);
    }

    /**
     * Получаем список движения по сервисах
     * и статуса процесса: (Завершен, Архив)
     * produces - Какой формат отправляем клиенту
     *
     * @param wfServiceId       - Id Послуи
     * @param indexProcessStatus - Индекс статуса процесса (Завершен, Архив)
     */
    @GetMapping(value = "/processMovements/{wfServiceId}/{indexProcessStatus}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcess> processes(@PathVariable int wfServiceId,
                                                     @PathVariable int indexProcessStatus){
        return  super.getListWFProcess(wfServiceId,indexProcessStatus);
    }

    /**
     * Получить пакет по текущему движению процесса
     *
     * @param wfPackId - Id пакета по текущему движению процесса
     * @return
     */
    @GetMapping(value = "/wfPackageOfProcessMovement/{wfPackId}/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WFPackage wfPackageOfProcessMovement(@PathVariable int wfPackId){
        return super.getWFPackageOfProcessMovement(wfPackId);
    }

    /**
     * Получить процес по текущему движению процесса
     *
     * @param wfProcessId - Id процеса по текущему движению процесса
     * @return
     */
    @GetMapping(value = "/wfProcessOfProcessMovement/{wfProcessId}/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WFProcess wfProcessOfProcessMovement(@PathVariable int wfProcessId){
        return  super.getWFProcessById(wfProcessId);
    }

    /**
     * Получаем список процесов по пакету
     * produces - Какой формат отправляем клиенту
     *
     * @param wfPackId - Id пакета по текущему движению процесса
     */
    @GetMapping(value = "/allWFProcessByWFPackageId/{wfPackId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcess> allWFProcessByWFPackageId(@PathVariable int wfPackId){
        return super.getAllWFProcessByWFPackageId(wfPackId);
    }

    /**
     * Получаем список движения по процессу, базового процесса
     * produces - Какой формат отправляем клиенту
     *
     * @param wfProcessId            - Id процесса
     * @param wfBaseProcessId       - Id базового процесса
     */
    @GetMapping(value = "/processMovementsByProcessIdByBaseProcess/{wfProcessId}/{wfBaseProcessId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcessMovement> processMovementsByProcessIdByBaseProcessId(@PathVariable int wfProcessId,
                                                                                              @PathVariable int wfBaseProcessId){
        return super.getListWFPMByProcessAndBaseProcess(wfProcessId,wfBaseProcessId);
    }
}

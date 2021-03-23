package org.speed.big.company.service.web.workflow.wf_manager_process_movement;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.model.workflow.WFProcess;
import org.speed.big.company.service.model.workflow.WFProcessMovement;
import org.speed.big.company.service.model.workflow.WFService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestManagerWFProcessMovementController.REST_URL)
public class RestManagerWFProcessMovementController extends AbstractManagerWFProcessMovementController{
    final static String REST_URL = "/rest/workflow/managerWFProcessMovements";

    /**
     * Получить список ролей которые есть у юзера
     * produces - Какой формат отправляем клиенту
     *
     * @return
     */
    @GetMapping(value = "/rolesFromUserRoles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> rolesFromUserRoles(){
        List<Role> listRolesFromUserRoles = super.getRoleFromUserRoles();

        return listRolesFromUserRoles != null
                ? new ResponseEntity<>(listRolesFromUserRoles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Получить список сервисов которые есть у юзера
     * produces - Какой формат отправляем клиенту
     *
     * @return
     */
    @GetMapping(value = "/wfServicesFromUserRoles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFService>> wfServicesFromUserRoles(){
        List<WFService> listWFServicesFromUserRoles = super.getWFServiceFromRoles();

        return listWFServicesFromUserRoles != null
                ? new ResponseEntity<>(listWFServicesFromUserRoles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<List<WFProcessMovement>> processMovements(@PathVariable int roleId,
                                                                    @PathVariable int wfServiceId,
                                                                    @PathVariable int indexProcessStatus){
        List<WFProcessMovement> wfProcessMovementList = super.getListWFProcessMovement(roleId,wfServiceId, indexProcessStatus);

        return wfProcessMovementList != null
                ? new ResponseEntity<>(wfProcessMovementList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<List<WFProcess>> processes(@PathVariable int wfServiceId,
                                                     @PathVariable int indexProcessStatus){
        List<WFProcess> wfProcessList = super.getListWFProcess(wfServiceId,indexProcessStatus);

        return wfProcessList != null
                ? new ResponseEntity<>(wfProcessList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Получить пакет по текущему движению процесса
     *
     * @param wfPackId - Id пакета по текущему движению процесса
     * @return
     */
    @GetMapping(value = "/wfPackageOfProcessMovement/{wfPackId}/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFPackage> wfPackageOfProcessMovement(@PathVariable int wfPackId){
        WFPackage wfPackage = super.getWFPackageOfProcessMovement(wfPackId);

        return wfPackage != null
                ? new ResponseEntity<>(wfPackage, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Получить процес по текущему движению процесса
     *
     * @param wfProcessId - Id процеса по текущему движению процесса
     * @return
     */
    @GetMapping(value = "/wfProcessOfProcessMovement/{wfProcessId}/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFProcess> wfProcessOfProcessMovement(@PathVariable int wfProcessId){
        WFProcess wfProcess = super.getWFProcessById(wfProcessId);

        return wfProcess != null
                ? new ResponseEntity<>(wfProcess, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Получаем список процесов по пакету
     * produces - Какой формат отправляем клиенту
     *
     * @param wfPackId - Id пакета по текущему движению процесса
     */
    @GetMapping(value = "/allWFProcessByWFPackageId/{wfPackId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFProcess>> allWFProcessByWFPackageId(@PathVariable int wfPackId){
        List<WFProcess> wfProcessList = super.getAllWFProcessByWFPackageId(wfPackId);

        return wfProcessList != null
                ? new ResponseEntity<>(wfProcessList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<List<WFProcessMovement>> processMovementsByProcessIdByBaseProcessId(@PathVariable int wfProcessId,
                                                                    @PathVariable int wfBaseProcessId){
        List<WFProcessMovement> wfProcessMovementsByProcessIdByBaseProcessId =
                super.getListWFPMByProcessAndBaseProcess(wfProcessId,wfBaseProcessId);

        return wfProcessMovementsByProcessIdByBaseProcessId != null
                ? new ResponseEntity<>(wfProcessMovementsByProcessIdByBaseProcessId, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

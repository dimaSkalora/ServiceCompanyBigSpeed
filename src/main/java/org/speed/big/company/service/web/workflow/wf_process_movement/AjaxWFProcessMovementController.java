package org.speed.big.company.service.web.workflow.wf_process_movement;

import org.speed.big.company.service.model.workflow.WFProcessMovement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxWFProcessMovementController.REST_URL)
public class AjaxWFProcessMovementController  extends AbstractWFProcessMovementController{
    final static String REST_URL = "/ajax/workflow/wfProcessMovements";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcessMovement> wfProcessMovements(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public WFProcessMovement wfProcessMovement(@PathVariable int id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createWithLocation(WFProcessMovement wfProcessMovement){
        if (wfProcessMovement.isNew())
            super.create(wfProcessMovement);
        else
            super.update(wfProcessMovement);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteWFPTM(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcessMovement> filterWFPM(WFProcessMovement wfProcessMovement){
        return super.filter(wfProcessMovement);
    }

}

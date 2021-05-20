package org.speed.big.company.service.web.workflow.wf_process_status;

import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxWFProcessStatusController.REST_URL)
public class AjaxWFProcessStatusController extends AbstractWFProcessStatusController{
    final static String REST_URL = "/ajax/workflow/wfProcessStatuses";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcessStatus> wfProcessStatuses(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public WFProcessStatus wfProcessStatus(@PathVariable int id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(WFProcessStatus wfProcessStatus){
        if (wfProcessStatus.isNew())
            super.create(wfProcessStatus);
        else
            super.update(wfProcessStatus, wfProcessStatus.getId());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteWFPS(@PathVariable int id){
        super.delete(id);
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcessStatus> filterWFPS(@RequestBody WFProcessStatus wfProcessStatus){
        return super.filter(wfProcessStatus);
    }
}

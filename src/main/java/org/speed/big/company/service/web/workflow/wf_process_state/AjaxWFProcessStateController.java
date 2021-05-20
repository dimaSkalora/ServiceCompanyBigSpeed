package org.speed.big.company.service.web.workflow.wf_process_state;

import org.speed.big.company.service.model.workflow.WFProcessState;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxWFProcessStateController.REST_URL)
public class AjaxWFProcessStateController  extends AbstractWFProcessStateController{
    final static String REST_URL = "/ajax/workflow/wfProcessStates";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcessState> wfProcessStates(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public WFProcessState wfProcessState(@PathVariable int id){
        return  super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(WFProcessState wfProcessState){
        if (wfProcessState.isNew())
            super.create(wfProcessState);
        else
            super.update(wfProcessState, wfProcessState.getId());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteWFPS(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcessState> filterWFPS(WFProcessState wfProcessState){
        return super.filter(wfProcessState);
    }
}

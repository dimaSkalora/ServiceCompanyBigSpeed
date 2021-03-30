package org.speed.big.company.service.web.workflow.wf_process;

import org.speed.big.company.service.model.workflow.WFProcess;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxWFProcessController.REST_URL)
public class AjaxWFProcessController extends AbstractWFProcessController{
    final static String REST_URL = "/ajax/workflow/wfProcesses";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcess> wfProcesses(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public WFProcess getWFP(@PathVariable int id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(WFProcess wfProcess){
        if (wfProcess.isNew())
            super.create(wfProcess);
        else
            super.update(wfProcess);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteWFP(@PathVariable int id){
        super.delete(id);
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFProcess> filterWFP(@RequestBody WFProcess wfProcess){
        return super.filter(wfProcess);
    }
}

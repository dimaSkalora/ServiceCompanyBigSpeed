package org.speed.big.company.service.web.workflow.wf_base_process;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxWFBaseProcessController.REST_URL)
public class AjaxWFBaseProcessController extends AbstractWFBaseProcessController{
    final static String REST_URL = "/ajax/workflow/wfBaseProcesses";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFBaseProcess> wfBaseProcesses(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public WFBaseProcess getWFBP(@PathVariable int id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<WFBaseProcess> createOrUpdate(WFBaseProcess wfBaseProcess){
        if (wfBaseProcess.isNew())
            super.create(wfBaseProcess);
        else
            super.update(wfBaseProcess, wfBaseProcess.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteWFBP(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFBaseProcess> filterWFBP(@RequestBody WFBaseProcess wfBaseProcess){
        return super.filter(wfBaseProcess);
    }
}

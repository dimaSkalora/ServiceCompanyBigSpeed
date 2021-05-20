package org.speed.big.company.service.web.workflow.wf_base_process_type;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxWFBaseProcessTypeController.REST_URL)
public class AjaxWFBaseProcessTypeController extends AbstractWFBaseProcessTypeController{
    final static String REST_URL = "/ajax/workflow/wfBaseProcessTypes";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFBaseProcessType> wfBaseProcessTypes(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public WFBaseProcessType getWFBPT(@PathVariable int id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(WFBaseProcessType wfBaseProcessType){
        if (wfBaseProcessType.isNew())
            super.create(wfBaseProcessType);
        else
            super.update(wfBaseProcessType, wfBaseProcessType.getId());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteWFBPT(int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFBaseProcessType> filterWFBPT(WFBaseProcessType wfBaseProcessType){
        return super.filter(wfBaseProcessType);
    }
}

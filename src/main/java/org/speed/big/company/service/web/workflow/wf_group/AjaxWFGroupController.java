package org.speed.big.company.service.web.workflow.wf_group;

import org.speed.big.company.service.model.workflow.WFGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxWFGroupController.REST_URL)
public class AjaxWFGroupController extends AbstractWFGroupController{
    final static String REST_URL = "/ajax/workflow/wfGroups";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFGroup> wfGroups(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public WFGroup wfGroup(@PathVariable int id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(WFGroup wfGroup){
        if (wfGroup.isNew())
            super.create(wfGroup);
        else
            super.update(wfGroup);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteWFG(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFGroup> filterWFG( WFGroup wfGroup){
        return super.filter(wfGroup);
    }
}


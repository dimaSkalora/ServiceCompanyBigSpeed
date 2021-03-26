package org.speed.big.company.service.web.workflow.wf_service;

import org.speed.big.company.service.model.workflow.WFService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxWFServiceController.REST_URL)
public class AjaxWFServiceController extends AbstractWFServiceController{
    static final String REST_URL = "/ajax/workflow/wfServices";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFService> getAll(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WFService getWFS(@PathVariable int id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(WFService wfService){
        if (wfService.isNew())
            super.create(wfService);
        else
            super.update(wfService);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteWFS(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFService> filterWFS(WFService wfService){
        return super.filter(wfService);
    }
}

package org.speed.big.company.service.web.workflow.wf_package;

import org.speed.big.company.service.model.workflow.WFPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxWFPackageController.REST_URL)
public class AjaxWFPackageController extends AbstractWFPackageController{
    final static String REST_URL = "/ajax/workflow/wfPackages";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFPackage> wfPackages(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WFPackage getWFP(@PathVariable int id){
        return  super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(WFPackage wfPackage){
        if (wfPackage.isNew())
            super.create(wfPackage);
        else
            super.update(wfPackage, wfPackage.getId());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteWFP(int id){
        super.delete(id);
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFPackage> filterWFP( WFPackage wfPackage){
        return super.filter(wfPackage);
    }
}

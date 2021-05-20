package org.speed.big.company.service.web.workflow.wf_package_status;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxWFPackageStatusController.REST_URL)
public class AjaxWFPackageStatusController extends AbstractWFPackageStatusController{
    static final String REST_URL="/ajax/workflow/wfPackageStatuses";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFPackageStatus> getAll(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WFPackageStatus getWFPS(@PathVariable int id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(WFPackageStatus wfPackageStatus){
        if (wfPackageStatus.isNew())
            super.create(wfPackageStatus);
        else
            super.update(wfPackageStatus, wfPackageStatus.getId());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteWFPS(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFPackageStatus> filterWFPS( WFPackageStatus wfPackageStatus){
        return super.filter(wfPackageStatus);
    }
}

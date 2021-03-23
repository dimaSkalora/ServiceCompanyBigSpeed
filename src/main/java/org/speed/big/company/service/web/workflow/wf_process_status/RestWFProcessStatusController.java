package org.speed.big.company.service.web.workflow.wf_process_status;

import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(RestWFProcessStatusController.REST_URL)
public class RestWFProcessStatusController extends AbstractWFProcessStatusController{
    final static String REST_URL = "/rest/workflow/wfProcessStatuses";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFProcessStatus>> wfProcessStatuses(){
        List<WFProcessStatus> list = super.getAll();

        return list != null
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFProcessStatus> wfProcessStatus(@PathVariable int id){
        WFProcessStatus wfProcessStatus = super.get(id);

        return wfProcessStatus != null
                ? new ResponseEntity<>(wfProcessStatus,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFProcessStatus> createWithLocation(@RequestBody WFProcessStatus wfProcessStatus){
        WFProcessStatus created = super.create(wfProcessStatus);

        URI uriOfResources = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"{id}")
                .buildAndExpand(wfProcessStatus.getId()).toUri();
        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);
        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfResources).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFProcessStatus> updateWFPS(@RequestBody WFProcessStatus wfProcessStatus){
        WFProcessStatus updated = super.update(wfProcessStatus);

        return updated != null
                ? new ResponseEntity<>(updated,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteWFPS(@PathVariable int id){
        super.delete(id);
    }

    @GetMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFProcessStatus>> filterWFPS(@RequestBody WFProcessStatus wfProcessStatus){
        List<WFProcessStatus> list = super.filter(wfProcessStatus);

        return list != null
                ? new ResponseEntity<>(list,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

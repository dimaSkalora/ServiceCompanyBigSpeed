package org.speed.big.company.service.web.workflow.wf_base_process_type;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(RestWFBaseProcessTypeController.REST_URL)
public class RestWFBaseProcessTypeController extends AbstractWFBaseProcessTypeController{
    final static String REST_URL = "/rest/workflow/wfBaseProcessTypes";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFBaseProcessType> wfBaseProcessTypes(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFBaseProcessType> getWFBPT(@PathVariable int id){
        WFBaseProcessType wfBaseProcessType = super.get(id);

        return wfBaseProcessType != null
                ? new ResponseEntity<>(wfBaseProcessType, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFBaseProcessType> createWithLocation(@RequestBody WFBaseProcessType wfBaseProcessType){
        WFBaseProcessType created = super.create(wfBaseProcessType);

        URI uriOfResources = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"{id}")
                .buildAndExpand(wfBaseProcessType.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);
        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfResources).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFBaseProcessType> updateWFBPT(@RequestBody WFBaseProcessType wfBaseProcessType){
        WFBaseProcessType updated = super.update(wfBaseProcessType);

        return updated != null
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteWFBPT(int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE
                ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFBaseProcessType>> filterWFBPT(@RequestBody WFBaseProcessType wfBaseProcessType){
        List<WFBaseProcessType> list = super.filter(wfBaseProcessType);

        return list != null
                ? new ResponseEntity<>(list,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}

package org.speed.big.company.service.web.workflow.wf_base_process_item;

import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(RestWFBaseProcessItemController.REST_URL)
public class RestWFBaseProcessItemController extends AbstractWFBaseProcessItemController{
    final static String REST_URL = "/rest/workflow/wfBaseProcessItems";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFBaseProcessItem>> wfBaseProcessItems(){
        List<WFBaseProcessItem> list = super.getAll();

        return list != null
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFBaseProcessItem> wfBaseProcessItem(@PathVariable int id){
        WFBaseProcessItem wfBaseProcessItem = super.get(id);

        return wfBaseProcessItem != null
                ? new ResponseEntity<>(wfBaseProcessItem,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFBaseProcessItem> createWithLocation(@RequestBody WFBaseProcessItem wfBaseProcessItem){
        WFBaseProcessItem created = super.create(wfBaseProcessItem);

        URI uriOfResources = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"{id}")
                .buildAndExpand(wfBaseProcessItem.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);

        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfResources).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFBaseProcessItem> updateWFBPI(@RequestBody WFBaseProcessItem wfBaseProcessItem){
        WFBaseProcessItem updated = super.update(wfBaseProcessItem);

        return updated != null
                ? new ResponseEntity<>(updated,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteWFBPI(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFBaseProcessItem>> filterWFBPI(@RequestBody WFBaseProcessItem wfBaseProcessItem){
        List<WFBaseProcessItem> list = super.filter(wfBaseProcessItem);

        return list != null
                ? new ResponseEntity<>(list,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

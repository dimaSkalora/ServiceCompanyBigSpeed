package org.speed.big.company.service.web.workflow.wf_group;

import org.speed.big.company.service.model.workflow.WFGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(RestWFGroupController.REST_URL)
public class RestWFGroupController extends AbstractWFGroupController{
    final static String REST_URL = "/rest/workflow/wfGroups";

    //produces - Какой формат отправляем клиенту
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFGroup>> wfGroups(){
        List<WFGroup> list = super.getAll();

        return list != null
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFGroup> wfGroup(@PathVariable int id){
        WFGroup wfGroup = super.get(id);

        return wfGroup != null
                ? new ResponseEntity<>(wfGroup,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //consumes - Какой формат получаем(от клиента)
    //produces - Какой формат отправляем клиенту
    //RequestBody - Аннотации, указывающие параметр метода, должны быть привязаны к телу веб-запроса.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFGroup> createWithLocation(@RequestBody WFGroup wfGroup){
        WFGroup created = super.create(wfGroup);

        URI uriOfResources = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"{id}")
                .buildAndExpand(wfGroup.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);

        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfResources).body(created);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFGroup> updateWFG(@RequestBody WFGroup wfGroup){
        WFGroup updated = super.update(wfGroup);

        return wfGroup != null
                ? new ResponseEntity<>(wfGroup,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteWFG(@PathVariable int id){
        super.delete(id);
    }

    //consumes - Какой формат получаем(от клиента)
    //produces - Какой формат отправляем клиенту
    //RequestBody - Аннотации, указывающие параметр метода, должны быть привязаны к телу веб-запроса.
    @PostMapping(value = "/filter",consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFGroup>> filterWFG(@RequestBody WFGroup wfGroup){
        List<WFGroup> list = super.filter(wfGroup);

        return list != null
                ? new ResponseEntity<>(list,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

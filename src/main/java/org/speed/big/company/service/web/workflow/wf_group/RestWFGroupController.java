package org.speed.big.company.service.web.workflow.wf_group;

import org.speed.big.company.service.model.workflow.WFGroup;
import org.speed.big.company.service.util.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(RestWFGroupController.REST_URL)
public class RestWFGroupController extends AbstractWFGroupController{
    final static String REST_URL = "/rest/workflow/wfGroups";

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFGroup> createWithLocation(@Valid @RequestBody WFGroup wfGroup){
        WFGroup created = super.create(wfGroup);

        URI uriOfResources = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"{id}")
                .buildAndExpand(wfGroup.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);

        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfResources).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFGroup> updateWFG(@Valid @RequestBody WFGroup wfGroup, @PathVariable("id") int id){
        WFGroup updated = super.update(wfGroup, id);

        return wfGroup != null
                ? new ResponseEntity<>(wfGroup,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteWFG(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter",consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFGroup>> filterWFG(@RequestBody WFGroup wfGroup){
        List<WFGroup> list = super.filter(wfGroup);

        return list != null
                ? new ResponseEntity<>(list,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

/*    //Обработка для @Valid(если не проходить валидацию то
    //выбрасывает MethodArgumentNotValidException)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ValidationUtil.handlerValidationExceptions(ex);
    }*/
}

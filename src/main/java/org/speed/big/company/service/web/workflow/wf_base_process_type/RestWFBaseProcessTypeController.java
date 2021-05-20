package org.speed.big.company.service.web.workflow.wf_base_process_type;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
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
    public ResponseEntity<WFBaseProcessType> createWithLocation(@Valid @RequestBody WFBaseProcessType wfBaseProcessType){
        WFBaseProcessType created = super.create(wfBaseProcessType);

        URI uriOfResources = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"{id}")
                .buildAndExpand(wfBaseProcessType.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);
        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfResources).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFBaseProcessType> updateWFBPT(@Valid @RequestBody WFBaseProcessType wfBaseProcessType, @PathVariable("id") int id){
        WFBaseProcessType updated = super.update(wfBaseProcessType, id);

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

/*    //Обработка для @Valid(если не проходить валидацию то
    //выбрасывает MethodArgumentNotValidException)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ValidationUtil.handlerValidationExceptions(ex);
    }*/

}

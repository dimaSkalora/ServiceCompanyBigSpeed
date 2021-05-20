package org.speed.big.company.service.web.workflow.wf_base_process;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
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
@RequestMapping(RestWFBaseProcessController.REST_URL)
public class RestWFBaseProcessController extends AbstractWFBaseProcessController{
    final static String REST_URL = "/rest/workflow/wfBaseProcesses";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFBaseProcess>> wfBaseProcesses(){
        List<WFBaseProcess> list = super.getAll();

        return list != null
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFBaseProcess> getWFBP(@PathVariable int id){
        WFBaseProcess wfBaseProcess = super.get(id);

        return wfBaseProcess != null
                ? new ResponseEntity<>(wfBaseProcess,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFBaseProcess> createWithLocation(@Valid @RequestBody WFBaseProcess wfBaseProcess){
        WFBaseProcess created = super.create(wfBaseProcess);

        URI uriOfResources = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"{id}")
                .buildAndExpand(wfBaseProcess.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);
        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfResources).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFBaseProcess> updateWFBP(@Valid @RequestBody WFBaseProcess wfBaseProcess, @PathVariable("id") int id){
        WFBaseProcess updated = super.update(wfBaseProcess, id);

        return updated != null
                ? new ResponseEntity<>(updated,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteWFBP(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFBaseProcess>> filterWFBP(@RequestBody WFBaseProcess wfBaseProcess){
        List<WFBaseProcess> list = super.filter(wfBaseProcess);

        return list != null
                ? new ResponseEntity<>(list,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Обработка для @Valid(если не проходить валидацию то
    //выбрасывает MethodArgumentNotValidException)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ValidationUtil.handlerValidationExceptions(ex);
    }
}

package org.speed.big.company.service.web.workflow.wf_process;

import org.speed.big.company.service.model.workflow.WFProcess;
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
@RequestMapping(RestWFProcessController.REST_URL)
public class RestWFProcessController extends AbstractWFProcessController{
    final static String REST_URL = "/rest/workflow/wfProcesses";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFProcess>> wfProcesses(){
        List<WFProcess> list = super.getAll();

        return list != null
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFProcess> getWFP(@PathVariable int id){
        WFProcess wfProcess = super.get(id);

        return wfProcess != null
                ? new ResponseEntity<>(wfProcess,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFProcess> createWithLocation(@Valid @RequestBody WFProcess wfProcess){
        WFProcess created = super.create(wfProcess);

        URI uriOfResources = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"{id}")
                .buildAndExpand(wfProcess.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);

        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfResources).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFProcess> updateWFP(@Valid @RequestBody WFProcess wfProcess){
        WFProcess updated = super.update(wfProcess);

        return updated != null
                ? new ResponseEntity<>(updated,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteWFP(@PathVariable int id){
        super.delete(id);
    }

    @GetMapping(value = "/filter",consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFProcess>> filterWFP(@RequestBody WFProcess wfProcess){
        List<WFProcess> list = super.filter(wfProcess);

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

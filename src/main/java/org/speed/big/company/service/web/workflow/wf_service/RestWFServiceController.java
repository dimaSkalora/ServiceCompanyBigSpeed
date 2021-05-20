package org.speed.big.company.service.web.workflow.wf_service;

import org.speed.big.company.service.model.workflow.WFService;
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
@RequestMapping(RestWFServiceController.REST_URL)
public class RestWFServiceController extends AbstractWFServiceController{
    static final String REST_URL = "/rest/workflow/wfServices";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFService> getAll(){
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFService> getWFS(@PathVariable int id){
        WFService wfService = super.get(id);

        return wfService != null
                ? new ResponseEntity<>(wfService, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFService> createWithLocation(@Valid @RequestBody WFService wfService){
        WFService created = super.create(wfService);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"{id}")
                .buildAndExpand(wfService.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);
        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFService> updateWFS(@Valid@RequestBody WFService wfService, @PathVariable("id") int id){
        WFService update = super.update(wfService, id);

        return update != null
                ? new ResponseEntity<>(update, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteWFS(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFService>> filterWFS(@RequestBody WFService wfService){
        List<WFService> list = super.filter(wfService);

        return list != null
                ? new ResponseEntity<>(list, HttpStatus.OK)
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

package org.speed.big.company.service.web.workflow.wf_package_status;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
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
@RequestMapping(RestWFPackageStatusController.REST_URL)
public class RestWFPackageStatusController extends AbstractWFPackageStatusController{
    static final String REST_URL="/rest/workflow/wfPackageStatuses";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFPackageStatus> getAll(){
        return super.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WFPackageStatus> getWFPS(@PathVariable int id){
        WFPackageStatus wfPackageStatus = super.get(id);

        return wfPackageStatus != null
                ? new ResponseEntity<>(wfPackageStatus, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFPackageStatus> createWithLocation(@Valid @RequestBody WFPackageStatus wfPackageStatus){
        WFPackageStatus created = super.create(wfPackageStatus);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"{id}")
                .buildAndExpand(wfPackageStatus.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);
        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFPackageStatus> updateWFPS(@Valid @RequestBody WFPackageStatus wfPackageStatus, @PathVariable("id") int id){
        WFPackageStatus updateWFPS = super.update(wfPackageStatus, id);

        return updateWFPS != null
                ? new ResponseEntity<>(updateWFPS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteWFPS(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter"
            ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFPackageStatus>> filterWFPS(@RequestBody WFPackageStatus wfPackageStatus){
        List<WFPackageStatus> list = super.filter(wfPackageStatus);

        return list != null
                ? new ResponseEntity<>(list,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

/*    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerValidationExceptions(MethodArgumentNotValidException ex) {
        return ValidationUtil.handlerValidationExceptions(ex);
    }*/
}

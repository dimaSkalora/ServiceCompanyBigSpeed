package org.speed.big.company.service.web.workflow.wf_package_status;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(RestWFPackageStatusController.REST_URL)
public class RestWFPackageStatusController extends AbstractWFPackageStatusController{
    static final String REST_URL="/rest/workflow/wfPackageStatuses";

    //produces - Какой формат отправляем клиенту
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WFPackageStatus> getAll(){
        return super.getAll();
    }

    //@PathVariable - Аннотации, указывающие, что параметр метода должен быть привязан к переменной шаблона UR
    @GetMapping("/{id}")
    public ResponseEntity<WFPackageStatus> getWFPS(@PathVariable int id){
        WFPackageStatus wfPackageStatus = super.get(id);

        return wfPackageStatus != null
                ? new ResponseEntity<>(wfPackageStatus, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //consumes - Какой формат получаем(от клиента)
    //produces - Какой формат отправляем клиенту
    //RequestBody - Аннотации, указывающие параметр метода, должны быть привязаны к телу веб-запроса.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFPackageStatus> createWithLocation(@RequestBody WFPackageStatus wfPackageStatus){
        WFPackageStatus created = super.create(wfPackageStatus);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"{id}")
                .buildAndExpand(wfPackageStatus.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);
        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WFPackageStatus> updateWFPS(@RequestBody WFPackageStatus wfPackageStatus){
        WFPackageStatus updateWFPS = super.update(wfPackageStatus);

        return updateWFPS != null
                ? new ResponseEntity<>(updateWFPS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteWFPS(@PathVariable int id){
        super.delete(id);
    }

    //consumes - Какой формат получаем(от клиента)
    //produces - Какой формат отправляем клиенту
    //RequestBody - Аннотации, указывающие параметр метода, должны быть привязаны к телу веб-запроса.
    @PostMapping(value = "/filter"
            ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WFPackageStatus>> filterWFPS(@RequestBody WFPackageStatus wfPackageStatus){
        List<WFPackageStatus> list = super.filter(wfPackageStatus);

        return list != null
                ? new ResponseEntity<>(list,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

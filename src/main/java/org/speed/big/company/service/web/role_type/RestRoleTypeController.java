package org.speed.big.company.service.web.role_type;

import org.speed.big.company.service.model.RoleType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(org.speed.big.company.service.web.role_type.RestRoleTypeController.REST_URL)
public class RestRoleTypeController extends AbstractRoleTypeController {
    static final String REST_URL = "/rest/roleTypes";

    //produces - Какой формат отправляем клиенту
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoleType> getAll() {
        List<RoleType> list = super.getAll();

        return list;
    }

    //@PathVariable - Аннотации, указывающие, что параметр метода должен быть привязан к переменной шаблона UR
    //produces - Какой формат отправляем клиенту
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleType> getRT(@PathVariable int id) {
        RoleType roleType = super.get(id);

        return roleType != null
                ? new ResponseEntity<>(roleType, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //consumes - Какой формат получаем(от клиента)
    //produces - Какой формат отправляем клиенту
    //RequestBody - Аннотации, указывающие параметр метода, должны быть привязаны к телу веб-запроса.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleType> createWithLocation(@RequestBody RoleType roleType){
        RoleType created = super.create(roleType);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);
        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return  ResponseEntity.created(uriOfNewResource).body(created);
    }


}

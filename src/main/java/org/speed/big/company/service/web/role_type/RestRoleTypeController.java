package org.speed.big.company.service.web.role_type;

import org.speed.big.company.service.model.RoleType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

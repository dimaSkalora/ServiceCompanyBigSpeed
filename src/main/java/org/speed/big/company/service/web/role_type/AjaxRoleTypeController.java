package org.speed.big.company.service.web.role_type;

import org.speed.big.company.service.model.RoleType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(org.speed.big.company.service.web.role_type.AjaxRoleTypeController.REST_URL)
public class AjaxRoleTypeController extends AbstractRoleTypeController {
    static final String REST_URL = "/ajax/roleTypes";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoleType> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoleType getRT(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(RoleType roleType){
         if (roleType.isNew())
             super.create(roleType);
         else
             super.update(roleType, roleType.getId());

         return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteRT(@PathVariable int id){
        super.delete(id);
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoleType> filter(RoleType roleType){
        return super.filterRoleType(roleType);
    }
}

package org.speed.big.company.service.web.role;

import org.speed.big.company.service.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(org.speed.big.company.service.web.role.AjaxRoleController.REST_URL)
public class AjaxRoleController extends AbstractRoleController {
    static final String REST_URL = "ajax/roles";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Role getRole(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(Role role) {
        if (role.isNew())
            super.create(role);
        else
            super.update(role);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role>filter(Role role) {
        return super.filterRole(role);
    }

}

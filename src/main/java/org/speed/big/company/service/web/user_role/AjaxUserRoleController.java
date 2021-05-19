package org.speed.big.company.service.web.user_role;

import org.speed.big.company.service.model.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AjaxUserRoleController.REST_URL)
public class AjaxUserRoleController extends AbstractUserRoleController{
    static final String REST_URL = "ajax/userRoles";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserRole> getAll(){
        List<UserRole> list =  super.getAll();
        return list;
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRole getUR(@PathVariable int id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(UserRole userRole) {
        if (userRole.isNew())
            super.create(userRole);
        else
            super.update(userRole, userRole.getId());

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        super.delete(id);
    }

    @GetMapping(value = "/filter/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserRole> filterUR(UserRole userRole){
        return super.filter(userRole);
    }
}

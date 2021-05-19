package org.speed.big.company.service.web.role;

import org.speed.big.company.service.model.Role;
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
@RequestMapping(RestRoleController.REST_URL)
public class RestRoleController extends AbstractRoleController {
    static final String REST_URL = "rest/roles";

    //produces - Какой формат отправляем клиенту
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> getAll() {
        List<Role> list = super.getAll();
        return list;
    }

    //@PathVariable - Аннотации, указывающие, что параметр метода должен быть привязан к переменной шаблона UR
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getRole(@PathVariable int id) {
        Role role = super.get(id);

        return role != null
                ? new ResponseEntity<>(role, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/getFromAllUsers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getRoleFromAllUsers(@PathVariable int id) {
        Role role = super.getFromAllUsers(id);

        return role != null
                ? new ResponseEntity<>(role, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //consumes - Какой формат получаем(от клиента)
    //produces - Какой формат отправляем клиенту
    //RequestBody - Аннотации, указывающие параметр метода, должны быть привязаны к телу веб-запроса.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> createWithLocation(@Valid @RequestBody Role role) {
        Role created = super.create(role);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uriOfNewResource);
        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> updateRole(@Valid @RequestBody Role role, @PathVariable("id") int id) {
        Role updateRole = super.update(role, id);

        return updateRole != null
                ? new ResponseEntity<>(updateRole, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping(value = "/filter",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> filter(@RequestBody Role role) {
        List<Role> filterRoles = super.filterRole(role);

        return filterRoles != null
                ? new ResponseEntity<>(filterRoles, HttpStatus.OK)
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
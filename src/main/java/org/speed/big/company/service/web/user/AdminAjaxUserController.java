package org.speed.big.company.service.web.user;

import org.speed.big.company.service.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(org.speed.big.company.service.web.user.AdminAjaxUserController.REST_URL)
public class AdminAjaxUserController extends AbstractUserController{

        static final String REST_URL = "/ajax/admin/users";

        //produces - Какой формат отправляем клиенту
        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public List<User> getAllU() {
            List<User> list = super.getAll();

            return list;
        }

        //@PathVariable - Аннотации, указывающие, что параметр метода должен быть привязан к переменной шаблона UR
        @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public User getU(@PathVariable("id") int id) {
            User user = super.get(id);

            return user;
        }

        @PostMapping
        public ResponseEntity<String> createOrUpdate(User user) {
            if (user.isNew())
                super.create(user);
            else
                super.update(user);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        @DeleteMapping(value = "/{id}")
        public void deleteUser(@PathVariable("id") int id) {
            super.delete(id);
        }

        //produces - Какой формат отправляем клиенту
        //RequestBody - Аннотации, указывающие параметр метода, должны быть привязаны к телу веб-запроса.
        @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<User>filter(User user){
            List<User> list = super.filterUser(user);

            return list;
        }

        @GetMapping(value = "/text")
        public String testUTF() {
            return "Русский текст";
        }

        @GetMapping(value = "/text2",produces = MediaType.APPLICATION_JSON_VALUE)
        public String testUTF2() {
            return "Русский текст";
        }
}

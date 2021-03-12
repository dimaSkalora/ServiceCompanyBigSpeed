package test.org.speed.big.company.service.service;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImplTest {
    /*private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    private UserService userService;

    private User user;
    private int idUser;

    @Before
    public void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("/spring/context_test.xml");
        userService = (UserService) applicationContext.getBean("userServiceImpl");
    }

    @Test
    public void create() {
        User user = new User();
        user.setName("test name");
        user.setEmail("test email" + LocalDateTime.now().getNano());
        user.setPassword("test password");
        user.setPhone("test " + LocalDateTime.now().getNano());
        user.setRegistered(LocalDate.now());
        user.setEnabled(true);

        userService.create(user);
        idUser = user.getId();

        log.info("create {}", user);
    }

    @Test
    public void update() {
        create();
        user = userService.get(idUser);
        log.info("get {}", user);

        user.setName("test name update");
        user.setPassword("test password update");

        userService.update(user);

        log.info("update {}", user);
    }

    @Test
    public void get() {
        create();
        user = userService.get(idUser);
        log.info("get {}", user);
    }

    @Test
    public void delete() {
        create();
        user = userService.get(idUser);
        log.info("get {}", user);

        log.info("delete {}", userService.delete(user.getId()));
    }

    @Test
    public void getAll() {
        log.info("getAll");

        List<User> users = userService.getAll();
        users.forEach(u -> log.info("user {}", u));
    }

    @Test
    public void getBetweenRegistered() {
        log.info("getBetweenRegistered");

        List<User> users = userService.getBetweenRegistered(LocalDate.now().minusDays(5), LocalDate.now().plusDays(10));
        users.forEach(u -> log.info("user {}", u));
    }

    @Test
    public void filterUser() {
        User user = new User();
        user.setName("test name");
        user.setPassword("test password");

        log.info("filterUser");
        List<User> users = userService.filterUser(user);
        users.forEach(u -> log.info("user {}", u));
    }

    @Test
    public void filterUserCondition(){
        User user = new User();
        user.setName("test name");

        String sqlCondition = " u.password = 'test password'";

        log.info("filterUserCondition");
        List<User> users = userService.filterUser(user,sqlCondition);
        users.forEach(u -> log.info("user {}",u));
    }*/

}

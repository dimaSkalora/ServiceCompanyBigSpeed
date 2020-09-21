package test.org.speed.big.company.service.repository.jpa;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class JpaUserRepositoryImplTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    private UserRepository userRepository;

    private User user;
    private int idUser;

    @Before
    public void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("/spring/context.xml");
        userRepository = (UserRepository) applicationContext.getBean("jpaUserRepositoryImpl");
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

        userRepository.save(user);
        idUser = user.getId();

        log.info("create {}", user);
    }

    @Test
    public void update() {
        create();
        user = userRepository.get(idUser);
        log.info("get {}", user);

        user.setName("test name update");
        user.setPassword("test password update");

        userRepository.save(user);

        log.info("update {}", user);
    }

    @Test
    public void get() {
        create();
        user = userRepository.get(idUser);
        log.info("get {}", user);
    }

    @Test
    public void delete() {
        create();
        user = userRepository.get(idUser);
        log.info("get {}", user);

        log.info("delete {}", userRepository.delete(user.getId()));
    }

    @Test
    public void getAll() {
        log.info("getAll");

        List<User> users = userRepository.getAll();
        users.forEach(u -> log.info("user {}", u));
    }

    @Test
    public void getBetweenRegistered() {
        log.info("getBetweenRegistered");

        List<User> users = userRepository.getBetweenRegistered(LocalDate.now().minusDays(5), LocalDate.now().plusDays(10));
        users.forEach(u -> log.info("user {}", u));
    }

    @Test
    public void filterUser() {
        User user = new User();
        user.setName("test name");
        user.setPassword("test password");
        user.setEnabled(true);

        log.info("filterUser");
        List<User> users = userRepository.filterUser(user);
        users.forEach(u -> log.info("user {}", u));
    }

    @Test
    public void filterUserCondition(){
        User user = new User();
        user.setName("test name");
        user.setEnabled(true);

        String sqlCondition = " u.password = 'test password'";

        log.info("filterUserCondition");
        List<User> users = userRepository.filterUser(user,sqlCondition);
        users.forEach(u -> log.info("user {}",u));
    }
}

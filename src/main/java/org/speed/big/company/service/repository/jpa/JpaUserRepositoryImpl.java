package org.speed.big.company.service.repository.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.repository.UserRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository("jpaUserRepositoryImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

   /* @Autowired
    private SessionFactory sessionFactory;


    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }*/

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getFromAllRoles(int id) {
        List<User> list = em.createNamedQuery(User.GET_FROM_ALL_ROLES)
                .setParameter("id",id)
                .getResultList();

        User user = DataAccessUtils.singleResult(list);

        return user;
    }

    @Override
    public User getByEmail(String email) {
        List<User> list = em.createNamedQuery(User.GET_BY_EMAIL)
                .setParameter("email",email)
                .getResultList();

        User user = DataAccessUtils.singleResult(list);

        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {

/*      User ref = em.getReference(User.class, id);
        em.remove(ref);

        Query query = em.createQuery("DELETE FROM User u WHERE u.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
*/
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public List<User> getBetweenRegistered(LocalDate startDate, LocalDate endDate) {
        List<User> list = em.createNamedQuery(User.BETWEEN_REGISTERED)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
        return list;
    }

    @Override
    public List<User> filterUser(User user) {
        return filterUser(user,null);
    }

    @Override
    public List<User> filterUser(User user, String sqlCondition) {
        String sqlFilterUser = "SELECT u FROM User u";
        int parameterCount = 0;

        if (user.getId() != null) {
            sqlFilterUser += " where u.id=:id";
            parameterCount++;
        }
        if (user.getName() != null){
            if (parameterCount > 0)
                sqlFilterUser += " and u.name=:name";
            else
                sqlFilterUser += " where u.name=:name";
            parameterCount++;
        }
        if (user.getEmail() != null){
            if (parameterCount > 0)
                sqlFilterUser += " and u.email=:email";
            else
                sqlFilterUser += " where u.email=:email";
            parameterCount++;
        }
        if (user.getPassword() != null){
            if (parameterCount > 0)
                sqlFilterUser += " and u.password=:password";
            else
                sqlFilterUser += " where u.password=:password";
            parameterCount++;
        }
        if (user.getPhone() != null){
            if (parameterCount > 0)
                sqlFilterUser += " and u.phone=:phone";
            else
                sqlFilterUser += " where u.phone=:phone";
            parameterCount++;
        }
        if (user.getRegistered() != null){
            if (parameterCount > 0)
                sqlFilterUser += " and u.registered=:registered";
            else
                sqlFilterUser += " where u.registered=:registered";
            parameterCount++;
        }
        if (parameterCount > 0)
            sqlFilterUser += " and u.enabled=:enabled";
        else
            sqlFilterUser += " where u.enabled=:enabled";

        if((sqlCondition != null) && (!sqlCondition.equals(""))){
            if (parameterCount > 0)
                sqlFilterUser += " and ( "+sqlCondition+" ) ";
            else
                sqlFilterUser = sqlFilterUser + " where ("+sqlCondition+" ) ";
        }

        Query query = em.createQuery(sqlFilterUser);
        List<User> list;

        if (user.getId() != null)
            query.setParameter("id", user.getId());
        if (user.getName() != null)
            query.setParameter("name",user.getName());
        if (user.getEmail() != null)
            query.setParameter("email",user.getEmail());
        if (user.getPassword() != null)
            query.setParameter("password",user.getPassword());
        if (user.getPhone() != null)
            query.setParameter("phone",user.getPhone());
        if (user.getRegistered() != null)
            query.setParameter("registered",user.getRegistered());
        query.setParameter("enabled",user.isEnabled());

        list = query.getResultList();

        return list;
    }

}

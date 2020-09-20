package org.speed.big.company.service.repository.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository("jpaUserRepositoryImpl")
@Transactional(readOnly = true)
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
        return null;
    }

    @Override
    public List<User> filterUser(User user) {
        return filterUser(user,null);
    }

    @Override
    public List<User> filterUser(User user, String sqlCondition) {
        return null;
    }
}

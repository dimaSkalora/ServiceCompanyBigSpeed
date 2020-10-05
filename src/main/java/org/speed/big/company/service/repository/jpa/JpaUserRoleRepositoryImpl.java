package org.speed.big.company.service.repository.jpa;

import org.speed.big.company.service.model.UserRole;
import org.speed.big.company.service.repository.UserRoleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRoleRepositoryImpl implements UserRoleRepository {

    /* @Autowired
     private SessionFactory sessionFactory;


     private Session openSession() {
         return sessionFactory.getCurrentSession();
     }*/

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserRole save(UserRole userRole) {
        if (userRole.isNew())
            entityManager.persist(userRole);
        else
            entityManager.merge(userRole);

        return userRole;
    }

    @Override
    public UserRole get(int id) {
/*        UserRole userRole = entityManager.createNamedQuery(UserRole.GET,UserRole.class)
                .setParameter("id",id)
                .getSingleResult();*/

         UserRole userRole = entityManager.find(UserRole.class,id);
        return userRole;
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        // getReference - выбросить  EntityNotFoundException если не найдено (загружаеть часть полей)
        // find - будет null  если не найдено (загружает все поля)
       /* UserRole userRole = entityManager.getReference(UserRole.class, id);
        entityManager.remove(userRole);*/

/*        boolean queryDelete = entityManager.createQuery("DELETE FROM UserRole ur where ur.id=:id")
                .setParameter("id",id)
                .executeUpdate() != 0;*/

      /*      boolean queryDelete =  entityManager.createNativeQuery("select from user_roles where id=:id")
            .setParameter("id",id)
            .executeUpdate() != 0;      */


        return entityManager.createNamedQuery(UserRole.DELETE)
                .setParameter("id",id)
                .executeUpdate() != 0;
    }

    @Override
    public List<UserRole> getAll() {
        return entityManager.createNamedQuery(UserRole.ALL_SORTED, UserRole.class).getResultList();
    }

    @Override
    public List<UserRole> filter(UserRole userRole) {
        String queryFilter = "select ur from UserRole ur ";
        int paramCount = 0;

        if (userRole.getId() != null) {
            queryFilter = queryFilter + " where ur.id=:id";
            paramCount++;
        }
        if (userRole.getUserId() != null){
            if (paramCount == 0)
                queryFilter += " where ur.user_id=:userId";
            else
                queryFilter += " and ur.user_id=:userId";
            paramCount++;
        }
        if (userRole.getRoleId() != null){
            if (paramCount == 0)
                queryFilter += " where ur.role_id=:roleId";
            else
                queryFilter += " and ur.role_id=:roleId";
            paramCount++;
        }
        if (userRole.getDateTime() != null){
            if (paramCount == 0)
                queryFilter += " where ur.date_time=:dateTime";
            else
                queryFilter += " and ur.date_time=:dateTime";
            paramCount++;
        }
        if (userRole.getComment() != null){
            if (paramCount == 0)
                queryFilter += " where ur.comment=:comment";
            else
                queryFilter += " and ur.comment=:comment";
            paramCount++;
        }

        queryFilter += "order by ur.user_id "; //default ASC -  по возрастанию

        Query query = entityManager.createQuery(queryFilter);
        List<UserRole> list;

        if (userRole.getId() != null)
            query.setParameter("id", userRole.getId());
        if(userRole.getUserId() != null)
            query.setParameter("userId", userRole.getUserId());
        if (userRole.getRoleId() != null)
            query.setParameter("roleId", userRole.getRoleId());
        if(userRole.getDateTime() != null)
            query.setParameter("dateTime", userRole.getDateTime());
        if (userRole.getComment() != null)
            query.setParameter("comment", userRole.getComment());

        list = query.getResultList();

        return list;
    }
}

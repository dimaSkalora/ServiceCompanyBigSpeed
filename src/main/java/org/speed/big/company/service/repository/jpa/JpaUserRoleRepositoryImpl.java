package org.speed.big.company.service.repository.jpa;

import org.speed.big.company.service.model.UserRole;
import org.speed.big.company.service.repository.UserRoleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JpaUserRoleRepositoryImpl implements UserRoleRepository {

    /* @Autowired
     private SessionFactory sessionFactory;


     private Session openSession() {
         return sessionFactory.getCurrentSession();
     }*/

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
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
        /*UserRole userRole = entityManager.find(UserRole.class,id);*/

        UserRole userRole = entityManager.createNamedQuery(UserRole.GET,UserRole.class)
                .setParameter("id",id)
                .getSingleResult();

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
        String queryFilter = "select ur from UserRole ur join fetch ur.userId join fetch ur.roleId ";
        int paramCount = 0;

        if (userRole.getId() != null) {
            queryFilter = queryFilter + " where ur.id=:id ";
            paramCount++;
        }
        if (userRole.getUserId() != null){
            if (paramCount == 0)
                queryFilter += " where ur.userId=:userId ";
            else
                queryFilter += " and ur.userId=:userId ";
            paramCount++;
        }
        if (userRole.getRoleId() != null){
            if (paramCount == 0)
                queryFilter += " where ur.roleId=:roleId ";
            else
                queryFilter += " and ur.roleId=:roleId ";
            paramCount++;
        }
        if (userRole.getDateTime() != null){
            if (paramCount == 0)
                queryFilter += " where ur.dateTime=:dateTime ";
            else
                queryFilter += " and ur.dateTime=:dateTime ";
            paramCount++;
        }
        if (userRole.getComment() != null){
            if (paramCount == 0)
                queryFilter += " where ur.comment=:comment ";
            else
                queryFilter += " and ur.comment=:comment ";
            paramCount++;
        }

        queryFilter += " order by ur.dateTime "; //default ASC -  по возрастанию

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

    @Override
    public List<UserRole> getByUser(int userId) {
        String queryGetByUser = "select ur from UserRole ur join fetch ur.userId join fetch ur.roleId " +
                " where ur.userId.id=:userId\n" +
                " order by ur.roleId.id";
        List<UserRole> userRoles = entityManager.createQuery(queryGetByUser,UserRole.class)
                .setParameter("userId",userId)
                .getResultList();

        return userRoles;
    }

    @Override
    public List<UserRole> getByRole(int roleId) {
        String queryGetByRole = "select ur from UserRole ur join fetch ur.userId join fetch ur.roleId " +
                " where ur.roleId.id=:roleId\n" +
                " order by ur.userId.id";
        List<UserRole> userRoles = entityManager.createQuery(queryGetByRole,UserRole.class)
                .setParameter("roleId",roleId)
                .getResultList();

        return userRoles;
    }
}

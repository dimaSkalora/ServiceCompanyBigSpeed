package org.speed.big.company.service.repository.data_jpa;

import org.speed.big.company.service.model.UserRole;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudUserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Transactional
    @Override
    UserRole save(UserRole userRole);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserRole ur WHERE ur.id=:id")
    int delete(@Param("id") int id);

    @Override
    List<UserRole> findAll(Sort sort);

    @Query("SELECT ur FROM UserRole ur JOIN FETCH ur.userId " +
            " JOIN FETCH ur.roleId WHERE ur.userId=:userId ")
    List<UserRole> findUserRoleByUserId(@Param("userId") Integer userId);

    @Query("SELECT ur FROM UserRole ur JOIN FETCH ur.userId " +
            " JOIN FETCH ur.roleId WHERE ur.roleId=:roleId ")
    List<UserRole> findUserRoleByRoleId(@Param("roleId") Integer roleId);

}

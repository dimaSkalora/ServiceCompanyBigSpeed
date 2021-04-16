package org.speed.big.company.service.repository.data_jpa;

import org.speed.big.company.service.model.Role;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRoleRepository extends JpaRepository<Role, Integer> {
    @Transactional
    @Override
    Role save (Role role);

    @Modifying
    @Transactional
    @Query("DELETE FROM Role r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<Role> findById(Integer id);

    @Query("SELECT DISTINCT r FROM Role r JOIN FETCH r.userList WHERE r.id=:id")
    Role findFromAllUsers(@Param("id") int id);

    @Override
    List<Role> findAll(Sort sort);

}

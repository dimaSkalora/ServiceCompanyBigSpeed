package org.speed.big.company.service.repository.data_jpa;

import org.speed.big.company.service.model.RoleType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRoleTypeRepository extends JpaRepository<RoleType, Integer> {
    @Transactional
    @Override
    RoleType save(RoleType roleType);

    @Modifying
    @Transactional
    @Query("DELETE FROM RoleType rt WHERE rt.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<RoleType> findById(Integer id);

    @Override
    List<RoleType> findAll(Sort sort);

}

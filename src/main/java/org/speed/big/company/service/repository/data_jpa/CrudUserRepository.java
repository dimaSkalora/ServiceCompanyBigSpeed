package org.speed.big.company.service.repository.data_jpa;

import org.speed.big.company.service.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Override
    User save(User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<User> findById(Integer id);

    @Query("SELECT DISTINCT u FROM User u join fetch u.roleList where u.id=:id ")
    User getFromAllRoles(@Param("id") int id);

    @Override
    List<User> findAll(Sort sort);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT u FROM User u where u.registered between :startDate and :endDate " +
            " ORDER BY u.registered")
    List<User> getBetweenRegistered(@Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);

    User findByEmail(String email);
}

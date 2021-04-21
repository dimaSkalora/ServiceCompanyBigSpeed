package org.speed.big.company.service.repository.data_jpa.workflow;

import org.speed.big.company.service.model.workflow.WFGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudWFGroupRepository extends JpaRepository<WFGroup, Integer> {
    @Transactional
    @Override
    WFGroup save(WFGroup wfGroup);

    @Modifying
    @Transactional
    @Query("DELETE FROM WFGroup wfg WHERE wfg.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<WFGroup> findById(Integer id);

    @Override
    List<WFGroup> findAll(Sort sort);
}

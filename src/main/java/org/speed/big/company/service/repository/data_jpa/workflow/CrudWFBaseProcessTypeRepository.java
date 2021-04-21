package org.speed.big.company.service.repository.data_jpa.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudWFBaseProcessTypeRepository extends JpaRepository<WFBaseProcessType, Integer> {
    @Transactional
    @Override
    WFBaseProcessType save(WFBaseProcessType wfBaseProcessType);

    @Modifying
    @Transactional
    @Query("DELETE FROM WFBaseProcessType wfbpt WHERE wfbpt.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<WFBaseProcessType> findById(Integer id);

    @Override
    List<WFBaseProcessType> findAll(Sort sort);
}

package org.speed.big.company.service.repository.data_jpa.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudWFBaseProcessRepository extends JpaRepository<WFBaseProcess, Integer> {
    @Transactional
    @Override
    WFBaseProcess save(WFBaseProcess wfBaseProcess);

    @Modifying
    @Transactional
    @Query("DELETE FROM WFBaseProcess wfbpro WHERE wfbpro.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT wfbp FROM WFBaseProcess wfbp " +
            " JOIN FETCH wfbp.wfServiceId JOIN FETCH wfbp.wfBaseProcessTypeId " +
            " WHERE wfbp.id=:id")
    @Override
    Optional<WFBaseProcess> findById(@Param("id") Integer id);

    @Override
    List<WFBaseProcess> findAll(Sort sort);
}

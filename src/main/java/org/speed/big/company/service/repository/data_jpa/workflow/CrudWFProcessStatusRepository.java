package org.speed.big.company.service.repository.data_jpa.workflow;

import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface CrudWFProcessStatusRepository extends JpaRepository<WFProcessStatus, Integer> {
    @Transactional
    @Override
    WFProcessStatus save(WFProcessStatus wfProcessStatus);

    @Modifying
    @Transactional
    @Query("DELETE FROM WFPackageStatus wfProStatus WHERE wfProStatus.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<WFProcessStatus> findById(Integer id);

    @Override
    List<WFProcessStatus> findAll(Sort sort);
}

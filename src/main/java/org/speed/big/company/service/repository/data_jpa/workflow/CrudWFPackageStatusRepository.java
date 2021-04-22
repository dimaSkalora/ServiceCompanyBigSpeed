package org.speed.big.company.service.repository.data_jpa.workflow;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudWFPackageStatusRepository extends JpaRepository<WFPackageStatus, Integer> {
    @Transactional
    @Override
    WFPackageStatus save(WFPackageStatus wfPackageStatus);

    @Modifying
    @Transactional
    @Query("DELETE FROM WFPackageStatus wfpstatus WHERE wfpstatus.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<WFPackageStatus> findById(Integer id);

    @Override
    List<WFPackageStatus> findAll(Sort sort);
}

package org.speed.big.company.service.repository.data_jpa.workflow;

import org.speed.big.company.service.model.workflow.WFPackage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudWFPackageRepository extends JpaRepository<WFPackage, Integer> {
    @Transactional
    @Override
    WFPackage save(WFPackage wfPackages);

    @Modifying
    @Transactional
    @Query("DELETE FROM WFPackage wfpack WHERE wfpack.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<WFPackage> findById(Integer id);

    @Override
    List<WFPackage> findAll(Sort sort);
}

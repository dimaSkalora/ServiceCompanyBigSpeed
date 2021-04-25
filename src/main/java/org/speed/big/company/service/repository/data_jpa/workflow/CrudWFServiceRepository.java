package org.speed.big.company.service.repository.data_jpa.workflow;

import org.speed.big.company.service.model.workflow.WFService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudWFServiceRepository extends JpaRepository<WFService, Integer> {
    @Transactional
    @Override
    WFService save(WFService wfService);

    @Modifying
    @Transactional
    @Query("DELETE FROM WFService wfs WHERE wfs.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<WFService> findById(Integer id);

    @Override
    List<WFService> findAll(Sort sort);
}

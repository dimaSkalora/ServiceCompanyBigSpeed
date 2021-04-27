package org.speed.big.company.service.repository.data_jpa.workflow;

import org.speed.big.company.service.model.workflow.WFProcessState;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudWFProcessStateRepository extends JpaRepository<WFProcessState, Integer> {
    @Transactional
    @Override
    WFProcessState save(WFProcessState wfProcessState);

    @Modifying
    @Transactional
    @Query("DELETE FROM WFProcessState wfproState WHERE wfproState.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT wfpstate FROM WFProcessState wfpstate " +
            " JOIN FETCH wfpstate.roleId JOIN FETCH wfpstate.wfGroupId WHERE wfpstate.id=:id")
    @Override
    Optional<WFProcessState> findById(@Param("id")Integer id);

    @Query("select wfpstate from WFProcessState wfpstate  " +
            " join fetch wfpstate.roleId join fetch wfpstate.wfGroupId ")
    @Override
    List<WFProcessState> findAll(Sort sort);
}

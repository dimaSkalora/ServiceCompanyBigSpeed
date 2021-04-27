package org.speed.big.company.service.repository.data_jpa.workflow;

import org.speed.big.company.service.model.workflow.WFProcessMovement;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudWFProcessMovementRepository extends JpaRepository<WFProcessMovement, Integer> {
    @Transactional
    @Override
    WFProcessMovement save(WFProcessMovement wfProcessMovement);

    @Modifying
    @Transactional
    @Query("DELETE FROM WFProcessMovement wfpm WHERE wfpm.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT wfpm FROM WFProcessMovement wfpm " +
            " JOIN FETCH wfpm.userId JOIN FETCH wfpm.wfPackageId " +
            " JOIN FETCH wfpm.wfStateId JOIN FETCH wfpm.wfProcessId " +
            " JOIN FETCH wfpm.wfBaseProcessId WHERE wfpm.id=:id")
    @Override
    Optional<WFProcessMovement> findById(@Param("id") Integer id);

    @Override
    List<WFProcessMovement> findAll(Sort sort);

    @Query("select wfpm from WFProcessMovement wfpm join fetch wfpm.userId join fetch wfpm.wfPackageId " +
            " join fetch wfpm.wfStateId join fetch wfpm.wfProcessId join fetch wfpm.wfBaseProcessId " +
            " where wfpm.wfStateId.roleId.id=:roleId and wfpm.wfBaseProcessId.wfServiceId.id=:wfServiceId\n "+
            " and wfpm.wfProcessId.wfProcessStatusId.id=:processStatus\n"+
            " and wfpm.isCompleted=:isCompleted and wfpm.isLast=:isLast\n ")
    List<WFProcessMovement> findWFProcessMovement(@Param("roleId") int roleId, @Param("wfServiceId") int wfServiceId,
                                                  @Param("processStatus") int processStatus,
                                                  @Param("isCompleted") boolean isCompleted, @Param("isLast") boolean isLast,
                                                  Sort sort);

    @Query("select wfpm from WFProcessMovement wfpm join fetch wfpm.userId join fetch wfpm.wfPackageId " +
            " join fetch wfpm.wfStateId join fetch wfpm.wfProcessId join fetch wfpm.wfBaseProcessId " +
            " where wfpm.wfProcessId.id=:wfProcessId and wfpm.wfBaseProcessId.id=:wfBaseProcessId\n ")
    List<WFProcessMovement> findWFProcessMovementByProcessAndBaseProcess(@Param("wfProcessId") int wfProcessId,
                                                                         @Param("wfBaseProcessId") int wfBaseProcessId,
                                                                         Sort sort);
}

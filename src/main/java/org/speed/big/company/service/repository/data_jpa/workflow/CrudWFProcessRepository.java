package org.speed.big.company.service.repository.data_jpa.workflow;

import org.speed.big.company.service.model.workflow.WFProcess;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudWFProcessRepository extends JpaRepository<WFProcess, Integer> {
    @Transactional
    @Override
    WFProcess save(WFProcess wfProcess);

    @Modifying
    @Transactional
    @Query("DELETE FROM WFProcess wfpro WHERE wfpro.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT wfpro FROM WFProcess wfpro \n" +
            " JOIN FETCH wfpro.wfPackageId JOIN FETCH wfpro.wfBaseProcessId \n" +
            " JOIN FETCH wfpro.wfProcessStatusId WHERE wfpro.id=:id")
    @Override
    Optional<WFProcess> findById(@Param("id") Integer id);

    @Override
    List<WFProcess> findAll(Sort sort);

    @Query("select wfpro from WFProcess wfpro where wfpro.wfBaseProcessId.wfServiceId.id=:wfServiceId\n "+
            " and wfpro.wfProcessStatusId.id=:wfProcessStatusId" )
    List<WFProcess> findWFProcessesByWFSIdAndWFPSId(@Param("wfServiceId") int wfServiceId,
                                                    @Param("wfProcessStatusId") int wfProcessStatusId, Sort sort);

    @Query("select wfpro from WFProcess wfpro where wfpro.wfPackageId.id=:wfPackageId" )
    List<WFProcess> findWFProcessesByWFPackageId(@Param("wfPackageId") int wfPackageId, Sort sort);
}

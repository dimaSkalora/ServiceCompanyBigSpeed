package org.speed.big.company.service.repository.data_jpa.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudWFBaseProcessItemRepository extends JpaRepository<WFBaseProcessItem, Integer> {

    @Transactional
    @Override
    WFBaseProcessItem save(WFBaseProcessItem wfBaseProcessItem);

    @Modifying
    @Transactional
    @Query("DELETE FROM WFBaseProcessItem wfbpi WHERE wfbpi.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT wfbpi FROM WFBaseProcessItem wfbpi " +
            " JOIN FETCH wfbpi.stateFromId JOIN FETCH wfbpi.stateToId " +
            " JOIN FETCH wfbpi.baseProcessId " +
            " WHERE wfbpi.id=:id")
    @Override
    Optional<WFBaseProcessItem> findById(@Param("") Integer id);

    @Query("SELECT wfbpi FROM WFBaseProcessItem wfbpi " +
            " JOIN FETCH wfbpi.stateFromId JOIN FETCH wfbpi.stateToId " +
            " JOIN FETCH wfbpi.baseProcessId ")
    @Override
    List<WFBaseProcessItem> findAll(Sort sort);

}

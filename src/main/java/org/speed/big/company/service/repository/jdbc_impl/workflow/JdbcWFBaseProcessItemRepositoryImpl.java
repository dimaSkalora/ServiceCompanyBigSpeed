package org.speed.big.company.service.repository.jdbc_impl.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow.WFBaseProcessItemRowMapper;
import org.speed.big.company.service.repository.workflow.WFBaseProcessItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Repository("jdbcWFBaseProcessItemRepositoryImpl")
public class JdbcWFBaseProcessItemRepositoryImpl implements WFBaseProcessItemRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    private final String sqlQuery = "select wfbpi.id as wfbpi_id, wfbpi.state_from_id as wfbpi_state_from_id,\n" +
            "wfbpi.state_to_id as wfbpi_state_to_id, wfbpi.base_process_id as wfbpi_base_process_id,\n" +
            "wfpsStateFromId.id as wfpsStateFromId_id, wfpsStateFromId.name as wfpsStateFromId_name, wfpsStateFromId.role_id as wfpsStateFromId_role_id," +
            "wfpsStateFromId.description as wfpsStateFromId_description,\n" +
            "wfpsStateToId.id as wfpsStateToId_id, wfpsStateToId.name as wfpsStateToId_name, wfpsStateToId.role_id as wfpsStateToId_role_id," +
            "wfpsStateToId.description as wfpsStateToId_description,\n" +
            "fromR.id as fromR_id, fromR.name as fromR_name, fromR.description as fromR_description,\n" +
            "toR.id as toR_id, toR.name as toR_name, toR.description as toR_description,\n" +
            "wfbprocess.id as wfbprocess_id, wfbprocess.name as wfbprocess_name,wfbprocess.description as wfbprocess_description\n" +
            "from wf_base_process_items wfbpi\n" +
            "left join wf_process_state wfpsStateFromId on wfbpi.state_from_id=wfpsStateFromId.id\n"+
            "left join role fromR on fromR.id = wfpsStateFromId.role_id\n"+
            "left join wf_process_state wfpsStateToId on wfbpi.state_from_id=wfpsStateToId.id\n" +
            "left join role toR on toR.id = wfpsStateToId.role_id\n"+
            "left join wf_base_process wfbprocess on wfbpi.base_process_id=wfbprocess.id";

    @Autowired
    public JdbcWFBaseProcessItemRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("wf_base_process_items")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public WFBaseProcessItem save(WFBaseProcessItem wfBaseProcessItem) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", wfBaseProcessItem.getId())
                .addValue("stateFromId", wfBaseProcessItem.getStateFromId())
                .addValue("stateToId",wfBaseProcessItem.getStateToId())
                .addValue("baseProcessId",wfBaseProcessItem.getBaseProcessId());

        if (wfBaseProcessItem.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            wfBaseProcessItem.setId(newKey.intValue());
        }else if (namedParameterJdbcTemplate.update("update wf_base_process_items \n" +
                "set state_from_id=:stateFromId, state_to_idstate_to_id=:stateToId,\n" +
                "base_process_id=:baseProcessId where id=:id",parameterSource) == 0){
            return null;
        }

        return wfBaseProcessItem;
    }

    @Override
    public WFBaseProcessItem get(int id) {
        String queryGet = sqlQuery + " where wfbpi.id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<WFBaseProcessItem> list = namedParameterJdbcTemplate.query(sqlQuery,
                parameterSource,new WFBaseProcessItemRowMapper());
        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        String queryDelete = "delete from wf_base_process_items where id=?";
        return jdbcTemplate.update(queryDelete,id) != 0;
    }

    @Override
    public List<WFBaseProcessItem> getAll() {
        return jdbcTemplate.query(sqlQuery,new WFBaseProcessItemRowMapper());
    }

    @Override
    public List<WFBaseProcessItem> filter(WFBaseProcessItem wfBaseProcessItem) {
        String queryFilter = sqlQuery;
        int paramCount = 0;
        List<WFBaseProcessItem> list = null;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (wfBaseProcessItem.getId() != null){
            parameterSource.addValue("id", wfBaseProcessItem.getId());
            paramCount++;
        }
        if (wfBaseProcessItem.getStateFromId() != null){
            parameterSource.addValue("stateFromId", wfBaseProcessItem.getStateFromId().getId());
            paramCount++;
        }
        if (wfBaseProcessItem.getStateToId() != null){
            parameterSource.addValue("stateToId", wfBaseProcessItem.getStateToId().getId());
            paramCount++;
        }
        if (wfBaseProcessItem.getBaseProcessId() != null){
            parameterSource.addValue("baseProcessId", wfBaseProcessItem.getBaseProcessId().getId());
        }

        for (var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (paramCount == 0){
                switch (paramName){
                    case "id"               -> queryFilter = queryFilter + " where wfbpi.id=:id\n";
                    case "stateFromId"      -> queryFilter += " where wfbpi.state_from_id=:stateFromId\n";
                    case "stateToId"        -> queryFilter += " where wfbpi.state_to_id=:stateToId\n";
                    case "baseProcessId"    -> queryFilter += " where wfbpi.base_process_id=:baseProcessId\n";
                }
            }else {
                switch (paramName){
                    case "id"               -> queryFilter = queryFilter + " and wfbpi.id=:id\n";
                    case "stateFromId"      -> queryFilter += " and wfbpi.state_from_id=:stateFromId\n";
                    case "stateToId"        -> queryFilter += " and wfbpi.state_to_id=:stateToId\n";
                    case "baseProcessId"    -> queryFilter += " and wfbpi.base_process_id=:baseProcessId\n";
                }
            }
        }

        queryFilter += " order by wfbpi.id";

        list = namedParameterJdbcTemplate.query(queryFilter,parameterSource, new WFBaseProcessItemRowMapper());

        return list;
    }

    @Override
    public List<WFProcessState> getListTransferWFProcessState(int processStateFromId, int baseProcessId) {
        String queryGetListTransferWFProcessState = sqlQuery +
                " where wfbpi.state_from_id=:processStateFromId\n"+
                " and wfbpi.base_process_id=:baseProcessId\n";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("processStateFromId",processStateFromId)
                .addValue("baseProcessId",baseProcessId);

        List<WFBaseProcessItem> list = namedParameterJdbcTemplate.query(queryGetListTransferWFProcessState,
                parameterSource, new WFBaseProcessItemRowMapper());

        List<WFProcessState> listTransferWFProcessStates = list.stream()
                .map(wbpi -> wbpi.getStateFromId())
                .collect(Collectors.toList());

        return listTransferWFProcessStates;
    }
}

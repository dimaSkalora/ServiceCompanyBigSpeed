package org.speed.big.company.service.repository.jdbc_impl.workflow;

import org.speed.big.company.service.model.workflow.WFProcessState;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow.WFProcessStateRowMapper;
import org.speed.big.company.service.repository.workflow.WFProcessStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("jdbcWFProcessStateRepositoryImpl")
public class JdbcWFProcessStateRepositoryImpl implements WFProcessStateRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    private final String sqlQuery = "select wfps.id as wfps_id,\n" +
            " wfps.name as wfps_name, wfps.role_id as wfps_role_id,\n" +
            " wfps.group_id as wfps_group_id, wfps.description as wfps_description,\n" +
            " r.id as r_id, r.name as r_name, r.description as r_description,\n" +
            " r.role_type_id as r_role_type_id, rt.id as rt_id, rt.name as rt_name,\n" +
            " wfg.id as wfg_id, wfg.name as wfg_name, \n" +
            " wfg.description as wfg_description\n" +
            " from wf_process_state wfps\n" +
            " left join role r on r.id = wfps.role_id\n" +
            " left join wf_group wfg on wfg.id=wfps.group_id\n";

    @Autowired
    public JdbcWFProcessStateRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("wf_process_state")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public WFProcessState save(WFProcessState wfProcessState) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",wfProcessState.getId())
                .addValue("name",wfProcessState.getName())
                .addValue("roleId",wfProcessState.getRoleId().getId())
                .addValue("groupId",wfProcessState.getGroupId().getId())
                .addValue("description",wfProcessState.getDescription());

        if (wfProcessState.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            wfProcessState.setId(newKey.intValue());
        }else if (namedParameterJdbcTemplate.update("update wf_process_state set name=:name,\n" +
                " description=:description where id=:id",parameterSource) == 0){
            return null;
        }

        return wfProcessState;
    }

    @Override
    public WFProcessState get(int id) {
        String queryGet = sqlQuery + " where wfps.id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<WFProcessState> list = namedParameterJdbcTemplate.query(queryGet
                ,parameterSource, new WFProcessStateRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from wf_process_state where id=?",id) != 0;
    }

    @Override
    public List<WFProcessState> getAll() {
        return jdbcTemplate.query(sqlQuery, new WFProcessStateRowMapper());
    }

    @Override
    public List<WFProcessState> filter(WFProcessState wfProcessState) {
        String queryFilter = sqlQuery;
        int paramCount = 0;
        List<WFProcessState> list = null;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (wfProcessState.getId() != null)
            parameterSource.addValue("id",wfProcessState.getId());
        if (wfProcessState.getName() != null)
            parameterSource.addValue("name",wfProcessState.getName());
        if (wfProcessState.getRoleId() != null)
            parameterSource.addValue("roleId",wfProcessState.getRoleId());
        if (wfProcessState.getGroupId() != null)
            parameterSource.addValue("groupId",wfProcessState.getGroupId());
        if (wfProcessState.getDescription() != null)
            parameterSource.addValue("description",wfProcessState.getDescription());

        for (var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (paramCount == 0){
                switch (paramName){
                    case "id"           -> queryFilter = queryFilter + " where wfps.id=:id\n";
                    case "name"         -> queryFilter += " where wfps.name=:name\n";
                    case "roleId"       -> queryFilter += " where wfps.roleId=:roleId\n";
                    case "groupId"      -> queryFilter += " where wfps.groupId=:groupId\n";
                    case "description"  -> queryFilter += " where wfps.description=:description\n";
                }
            }else {
                switch (paramName){
                    case "id"           -> queryFilter = queryFilter + " and wfps.id=:id\n";
                    case "name"         -> queryFilter += " and wfps.name=:name\n";
                    case "roleId"       -> queryFilter += " and wfps.roleId=:roleId\n";
                    case "groupId"      -> queryFilter += " and wfps.groupId=:groupId\n";
                    case "description"  -> queryFilter += " and wfps.description=:description\n";
                }
            }
            paramCount++;
        }

        queryFilter += "order by wfps.name";

        list = namedParameterJdbcTemplate.query(queryFilter,parameterSource, new WFProcessStateRowMapper());

        return list;
    }
}

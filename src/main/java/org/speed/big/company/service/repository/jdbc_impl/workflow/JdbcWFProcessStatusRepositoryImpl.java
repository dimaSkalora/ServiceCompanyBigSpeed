package org.speed.big.company.service.repository.jdbc_impl.workflow;

import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow.WFProcessStatusRowMapper;
import org.speed.big.company.service.repository.workflow.WFProcessStatusRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("jdbcWFProcessStatusRepositoryImpl")
@Transactional(readOnly = true)
public class JdbcWFProcessStatusRepositoryImpl implements WFProcessStatusRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    private final String sqlQuery = "select wfpstatus.id as wfpstatus_id, \n" +
            " wfpstatus.name as wfpstatus_name from wf_process_status wfpstatus\n";

    public JdbcWFProcessStatusRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("wf_process_status")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    @Override
    public WFProcessStatus save(WFProcessStatus wfProcessStatus) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",wfProcessStatus.getId())
                .addValue("name",wfProcessStatus.getName());

        if (wfProcessStatus.isNew()){
            Number newKwey = jdbcInsert.executeAndReturnKey(parameterSource);
            wfProcessStatus.setId(newKwey.intValue());
        }else if (namedParameterJdbcTemplate.update("update wf_process_status set name=:name " +
                " where id=:id",parameterSource) == 0){
            return null;
        }

        return wfProcessStatus;
    }

    @Override
    public WFProcessStatus get(int id) {
        String queryGet = sqlQuery+" where wfpstatus.id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<WFProcessStatus> list = namedParameterJdbcTemplate.query(queryGet,
                parameterSource, new WFProcessStatusRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from wf_process_status where id=?",id) != 0;
    }

    @Override
    public List<WFProcessStatus> getAll() {
        return jdbcTemplate.query(sqlQuery, new WFProcessStatusRowMapper());
    }

    @Override
    public List<WFProcessStatus> filter(WFProcessStatus wfProcessStatus) {
        String queryFilter = sqlQuery;
        int paramCount=0;
        List<WFProcessStatus> list;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (wfProcessStatus.getId() != null)
            parameterSource.addValue("id",wfProcessStatus.getId());
        if (wfProcessStatus.getName() != null)
            parameterSource.addValue("name",wfProcessStatus.getName());

        for(var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (paramCount == 0){
                switch (paramName){
                    case "id"       -> queryFilter = queryFilter + "where wfpstatus.id=:id\n";
                    case "name"     -> queryFilter += " where wfpstatus.name=:name\n";
                }
            }else {
                switch (paramName){
                    case "id"       -> queryFilter = queryFilter + "and wfpstatus.id=:id\n";
                    case "name"     -> queryFilter += " and wfpstatus.name=:name\n";
                }
            }
            paramCount++;
        }

        queryFilter += "order by wfpstatus.name";

        list = namedParameterJdbcTemplate.query(queryFilter
                , parameterSource, new WFProcessStatusRowMapper());

        return list;
    }
}

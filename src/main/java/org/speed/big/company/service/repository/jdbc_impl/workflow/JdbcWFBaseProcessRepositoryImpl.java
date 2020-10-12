package org.speed.big.company.service.repository.jdbc_impl.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow.WFBaseProcessRowMapper;
import org.speed.big.company.service.repository.workflow.WFBaseProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("jdbcWFBaseProcessRepositoryImpl")
public class JdbcWFBaseProcessRepositoryImpl implements WFBaseProcessRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    private BeanPropertyRowMapper<WFBaseProcess> ROW_MAPPER_WF_BASE_PROCESS = BeanPropertyRowMapper
            .newInstance(WFBaseProcess.class);

    private final String sqlQuery = "select wfbp.id as wfbp_id, wfbp.name as wfbp_name,\n" +
            " wfbp.description as wfbp_description, wfbp.wf_service_id as wfbp_wf_service_id,\n" +
            " wfbp.wf_base_process_type_id as wfbp_wf_base_process_type_id,\n" +
            " wfs.id as wfs_id, wfs.name as wfs_name, \n" +
            " wfbpt.id as wfbpt_id, wfbpt.name as wfbpt_name\n" +
            " from wf_base_process wfbp" +
            " left join wf_service wfs on wfbp.wfServiceId=wfs.id\n" +
            " left join wf_base_process_type wfbpt on wfbp.wf_base_process_type_id=wfbpt.id\n";

    @Autowired
    public JdbcWFBaseProcessRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("wf_base_process")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public WFBaseProcess save(WFBaseProcess wfBaseProcess) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",wfBaseProcess.getId())
                .addValue("name",wfBaseProcess.getName())
                .addValue("description", wfBaseProcess.getDescription())
                .addValue("wfServiceId",wfBaseProcess.getWfServiceId().getId())
                .addValue("wfBaseProcessTypeId",wfBaseProcess.getWfBaseProcessTypeId().getId());

        if (wfBaseProcess.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            wfBaseProcess.setId(newKey.intValue());
        }else if(namedParameterJdbcTemplate.update("update wf_base_process wfbp set wfbp.name=:name,\n" +
                " wfbp.description=:description, wfbp.wf_service_id=:wfServiceId,\n" +
                " wfbp.wf_base_process_type_id=:wfBaseProcessTypeId where efbp.id=:id", parameterSource) == 0)
            return null;

        return wfBaseProcess;
    }

    @Override
    public WFBaseProcess get(int id) {
        String queryGet = sqlQuery + " where wfbp.id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<WFBaseProcess> list = namedParameterJdbcTemplate.query(queryGet,parameterSource, new WFBaseProcessRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from wf_base_process where id=?",id) != 0;
    }

    @Override
    public List<WFBaseProcess> getAll() {
        return jdbcTemplate.query(sqlQuery,new WFBaseProcessRowMapper());
    }

    @Override
    public List<WFBaseProcess> filter(WFBaseProcess wfBaseProcess) {
        String queryFilter = sqlQuery;
        int paramCount = 0;
        List<WFBaseProcess> list;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (wfBaseProcess.getId() != null) {
            parameterSource.addValue("id",wfBaseProcess.getId());
            paramCount++;
        }
        if (wfBaseProcess.getName() != null){
            parameterSource.addValue("name",wfBaseProcess.getName());
            paramCount++;
        }
        if (wfBaseProcess.getDescription() != null){
            parameterSource.addValue("description",wfBaseProcess.getDescription());
            paramCount++;
        }
        if(wfBaseProcess.getWfServiceId() != null){
            parameterSource.addValue("wfServiceId",wfBaseProcess.getWfServiceId());
            paramCount++;
        }
        if (wfBaseProcess.getWfBaseProcessTypeId() != null){
            parameterSource.addValue("wfBaseProcessTypeId",wfBaseProcess.getWfBaseProcessTypeId());
            paramCount++;
        }

        for (var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (paramCount == 0){
                switch (paramName){
                    case "id"                   -> queryFilter = queryFilter + " where wfbp.id=:id\n";
                    case "name"                 -> queryFilter += " where wfbp.name=:name\n";
                    case "description"          -> queryFilter +=  " where wfbp.description=:description\n";
                    case "wfServiceId"          -> queryFilter +=  " where wfbp.wf_service_id=:wfServiceId\n";
                    case "wfBaseProcessTypeId"  -> queryFilter += " where wfbp.wf_base_process_type_id=:wfBaseProcessTypeId\n";
                }
            }else {
                switch (paramName){
                    case "id"                   -> queryFilter = queryFilter + " and wfbp.id=:id\n";
                    case "name"                 -> queryFilter += " and wfbp.name=:name\n";
                    case "description"          -> queryFilter += " and wfbp.description=:description\n";
                    case "wfServiceId"          -> queryFilter += " and wfbp.wf_service_id=:wfServiceId\n";
                    case "wfBaseProcessTypeId"  -> queryFilter += " and wfbp.wf_base_process_type_id=:wfBaseProcessTypeId\n";
                }
            }
        }

        queryFilter += " order by wfbp.name";

        list = namedParameterJdbcTemplate.query(queryFilter,
                parameterSource,new WFBaseProcessRowMapper());

        return list;
    }
}

package org.speed.big.company.service.repository.jdbc_impl.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow.WFBaseProcessTypeRowMapper;
import org.speed.big.company.service.repository.workflow.WFBaseProcessTypeRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("jdbcWFBaseProcessTypeRepositoryImpl")
public class JdbcWFBaseProcessTypeRepositoryImpl implements WFBaseProcessTypeRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    private static RowMapper<WFBaseProcessType> ROW_MAPPER_WF_BASE_PROCESS_TYPE = BeanPropertyRowMapper
            .newInstance(WFBaseProcessType.class);

    private final String sqlQuery = "select wfbpt.id as wfbpt_id,\n" +
            " wfbpt.name as wfbpt_name from wf_base_process_type wfbpt";


    public JdbcWFBaseProcessTypeRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                    //Укажите имя таблицы, которое будет использоваться для вставки.
                    .withTableName("wf_base_process_type")
                    //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                    .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public WFBaseProcessType save(WFBaseProcessType wfBaseProcessType) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",wfBaseProcessType.getId())
                .addValue("name",wfBaseProcessType.getName());

        if (wfBaseProcessType.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            wfBaseProcessType.setId(newKey.intValue());
        }else if(namedParameterJdbcTemplate.update("update wf_base_process_type " +
                "set name=:name where id=:id",parameterSource) == 0){
            return null;
        }

        return wfBaseProcessType;
    }

    @Override
    public WFBaseProcessType get(int id) {
        String queryGet = sqlQuery + " where wfbpt.id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<WFBaseProcessType> list = namedParameterJdbcTemplate.query(queryGet,
                parameterSource, new WFBaseProcessTypeRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        String queryDelete = "delete from wf_base_process_type wfbpt where wfbpt.id=?";

        return jdbcTemplate.update(queryDelete,id) != 0;
    }

    @Override
    public List<WFBaseProcessType> getAll() {
        return jdbcTemplate.query(sqlQuery, new WFBaseProcessTypeRowMapper());
    }

    @Override
    public List<WFBaseProcessType> filter(WFBaseProcessType wfBaseProcessType) {
        String queryFilter = sqlQuery;
        int paramCount = 0;
        List<WFBaseProcessType> list;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (wfBaseProcessType.getId() != null){
            parameterSource.addValue("id",wfBaseProcessType.getId());
            paramCount++;
        }
        if (wfBaseProcessType.getName() != null)
            parameterSource.addValue("name", wfBaseProcessType.getName());

        for (var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (paramCount == 0){
                switch (paramName){
                    case "id"       -> queryFilter = queryFilter+" where wfbpt.id=:id\n";
                    case "name"     -> queryFilter += " where wfbpt.name=:name\n";
                }
            }else {
                switch (paramName){
                    case "id"       -> queryFilter = queryFilter+" and wfbpt.id=:id\n";
                    case "name"     -> queryFilter += " and wfbpt.name=:name\n";
                }
            }
        }

        queryFilter += "order by wfbpt.name";

        list = namedParameterJdbcTemplate.query(queryFilter
                ,parameterSource, new WFBaseProcessTypeRowMapper());

        return list;
    }
}

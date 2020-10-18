package org.speed.big.company.service.repository.jdbc_impl.workflow;

import org.speed.big.company.service.model.workflow.WFGroup;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow.WFGroupRowMapper;
import org.speed.big.company.service.repository.workflow.WFGroupRepository;
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

@Repository("jdbcWFGroupRepositoryImpl")
public class JdbcWFGroupRepositoryImpl implements WFGroupRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    private static RowMapper<WFGroup> ROW_MAPPER_WF_GROUP = BeanPropertyRowMapper.newInstance(WFGroup.class);

    private final String sqlQuery = "select wfg.id as wfg_id, wfg.name as wfg_name, \n" +
            " wfg.description as wfg_description from wf_group wfg ";

    public JdbcWFGroupRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("wf_group")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public WFGroup save(WFGroup wfGroup) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",wfGroup.getId())
                .addValue("name",wfGroup.getName())
                .addValue("description",wfGroup.getDescription());

        if (wfGroup.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            wfGroup.setId(newKey.intValue());
        }else if (namedParameterJdbcTemplate.update("update wf_group " +
                "set name=:name, description=:description",parameterSource) == 0){
            return null;
        }
        return wfGroup;
    }

    @Override
    public WFGroup get(int id) {
        String queryGet = sqlQuery + " where wfg.id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<WFGroup> list = namedParameterJdbcTemplate.query(queryGet, new WFGroupRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from wf_group where id=?",id) != 0;
    }

    @Override
    public List<WFGroup> getAll() {
        return jdbcTemplate.query(sqlQuery, new WFGroupRowMapper());
    }

    @Override
    public List<WFGroup> filter(WFGroup wfGroup) {
        StringBuilder queryFilter = new StringBuilder();
        queryFilter.append(sqlQuery);
        int paramCount = 0;
        List<WFGroup> list = null;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (wfGroup.getId() != null)
            parameterSource.addValue("id",wfGroup.getId());
        if (wfGroup.getName() != null)
            parameterSource.addValue("name",wfGroup.getName());
        if (wfGroup.getDescription() != null)
            parameterSource.addValue("description",wfGroup.getDescription());

        for (var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (paramCount == 0){
                switch (paramName){
                    case "id"           -> queryFilter.append(" where wfg.id=:id\n");
                    case "name"         -> queryFilter.append(" where wfg.name=:name\n");
                    case "description"  -> queryFilter.append(" where wfg.description=:description\n");
                }
            }else {
                switch (paramName){
                    case "id"           -> queryFilter.append(" and wfg.id=:id\n");
                    case "name"         -> queryFilter.append(" and wfg.name=:name\n");
                    case "description"  -> queryFilter.append(" and wfg.description=:description\n");
                }
            }
            paramCount++;
        }

        queryFilter.append(" order by wfg.name");

        list = namedParameterJdbcTemplate.query(queryFilter.toString()
                , parameterSource, new WFGroupRowMapper());

        return list;
    }
}

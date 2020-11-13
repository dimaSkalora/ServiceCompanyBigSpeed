package org.speed.big.company.service.repository.jdbc_impl.workflow;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.workflow.WFService;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow.WFServiceRowMapper;
import org.speed.big.company.service.repository.workflow.WFServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("jdbcWFServiceRepositoryImpl")
public class JdbcWFServiceRepositoryImpl implements WFServiceRepository {
    /*
     *  JdbcTemplate - это мощный механизм для подключения к базе данных и выполнения SQL-запросов.
     *  Мы можем выполнять все операции с базой данных с помощью класса JdbcTemplate, такие как вставка,
     *  обновление, удаление и извлечение данных из базы данных.
     */
    private JdbcTemplate jdbcTemplate;
    //способ вставки данных по именованному параметру. Таким образом мы используем имена вместо? (Знак вопроса)
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /* SimpleJdbcInsert - многопоточный, многоразовый объект, обеспечивающий удобные возможности вставки для таблицы.
     *  Он обеспечивает обработку метаданных, чтобы упростить код, необходимый для построения основного оператора insert.
     *  Все, что вам нужно указать, - это имя таблицы и Карта, содержащая имена столбцов и значения столбца.
     */
    private SimpleJdbcInsert jdbcInsert;

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    private static RowMapper<WFService> ROW_MAPPER_WF_SERVICE = BeanPropertyRowMapper.newInstance(WFService.class);

    private final String sqlQuery = "select wfs.id as wfs_id, wfs.name as wfs_name \n" +
            "from wf_service wfs \n";

    @Autowired
    public JdbcWFServiceRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("wf_service")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public WFService save(WFService wfService) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", wfService.getId())
                .addValue("name", wfService.getName());

         /*BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства объекта создаёт параметр
        с именем свойства и его значением.*/
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(wfService);

        if (wfService.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            //Number newKey = jdbcInsert.executeAndReturnKey(beanPropertySqlParameterSource);
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            wfService.setId(newKey.intValue());
        }else if (namedParameterJdbcTemplate.update("update wf_service set name=:name " +
                "where id=:id ",parameterSource) == 0){
            return null;
        }

        return wfService;
    }

    @Override
    public WFService get(int id) {
        String queryGet = sqlQuery + " where id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        List<WFService> list = namedParameterJdbcTemplate.query(queryGet, parameterSource, new WFServiceRowMapper());

        return DataAccessUtils.singleResult(list); //Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from wf_service where id=?",id) != 0;
    }

    @Override
    public List<WFService> getAll() {
        return jdbcTemplate.query(sqlQuery, new WFServiceRowMapper());
    }

    @Override
    public List<WFService> filter(WFService wfService) {
        String queryFilter = sqlQuery;
        int paramCount = 0;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (wfService.getId() != null)
            parameterSource.addValue("id", wfService.getId());
        if (wfService.getName() != null)
            parameterSource.addValue("name", wfService.getName());

        for (var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (paramCount == 0){
                switch (paramName){
                    case "id"       -> queryFilter = queryFilter + " where id=:id\n";
                    case "name"     -> queryFilter += " where name=:name\n";
                }
            }else {
                switch (paramName){
                    case "id"       -> queryFilter = queryFilter + " and id=:id\n";
                    case "name"     -> queryFilter += " and name=:name\n";
                }
            }
            paramCount++;
        }

        return namedParameterJdbcTemplate.query(queryFilter,
                parameterSource,new WFServiceRowMapper());
    }

    @Override
    public List<WFService> getWFServiceFromRoles(List<Role> roles) {
        StringBuilder sbRoles = new StringBuilder();
        for (Role role: roles)
            sbRoles.append("\'"+role+"\',");
        String sqlGetWFServiceFromUserRoleByUser = sqlQuery +
                " where wfs.name in ( "+sbRoles+" )";
        List<WFService> list = jdbcTemplate.query(sqlGetWFServiceFromUserRoleByUser
                    ,new WFServiceRowMapper());

        return list;
    }
}

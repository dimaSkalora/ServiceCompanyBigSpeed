package org.speed.big.company.service.repository.jdbc_impl;

import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.repository.RoleTypeRepository;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.RoleTypeRowMapper;
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

@Repository("jdbcRoleTypeRepositoryImpl")
public class JdbcRoleTypeRepositoryImpl implements RoleTypeRepository {
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
    //private static BeanPropertyRowMapper<RoleType> ROW_MAPPER_ROLE_TYPE = BeanPropertyRowMapper.newInstance(RoleType.class);
    private static RowMapper<RoleType> ROW_MAPPER_ROLE_TYPE = BeanPropertyRowMapper.newInstance(RoleType.class);

    private final String sqlQuery = "select rt.id, rt.name from role_type rt";

    public JdbcRoleTypeRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("role_type")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public RoleType save(RoleType roleType) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id",roleType.getId()) //Добавьте параметр к этому источнику параметра.
                .addValue("name",roleType.getName());
        /*        BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства объекта создаёт параметр
        с именем свойства и его значением.*/
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(roleType);
        if (roleType.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            //Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            Number newKey = jdbcInsert.executeAndReturnKey(map);

            roleType.setId(newKey.intValue());
        }else {
            namedParameterJdbcTemplate.update("UPDATE role_type set name=:name where id=:id",parameterSource);
        }

        return roleType;
    }

    @Override
    public RoleType get(int id) {
        /*    String sqlGet = sqlQuery + " where rt.id=?";
        List<RoleType> list = jdbcTemplate.query(sqlGet, new RoleTypeRowMapper(),id);*/

        String sqlGet = sqlQuery + " where rt.id=:id";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<RoleType> list = namedParameterJdbcTemplate.query(sqlGet,parameterSource,new RoleTypeRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM role_type where id=?",id) != 0;
    }

    @Override
    public List<RoleType> getAll() {
        return jdbcTemplate.query(sqlQuery, new RoleTypeRowMapper());
    }

    @Override
    public List<RoleType> filterRoleType(RoleType roleType) {
        List<RoleType> list;
        String sqlFilterRoleType = sqlQuery;
        int count = 0;

        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (roleType.getId() != null)
            parameterSource.addValue("id",roleType.getId());
        if (roleType.getName() != null)
            parameterSource.addValue("name",roleType.getName());

        for(var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if(count == 0){
                switch (paramName){
                    case "id"       -> sqlFilterRoleType = sqlFilterRoleType +" where rt.id=:id";
                    case "name"     -> sqlFilterRoleType += " where rt.name=:name";
                }
            }else {
                switch (paramName){
                    case "id"       -> sqlFilterRoleType = sqlFilterRoleType +" and rt.id=:id";
                    case "name"     -> sqlFilterRoleType += " and rt.name=:name";
                }
            }
            count++;
        }

        list = namedParameterJdbcTemplate.query(sqlFilterRoleType,parameterSource, new RoleTypeRowMapper());

        return list;
    }
}

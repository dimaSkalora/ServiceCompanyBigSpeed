package org.speed.big.company.service.repository.jdbc_impl;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.repository.RoleRepository;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.RoleRowMapper;
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

@Repository("jdbcRoleRepositoryImpl")
public class JdbcRoleRepositoryImpl implements RoleRepository {
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
    //private static BeanPropertyRowMapper<Role> ROW_MAPPER_ROLE = BeanPropertyRowMapper.newInstance(Role.class);
    private static RowMapper<Role> ROW_MAPPER_ROLE = BeanPropertyRowMapper.newInstance(Role.class);

    private final String sqlQuery = "select r.id as r_id, r.name as r_name,\n" +
            " r.description as r_description, r.role_type_id as r_role_type_id,\n " +
            " rt.id as rt_id, rt.name as rt_name from roles r \n" +
            " left join role_type rt on r.role_type_id=rt.id";

    @Autowired
    public JdbcRoleRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("roles")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Role save(Role role) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id",role.getId()) //Добавьте параметр к этому источнику параметра.
                .addValue("name",role.getName())
                .addValue("description",role.getDescription())
                .addValue("roleTypeId",role.getRoleTypeId().getId());

        if (role.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            Number newKey = jdbcInsert.executeAndReturnKey(map);
            role.setId(newKey.intValue());
        }else {
            namedParameterJdbcTemplate.update("update roles set name=:name, description=:description," +
                    " role_type_id=:roleTypeId where id=:id",map);
        }

        return role;
    }

    @Override
    public Role get(int id) {
        String sqlGet = sqlQuery + " where r.id=:id";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<Role> list = namedParameterJdbcTemplate.query(sqlGet,parameterSource,new RoleRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE from roles where id=?",id) != 0;
    }

    @Override
    public List<Role> getAll() {
        return jdbcTemplate.query(sqlQuery,new RoleRowMapper());
    }

    @Override
    public List<Role> filter(Role role) {
        List<Role> list;
        int count = 0;
        String sqlFilterRole = sqlQuery;
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource map = new MapSqlParameterSource();
        if (role.getId() != null)
            map.addValue("id",role.getId());
        if (role.getName() != null)
            map .addValue("name",role.getName());
        if (role.getDescription() != null)
            map.addValue("description",role.getDescription());
        if (role.getRoleTypeId() != null)
            map.addValue("roleTypeId",role.getRoleTypeId().getId());

        for (var entrySet: map.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (count == 0){
                switch (paramName){
                    case "id"           -> sqlFilterRole = sqlFilterRole + " where r.id=:id ";
                    case "name"         -> sqlFilterRole += " where r.name=:name ";
                    case "description"  -> sqlFilterRole += " where r.description=:description ";
                    case "roleTypeId"   -> sqlFilterRole += " where r.role_type_id=:roleTypeId ";
                }
            }else {
                switch (paramName){
                    case "id"           -> sqlFilterRole = sqlFilterRole + " r.and id=:id ";
                    case "name"         -> sqlFilterRole += " and r.name=:name ";
                    case "description"  -> sqlFilterRole += " and r.description=:description ";
                    case "roleTypeId"   -> sqlFilterRole += " and r.roleTypeId=:roleTypeId ";
                }
            }
            count++;
        }

        list = namedParameterJdbcTemplate.query(sqlFilterRole,map, new RoleRowMapper());

        return list;
    }

    @Override
    public List<Role> getRoleFromUserRoleByUser(int userId) {
        return getRoleFromUserRoleByUserRoleType(userId,Integer.MIN_VALUE);
    }

    @Override
    public List<Role> getRoleFromUserRoleByUserRoleType(int userId, int roleTypeId) {
        String sqlGetRoleFromUserRoleByUser = sqlQuery +
                " left join user_roles ur on ur.role_id=r.id\n" +
                " where ur.user_id =: userId\n ";

        List<Role> list;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("userId",userId);
        if (roleTypeId != Integer.MIN_VALUE) {
            sqlGetRoleFromUserRoleByUser += " and r.role_type_id:=roleTypeId\n";
            parameterSource.addValue("roleTypeId",roleTypeId);
        }

        list = namedParameterJdbcTemplate.query(sqlGetRoleFromUserRoleByUser
                ,parameterSource, new RoleRowMapper());

        return list;
    }
}

package org.speed.big.company.service.repository.jdbc_impl;

import org.speed.big.company.service.model.UserRole;
import org.speed.big.company.service.repository.UserRoleRepository;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.UserRoleRowMapper;
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

@Repository("jdbcUserRoleRepositoryImpl")
public class JdbcUserRoleRepositoryImpl implements UserRoleRepository {
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
    //private static BeanPropertyRowMapper<UserRole> ROW_MAPPER_USER_ROLE = BeanPropertyRowMapper.newInstance(UserRole.class);
    private static RowMapper<UserRole> ROW_MAPPER_USER_ROLE = BeanPropertyRowMapper.newInstance(UserRole.class);


    private final String sqlQuery = " select ur.id as ur_id, ur.user_id as ur_user_id,\n" +
            " ur.role_id as ur_role_id, ur.date_time as ur_date_time, ur.comment as ur_comment,\n" +
            " u.id as u_id, u.name as u_name,\n " +
            " u.email as u_email, u.password as u_password,\n " +
            " u.phone as u_phone, u.registered as u_registered, u.enabled as u_enabled,\n " +
            " r.id as r_id, r.name as r_name,\n" +
            " r.description as r_description, r.role_type_id as r_role_type_id\n " +
            " from user_roles ur " +
            " left join users u on ur.user_id=u.id \n" +
            " left join roles r on ur.role_id=r.id \n";

    public JdbcUserRoleRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("user_roles")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public UserRole save(UserRole userRole) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
               .addValue("id",userRole.getId())
               .addValue("userId",userRole.getUserId().getId())
                .addValue("roleId",userRole.getRoleId().getId())
                .addValue("dataTime",userRole.getDateTime())
                .addValue("comment",userRole.getComment());

        if (userRole.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            userRole.setId(newKey.intValue());
        }else {
            namedParameterJdbcTemplate.update("update user_roles set user_id=:userId, role_id=:roleId," +
                    " date_time=:dataTime, comment:=comment where id=:id",parameterSource);
        }

        return userRole;
    }

    @Override
    public UserRole get(int id) {
        String sqlGet = sqlQuery + " where ur.id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<UserRole> list = namedParameterJdbcTemplate.query(sqlGet,parameterSource, new UserRoleRowMapper());

        return DataAccessUtils.singleResult(list); //Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from user_roles where id=?",id) != 0;
    }

    @Override
    public List<UserRole> getAll() {
        return jdbcTemplate.query(sqlQuery,new UserRoleRowMapper());
    }

    @Override
    public List<UserRole> filter(UserRole userRole) {
        String sqlFilter = sqlQuery;
        int count = 0;
        List<UserRole> list = null;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (userRole.getId() != null)
            parameterSource.addValue("id",userRole.getId());
        if (userRole.getUserId() != null)
            parameterSource.addValue("userId",userRole.getUserId());
        if (userRole.getRoleId() != null)
            parameterSource.addValue("roleId",userRole.getRoleId().getId());
        if (userRole.getDateTime() != null)
            parameterSource.addValue("dateTime",userRole.getDateTime());
        if (userRole.getComment() != null)
            parameterSource.addValue("comment",userRole.getComment());

        for (var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (count == 0){
                switch (paramName){
                    case "id"       -> sqlFilter = sqlFilter + " where id=:id \n";
                    case "userId"   -> sqlFilter += " where user_id=:userId\n";
                    case "roleId"   -> sqlFilter += " where role_id=:roleId\n";
                    case "dateTime" -> sqlFilter += " where date_time=:dateTime\n";
                    case "comment" -> sqlFilter += " where comment=:comment\n";
                }
            }else {
                switch (paramName){
                    case "id"       -> sqlFilter = sqlFilter + " and id=:id \n";
                    case "userId"   -> sqlFilter += " and user_id=:userId\n";
                    case "roleId"   -> sqlFilter += " and role_id=:roleId\n";
                    case "dateTime" -> sqlFilter += " and date_time=:dateTime\n";
                    case "comment" -> sqlFilter += " and comment=:comment\n";
                }
            }
            count++;
        }

        list = namedParameterJdbcTemplate.query(sqlFilter,parameterSource, new UserRoleRowMapper());

        return list;
    }
}

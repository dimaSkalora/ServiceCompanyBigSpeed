package org.speed.big.company.service.repository.jdbc_impl;

import org.speed.big.company.service.model.User;
import org.speed.big.company.service.repository.UserRepository;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.UserRowMapper;
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
import java.time.LocalDate;
import java.util.List;

@Repository("jdbcUserRepositoryImpl")
public class JdbcUserRepositoryImpl implements UserRepository {
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
    //private static BeanPropertyRowMapper<User> ROW_MAPPER_USER = BeanPropertyRowMapper.newInstance(User.class);
    private static RowMapper<User> ROW_MAPPER_USER = BeanPropertyRowMapper.newInstance(User.class);

    private final String sqlQuery = "select u.id as u_id, u.name as u_anme,\n " +
            " u.email as u_email, u.password as u_password,\n " +
            "u.phone as u_phone, u.registered as u_registered, u.enabled as u_enabled\n " +
            " from users u";

    public JdbcUserRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("users")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User get(int id) {
    /*    String sqlGet = sqlQuery + " where u_id=?";
        List<User> list = jdbcTemplate.query(sqlGet, new UserRowMapper(),id);*/

        String sqlGet = sqlQuery + " where u_id=:id";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<User> list = namedParameterJdbcTemplate.query(sqlGet,parameterSource, new UserRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?",id) != 0;
    }

    @Override
    public List<User> getAll() {
        List<User> list = jdbcTemplate.query(sqlQuery, new UserRowMapper());
        return list;
    }

    @Override
    public List<User> getBetweenRegistered(LocalDate startDate, LocalDate endDate) {
       /* String sqlBetweenRegistered = sqlQuery+" where u.registered between ? and ?\n" +
                " order by u.registered desc";// DESC - по убыванию
        List<User> list = jdbcTemplate.query(sqlBetweenRegistered, new UserRowMapper(),startDate,endDate);
*/
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("startDate", startDate)
                .addValue("endDate", endDate);
        String sqlBetweenRegistered = sqlQuery+" where u.registered between :startDate and :endDate\n" +
                " order be u.registered DESC";// DESC - по убыванию
        List<User> list = namedParameterJdbcTemplate.query(sqlBetweenRegistered
                ,parameterSource,new UserRowMapper());
        return list;
    }

    @Override
    public List<User> filterUser(User user) {
        return filterUser(user,null);
    }

    @Override
    public List<User> filterUser(User user, String sqlCondition) {
        List<User> list;
        String sqlFilterUser = sqlQuery;
        int count = 0;

        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (user.getId() != null)
            parameterSource.addValue("id",user.getId());
        if (user.getName() != null)
            parameterSource.addValue("name",user.getName());
        if (user.getEmail() != null)
            parameterSource.addValue("email",user.getEmail());
        if (user.getPassword() != null)
            parameterSource.addValue("password",user.getPassword());
        if (user.getPhone() != null)
            parameterSource.addValue("phone",user.getPhone());
        if (user.getRegistered() != null)
            parameterSource.addValue("registered",user.getRegistered());
        if (user.isEnabled() != null)
            parameterSource.addValue("enabled",user.isEnabled());

        for (var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (count == 0){
                switch (paramName){
                    case "id"           -> sqlFilterUser = sqlFilterUser +" where u.id=:id";
                    case "name"         -> sqlFilterUser += " where u.name=:name";
                    case "email"        -> sqlFilterUser += " where u.email=:email";
                    case "password"     -> sqlFilterUser += " where u.password=:password";
                    case "phone"        -> sqlFilterUser += " where u.phone=:phone";
                    case "registered"   -> sqlFilterUser += " where u.registered=:registered";
                    case "enabled"      -> sqlFilterUser += " where u.enabled=:enabled";
                }
            }else {
                switch (paramName){
                    case "id"           -> sqlFilterUser = sqlFilterUser +" and u.id=:id";
                    case "name"         -> sqlFilterUser += " and u.name=:name";
                    case "email"        -> sqlFilterUser += " and u.email=:email";
                    case "password"     -> sqlFilterUser += " and u.password=:password";
                    case "phone"        -> sqlFilterUser += " and u.phone=:phone";
                    case "registered"   -> sqlFilterUser += " and u.registered=:registered";
                    case "enabled"      -> sqlFilterUser += " and u.enabled=:enabled";
                }
            }
            count++;
        }

        if((sqlCondition != null) && (!sqlCondition.equals("")))
            if (parameterSource.getParameterNames().length > 0)
                sqlFilterUser += " and ( "+sqlCondition+" ) ";
            else
                sqlFilterUser = sqlFilterUser + " where ("+sqlCondition+" ) ";

        list = namedParameterJdbcTemplate.query(sqlFilterUser,parameterSource, new UserRowMapper());

        return list;
    }
}

package org.speed.big.company.service.repository.jdbc_impl.workflow;

import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.speed.big.company.service.repository.WFPackageStatusRepository;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow.WFPackageStatusRowMapper;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("jdbcWFPackageStatusRepositoryImpl")
@Transactional(readOnly = true)
public class JdbcWFPackageStatusRepositoryImpl implements WFPackageStatusRepository {

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
    private static RowMapper<WFPackageStatus> ROW_MAPPER_WF_PACKAGE_STATUS = BeanPropertyRowMapper.newInstance(WFPackageStatus.class);

    private final String sqlQuery = "select wfps.id as wfps_id, wfps.name as wfps_name \n" +
            "from wf_package_status wfps \n";

    public JdbcWFPackageStatusRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("wf_package_status")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    @Override
    public WFPackageStatus save(WFPackageStatus wfPackageStatus) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", wfPackageStatus.getId())
                .addValue("name", wfPackageStatus.getName());

         /*BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства объекта создаёт параметр
        с именем свойства и его значением.*/
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(wfPackageStatus);
        if (wfPackageStatus.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            //Number newKey = jdbcInsert.executeAndReturnKey(beanPropertySqlParameterSource);
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);

            wfPackageStatus.setId(newKey.intValue());
        }else if (namedParameterJdbcTemplate.update("update wf_package_status set name =:name " +
                " wher id=:id",parameterSource) == 0){
            return null;
        }

        return wfPackageStatus;
    }

    @Override
    public WFPackageStatus get(int id) {
 /*       String sqlQueryGet = sqlQuery + " where id=? ";
        List<WFPackageStatus> list = jdbcTemplate.query(sqlQueryGet,new WFPackageStatusRowMapper(),id);
        return DataAccessUtils.singleResult(list); //Возвращает один объект результата из данной коллекции.*/

        String sqlQieryGet = sqlQuery + " where id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<WFPackageStatus> list = namedParameterJdbcTemplate.query(sqlQieryGet,
                parameterSource, new WFPackageStatusRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from wf_package_status where id=?",id) != 0;
    }

    @Override
    public List<WFPackageStatus> getAll() {
        return jdbcTemplate.query(sqlQuery, new WFPackageStatusRowMapper());
    }

    @Override
    public List<WFPackageStatus> filter(WFPackageStatus wfPackageStatus) {
        String sqlFilter = sqlQuery;
        int paramCount = 0;
        List<WFPackageStatus> list;
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (wfPackageStatus.getId() != null)
            parameterSource.addValue("id", wfPackageStatus.getId());
        if (wfPackageStatus.getName() != null)
            parameterSource.addValue("name", wfPackageStatus.getName());

        for (var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (paramCount == 0){
                switch (paramName){
                    case "id"   -> sqlFilter = sqlFilter + " where id=:id \n";
                    case "name" -> sqlFilter += " where name=:name \n";
                }
            }else {
                switch (paramName){
                    case "id"   -> sqlFilter = sqlFilter + " and id=:id \n";
                    case "name" -> sqlFilter += " and name=:name \n";
                }
            }
            paramCount++;
        }

        list = namedParameterJdbcTemplate.query(sqlFilter,
                parameterSource,new WFPackageStatusRowMapper());

        return list;
    }
}

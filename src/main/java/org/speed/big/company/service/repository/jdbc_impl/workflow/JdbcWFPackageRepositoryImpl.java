package org.speed.big.company.service.repository.jdbc_impl.workflow;

import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow.WFPackageRowMapper;
import org.speed.big.company.service.repository.workflow.WFPackageRepository;
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

@Repository("jdbcWFPackageRepositoryImpl")
public class JdbcWFPackageRepositoryImpl implements WFPackageRepository {

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
    private static RowMapper<WFPackage> ROW_MAPPER_WF_PACKAGE = BeanPropertyRowMapper.newInstance(WFPackage.class);

    private final String sqlQuery = "select wfp.id as wfp_id, wfp.name as wpf_name,\n" +
            "wfp.date_registration as wfp_date_registration, wfp.customer_name as wfp_customer_name,\n" +
            "wfp.customer_address as wfp_customer_address" +
            "from wf_package wfp " +
            "left join wf_package_status wfps on wfp.wf_package_status_id=wfps.id\n" +
            "left join wf_service wfs on wfp.wf_service_id=wfs.id\n";

    public JdbcWFPackageRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("wf_package")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public WFPackage save(WFPackage wfPackage) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", wfPackage.getId())
                .addValue("name", wfPackage.getName())
                .addValue("dateRegistration", wfPackage.getDateRegistration())
                .addValue("customerName", wfPackage.getCustomerName())
                .addValue("customerAddress", wfPackage.getCustomerAddress())
                .addValue("customerAddressJur", wfPackage.getCustomerAddressJur())
                .addValue("customerPhone", wfPackage.getCustomerPhone())
                .addValue("customerEmail", wfPackage.getCustomerEmail())
                .addValue("contractNumber", wfPackage.getContractNumber())
                .addValue("description", wfPackage.getDescription())
                .addValue("userAdd", wfPackage.getUserAdd())
                .addValue("dateAdd", wfPackage.getDateAdd())
                .addValue("userEdit", wfPackage.getUserEdit())
                .addValue("dateEdit", wfPackage.getDateEdit())
                .addValue("wfServiceId", wfPackage.getWfServiceId().getId())
                .addValue("wfPackageStatusId", wfPackage);

        if (wfPackage.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            wfPackage.setId(newKey.intValue());
        }else if(namedParameterJdbcTemplate.update("update wf_package set name=:name, " +
                " date_registration=:dateRegistration, customer_name=:customerName," +
                " customer_address=:customerAddress, customer_address_jur=:customerAddressJur," +
                " customer_phone=:customerPhone, customer_email=:customerEmail," +
                " contract_number=:contractNumber, description=:description," +
                " user_add=:userAdd, date_add=:dateAdd, user_edit=:userEdit," +
                " date_edit=:dateEdit, wf_service_id=:wfServiceId, " +
                " wf_package_status_id=:wfPackageStatusId where id=:id" +
                "",parameterSource) == 0){
            return null;
        }

        return wfPackage;
    }

    @Override
    public WFPackage get(int id) {
        String queryGet = sqlQuery + " where wfp.id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<WFPackage> list = namedParameterJdbcTemplate.query(queryGet,
                parameterSource, new WFPackageRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from wf_package where id=?",id) != 0;
    }

    @Override
    public List<WFPackage> getAll() {
        return jdbcTemplate.query(sqlQuery, new WFPackageRowMapper());
    }

    @Override
    public List<WFPackage> filter(WFPackage wfPackage) {
        return null;
    }
}

package org.speed.big.company.service.repository.jdbc_impl.workflow;

import org.speed.big.company.service.model.workflow.WFProcess;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow.WFProcessRowMapper;
import org.speed.big.company.service.repository.workflow.WFProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

@Repository("jdbcWFProcessRepositoryImpl")
public class JdbcWFProcessRepositoryImpl implements WFProcessRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    private static RowMapper<WFProcess> ROW_MAPPER_WF_PROCESS = BeanPropertyRowMapper.newInstance(WFProcess.class);

    private final String sqlQuery = "select wfp.id as wfp_id, wfp.start_date as wfp_start_date,\n" +
            " wfp.final_date as wfp_final_date, wfp.description as wfp_description,\n" +
            " wfp.date_edit as wfp_date_edit, wfp.user_edit as wfp_user_edit, wfp.wf_package_id as wfp_wf_package_id,\n" +
            " wfp.wf_base_process_id as wfp_wf_base_process_id, wfp.wf_process_status_id as wfp_wf_process_status_id,\n" +
            " wfp.id as wfp_id, wfp.name as wfp_name,\n" +
            " wfp.date_registration as wfp_date_registration, wfp.customer_name as wfp_customer_name,\n" +
            " wfp.customer_address as wfp_customer_address, wfp.customer_address_jur as wfp_customer_address_jur,\n" +
            " wfp.customer_phone as wfp_customer_phone, wfp.customer_email as wfp_customer_email,\n" +
            " wfp.contract_number as wfp_contract_number, wfp.description as wfp_description,\n" +
            " wfp.user_add as wfp_user_add, wfp.date_add as wfp_date_add,\n" +
            " wfp.user_edit as wfp_user_edit, wfp.date_edit as wfp_date_edit,\n" +
            " wfp.wf_service_id as wfp_wf_service_id, wfp.wf_package_status_id as wfp_wf_package_status_id,\n" +
            " wfps.id as wfps_id, wfps.name as wfps_name,\n" +
            " wfs.id as wfs_id, wfs.name as wfs_name,\n" +
            " wfbp.id as wfbp_id, wfbp.name as wfbp_name,\n" +
            " wfbp.description as wfbp_description, wfbp.wf_service_id as wfbp_wf_service_id,\n" +
            " wfbp.wf_base_process_type_id as wfbp_wf_base_process_type_id,\n" +
            " wfpstatus.id as wfpstatus_id, wfpstatus.name as wfpstatus_name,\n"+
            " wfs.id as wfs_id, wfs.name as wfs_name \n" +
            " from wf_process wfp" +
            " left join wf_package wfpack ON wfpack.id = wfp.wf_package_id\n" +
            " left join wf_base_process wfbp ON wfbp.id = wfp.wf_base_process_id\n" +
            " left join wf_service wfs on wfbp.wf_service_id=wfs.id\n"+
            " left join wf_process_status_id wfps ON wfps.id = wfp.wf_process_status_id\n";

    @Autowired
    public JdbcWFProcessRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("wf_process")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public WFProcess save(WFProcess wfProcess) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",wfProcess.getId())
                .addValue("startDate",wfProcess.getStartDate())
                .addValue("finalDate",wfProcess.getFinalDate())
                .addValue("description",wfProcess.getDescription())
                .addValue("dateEdit",wfProcess.getDateEdit())
                .addValue("userEdit",wfProcess.getUserEdit())
                .addValue("wfPackageId",wfProcess.getWfPackageId().getId())
                .addValue("wfBaseProcessId",wfProcess.getWfBaseProcessId().getId())
                .addValue("wfProcessStatusId",wfProcess.getWfProcessStatusId().getId());

        if (wfProcess.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            wfProcess.setId(newKey.intValue());
        }else if (namedParameterJdbcTemplate.update("update wf_process set start_date=:startDate,\n" +
                "final_date=:finalDate, description=:description, date_edit=:dateEdit,\n" +
                "user_edit=:userEdit, wf_package_id=:wfPackageId, wf_base_process_id=:wfBaseProcessId,\n" +
                "wf_process_status_id=:wfProcessStatusId",parameterSource) == 0){
            return null;
        }

        return wfProcess;
    }

    @Override
    public WFProcess get(int id) {
        String queryGet = sqlQuery + " wfp.id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);

        List<WFProcess> list = namedParameterJdbcTemplate.query(queryGet
                ,parameterSource, new WFProcessRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from wf_process where id=?",id) != 0;
    }

    @Override
    public List<WFProcess> getAll() {
        return jdbcTemplate.query(sqlQuery, new WFProcessRowMapper());
    }

    @Override
    public List<WFProcess> filter(WFProcess wfProcess) {
        return filter(wfProcess,null);
    }

    @Override
    public List<WFProcess> filter(WFProcess wfProcess, String sqlCondition) {
        StringBuilder queryFilter = new StringBuilder();
        queryFilter.append(sqlQuery);
        int paramCount = 0;
        List<WFProcess> list;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (wfProcess.getId() != 0)
            parameterSource.addValue("id", wfProcess.getId());
        if (wfProcess.getStartDate() != null)
            parameterSource.addValue("startDate",wfProcess.getStartDate());
        if (wfProcess.getFinalDate() != null)
            parameterSource.addValue("finalDate",wfProcess.getFinalDate());
        if (wfProcess.getDescription() != null)
            parameterSource.addValue("description",wfProcess.getDescription());
        if (wfProcess.getDateEdit() != null)
            parameterSource.addValue("dateEdit",wfProcess.getDateEdit());
        if (wfProcess.getUserEdit() != null)
            parameterSource.addValue("userEdit",wfProcess.getUserEdit());
        if (wfProcess.getWfPackageId() != null)
            parameterSource.addValue("wfPackageId",wfProcess.getWfPackageId().getId());
        if (wfProcess.getWfBaseProcessId() != null)
            parameterSource.addValue("wfBaseProcessId",wfProcess.getWfBaseProcessId().getId());
        if (wfProcess.getWfProcessStatusId() != null)
            parameterSource.addValue("wfProcessStatusId",wfProcess.getWfProcessStatusId().getId());

        for (var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (paramCount == 0){
                switch (paramName){
                    case "id"               -> queryFilter.append(" where wfp.id=:id");
                    case "startDate"        -> queryFilter.append(" where wfp.start_date=:startDate");
                    case "finalDate"        -> queryFilter.append(" where wfp.final_date=:finalDate");
                    case "description"      -> queryFilter.append(" where wfp.description=:description");
                    case "dateEdit"         -> queryFilter.append(" where wfp.date_edit=:dateEdit");
                    case "userEdit"         -> queryFilter.append(" where wfp.user_edit=:userEdit");
                    case "wfPackageId"      -> queryFilter.append(" where wfp.wf_package_id=:wfPackageId");
                    case "wfBaseProcessId"  -> queryFilter.append(" where wfp.wf_base_process_id=:wfBaseProcessId");
                    case "wfProcessStatusId"-> queryFilter.append(" where wfp.wf_process_status_id=:wfProcessStatusId");
                }
            }else {
                switch (paramName){
                    case "id"               -> queryFilter.append(" and wfp.id=:id");
                    case "startDate"        -> queryFilter.append(" and wfp.start_date=:startDate");
                    case "finalDate"        -> queryFilter.append(" and wfp.final_date=:finalDate");
                    case "description"      -> queryFilter.append(" and wfp.description=:description");
                    case "dateEdit"         -> queryFilter.append(" and wfp.date_edit=:dateEdit");
                    case "userEdit"         -> queryFilter.append(" and wfp.user_edit=:userEdit");
                    case "wfPackageId"      -> queryFilter.append(" and wfp.wf_package_id=:wfPackageId");
                    case "wfBaseProcessId"  -> queryFilter.append(" and wfp.wf_base_process_id=:wfBaseProcessId");
                    case "wfProcessStatusId"-> queryFilter.append(" and wfp.wf_process_status_id=:wfProcessStatusId");
                }
            }
            paramCount++;
        }

        if ((sqlCondition != null) && !("".equals(sqlCondition))){
            if (parameterSource.getParameterNames().length > 0)
                queryFilter.append(" and ( "+sqlCondition+" )");
            else
                queryFilter.append(" where ( "+sqlCondition+" )");
        }

        queryFilter.append(" order by wfp.start_date DESC");// DESC - по убыванию.

        list = namedParameterJdbcTemplate.query(String.valueOf(queryFilter),
                parameterSource, new WFProcessRowMapper());

        return list;
    }

    @Override
    public List<WFProcess> getListWFProcess(int wfServiceId, int wfProcessStatusId) {
        String queryGetListWFProcess = sqlQuery;
        List<WFProcess> list = null;

        queryGetListWFProcess +=
                " where wfbp.wf_service_id=:wfServiceId\n " +
                        " and wfpro.wf_process_status_id=:wfProcessStatusId\n"+
                        " order by wfpm.start_date_time desc "; // по убиванию

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("wfServiceId",wfServiceId)
                .addValue("wfProcessStatusId",wfProcessStatusId);

        list = namedParameterJdbcTemplate.query(queryGetListWFProcess,parameterSource,new WFProcessRowMapper());

        return list;
    }
}

package org.speed.big.company.service.repository.jdbc_impl.workflow;

import org.speed.big.company.service.model.workflow.WFProcessMovement;
import org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow.WFProcessMovementRowMapper;
import org.speed.big.company.service.repository.workflow.WFProcessMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("jdbcWFProcessMovementRepositoryImpl")
@Transactional(readOnly = true)
public class JdbcWFProcessMovementRepositoryImpl implements WFProcessMovementRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    private static RowMapper<WFProcessMovement> ROW_MAPPER_WF_PROCESS_MOVEMENT = BeanPropertyRowMapper.newInstance(WFProcessMovement.class);

    private final String sqlQuery = "select wfpm.id as wfpm_id, wfpm.start_date_time as wfpm_start_date_time, wfpm.final_date_time as wfpm_final_date_time,\n" +
            "wfpm.is_completed as wfpm_is_completed, wfpm.description as wfpm_description, wfpm.date_edit as wfpm_date_edit,\n" +
            "wfpm.user_edit as wfpm_user_edit, wfpm.user_id as wfpm_user_id, wfpm.wf_package_id as wfpm_wf_package_id,\n" +
            "wfpm.wf_state_id as wfpm_wf_state_id, wfpm.wf_process_id as wfpm_wf_process_id,\n" +
            "wfpm.wf_base_process_id as wfpm_wf_base_process_id, wfpm.is_last as wfpm_is_last,\n" +
            /*------------------------------users-----------------------------------------*/
            "u.id as u_id, u.name as u_name, u.email as u_email, u.password as u_password,\n " +
            "u.phone as u_phone, u.registered as u_registered, u.enabled as u_enabled\n " +
            /*------------------------------wf_package-----------------------------------------*/
            "wfp.id as wfp_id, wfp.name as wfp_name,\n" +
            "wfp.date_registration as wfp_date_registration, wfp.customer_name as wfp_customer_name,\n" +
            "wfp.customer_address as wfp_customer_address, wfp.customer_address_jur as wfp_customer_address_jur,\n" +
            "wfp.customer_phone as wfp_customer_phone, wfp.customer_email as wfp_customer_email,\n" +
            "wfp.contract_number as wfp_contract_number, wfp.description as wfp_description,\n" +
            "wfp.user_add as wfp_user_add, wfp.date_add as wfp_date_add,\n" +
            "wfp.user_edit as wfp_user_edit, wfp.date_edit as wfp_date_edit,\n" +
            "wfp.wf_service_id as wfp_wf_service_id, wfp.wf_package_status_id as wfp_wf_package_status_id,\n" +
            "wfps.id as wfps_id, wfps.name as wfps_name,\n" +
            "wfs.id as wfs_id, wfs.name as wfs_name\n" +
            /*------------------------------wf_process_state-----------------------------------------*/
            " wfps.id as wfps_id,\n" +
            " wfps.name as wfps_name, wfps.role_id as wfps_role_id,\n" +
            " wfps.wf_group_id as wfps_wf_group_id, wfps.description as wfps_description,\n" +
            " r.id as r_id, r.name as r_name, r.description as r_description,\n" +
            " r.role_type_id as r_role_type_id, rt.id as rt_id, rt.name as rt_name,\n" +
            " wfg.id as wfg_id, wfg.name as wfg_name, \n" +
            " wfg.description as wfg_description\n" +
            /*------------------------------wf_process-----------------------------------------*/
            " wfpro.id as wfpro_id, wfpro.start_date as wfpro_start_date,\n" +
            " wfpro.final_date as wfpro_final_date, wfpro.description as wfpro_description,\n" +
            " wfpro.date_edit as wfpro_date_edit, wfpro.user_edit as wfpro_user_edit, wfpro.wf_package_id as wfpro_wf_package_id,\n" +
            " wfpro.wf_base_process_id as wfpro_wf_base_process_id, wfpro.wf_process_status_id as wfpro_wf_process_status_id,\n" +
            " wfps.id as wfps_id, wfps.name as wfps_name,\n" +
            " wfs.id as wfs_id, wfs.name as wfs_name,\n" +
            " wfbp.id as wfbp_id, wfbp.name as wfbp_name,\n" +
            " wfbp.description as wfbp_description, wfbp.wf_service_id as wfbp_wf_service_id,\n" +
            " wfbp.wf_base_process_type_id as wfbp_wf_base_process_type_id,\n" +
            " wfpstatus.id as wfpstatus_id, wfpstatus.name as wfpstatus_name\n"+
            "from wf_process_movement wfpm\n" +
            /*------------------------------left join users-----------------------------------------*/
            "left join users u on u.id=wfpm.user_id\n" +
            /*------------------------------left join wf_package-------------------------------------*/
            "left join wf_package wfp on wfp.id=wfpm.wf_package_id\n" +
            "left join wf_package_status wfps on wfp.wf_package_status_id=wfps.id\n" +
            "left join wf_service wfs on wfp.wf_service_id=wfs.id\n"+
            /*------------------------------left join wf_process_state-----------------------------------*/
            "left join wf_process_state wfprostate on wfprostate.id=wfpm.wf_state_id\n" +
            " left join role r on r.id = wfprostate.role_id\n" +
            " left join wf_group wfg on wfg.id=wfprostate.group_id\n"+
            /*------------------------------left join wf_process-----------------------------------*/
            " left join wf_process wfpro on wfpro.id=wfpm.wf_process_id" +
            " left join wf_base_process wfbp ON wfbp.id = wfpro.wf_base_process_id\n" +
            " left join wf_process_status_id wfpstatus ON wfpstatus.id = wfpro.wf_process_status_id\n"+
            "";

    @Autowired
    public JdbcWFProcessMovementRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("wf_process_movement")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    @Override
    public WFProcessMovement save(WFProcessMovement wfProcessMovement) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",wfProcessMovement)
                .addValue("startDateTime",wfProcessMovement.getStartDateTime())
                .addValue("finalDateTime",wfProcessMovement.getFinalDateTime())
                .addValue("isCompleted",wfProcessMovement.isCompleted())
                .addValue("description",wfProcessMovement.getDescription())
                .addValue("dateEdit",wfProcessMovement.getDateEdit())
                .addValue("userEdit",wfProcessMovement.getUserEdit())
                .addValue("userId",wfProcessMovement.getUserId().getId())
                .addValue("wfPackageId",wfProcessMovement.getWfPackageId().getId())
                .addValue("wfStateId",wfProcessMovement.getWfStateId().getId())
                .addValue("wfProcessId",wfProcessMovement.getWfProcessId().getId())
                .addValue("wfBaseProcessId",wfProcessMovement.getWfBaseProcessId().getId())
                .addValue("isLast",wfProcessMovement.isLast());

        if (wfProcessMovement.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            wfProcessMovement.setId(newKey.intValue());
        }else if (namedParameterJdbcTemplate.update("update wf_process_movement set start_date_time=:startDateTime,\n" +
                "final_date_time=:finalDateTime, is_completed=:isCompleted,description=:description,\n" +
                "date_edit=:dateEdit,user_edit=:userEdit,user_id=:userId,wf_package_id=:wfPackageId,\n" +
                "wf_state_id=:wfStateId,wf_process_id=:wfProcessId,wf_base_process_id=:wfBaseProcessId,\n" +
                "is_last=:isLast where id=:id ",parameterSource) == 0){
            return null;
        }

        return wfProcessMovement;
    }

    @Override
    public WFProcessMovement get(int id) {
        String queryGet = sqlQuery + " wfpm.id=:id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);

        //WFProcessMovement wfProcessMovement = namedParameterJdbcTemplate.queryForObject(queryGet,parameterSource, new WFProcessMovementRowMapper());

        List<WFProcessMovement> list = namedParameterJdbcTemplate.query(queryGet,parameterSource, new WFProcessMovementRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from wf_process_movement where id=?",id) != 0;
    }

    @Override
    public List<WFProcessMovement> getAll() {
        return jdbcTemplate.query(sqlQuery, new WFProcessMovementRowMapper());
    }

    @Override
    public List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement) {
        return filter(wfProcessMovement,null);
    }

    @Override
    public List<WFProcessMovement> filter(WFProcessMovement wfProcessMovement, String sqlCondition) {
        String queryFilter = sqlQuery;
        int paramCount = 0;
        List<WFProcessMovement> list = null;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (wfProcessMovement.getId() != null)
            parameterSource.addValue("id",wfProcessMovement.getId());
        if (wfProcessMovement.getStartDateTime() != null)
            parameterSource.addValue("startDateTime",wfProcessMovement.getStartDateTime());
        if (wfProcessMovement.getFinalDateTime() != null)
            parameterSource.addValue("finalDateTime",wfProcessMovement.getFinalDateTime());
        parameterSource.addValue("isCompleted",wfProcessMovement.isCompleted());
        if (wfProcessMovement.getDescription() != null)
            parameterSource.addValue("description",wfProcessMovement.getDescription());
        if (wfProcessMovement.getDateEdit() != null)
            parameterSource.addValue("dateEdit",wfProcessMovement.getDateEdit());
        if (wfProcessMovement.getUserEdit() != null)
            parameterSource.addValue("userEdit",wfProcessMovement.getUserEdit());
        if (wfProcessMovement.getUserId() != null)
            parameterSource.addValue("userId",wfProcessMovement.getUserId().getId());
        if (wfProcessMovement.getWfPackageId() != null)
            parameterSource.addValue("wfPackageId",wfProcessMovement.getWfPackageId().getId());
        if (wfProcessMovement.getWfStateId() != null)
            parameterSource.addValue("wfStateId",wfProcessMovement.getWfStateId().getId());
        if (wfProcessMovement.getWfProcessId() != null)
            parameterSource.addValue("wfProcessId",wfProcessMovement.getWfProcessId().getId());
        if (wfProcessMovement.getWfBaseProcessId() != null)
            parameterSource.addValue("wfBaseProcessId",wfProcessMovement.getWfBaseProcessId().getId());
        parameterSource.addValue("isLast",wfProcessMovement.isLast());

        for (var entrySet: parameterSource.getValues().entrySet()){
            String paramName = entrySet.getKey();
            if (paramCount == 0){
                switch (paramName){
                    case "id"               -> queryFilter = queryFilter +" where wfpm.id=:id\n";
                    case "startDateTime"    -> queryFilter += " where wfpm.start_date_time=:startDateTime\n";
                    case "finalDateTime"    -> queryFilter += " where wfpm.final_date_time=:finalDateTime\n";
                    case "isCompleted"      -> queryFilter += " where wfpm.is_completed=:isCompleted\n";
                    case "description"      -> queryFilter += " where wfpm.description=:description\n";
                    case "dateEdit"         -> queryFilter += " where wfpm.date_edit=:dateEdit\n";
                    case "userEdit"         -> queryFilter += " where wfpm.user_edit=:userEdit\n";
                    case "userId"           -> queryFilter += " where wfpm.user_id=:userId\n";
                    case "wfPackageId"      -> queryFilter += " where wfpm.wf_package_id=:wfPackageId\n";
                    case "wfStateId"        -> queryFilter += " where wfpm.wf_state_id=:wfStateId\n";
                    case "wfProcessId"      -> queryFilter += " where wfpm.wf_process_id=:wfProcessId\n";
                    case "wfBaseProcessId"  -> queryFilter += " where wfpm.wf_base_process_id=:wfBaseProcessId\n";
                    case "isLast"           -> queryFilter += " where wfpm.is_last=:isLast\n";
                }
            }else {
                switch (paramName){
                    case "id"               -> queryFilter = queryFilter +" and wfpm.id=:id\n";
                    case "startDateTime"    -> queryFilter += " and wfpm.start_date_time=:startDateTime\n";
                    case "finalDateTime"    -> queryFilter += " and wfpm.final_date_time=:finalDateTime\n";
                    case "isCompleted"      -> queryFilter += " and wfpm.is_completed=:isCompleted\n";
                    case "description"      -> queryFilter += " and wfpm.description=:description\n";
                    case "dateEdit"         -> queryFilter += " and wfpm.date_edit=:dateEdit\n";
                    case "userEdit"         -> queryFilter += " and wfpm.user_edit=:userEdit\n";
                    case "userId"           -> queryFilter += " and wfpm.user_id=:userId\n";
                    case "wfPackageId"      -> queryFilter += " and wfpm.wf_package_id=:wfPackageId\n";
                    case "wfStateId"        -> queryFilter += " and wfpm.wf_state_id=:wfStateId\n";
                    case "wfProcessId"      -> queryFilter += " and wfpm.wf_process_id=:wfProcessId\n";
                    case "wfBaseProcessId"  -> queryFilter += " and wfpm.wf_base_process_id=:wfBaseProcessId\n";
                    case "isLast"           -> queryFilter += " and wfpm.is_last=:isLast\n";
                }
            }
            paramCount++;
        }

        if ((sqlCondition != null) && !("".equals(sqlCondition))){
            if (parameterSource.getParameterNames().length <= 0)
                queryFilter = queryFilter +" where ( "+sqlCondition+")";
            else
                queryFilter += " and ( "+sqlCondition+")";
        }

        queryFilter += " order by wfpm.start_date_time desc";// DESC - по убыванию.

        list = namedParameterJdbcTemplate.query(queryFilter,parameterSource,new WFProcessMovementRowMapper());

        return list;
    }

    @Override
    public List<WFProcessMovement> getListWFProcessMovement(int roleId, int wfServiceId, int processStatus, boolean isCompleted, boolean isLast) {
        String queryGetListWFProcessMovement = sqlQuery;

        queryGetListWFProcessMovement +=
                " where wfps.role_id=:roleId \n" +
                " and wfbp.wf_service_id=:wfServiceId\n "+
                " and wfpro.wf_process_status_id=:processStatus\n"+
                " and wfpm.is_completed=:isCompleted\n"+
                " and wfpm.is_last=:isLast\n "+
                " order by wfpm.start_date_time desc "; // по убиванию

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("roleId",roleId)
                .addValue("wfServiceId",wfServiceId)
                .addValue("processStatus",processStatus)
                .addValue("isCompleted",isCompleted)
                .addValue("isLast",isLast);

        List<WFProcessMovement> list = namedParameterJdbcTemplate.query(queryGetListWFProcessMovement,
                parameterSource,new WFProcessMovementRowMapper());

        return list;
    }

    @Override
    public List<WFProcessMovement> getListWFPMByProcessAndBaseProcess(int wfProcessId, int wfBaseProcessId) {
        String queryGetListWFMByProcessAndBaseProcess = sqlQuery;

        queryGetListWFMByProcessAndBaseProcess +=
                " where wfps.wf_process_id=:wfProcessId \n" +
                        " and wfbp.wf_base_process_id=:wfBaseProcessId\n "+
                        " order by wfpm.start_date_time desc "; // по убиванию

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("wfProcessId",wfProcessId)
                .addValue("wfBaseProcessId",wfBaseProcessId);

        List<WFProcessMovement> list = namedParameterJdbcTemplate.query(queryGetListWFMByProcessAndBaseProcess,
                parameterSource,new WFProcessMovementRowMapper());

        return list;
    }

    @Override
    public int currentStateIdOfWFProcessMovementById(int id) {
        String queryCurrentStateWFPMByWFPackageId = sqlQuery;

        queryCurrentStateWFPMByWFPackageId += " where wfpm.id=:id \n" +
                " and wfpm.is_completed=" + WFProcessMovement.NOT_COMPLETED +"\n"+
                " and wfpm.is_last=" + WFProcessMovement.IS_LAST;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);


        List<WFProcessMovement> list = namedParameterJdbcTemplate.query(queryCurrentStateWFPMByWFPackageId,parameterSource, new WFProcessMovementRowMapper());

        //DataAccessUtils.singleResult - Возвращает один объект результата из данной коллекции.
        return DataAccessUtils.singleResult(list).getWfStateId().getId();
    }

    @Override
    public int currentStateIdOfWFProcessMovement(int wfPackageId, int wfProcessId, int wfBaseProcessId) {
        String queryCurrentStateWFPMByWFPackageId = sqlQuery;

        queryCurrentStateWFPMByWFPackageId += " where wfpm.wf_package_id=:wfPackageId \n" +
                " and wfpm.wf_process_id=:wfProcessId\n"+
                " and wfpm.wf_base_process_id=:wfBaseProcessId\n"+
                " and wfpm.is_completed=" + WFProcessMovement.NOT_COMPLETED +"\n"+
                " and wfpm.is_last=" + WFProcessMovement.IS_LAST;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("wfPackageId",wfPackageId)
                .addValue("wfProcessId",wfProcessId)
                .addValue("wfBaseProcessId",wfBaseProcessId);


        List<WFProcessMovement> list = namedParameterJdbcTemplate.query(queryCurrentStateWFPMByWFPackageId,parameterSource, new WFProcessMovementRowMapper());

        //DataAccessUtils.singleResult - Возвращает один объект результата из данной коллекции.
        return DataAccessUtils.singleResult(list).getWfStateId().getId();
    }

}

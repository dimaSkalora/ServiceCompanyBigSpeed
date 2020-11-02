package org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.model.workflow.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class WFProcessMovementRowMapper implements RowMapper<WFProcessMovement> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public WFProcessMovement mapRow(ResultSet rs, int rowNum) throws SQLException {
        /*------------------------------start user-------------------------------------------------*/
        User user = new User();
        user.setId(rs.getInt("u_id"));
        user.setName(rs.getString("u_name"));
        user.setEmail(rs.getString("u_email"));
        user.setPassword(rs.getString("u_password"));
        user.setPhone(rs.getString("u_phone"));
        user.setRegistered(rs.getDate("u_registered").toLocalDate());
        user.setEnabled(rs.getBoolean("u_enabled"));
        /*------------------------------end users-------------------------------------------------*/
        /*------------------------------start wfPackage-------------------------------------------------*/
        WFPackageStatus wfPackageStatus = new WFPackageStatus();
        wfPackageStatus.setId(rs.getInt("wfps_id"));
        wfPackageStatus.setName(rs.getString("wfps_name"));

        WFService wfService = new WFService();
        wfService.setId(rs.getInt("wfs_id"));
        wfService.setName(rs.getString("wfs_name"));

        WFPackage wfPackage = new WFPackage();
        wfPackage.setId(rs.getInt("wfp_id"));
        wfPackage.setName(rs.getString("wfp_name"));
        wfPackage.setDateRegistration(rs.getDate("wfp_date_registration").toLocalDate());
        wfPackage.setCustomerName(rs.getString("wfp_customer_name"));
        wfPackage.setCustomerAddress(rs.getString("wfp_customer_address"));
        wfPackage.setCustomerAddressJur(rs.getString("wfp_customer_address_jur"));
        wfPackage.setCustomerPhone(rs.getString("wfp_customer_phone"));
        wfPackage.setCustomerEmail(rs.getString("wfp_customer_email"));
        wfPackage.setContractNumber(rs.getString("wfp_contract_number"));
        wfPackage.setDescription(rs.getString("wfp_description"));
        wfPackage.setUserAdd(rs.getString("wfp_user_add"));
        wfPackage.setDateAdd(rs.getTimestamp("wfp_date_add").toLocalDateTime());
        wfPackage.setUserEdit(rs.getString("wfp_user_edit"));
        wfPackage.setDateEdit(rs.getTimestamp("wfp_date_edit").toLocalDateTime());
        wfPackage.setWfPackageStatusId(wfPackageStatus);
        wfPackage.setWfServiceId(wfService);
        /*------------------------------end wfPackage-------------------------------------------------*/
        /*------------------------------start wfProcessState-------------------------------------------------*/
        RoleType roleType = new RoleType();
        roleType.setId(rs.getInt("r_role_type_id"));

        Role role = new Role();
        role.setId(rs.getInt("r_id"));
        role.setName(rs.getString("r_name"));
        role.setDescription(rs.getString("r_description"));
        role.setRoleTypeId(roleType);

        WFGroup wfGroup = new WFGroup();
        wfGroup.setId(rs.getInt("wfg_id"));
        wfGroup.setName(rs.getString("wfg_name"));
        wfGroup.setDescription(rs.getString("wfg_description"));

        WFProcessState wfProcessState = new WFProcessState();
        wfProcessState.setId(rs.getInt("wfps_id"));
        wfProcessState.setName(rs.getString("wfps_name"));
        wfProcessState.setRoleId(role);
        wfProcessState.setWfGroupId(wfGroup);
        wfProcessState.setDescription(rs.getString("wfps_description"));
        /*------------------------------end wfProcessState-------------------------------------------------*/
        /*------------------------------start wfProcess-------------------------------------------------*/
        WFBaseProcess wfBaseProcess = new WFBaseProcess();
        wfBaseProcess.setId(rs.getInt("wfbp_id"));
        wfBaseProcess.setName(rs.getString("wfbp_name"));
        wfBaseProcess.setDescription(rs.getString("wfbp_description"));

        WFProcessStatus wfProcessStatus = new WFProcessStatus();
        wfProcessStatus.setId(rs.getInt("wfpstatus_id"));
        wfProcessStatus.setName(rs.getString("wfpstatus_name"));

        WFProcess wfProcess = new WFProcess();
        wfProcess.setId(rs.getInt("wfpro_id"));
        wfProcess.setStartDate(rs.getTimestamp("wfpro_start_date").toLocalDateTime());
        if (rs.getTimestamp("wfpro_final_date").toLocalDateTime() != null)
            wfProcess.setFinalDate(rs.getTimestamp("wfpro_final_date").toLocalDateTime());
        if (rs.getString("wfpro_description") != null)
            wfProcess.setDescription(rs.getString("wfpro_description"));
        wfProcess.setDateEdit(rs.getTimestamp("wfpro_date_edit").toLocalDateTime());
        wfProcess.setUserEdit(rs.getString("wfpro_user_edit"));
        wfProcess.setWfPackageId(wfPackage);
        wfProcess.setWfBaseProcessId(wfBaseProcess);
        wfProcess.setWfProcessStatusId(wfProcessStatus);
        /*------------------------------end wfProcess-------------------------------------------------*/
        /*------------------------------start wfBaseProcess-------------------------------------------------*/
        WFBaseProcessType wfBaseProcessType = new WFBaseProcessType();
        wfBaseProcessType.setId(rs.getInt("wfbpt_id"));
        wfBaseProcessType.setName(rs.getString("wfbpt_name"));

        wfBaseProcess.setWfServiceId(wfService);
        wfBaseProcess.setWfBaseProcessTypeId(wfBaseProcessType);
        /*------------------------------end wfProcess-------------------------------------------------*/

        WFProcessMovement wfProcessMovement = new WFProcessMovement();
        wfProcessMovement.setId(rs.getInt("wfpm_id"));
        wfProcessMovement.setStartDateTime(rs.getTimestamp("wfpm_start_date_time").toLocalDateTime());
        if (rs.getTimestamp("wfpm_final_date_time").toLocalDateTime() != null)
            wfProcessMovement.setFinalDateTime(rs.getTimestamp("wfpm_final_date_time").toLocalDateTime());
        wfProcessMovement.setCompleted(rs.getBoolean("wfpm_is_completed"));
        if (rs.getString("wfpm_description") != null)
            wfProcessMovement.setDescription(rs.getString("wfpm_description"));
        wfProcessMovement.setDateEdit(rs.getTimestamp("wfpm_date_edit").toLocalDateTime());
        wfProcessMovement.setUserEdit(rs.getString("wfpm_user_edit"));
        wfProcessMovement.setUserId(user);
        wfProcessMovement.setWfPackageId(wfPackage);
        wfProcessMovement.setWfStateId(wfProcessState);
        wfProcessMovement.setWfProcessId(wfProcess);
        wfProcessMovement.setWfBaseProcessId(wfBaseProcess);
        wfProcessMovement.setLast(rs.getBoolean("wfpm_is_last"));

        return wfProcessMovement;
    }
}
